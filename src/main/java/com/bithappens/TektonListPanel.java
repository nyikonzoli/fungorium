package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TektonListPanel extends JPanel implements IFungoriumPanel /*, ActionListener*/ {
    private TektonViewPanel tektonViewPanel;
    private ObjectSelectorPanel objectSelectorPanel;
    private FungoriumFrame fungoriumFrame;
    private JScrollPane scrollPane;
    private JPanel gridPanel;
    //private ArrayList<JButton> tektonButtons = new ArrayList<>(); 

    public TektonListPanel(FungoriumFrame frame, TektonViewPanel tektonViewPanel, ObjectSelectorPanel objectSelectorPanel) {
        fungoriumFrame = frame;
        this.tektonViewPanel = tektonViewPanel;
        this.objectSelectorPanel = objectSelectorPanel;
        // TESZT JELLEG INNENTŐL
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 2, 0, 0));
        scrollPane = new JScrollPane(gridPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(200, 600));


        //debug backgorund
        setBackground(Color.CYAN);
        //List<Tekton> tektons = frame.getPrototype().getGame().getGameField();
        /*
        for (Tekton currentTekton : tektons) {
            JButton newTektonButton = new JButton(currentTekton.getClass().getName());
            newTektonButton.addActionListener(this);
            tektonButtons.add(newTektonButton);
            gridPanel.add(newTektonButton);
        }
        */
        resetButtons();     
        this.add(scrollPane);  
 
    }
    @Override
    public void reset() {

    }

    private void resetButtons() {
        List<Tekton> tektons = fungoriumFrame.getPrototype().getGame().getGameField();
        for (Tekton tekton : tektons) {
            JButton button = new JButton();
            button.setText(fungoriumFrame.getPrototype().getKey(tekton));
            button.addActionListener(e -> {
                System.out.println("gomb teszt klikk");
                objectSelectorPanel.setTekton(tekton);
            });
            gridPanel.add(button);
        }
    }

    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton currentTektonButton : tektonButtons){
            if(e.getSource() == currentTektonButton){
                System.out.println(currentTektonButton.getText());
            }
        }
    }
    */
}
