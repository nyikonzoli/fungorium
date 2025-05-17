package com.bithappens;

import java.awt.Color;

import javax.swing.JPanel;

public class ActionSelectorPanel extends JPanel implements IFungoriumPanel{
    private FungoriumFrame fungoriumFrame;
    public ActionSelectorPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;

        //debug color
        //setBackground(Color.RED);
    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    public void selectObject(MushroomBody mushroomBody) {
        // TODO
    }
    public void selectObject(Insect insect) {
        // TODO
    }
    public void selectObject(Mycelium mycelium) {
        // TODO - kristófé
    }
}
