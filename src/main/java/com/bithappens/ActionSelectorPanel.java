package com.bithappens;

import java.awt.Component;
import java.awt.Dimension;
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

    private final String[] out = new String[1];
    JLabel actionInformation = new JLabel(out[0]);
    JLabel informationLabel = new JLabel();

    public ActionSelectorPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
        this.setPreferredSize(new Dimension(800, 60));
        //debug color
        //setBackground(Color.RED);
    }
    @Override
    public void reset() {
        removeAll();
        revalidate();
        repaint();
    }
    
    public void selectObject(MushroomBody mushroomBody) {
        reset();
        Prototype p = fungoriumFrame.getPrototype();
        // combo box fill
        Tekton t = mushroomBody.getLocation();
        if (!(mushroomBody instanceof SuperMushroomBody)) {
            fillTargetSelectorComboBox(t.getNeighbours());
        } else {
            ArrayList<Tekton> reachables = new ArrayList<>(t.getNeighbours());
            for (Tekton tekton : t.getNeighbours()) {
                for (Tekton neighborOfNeighbors : tekton.getNeighbours()) {
                    if (!reachables.contains(neighborOfNeighbors)) {
                        reachables.add(neighborOfNeighbors);
                    }
                }
            }
            fillTargetSelectorComboBox(reachables);
        }
        // grow mycelium button
        JButton growMyButton = new JButton("Grow mycelium");
        growMyButton.addActionListener(e -> {
            out[0] = p.handleInput(
                "growmy "
                + p.getKey(p.getGame().getCurrentPlayer()) + " "
                + p.getKey(mushroomBody.getLocation()) + " "
                + p.getKey(targetSelectorComboBox.getSelectedItem())
            );
            actionInformation.setText(out[0]);
            redrawMushroom(mushroomBody);
        });
        this.add(growMyButton);

        JButton spreadSporeButton = new JButton("Spread spore");
        spreadSporeButton.addActionListener(e -> {
            out[0] = p.handleInput(
                "spreadsp "
                + p.getKey(mushroomBody) + " "
                + p.getKey(targetSelectorComboBox.getSelectedItem())
            );
            actionInformation.setText(out[0]);
            redrawMushroom(mushroomBody);
        });
        this.add(spreadSporeButton);

        JButton promoteButton = new JButton("Grow SuperMushroom");
        promoteButton.addActionListener(e -> {
            out[0] = p.handleInput(
                "growmu "
                + p.getKey(p.getGame().getCurrentPlayer()) + " "
                + p.getKey(mushroomBody.getLocation()) + " -s"
            );
            actionInformation.setText(out[0]);
            redrawMushroom(mushroomBody);
        });
        this.add(promoteButton);

        informationLabel.setText("Status: " + (mushroomBody.getAlive() ? "alive" : "dead")+ "   ActionPoints: " + mushroomBody.getActions() + "   Spores: " + mushroomBody.getSpores().size());
        informationLabel.setFont(informationLabel.getFont().deriveFont(20f));
        
        this.add(informationLabel);

        actionInformation.setFont(informationLabel.getFont().deriveFont(20f));

        this.add(actionInformation);

        fungoriumFrame.redrawAll();
        revalidate();
        repaint();

    }
    public void selectObject(Insect insect) {
        
    }
    public void selectObject(Tekton center, Mycelium mycelium) {
        reset();
        fillTargetSelectorComboBox(center.getNeighbours());
        JComboBox<Insect> insectSelectorComboBox = new JComboBox<>(center.getInsects().toArray(new Insect[0]));
        setInsectSelectorComboBoxRenderer(insectSelectorComboBox);
        Prototype p = fungoriumFrame.getPrototype();
        // grow mushroom button
        JButton growMuButton = new JButton("Grow mushroom");
        growMuButton.addActionListener(e -> {
            p.handleInput(
                "growmu " 
                + p.getKey(mycelium.getMaster()) + " "
                + p.getKey(center)
            );
            fungoriumFrame.redrawAll();
        });
        this.add(growMuButton);
        // grow mycelium button
        JButton growMyButton = new JButton("Grow mycelium");
        growMyButton.addActionListener(e -> {
            //mycelium.getMaster().initiateMyceliumGrowth(center, (Tekton)targetSelectorComboBox.getSelectedItem());
            p.handleInput(
                "growmy "
                + p.getKey(mycelium.getMaster()) + " "
                + p.getKey(center) + " "
                + p.getKey(targetSelectorComboBox.getSelectedItem())
            );
            fungoriumFrame.redrawAll();
        });
        this.add(growMyButton);
        // eat insect button - currently tries eating every insect
        JButton eatInsecButton = new JButton("Eat insect");
        eatInsecButton.addActionListener(e -> {
            //mycelium.eatInsect((Insect)insectSelectorComboBox.getSelectedItem(), center);
            p.handleInput(
                "eatin " 
                + p.getKey(mycelium.getMaster()) + " "
                + p.getKey(insectSelectorComboBox.getSelectedItem())
            );
            fungoriumFrame.redrawAll();
        });
        this.add(eatInsecButton);
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
    @Override
    public void redraw() {
        // can be empty for now
    }

    public void redrawMushroom(MushroomBody mb){
        selectObject(mb);
    }
}
