package chess.gui;

import chess.core.Control;

import javax.swing.*;
import java.awt.*;

public class Round extends JPanel {
    private JLabel roundLabel;
    public Round() {


        this.roundLabel = new JLabel();
        this.roundLabel.setSize(300, 200);
        this.roundLabel.setFont(new Font("Calibri", Font.BOLD, 20));

        roundLabel.setText("Round:" + Control.getRound());
        add(roundLabel);
        this.setOpaque(false);

    }



    @Override
    protected void paintComponent(Graphics g) {
        roundLabel.setText("Round:" + Control.getRound());
        this.setOpaque(false);
    }
}
