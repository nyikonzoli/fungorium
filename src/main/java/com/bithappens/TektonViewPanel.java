package com.bithappens;

import java.awt.Color;

import javax.swing.JPanel;

public class TektonViewPanel extends JPanel implements IFungoriumPanel{
    private FungoriumFrame fungoriumFrame;
    private Tekton selectedTekton;
    public TektonViewPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;

        //debug color
        setBackground(Color.WHITE);
    }
    @Override
    public void reset() {
        removeAll();
        selectedTekton = null;
        revalidate();
        repaint();
    }
    public void setTekton(Tekton tekton) {
        reset();
        selectedTekton = tekton;
        /*
         * Todo: kirajzolás megalósít
         */
    }
}
