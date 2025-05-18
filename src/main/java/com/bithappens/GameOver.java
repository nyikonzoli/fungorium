package com.bithappens;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JFrame{
    private String mmasterWinner;
    private String imasterWinner;
    private FungoriumFrame fframe;

    public GameOver(String mm, String im, FungoriumFrame ff){
        mmasterWinner = mm;
        imasterWinner = im;
        fframe = ff;
        fframe.setEnabled(false);
        this.setTitle("Game Over");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(800, 800));
        this.setLocationRelativeTo(fframe);
        this.setResizable(false);
        addElements();
        
        this.setVisible(true);
    }

    public void addElements(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel mmLabel = new JLabel("Winner MushroomMaster: " + mmasterWinner);
        JLabel imLabel = new JLabel("Winner InsectMaster: " + imasterWinner);
        mmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add spacing and components
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(mmLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(imLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(newGameButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(exitButton);

        // Button actions
        newGameButton.addActionListener(e -> {
            dispose();
            fframe.dispose();
        });

        exitButton.addActionListener(e -> {
            fframe.dispose();
            dispose();
            System.exit(0);
        });

        this.setContentPane(panel);

    }
}
