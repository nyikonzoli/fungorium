package com.bithappens;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TektonViewPanel extends JPanel implements IFungoriumPanel{

    private FungoriumFrame fungoriumFrame;
    private Tekton selectedTekton;
    private ArrayList<ColoredLine> lines = new ArrayList<>();
    // Tekton label size
    private Dimension labelDimension = new Dimension(80, 80);

    public TektonViewPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;

        setBackground(new Color(153, 217, 234));
        setLayout(null);
    }
    @Override
    public void reset() {
        removeAll();
        selectedTekton = null;
        lines.clear();
        revalidate();
        repaint();
    }
    public void selectTekton(Tekton tekton) {
        reset();
        selectedTekton = tekton;
        /*
         * TODO: kirajzolás megalósít
         */
        
        drawAll(tekton);
    }
    private void drawAll(Tekton t) {
        // Center tekton
        labelFactory(t, 0, 0);
        // Circle radius
        int radius = 160;
        int n = t.getNeighbours().size();
        ArrayList<Tekton> neighbors = t.getNeighbours();
        // Order neighbors 
        for (int i = 0; i < n; i++) {
            Tekton current = neighbors.get(i);
            for (int j = i + 1; j < n; j++) {
                Tekton possibleNeighbor = neighbors.get(j);
                if (current.getNeighbours().contains(possibleNeighbor)) {
                    Collections.swap(neighbors, i + 1, j);
                    break;
                }
            }
        }
        ArrayList<Tekton> visited = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Tekton current = neighbors.get(i);
            double angle = 2 * Math.PI * i / n;
            int x = (int)(radius * Math.cos(angle));
            int y = (int)(radius * Math.sin(angle));
            labelFactory(current, x, y);
            lines.add(new ColoredLine(new Point(0, 0), new Point(x, y), Color.black));

            for (Tekton neigborOfNeighbor : current.getNeighbours()) {
                if (neighbors.contains(neigborOfNeighbor) && !visited.contains(neigborOfNeighbor)) {
                    int idx = neighbors.indexOf(neigborOfNeighbor);
                    double angle2 = 2 * Math.PI * idx / n;
                    int x2 = (int)(radius * Math.cos(angle2));
                    int y2 = (int)(radius * Math.sin(angle2));
                    lines.add(new ColoredLine(new Point(x2, y2), new Point(x, y), Color.black));
                }
            }
            visited.add(current);
        }
    }
    private void labelFactory(Tekton t, int x, int y) {
        Point p = calculateLabelPosition(x, y);
        JLabel label = new JLabel();
        label.setSize(labelDimension);
        label.setBounds(p.x,p.y,labelDimension.width,labelDimension.height);
        label.setText(TektonListPanel.tektonHTMLGenerator(fungoriumFrame, t));
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.add(label);
    }
    /**
     * Calculates position of label on the panel by getting 2 coordinates in a 
     * coordinate space where the origin is the middle of the panel
     * @param x X coordinate from the center of the panel
     * @param y Y coordinate from the center of the panel
     * @return Point representing the actual position on the panel
     */
    private Point calculateLabelPosition(int wX, int wY) {
        int cX = getWidth() / 2 + (wX - labelDimension.width / 2);
        int cY = getHeight() / 2 - (wY + labelDimension.height / 2);
        return new Point(cX, cY);
    }
    private Point calculatePosition(int wX, int wY) {
        int cX = getWidth() / 2 + wX;
        int cY = getHeight() / 2 - wY;
        return new Point(cX, cY);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // neighbors
        Graphics2D g2D = (Graphics2D)g;
        for (ColoredLine line : lines) {
            g2D.setColor(line.color);
            g2D.setStroke(new BasicStroke(2));
            Point p1 = calculatePosition(line.point1.x, line.point1.y);
            Point p2 = calculatePosition(line.point2.x, line.point2.y);
            g2D.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    class ColoredLine {
        public Point point1;
        public Point point2;
        public Color color;
        public ColoredLine(Point p1, Point p2, Color c) {
            point1 = p1;
            point2 = p2;
            color = c;
        }
    }
}
