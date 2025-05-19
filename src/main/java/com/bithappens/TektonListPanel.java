package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class TektonListPanel extends JPanel implements IFungoriumPanel /*, ActionListener*/ {
    private TektonViewPanel tektonViewPanel;
    private ObjectSelectorPanel objectSelectorPanel;
    private FungoriumFrame fungoriumFrame;
    private JScrollPane scrollPane;
    private JPanel gridPanel;
    //private ArrayList<JButton> tektonButtons = new ArrayList<>(); 
    /**
     * Constructor of TektonListPanel
     * @param frame FungoriumFrame containing the panel
     * @param tektonViewPanel TektonViewPanel controlled by this panel
     * @param objectSelectorPanel ObjectSelectorPanel controlled by this panel
     */
    public TektonListPanel(FungoriumFrame frame, TektonViewPanel tektonViewPanel, ObjectSelectorPanel objectSelectorPanel) {
        fungoriumFrame = frame;
        this.tektonViewPanel = tektonViewPanel;
        this.objectSelectorPanel = objectSelectorPanel;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 2, 0, 0));
        scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 600));
        this.setMaximumSize(new Dimension(100, 600));
        this.setMinimumSize(new Dimension(100, 600));

        reset();     
        this.add(scrollPane);  
    }
    @Override
    public void reset() {
        resetButtons();
        gridPanel.revalidate();
        gridPanel.repaint();
    }
    /** 
     * Resets all buttons in the grid
     */
    private void resetButtons() {
        gridPanel.removeAll();
        List<Tekton> tektons = fungoriumFrame.getPrototype().getGame().getGameField();
        for (Tekton tekton : tektons) {
            JButton button = new JButton();
            button.setText(TektonListPanel.tektonHTMLGenerator(fungoriumFrame, tekton));
            button.addActionListener(e -> {
                System.out.println("gomb teszt klikk");
                objectSelectorPanel.reset();
                objectSelectorPanel.selectTekton(tekton);
                tektonViewPanel.selectTekton(tekton);
            });
            button.setMargin(new Insets(1, 1, 1, 1));
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setHorizontalTextPosition(SwingConstants.LEFT);
            button.setVerticalAlignment(SwingConstants.TOP);
            button.setVerticalTextPosition(SwingConstants.TOP);
            Dimension buttonDimension = new Dimension(100,100);
            button.setMinimumSize(buttonDimension);
            button.setMaximumSize(buttonDimension);
            button.setPreferredSize(buttonDimension);
            gridPanel.add(button);
        }
        /*
        gridPanel.repaint();
        gridPanel.revalidate();
        scrollPane.repaint();
        scrollPane.revalidate();
        */
    }
    /**
     * Function that returns a HTML font
     * @param c Color of the HTML font
     * @return String containing the HTML font 
     */
    private static String getFontColor(Color c) {
        if(c == null){
            return "";
        }
        return "<font color='rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() +")'>";
    }
    /**
     * Generates a HTML string describing a Tekton
     * @param frame FungoriumFrame 
     * @param t Tekton to be described
     * @return String containing valid HTML code
     */
    public static String tektonHTMLGenerator(FungoriumFrame frame, Tekton t) {
        Prototype p = frame.getPrototype();
        StringBuilder sb = new StringBuilder();
        sb.append("<html><font color='black'>" + p.getKey(t) + " (" + t.getClass().getSimpleName() + ")</font><br>");
        if(t.getMushroomBody() != null) {
            MushroomBody mb = t.getMushroomBody();
            sb.append("<font color='black'>Mushroom: </font>");
            Player owner = null;
            for (Player player : p.getGame().getPlayers()) {
                if (player.getOwnedObjects().contains(mb)) {
                    owner = player;
                }
            }
            if (owner != null) {
                sb.append(getFontColor(frame.getPlayerColor(owner)));
            }
            if (mb instanceof SuperMushroomBody) {
                sb.append("S");
            } else {
                sb.append("M");
            }
            if (!mb.alive) {
                sb.append(" (Dead)");
            }
            sb.append("</font><br>");
        }
        if (!t.getInsects().isEmpty()) {
            sb.append("<font color='black'>Insects: </font>");
        }
        for (Insect i : t.getInsects()) {
            sb.append(getFontColor(frame.getPlayerColor(i.getImaster())) + p.getKey(i));
            if (i.isStunned()) {
                sb.append("[p]");
            }
            sb.append(" </font>");
        }
        if (!t.getInsects().isEmpty()) {
            sb.append("<br>");
        }
        if (!t.getSpores().isEmpty()) {
            sb.append("<font color='black'>Spores: </font>");
        }
        ArrayList<MushroomMaster> sporeOwners = new ArrayList<>();
        for (Spore s : t.getSpores()) {
            if (!sporeOwners.contains(s.getMushroomMaster())) {
                sporeOwners.add(s.getMushroomMaster());
            }
        }
        for (MushroomMaster mm : sporeOwners) {
            int count = 0;
            for (Spore s : t.getSpores()) {
                if (s.getMushroomMaster().equals(mm)) {
                    count++;
                }
            }
            sb.append(getFontColor(frame.getPlayerColor(mm)) + count);
            sb.append(" </font>");
        }
        sb.append("</html>");
        return sb.toString();
    }

    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton currentTektonButton : tektonButtons){
            if(e.getSource() == currentTektonButton){
                System.out.println(currentTektonButton.getText());
            }
        }
    }
    */
    @Override
    public void redraw() {
        reset();
    }
}
