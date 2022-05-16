package chess.gui;

import chess.core.Control;

import javax.swing.*;

public class End extends JDialog {
    char result;
    ImageIcon imageIcon;
    ChessBoard chessBoard;

    public End(char c,ChessBoard chessBoard) {
        this.chessBoard=chessBoard;

        this.result = c;

        if (result == 'D') {
            imageIcon = new ImageIcon("./src/chess/gui/image/drawn.png");
        } else if (result == 'W') {
            imageIcon = new ImageIcon("./src/chess/gui/image/whiteWin.png");
        } else if (result == 'B') {
            imageIcon = new ImageIcon("./src/chess/gui/image/blackWin.png");
        }

        this.setModal(true);
        this.setTitle("Ending Message");
        JLayeredPane layeredPane = new JLayeredPane();
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(0, 0, 400, 300);


        JButton confirmButton = new JButton("yes");
        JButton tryButton = new JButton("try again");
        tryButton.setIcon(new ImageIcon("./src/chess/gui/image/restart.png"));
        confirmButton.setBounds(250, 125, 100, 50);
        tryButton.setBounds(250, 175, 100, 50);


        tryButton.addActionListener(e->{
            Control.restart();
            ChessBoard.pieces = Control.getCharBoard();
            chessBoard.repaint();
          /*  ChessBoard.blackTime=30;
            ChessBoard.whiteTime=30;*/
            this.setVisible(false);
        });

        layeredPane.add(jLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(confirmButton, JLayeredPane.MODAL_LAYER);
        layeredPane.add(tryButton, JLayeredPane.MODAL_LAYER);


        confirmButton.addActionListener((e) -> {
            this.setVisible(false);
        });

        this.add(layeredPane);
        this.setLocationRelativeTo(null);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setResizable(false);


    }
}
