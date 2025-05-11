package com.bithappens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HeaderPanel extends JPanel implements IFungoriumPanel {

    private JComboBox<String> players = new JComboBox<>();
    private JLabel currentPlayer = new JLabel();
    private FungoriumFrame fungoriumFrame;

    public HeaderPanel(FungoriumFrame frame){
        fungoriumFrame = frame;
        //this.setBounds(0, 0, 1800, 150);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        Font buttonFont = new Font("Arial", Font.BOLD, 10);
        Dimension elementDimension = new Dimension(90, 30);
        // ELEMENTS
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 30));
        //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel addPlayersPanel = new JPanel();
        addPlayersPanel.setLayout(new BoxLayout(addPlayersPanel, BoxLayout.Y_AXIS));
        JLabel numberOfPlayersText = new JLabel("Number of Players: ");
        numberOfPlayersText.setFont(new Font("Arial", Font.PLAIN, 10));
        JTextField numberOfPlayers = new JTextField();

        addPlayersPanel.add(numberOfPlayersText);
        addPlayersPanel.add(numberOfPlayers);

        JButton newGame = new JButton("New Game");
        newGame.setPreferredSize(elementDimension);
        newGame.setFont(buttonFont);
        newGame.addActionListener(e -> {
            // TODO: implement
            fungoriumFrame.setPlayerColors();
        });

        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        JLabel saveText = new JLabel("Name of the save file: ");
        saveText.setFont(new Font("Arial", Font.PLAIN, 10));
        JTextField saveTextField = new JTextField();

        savePanel.add(saveText);
        savePanel.add(saveTextField);

        JButton save = new JButton("Save");
        save.setPreferredSize(elementDimension);
        save.setFont(buttonFont);
        save.addActionListener(e -> {
            // TODO: implement
        });

        JButton load = new JButton("Load");
        load.setPreferredSize(elementDimension);
        load.setFont(buttonFont);
        load.addActionListener(e -> {
            // TODO: implement
            fungoriumFrame.setPlayerColors();
        });

        players.setPreferredSize(elementDimension);

        JLabel currentPlayerText = new JLabel("Current Player: ");
        currentPlayerText.setFont(new Font("Arial", Font.BOLD, 10));
        currentPlayerText.setPreferredSize(elementDimension);

        currentPlayer.setFont(new Font("Arial", Font.BOLD, 10));
        currentPlayer.setForeground(Color.RED);
        /*
         * Next Player button with action listener
         */
        JButton nextPlayer = new JButton("Next Player");
        nextPlayer.setPreferredSize(elementDimension);
        nextPlayer.setMargin(new Insets(0, 0, 0, 0));
        nextPlayer.setFont(buttonFont);
        nextPlayer.addActionListener(e -> {
            System.out.println("Next round pressed");
            fungoriumFrame.getPrototype().getGame().nextPlayer();
            System.out.println(fungoriumFrame.getPrototype().getKey(fungoriumFrame.getPrototype().getGame().getCurrentPlayer()));
            fungoriumFrame.resetAll();
        });

        fillPlayerList();
        setCurrentPlayer();
        // different names with different colors
        
        players.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value != null) {
                    String playerKey = value.toString();
                    // megkeressük a Player példányt a key alapján
                    for (Player p : fungoriumFrame.getPrototype().getGame().getPlayers()) {
                        if (fungoriumFrame.getPrototype().getKey(p).equals(playerKey)) {
                            label.setForeground(fungoriumFrame.getPlayerColor(p));
                            break;
                        }
                    }
                }

                return label;
            }
        });
        players.setSelectedItem(null);
        
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

    private void fillPlayerList(){
        players.removeAllItems();
        for (Player p : fungoriumFrame.getPrototype().getGame().getPlayers()) {
            players.addItem(fungoriumFrame.getPrototype().getKey(p));
        }
    }

    public void setCurrentPlayer(){
        Player p = fungoriumFrame.getPrototype().getGame().getCurrentPlayer();
        currentPlayer.setText(fungoriumFrame.getPrototype().getKey(p));
        currentPlayer.setForeground(fungoriumFrame.getPlayerColor(p));
    }
    @Override
    public void reset() {
        fillPlayerList();
        setCurrentPlayer();
        players.setSelectedItem(null);
        currentPlayer.repaint();
        currentPlayer.revalidate();
    }
}
