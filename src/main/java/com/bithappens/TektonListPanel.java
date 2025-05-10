package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TektonListPanel extends JPanel implements IFungoriumPanel {
    private TektonViewPanel tektonViewPanel;
    private ObjectSelectorPanel objectSelectorPanel;
    private FungoriumFrame fungoriumFrame;
    private JScrollPane scrollPane;
    private JPanel gridPanel;
    public TektonListPanel(FungoriumFrame frame, TektonViewPanel tektonViewPanel, ObjectSelectorPanel objectSelectorPanel) {
        fungoriumFrame = frame;
        this.tektonViewPanel = tektonViewPanel;
        this.objectSelectorPanel = objectSelectorPanel;
        // TESZT JELLEG INNENTŐL
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 2, 0, 0));
        scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(200, 800));
        //debug backgorund
        setBackground(Color.CYAN);
        for (int i = 0; i < 90; i++) {
            gridPanel.add(new JButton("Teszt: " + i));
        }
        this.add(scrollPane);        
    }
    @Override
    public void reset() {

    }
}
