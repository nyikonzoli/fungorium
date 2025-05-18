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

        // Addig szúrunk be, míg el nem érjük a kívánt pontszámot
        while (nextIndex < pointCount) {
            Triangle t = triangles.remove(rand.nextInt(triangles.size()));

            int newPoint = nextIndex++;
            adjacency.put(newPoint, new HashSet<>());

            // új pontot kapcsoljuk a három meglévőhöz
            if (canConnect(newPoint, t.a, adjacency)) {
                connect(newPoint, t.a, adjacency);
            }
            if (canConnect(newPoint, t.b, adjacency)) {
                connect(newPoint, t.b, adjacency);
            }
            if (canConnect(newPoint, t.c, adjacency)) {
                connect(newPoint, t.c, adjacency);
            }

            // három új háromszög keletkezik
            triangles.add(new Triangle(t.a, t.b, newPoint));
            triangles.add(new Triangle(t.b, t.c, newPoint));
            triangles.add(new Triangle(t.c, t.a, newPoint));
        }


        // remove evil edges



        return adjacency;
    }

    private static void connect(int u, int v, Map<Integer, Set<Integer>> adjacency) {
        adjacency.get(u).add(v);
        adjacency.get(v).add(u);
    }
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