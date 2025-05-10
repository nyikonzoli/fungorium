package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class FungoriumFrame extends JFrame {
    Prototype prototype;
    ArrayList<IFungoriumPanel> panels = new ArrayList<>();
    public FungoriumFrame(Prototype p) {
        prototype = p;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(1800, 1000));
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0x312E2B));
        this.setLayout(null);

        HeaderPanel header = new HeaderPanel();

        List<Player> allPlayers = new ArrayList();
        allPlayers = prototype.getGame().getPlayers();
        ArrayList<String> PlayerNames = new ArrayList<>();


        for (Player allPlayer : allPlayers) {
            PlayerNames.add(allPlayer.getTypeName());
        }

        String currentPlayerString = prototype.getGame().getCurrentPlayer().getTypeName();

        header.fillPlayerList(PlayerNames);
        header.setCurrentPlayer(currentPlayerString);

        this.add(header);


        this.setVisible(true);
    }
}
