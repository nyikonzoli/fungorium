package com.bithappens;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameOver extends JFrame{
    private String mmasterWinner;
    private String imasterWinner;

    public GameOver(String mm, String im){
        mmasterWinner = mm;
        imasterWinner = im;
        this.setTitle("Game Over");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(800, 800));
        addElements();
    }

    public void addElements(){
        
    }
}
