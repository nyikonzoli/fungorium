package com.bithappens;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ActionSelectorPanel extends JPanel implements IFungoriumPanel{
    private FungoriumFrame fungoriumFrame;
    private JComboBox<Tekton> targetSelectorComboBox;
    public ActionSelectorPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;
        this.setLayout(new FlowLayout());
        //debug color
        //setBackground(Color.RED);
    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'reset'");
        removeAll();
        revalidate();
        repaint();
    }
    public void selectObject(MushroomBody mushroomBody) {
        // TODO + super mushroom dolog + mycelium növesztés is, a szomszédos mezőkre
        
    }
    public void selectObject(Insect insect) {
        // TODO
    }
    public void selectObject(Tekton center, Mycelium mycelium) {
        reset();
        fillTargetSelectorComboBox(center.getNeighbours());
        JComboBox<Insect> insectSelectorComboBox = new JComboBox<>(center.getInsects().toArray(new Insect[0]));
        setInsectSelectorComboBoxRenderer(insectSelectorComboBox);
        // grow button
        JButton growButton = new JButton("Grow");
        growButton.addActionListener(e -> {
            mycelium.getMaster().initiateMyceliumGrowth(center, (Tekton)targetSelectorComboBox.getSelectedItem());
        });
        this.add(growButton);
        // eat insect button - currently tries eating every insect
        JButton eatInsecButton = new JButton("Eat insect");
        eatInsecButton.addActionListener(e -> {
            mycelium.eatInsect((Insect)insectSelectorComboBox.getSelectedItem(), center);
        });
        // cut label
        JLabel cutLabel = new JLabel();
        cutLabel.setText((mycelium.isCut() ? "Cut" : "Not cut"));
        this.add(cutLabel);
        // ttl label
        JLabel ttlLabel = new JLabel("Time to live: " + mycelium.getTimeToLive());
        this.add(ttlLabel);
        revalidate();
        repaint();
    }
    
    private void fillTargetSelectorComboBox(ArrayList<Tekton> tektonList) {
        targetSelectorComboBox = new JComboBox<>(tektonList.toArray(new Tekton[0]));
        targetSelectorComboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Tekton) {
                        setText(fungoriumFrame.getPrototype().getKey(value));
                    }
                    return this;
                }
            });
        this.add(targetSelectorComboBox);
    }
    private void setInsectSelectorComboBoxRenderer(JComboBox<Insect> comboBox) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Insect) {
                        setText(fungoriumFrame.getPrototype().getKey(value));
                    }
                    return this;
                }
            });
        this.add(comboBox);
    }
}
