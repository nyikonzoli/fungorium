package com.bithappens;

import java.awt.Color;

import javax.swing.JPanel;

public class TektonViewPanel extends JPanel implements IFungoriumPanel{
    private FungoriumFrame fungoriumFrame;
    public TektonViewPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;

        //debug color
        setBackground(Color.WHITE);
    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
}
