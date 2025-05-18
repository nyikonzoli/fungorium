package com.bithappens;

import java.awt.Color;
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
    Prototype p;

    public ActionSelectorPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;
        p = fungoriumFrame.getPrototype();
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
        this.setPreferredSize(new Dimension(800, 60));
        //debug color
        //setBackground(Color.RED);
        this.setBackground(new Color(196, 159, 130));
    }
    @Override
    public void reset() {
        out[0] = "";
        actionInformation.setText(out[0]);
        informationLabel.setText("");
        removeAll();
        revalidate();
        repaint();
    }
    
    public void selectObject(MushroomBody mushroomBody) {
        reset();
        updateInformationLabel(mushroomBody);
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
            updateInformationLabel(mushroomBody);
            fungoriumFrame.redrawAll();
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
            updateInformationLabel(mushroomBody);
            fungoriumFrame.redrawAll();
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
            updateInformationLabel(mushroomBody);
            fungoriumFrame.redrawAll();
        });
        this.add(promoteButton);

        informationLabel.setFont(informationLabel.getFont().deriveFont(16f));
        this.add(informationLabel);

        actionInformation.setFont(informationLabel.getFont().deriveFont(16f));
        this.add(actionInformation);

        revalidate();
        repaint();

    }
    public void selectObject(Insect insect) {
        reset();
        fillTargetSelectorComboBox(insect.getLocation().getNeighbours());

        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(e -> {
            out[0] = p.handleInput(
                "move "
                + p.getKey(insect) + " "
                + p.getKey(targetSelectorComboBox.getSelectedItem())
            );
            actionInformation.setText(out[0]);
            fungoriumFrame.redrawAll();
        });
        this.add(moveButton);

        JButton cutButton = new JButton("Cut mycelium");
        cutButton.addActionListener(e -> {
            if (!insect.getLocation().getMyceliums().isEmpty()) {
                out[0] = p.handleInput(
                    "cut "
                    + p.getKey(insect) + " "
                    + p.getKey(insect.getLocation().getMyceliums().get(0))
                );
                actionInformation.setText(out[0]);
            }
            else{
                actionInformation.setText("No Myceliums on Tekton");
            }
            fungoriumFrame.redrawAll();
        });
        this.add(cutButton);

        JButton eatButton = new JButton("Eat");
        eatButton.addActionListener(e -> {
            if(!insect.getLocation().getSpores().isEmpty()){
                out[0] = p.handleInput(
                    "eatsp "
                    + p.getKey(insect)
                );
                actionInformation.setText(out[0]+ ", " + insect.getEffect());
            }
            else{
                actionInformation.setText("No Spore on Tekton");
            }
            fungoriumFrame.redrawAll();
        });
        this.add(eatButton);

        updateInformationLabel(insect);
        informationLabel.setFont(informationLabel.getFont().deriveFont(16f));
        this.add(informationLabel);

        actionInformation.setFont(informationLabel.getFont().deriveFont(16f));
        this.add(actionInformation);

        revalidate();
        repaint();

    }
    public void selectObject(Tekton center, Mycelium mycelium) {
        reset();
        updateInformationLabel(mycelium);
        fillTargetSelectorComboBox(center.getNeighbours());
        JComboBox<Insect> insectSelectorComboBox = new JComboBox<>(center.getInsects().toArray(new Insect[0]));
        setInsectSelectorComboBoxRenderer(insectSelectorComboBox);
        // grow mushroom button
        JButton growMuButton = new JButton("Grow mushroom");
        growMuButton.addActionListener(e -> {
            out[0] = p.handleInput(
                "growmu " 
                + p.getKey(mycelium.getMaster()) + " "
                + p.getKey(mycelium.getTektonEnd())
            );
            actionInformation.setText(out[0]);
            fungoriumFrame.redrawAll();
        });
        this.add(growMuButton);
        // grow mycelium button
        JButton growMyButton = new JButton("Grow mycelium");
        growMyButton.addActionListener(e -> {
            //mycelium.getMaster().initiateMyceliumGrowth(center, (Tekton)targetSelectorComboBox.getSelectedItem());
            out[0] = p.handleInput(
                "growmy "
                + p.getKey(mycelium.getMaster()) + " "
                + p.getKey(center) + " "
                + p.getKey(targetSelectorComboBox.getSelectedItem())
            );
            actionInformation.setText(out[0]);
            fungoriumFrame.redrawAll();
        });
        this.add(growMyButton);
        // eat insect button - currently tries eating every insect
        JButton eatInsecButton = new JButton("Eat insect");
        eatInsecButton.addActionListener(e -> {
            //mycelium.eatInsect((Insect)insectSelectorComboBox.getSelectedItem(), center);
            out[0] = p.handleInput(
                "eatin " 
                + p.getKey(mycelium.getMaster()) + " "
                + p.getKey(insectSelectorComboBox.getSelectedItem())
            );
            actionInformation.setText(out[0]);
            fungoriumFrame.redrawAll();
        });
        this.add(eatInsecButton);

        updateInformationLabel(mycelium);
        informationLabel.setFont(informationLabel.getFont().deriveFont(16f));
        
        this.add(informationLabel);

        actionInformation.setFont(informationLabel.getFont().deriveFont(16f));
        this.add(actionInformation);

        fungoriumFrame.redrawAll();
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

    private void updateInformationLabel(MushroomBody mushroomBody){
        informationLabel.setText("Status: " + (mushroomBody.getAlive() ? "alive" : "dead")+ "   ActionPoints: " + mushroomBody.getActions() + "   Spores: " + mushroomBody.getSpores().size() + "   ");
    }

    private void updateInformationLabel(Mycelium mycelium){
        informationLabel.setText((mycelium.isCut() ? "Cut" : "Not cut") + "   Time to live: " + mycelium.getTimeToLive());
    }
    private void updateInformationLabel(Insect insect){
        informationLabel.setText("   Action Points: " + insect.getActionPoints() + ((insect.isStunned()) ? "   Stunned" : "   Not stunned") + ((insect.getCanCutMycelium()) ? "   Can cut" : "   Can't cut"));
    }
}
