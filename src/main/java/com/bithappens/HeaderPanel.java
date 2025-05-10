package com.bithappens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HeaderPanel extends JPanel implements IFungoriumPanel, ActionListener {

    JComboBox<String> players = new JComboBox<>();
    JLabel currentPlayer = new JLabel();

    public HeaderPanel(){
        this.setBounds(0, 0, 1800, 150);
        this.setBackground(Color.GREEN);

        Font buttonFont = new Font("Arial", Font.BOLD, 20);

        // ELEMENTS
        setLayout(new FlowLayout(FlowLayout.LEADING, 40, 50));

        JPanel addPlayersPanel = new JPanel();
        addPlayersPanel.setLayout(new BoxLayout(addPlayersPanel, BoxLayout.Y_AXIS));
        JLabel numberOfPlayersText = new JLabel("Number of Players: ");
        numberOfPlayersText.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField numberOfPlayers = new JTextField();

        addPlayersPanel.add(numberOfPlayersText);
        addPlayersPanel.add(numberOfPlayers);

        JButton newGame = new JButton("New Game");
        newGame.setPreferredSize(new Dimension(140, 50));
        newGame.setFont(buttonFont);

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        JLabel saveText = new JLabel("Name of the save file: ");
        saveText.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField saveTextField = new JTextField();

        savePanel.add(saveText);
        savePanel.add(saveTextField);

        JButton save = new JButton("Save");
        save.setPreferredSize(new Dimension(90, 50));
        save.setFont(buttonFont);

        JButton load = new JButton("Load");
        load.setPreferredSize(new Dimension(90, 50));
        load.setFont(buttonFont);

        players.setPreferredSize(new Dimension(150, 50));

        JLabel currentPlayerText = new JLabel("Current Player: ");
        currentPlayerText.setFont(new Font("Arial", Font.BOLD, 20));
        currentPlayerText.setPreferredSize(new Dimension(150, 50));

        currentPlayer.setFont(new Font("Arial", Font.BOLD, 20));
        currentPlayer.setForeground(Color.RED);

        JButton nextPlayer = new JButton("Next Player");
        nextPlayer.setPreferredSize(new Dimension(150, 50));
        nextPlayer.setFont(buttonFont);


        this.add(addPlayersPanel);
        this.add(newGame);
        this.add(savePanel);
        this.add(save);
        this.add(load);
        this.add(players);
        this.add(currentPlayerText);
        this.add(currentPlayer);
        this.add(nextPlayer);

    }

    public void fillPlayerList(ArrayList<String> playerNames){

        for(String name : playerNames){
            players.addItem(name);
        }
    }

    public void setCurrentPlayer(String name){
        currentPlayer.setText(name);
    }
    @Override
    public void reset() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
