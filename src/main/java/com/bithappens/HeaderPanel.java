package com.bithappens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HeaderPanel extends JPanel implements IFungoriumPanel {

    private JComboBox<String> players = new JComboBox<>();
    private JLabel currentPlayer = new JLabel();
    private JLabel roundCound = new JLabel();
    private FungoriumFrame fungoriumFrame;
    private ImageIcon backgroundImage;
    /**
     * Constructor of HeaderPanel that sets up the panel
     * @param frame FungoriumFrame that contains the header panel
     */
    public HeaderPanel(FungoriumFrame frame){
        /*
         * SET UP PANEL
         */
        fungoriumFrame = frame;
        //this.setBounds(0, 0, 1800, 150);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // Buttons' font to use
        Font buttonFont = new Font("Arial", Font.BOLD, 10);
        // Components' preferred dimensions
        Dimension elementDimension = new Dimension(90, 30);
        // Panel layout
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 30));
        //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        backgroundImage = new ImageIcon("src/main/resources/header-icon-full.png");
        /*
         * SELECT NUMBER OF PLAYERS PANEL
         */
        JPanel addPlayersPanel = new JPanel();
        addPlayersPanel.setLayout(new BoxLayout(addPlayersPanel, BoxLayout.Y_AXIS));
        JLabel numberOfPlayersText = new JLabel("Number of Players: ");
        numberOfPlayersText.setFont(new Font("Arial", Font.PLAIN, 10));
        JTextField numberOfPlayers = new JTextField();
        

        addPlayersPanel.add(numberOfPlayersText);
        addPlayersPanel.add(numberOfPlayers);
        /*
         * NEW GAME BUTTON
         */
        JButton newGame = new JButton("New Game");
        newGame.setPreferredSize(elementDimension);
        newGame.setFont(buttonFont);
        newGame.addActionListener(e -> {
            // TODO: implement
            String numberOfPlayerString = numberOfPlayers.getText();
            
            fungoriumFrame.getPrototype().handleInput("newgame");
            




            fungoriumFrame.setPlayerColors();
        });
        /*
         * SAVE GAME PANEL
         */
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
            String saveFileName = saveTextField.getText();
            saveTextField.setText("");
            if (saveFileName != null && !saveFileName.isBlank()) {
                String result = fungoriumFrame.getPrototype().handleInput("save " + saveFileName);
                JOptionPane.showMessageDialog(null, result);
                //fungoriumFrame.resetAll();
                //TODO: panelek frissiteses az uj allapotra

            } else {
                JOptionPane.showMessageDialog(null, "Mentés megszakítva vagy érvénytelen név.");
            }


        });
        /*
         * LOAD GAME PANEL
         */
        JPanel loadPanel = new JPanel();
        loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.Y_AXIS));
        JLabel loadText = new JLabel("Name of the load file: ");
        loadText.setFont(new Font("Arial", Font.PLAIN, 10));
        JTextField loadTextField = new JTextField();

        loadPanel.add(loadText);
        loadPanel.add(loadTextField);

        JButton load = new JButton("Load");
        load.setPreferredSize(elementDimension);
        load.setFont(buttonFont);
        load.addActionListener(e -> {
            String loadFileName = loadTextField.getText();
            loadTextField.setText("");
            if (loadFileName != null && !loadFileName.isBlank()) {
                String result = fungoriumFrame.getPrototype().handleInput("load " + loadFileName);
                JOptionPane.showMessageDialog(null, result);
                //fungoriumFrame.resetAll();
                //TODO: panelek frissiteses az uj allapotra

                
            } else {
                JOptionPane.showMessageDialog(null, "Betöltés megszakítva vagy érvénytelen név.");
            }


            fungoriumFrame.setPlayerColors();

            

        });
        /*
         * PLAYER LIST 
         */
        players.setPreferredSize(elementDimension);
        fillPlayerList();
        setCurrentPlayer();
        players.setSelectedItem(null);
        /*
         * CURRENT PLAYER 
         */
        JLabel currentPlayerText = new JLabel("Current Player: ");
        
        currentPlayerText.setFont(new Font("Arial", Font.BOLD, 10));
        currentPlayerText.setPreferredSize(elementDimension);

        currentPlayer.setFont(new Font("Arial", Font.BOLD, 10));
        currentPlayer.setForeground(Color.RED);
        /*
         * NEXT PLAYER BUTTON
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

        /*
         * PLAYER LIST RENDERER SO IT SHOWS COLORS
         */
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

        /*
         * RoundCount
         */        
        roundCound.setFont(new Font("Arial", Font.BOLD, 10));
        roundCound.setPreferredSize(elementDimension);
        roundCound.setText("Rounds: " + frame.getPrototype().getGame().getRoundCount() + "/" +  frame.getPrototype().getGame().finishGame );
        
        /*
         * ADD COMPONENTS TO PANEL
         */
        this.add(addPlayersPanel);
        this.add(newGame);
        this.add(savePanel);
        this.add(save);
        this.add(loadPanel);
        this.add(load);
        this.add(players);
        this.add(currentPlayerText);
        this.add(currentPlayer);
        this.add(nextPlayer);
        this.add(roundCound);

    }
    /**
     * Fills the players' combobox with the player names
     */
    private void fillPlayerList(){
        players.removeAllItems();
        for (Player p : fungoriumFrame.getPrototype().getGame().getPlayers()) {
            players.addItem(fungoriumFrame.getPrototype().getKey(p));
        }
    }
    /**
     * Syncs the current player with the actual current player 
     */
    public void setCurrentPlayer(){
        Player p = fungoriumFrame.getPrototype().getGame().getCurrentPlayer();
        currentPlayer.setText(fungoriumFrame.getPrototype().getKey(p));
        currentPlayer.setForeground(fungoriumFrame.getPlayerColor(p));
    }

    /**
     * Syncs the roundCount with the actual roundcount xd 
     */
    public void setRoundCount(){
        int current = fungoriumFrame.getPrototype().getGame().getRoundCount();
        int total = fungoriumFrame.getPrototype().getGame().finishGame;
        
        roundCound.setText("Rounds: " + current + "/" + total);
    }
    

    @Override
    public void reset() {
        fillPlayerList();
        setCurrentPlayer();
        setRoundCount();
        players.setSelectedItem(null);
        currentPlayer.repaint();
        currentPlayer.revalidate();
        roundCound.repaint();
        roundCound.revalidate();
        
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw background image
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
    }
}
