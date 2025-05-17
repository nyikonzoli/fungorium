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
    /**
     * Constoructor of TektonViewPanel, sets a FungoriumFrame as the container containing it
     * @param frame FungoriumFrame containing the panel
     */
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
    /**
     * Selects a Tekton to base the panel's content on
     * @param tekton Selected Tekton
     */
    public void selectTekton(Tekton tekton) {
        selectedTekton = tekton;
        redrawAll();
    }
    /**
     * Draws adds all labels representing Tektons to the panel. Adds all neighbor connections as lines
     * to the lines list.
     * @param t Center Tekton on the panel
     */
    public void redrawAll() {
        reset();
        Tekton t = selectedTekton;
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
            int x = (int)Math.round(radius * Math.cos(angle));
            int y = (int)Math.round(radius * Math.sin(angle));
            labelFactory(current, x, y);
            lines.add(new ColoredLine(new Point(0, 0), new Point(x, y), lineColorBetweenNeighbors(t, current)));
            // Neighbor to neighbor lines
            StringBuilder sb = new StringBuilder("<html><font size='3' color='gray'>Neighbors:</font><br>");
            for (Tekton neigborOfNeighbor : current.getNeighbours()) {
                if (neighbors.contains(neigborOfNeighbor) && !visited.contains(neigborOfNeighbor)) {
                    int idx = neighbors.indexOf(neigborOfNeighbor);
                    double angle2 = 2 * Math.PI * idx / n;
                    int x2 = (int)Math.round(radius * Math.cos(angle2));
                    int y2 = (int)Math.round(radius * Math.sin(angle2));
                    lines.add(new ColoredLine(new Point(x2, y2), new Point(x, y), lineColorBetweenNeighbors(current, neigborOfNeighbor)));

                } else if (!neighbors.contains(neigborOfNeighbor) && !neigborOfNeighbor.equals(t)) {
                    sb.append("<font size='2' color='gray'>" + fungoriumFrame.getPrototype().getKey(neigborOfNeighbor) + " </font>");
                }
            }
            sb.append("</html>");
            visited.add(current);
            // Neighbor of neighbor label
            int xStringLabel = (int)Math.round((radius + labelDimension.width * 1.5) * Math.cos(angle));
            int yStringLabel = (int)Math.round((radius + labelDimension.height * 1.5) * Math.sin(angle));
            lines.add(new ColoredLine(new Point(xStringLabel, yStringLabel), new Point(x, y), Color.gray));
            labelFactory(sb.toString(), xStringLabel, yStringLabel);
        }
        revalidate();
        repaint();
    }
    private Color lineColorBetweenNeighbors(Tekton start, Tekton end) {
        Mycelium m = null;
        for (Mycelium myc : start.getMyceliums()) {
            if (myc.getTektonEnd().equals(end) || myc.getTektonStart().equals(end)) {
                m = myc;
            }
        }
        return (m == null) ? Color.BLACK : fungoriumFrame.getPlayerColor(m.getMaster());
    }
    /**
     * Adds a label with text to the specified world coordinates
     * @param s Label text
     * @param x World coordinate X
     * @param y World coordinate Y
     */
    private void labelFactory(String s, int x, int y) {
        Point p = calculateLabelPosition(x, y);
        JLabel label = new JLabel();
        label.setSize(labelDimension);
        label.setBounds(p.x,p.y,labelDimension.width,labelDimension.height);
        label.setText(s);
        label.setOpaque(true);
        label.setBackground(this.getBackground());
        label.setBorder(BorderFactory.createLineBorder(Color.gray, 1, true));
        this.add(label);
    }
    /**
     * Adds a label with Tekton description in it to the specified world coordinates
     * @param t Tekton to be described
     * @param x World coordinate X
     * @param y World coordinate Y
     */
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
    /**
     * Calculates panel position based on world coordinates
     * @param wX World coordinate X
     * @param wY World coordinate Y
     * @return Point containing the screen coordinates (relative to the panels top left corner)
     */
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
    /**
     * Simple ColoredLine class used to draw lines in paintComponent method
     */
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
