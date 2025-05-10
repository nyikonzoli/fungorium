package com.bithappens;

import java.awt.Color;

import javax.swing.JPanel;

public class ObjectSelectorPanel extends JPanel implements IFungoriumPanel{
    private ActionSelectorPanel actionSelectorPanel;
    private FungoriumFrame fungoriumFrame;
    public ObjectSelectorPanel(FungoriumFrame frame, ActionSelectorPanel actionSelectorPanel) {
        fungoriumFrame = frame;
        this.actionSelectorPanel = actionSelectorPanel;
        //debug backgorund
        setBackground(Color.MAGENTA);
    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

}
