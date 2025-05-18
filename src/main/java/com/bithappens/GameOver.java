package com.bithappens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JFrame{
    private String mmasterWinner;
    private String imasterWinner;
    private FungoriumFrame fframe;

    // TESZTHEZ CTOR
    public GameOver(FungoriumFrame ff){
        mmasterWinner = "mmaster1";
        imasterWinner = "imaster1";
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

    public GameOver(String mm, String im, FungoriumFrame ff){
        mmasterWinner = mm;
        imasterWinner = im;
        fframe = ff;
        fframe.setEnabled(false);
        this.setTitle("Game Over");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));
        this.setLocationRelativeTo(fframe);
        this.setResizable(false);
        addElements();
        
        this.setVisible(true);
    }

    public void addElements(){

        ImageIcon windowIcon = new ImageIcon("src/main/resources/window-icon.png");
        this.setIconImage(windowIcon.getImage());

        BufferedImage bgImage;
        try {
            bgImage = ImageIO.read(new File("src/main/resources/GameOverImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 20, 20, 20));
        panel.setOpaque(false);

        Font titlePixel = loadPixelFont(64f);
        Font playersPixel = loadPixelFont(24f);
        Font buttonPixel = loadPixelFont(24f);
        Dimension buttonSize = new Dimension(240, 80);

        JLabel titleLabel = new JLabel("GAME OVER");
        titleLabel.setFont(titlePixel);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel mmLabel = new JLabel("Winner MushroomMaster: " + mmasterWinner);
        JLabel imLabel = new JLabel("Winner InsectMaster: " + imasterWinner);
        mmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mmLabel.setForeground(Color.BLACK);
        imLabel.setForeground(Color.BLACK);

        mmLabel.setFont(playersPixel);
        imLabel.setFont(playersPixel);

        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton.setFont(buttonPixel);
        exitButton.setFont(buttonPixel);
        newGameButton.setMaximumSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        panel.add(Box.createVerticalStrut(60));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(mmLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(imLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(newGameButton);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(exitButton);

        newGameButton.addActionListener(e -> {
            dispose();
            fframe.dispose();
            Prototype newPrototype = new Prototype();
            FungoriumFrame newFrame = new FungoriumFrame(newPrototype);
        });

        exitButton.addActionListener(e -> {
            fframe.dispose();
            dispose();
            System.exit(0);
        });

        this.setContentPane(panel);
    }

    public Font loadPixelFont(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/PixelFont.ttf"));
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            return new Font("Monospaced", Font.PLAIN, (int) size);
        }
    }
}
