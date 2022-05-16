package chess.gui;

import chess.core.Control;

import javax.swing.*;
import java.awt.*;

public class Status extends JPanel {
    private JLabel playerLabel;
    String currentPlayer;
    public Status() {

        this.playerLabel = new JLabel();
        this.playerLabel.setSize(300, 200);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        playerLabel.setBackground(Color.YELLOW);

        if(Control.getTurn()=='W'){
            currentPlayer="WHITE";
        }else if(Control.getTurn()=='B'){
            currentPlayer="BLACK";
        }

        this.setPlayerText(currentPlayer);

        add(playerLabel);

    }

    public void setPlayerText(String playerText) {
        this.playerLabel.setText(playerText + "'s turn");
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(Control.getTurn()=='W'){
            currentPlayer="WHITE";
        }else if(Control.getTurn()=='B'){
            currentPlayer="BLACK";
        }

        this.setPlayerText(currentPlayer);

    }
}
