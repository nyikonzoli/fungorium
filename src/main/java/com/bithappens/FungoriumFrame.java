package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class FungoriumFrame extends JFrame {
    private Prototype prototype;
    private HashMap<Player, Color> playerColorMap;
    ArrayList<IFungoriumPanel> panels = new ArrayList<>();

    public FungoriumFrame(Prototype p) {
        prototype = p;
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(1280, 960));
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x312E2B));
        this.setLayout(new GridBagLayout());
        ImageIcon windowIcon = new ImageIcon("src/main/resources/window-icon.png");
        this.setIconImage(windowIcon.getImage());
        this.setTitle("Fungorium");
        GridBagConstraints gbc = new GridBagConstraints();
        playerColorMap = new HashMap<>();
        setPlayerColors();
        /*
         * Create Panels and add them to panels list
         */
        HeaderPanel headerPanel = new HeaderPanel(this);
        TektonViewPanel tektonViewPanel = new TektonViewPanel(this);
        ActionSelectorPanel actionSelectorPanel = new ActionSelectorPanel(this);
        ObjectSelectorPanel objectSelectorPanel = new ObjectSelectorPanel(this, actionSelectorPanel);
        TektonListPanel tektonListPanel = new TektonListPanel(this, tektonViewPanel, objectSelectorPanel);

        /*
        List<Player> allPlayers = new ArrayList<>();
        allPlayers = prototype.getGame().getPlayers();
        ArrayList<String> PlayerNames = new ArrayList<>();


        for (Player allPlayer : allPlayers) {
            PlayerNames.add(allPlayer.getTypeName());
        }
        */
        /*
         * Add to this GridBagLayout frame
         */
         // headerPanel - legfelül, 2 oszlopon át
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 4.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerPanel, gbc);

        // tektonListPanel - bal felső
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 3.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(tektonListPanel, gbc);

        // tektonViewPanel - jobb felső
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 3.0;
        this.add(tektonViewPanel, gbc);

        // objectSelectorPanel - bal alsó
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        //gbc.fill = GridBagConstraints.BOTH;
        this.add(objectSelectorPanel, gbc);

        // actionSelectorPanel - jobb alsó
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 3.0;
        this.add(actionSelectorPanel, gbc);

        this.setVisible(true);
        panels.add(headerPanel);
        panels.add(tektonListPanel);
        panels.add(objectSelectorPanel);
        panels.add(tektonViewPanel);
        panels.add(actionSelectorPanel);

        
    }
    public void setPlayerColors() {
        ArrayList<Color> predefinedColors = new ArrayList<>(List.of(          
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.ORANGE,
            Color.MAGENTA,
            Color.CYAN,
            Color.YELLOW,
            new Color(128, 0, 128),   // lila
            new Color(255, 105, 180), // pink
            new Color(139, 69, 19)    // barna
        ));
        Random random = new Random();
        for (int i = 0; i < prototype.getGame().getPlayers().size(); i++) {
            Player p = prototype.getGame().getPlayers().get(i);
            if (i < predefinedColors.size()) {
                playerColorMap.put(p, predefinedColors.get(i));
            } else {
                playerColorMap.put(p, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            }
        }
    }
    public Color getPlayerColor(Player p) {
        return playerColorMap.get(p);
    }
    public Prototype getPrototype(){
        return prototype;
    }

    public void resetAll() {
        for (IFungoriumPanel panel : panels) {
            panel.reset();
        }
    }
    public void redrawAll() {
        for (IFungoriumPanel panel : panels) {
            panel.redraw();
        }
    }
}
