package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ObjectSelectorPanel extends JPanel implements IFungoriumPanel{
    private ActionSelectorPanel actionSelectorPanel;
    private FungoriumFrame fungoriumFrame;
    private Tekton selectedTekton;
    private ImageIcon backgroundImage;
    public ObjectSelectorPanel(FungoriumFrame frame, ActionSelectorPanel actionSelectorPanel) {
        fungoriumFrame = frame;
        this.actionSelectorPanel = actionSelectorPanel;
        this.setPreferredSize(new Dimension(350, 80));
        this.setMinimumSize(new Dimension(350, 80));
        this.setMaximumSize(new Dimension(350, 80));
        //debug backgorund
        setBackground(new Color(36, 94, 50));
        backgroundImage = new ImageIcon("src/main/resources/object-selector-default.png");
    }
    @Override
    public void reset() {
        removeAll();
        backgroundImage = new ImageIcon("src/main/resources/object-selector-default.png");
        selectedTekton = null;
        revalidate();
        repaint();
        actionSelectorPanel.reset();
    }
    public void selectTekton(Tekton tekton) {
        removeAll();
        selectedTekton = tekton;
        Player currentPlayer = fungoriumFrame.getPrototype().getGame().getCurrentPlayer();
        
        if (currentPlayer instanceof MushroomMaster) {
            backgroundImage = new ImageIcon("src/main/resources/object-selector-mm-final.png");
        } else {
            backgroundImage = new ImageIcon("src/main/resources/object-selector-im.png");
        }
        for (Mycelium mycelium : tekton.myceliums) {
            if (currentPlayer.equals(mycelium.getMaster())) {
                JButton mycButton = new JButton();
                mycButton.setText(fungoriumFrame.getPrototype().getKey(mycelium));
                mycButton.addActionListener(e -> {
                    actionSelectorPanel.selectObject(tekton, mycelium);
                    System.out.println("Mycelium selected");
                });
                this.add(mycButton);
            }
        }
        if (tekton.getMushroomBody() != null && currentPlayer.getOwnedObjects().contains(tekton.getMushroomBody())) {
            JButton mushroomButton = new JButton();
            mushroomButton.setText(fungoriumFrame.getPrototype().getKey(tekton.getMushroomBody()));
            mushroomButton.addActionListener(e -> {
                actionSelectorPanel.selectObject(tekton.getMushroomBody());
                System.out.println("Mushroom selected");
            });
            this.add(mushroomButton);
        }
        for (Insect insect : tekton.getInsects()) {
            if (currentPlayer.equals(insect.getImaster())) {
                JButton insectButton = new JButton();
                insectButton.setText(fungoriumFrame.getPrototype().getKey(insect));
                insectButton.addActionListener(e -> {
                    actionSelectorPanel.selectObject(insect);
                    System.out.println("Insect selected");
                });
                this.add(insectButton);
            }
        }
        //repaint();
        //revalidate();
    }
    @Override
    public void redraw() {
        selectTekton(selectedTekton);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background image
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }
}
