package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class FungoriumFrame extends JFrame {
    private Prototype prototype;
    ArrayList<IFungoriumPanel> panels = new ArrayList<>();
    public FungoriumFrame(Prototype p) {
        prototype = p;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(1800, 1000));
        this.setResizable(true);
        this.getContentPane().setBackground(new Color(0x312E2B));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        /*
         * Create Panels and add them to panels list
         */
        HeaderPanel headerPanel = new HeaderPanel(this);
        TektonViewPanel tektonViewPanel = new TektonViewPanel(this);
        ActionSelectorPanel actionSelectorPanel = new ActionSelectorPanel(this);
        ObjectSelectorPanel objectSelectorPanel = new ObjectSelectorPanel(this, actionSelectorPanel);
        TektonListPanel tektonListPanel = new TektonListPanel(this, tektonViewPanel, objectSelectorPanel);


        List<Player> allPlayers = new ArrayList<>();
        allPlayers = prototype.getGame().getPlayers();
        ArrayList<String> PlayerNames = new ArrayList<>();


        for (Player allPlayer : allPlayers) {
            PlayerNames.add(allPlayer.getTypeName());
        }

        String currentPlayerString = prototype.getGame().getCurrentPlayer().getTypeName();

        headerPanel.fillPlayerList(PlayerNames);
        headerPanel.setCurrentPlayer(currentPlayerString);

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
        this.add(objectSelectorPanel, gbc);

        // actionSelectorPanel - jobb alsó
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 3.0;
        this.add(actionSelectorPanel, gbc);

        this.setVisible(true);
    }

    public Prototype getPrototype(){
        return prototype;
    }

    public void resetAll() {

    }
}
