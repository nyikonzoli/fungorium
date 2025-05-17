package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ObjectSelectorPanel extends JPanel implements IFungoriumPanel{
    private ActionSelectorPanel actionSelectorPanel;
    private FungoriumFrame fungoriumFrame;
    private Tekton selectedTekton;
    public ObjectSelectorPanel(FungoriumFrame frame, ActionSelectorPanel actionSelectorPanel) {
        fungoriumFrame = frame;
        this.actionSelectorPanel = actionSelectorPanel;
        this.setMinimumSize(new Dimension(100, 80));
        //debug backgorund
        //setBackground(Color.MAGENTA);
    }
    @Override
    public void reset() {
        removeAll();
        selectedTekton = null;
        revalidate();
        repaint();
        actionSelectorPanel.reset();
    }
    public void selectTekton(Tekton tekton) {
        removeAll();
        selectedTekton = tekton;
        Player currentPlayer = fungoriumFrame.getPrototype().getGame().getCurrentPlayer();
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
}
