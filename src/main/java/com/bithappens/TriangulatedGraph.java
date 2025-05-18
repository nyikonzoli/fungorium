package com.bithappens;

import java.util.*;

public class TriangulatedGraph {
    public static class Triangle {
        int a, b, c;
        public Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    /**
     * Pseudorandomly generates a triangulated graph (without crossing edges) with regards to the topology of the 
     * Tekton field (so no neighbor of a center Tekton can have more than 2 other neighbors that neighbor the center Tekton)
     * @param seed Seed needed to seeded random generation
     * @param pointCount Number of points in the desired graph
     * @return A Map where the first Integer value is the index of a point and the second Set contains the indices of neighboring points
     */
    public static Map<Integer, Set<Integer>> generateTriangulatedGraph(long seed, int pointCount) {
        if (pointCount < 3) {
            throw new IllegalArgumentException("Minimum 3 pont kell.");
        }

        Map<Integer, Set<Integer>> adjacency = new HashMap<>();
        List<Triangle> triangles = new ArrayList<>();
        Random rand = new Random(seed);

        // Alap háromszög
        for (int i = 0; i < 3; i++) {
            adjacency.put(i, new HashSet<>());
        }
        connect(0, 1, adjacency);
        connect(1, 2, adjacency);
        connect(2, 0, adjacency);
        triangles.add(new Triangle(0, 1, 2));

        int nextIndex = 3;

        while (nextIndex < pointCount) {
            Triangle t = triangles.remove(rand.nextInt(triangles.size()));

            int newPoint = nextIndex++;
            adjacency.put(newPoint, new HashSet<>());

            // új pont a három meglévőhöz

            connect(newPoint, t.a, adjacency);
            connect(newPoint, t.b, adjacency);
            connect(newPoint, t.c, adjacency);
            // három új háromszög 
            triangles.add(new Triangle(t.a, t.b, newPoint));
            triangles.add(new Triangle(t.b, t.c, newPoint));
            triangles.add(new Triangle(t.c, t.a, newPoint));
        }
        return adjacency;
    }
    /**
     * Connects the two points with an edge represented by adding the two neighborint points' indices
     * to eachothers neighbors
     * @param u First point's index
     * @param v Second point's index
     * @param adjacency Adjacency Map
     */
    private static void connect(int u, int v, Map<Integer, Set<Integer>> adjacency) {
        if (!canConnect(u, v, adjacency)) return;
        adjacency.get(u).add(v);
        adjacency.get(v).add(u);
    }
    /**
     * Returns whether the two points can actually be connected, so that it's going to be a possible field of Tektons
     * @param u First point's index
     * @param v First point's index
     * @param adjacency Adjacency Map
     * @return Whether they can be connected without violating the Tekton field's criteria
     */
    private static boolean canConnect(int u, int v, Map<Integer, Set<Integer>> adjacency) {
        // ellenőrizzük, hogy van-e közös szomszéd, akinél 2-nél több szomszéd lesz
        Set<Integer> neighborsU = adjacency.get(u);
        Set<Integer> neighborsV = adjacency.get(v);
        for (Integer n : neighborsU) {
            if (neighborsV.contains(n)) {
                // megszámoljuk, hány közös szomszéd van u és v között n számára
                int count = 0;
                for (Integer x : adjacency.get(n)) {
                    if (x.equals(u) || x.equals(v)) count++;
                }
                if (count >= 2) {
                    return false;
                }
            }
        }
        return true;
    }
   
}