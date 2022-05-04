package chess.core.gui;

import javax.swing.*;

public class ChessFrame extends JFrame {

    JPanel root = new JPanel();
    ChessBoard chessBoard = new ChessBoard();


    public ChessFrame() {


        root.setLayout(new WholeLayout());

        root.add(chessBoard);

        this.setContentPane(root);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("chessboard");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);


    }



}



