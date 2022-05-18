package chess.gui;

import javax.swing.*;

public class ChessFrame extends JFrame {

    JPanel root = new JPanel();


    public ChessFrame() {

        ChessBoard chessBoard = new ChessBoard();
        root.setLayout(new WholeLayout());

        root.add(chessBoard);


        this.setContentPane(root);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("chessboard");
        this.setSize(750, 500);
        this.setLocationRelativeTo(null);

    }

    public ChessFrame(int i) {

        ChessBoard chessBoardAI=new ChessBoard(1);
        root.setLayout(new WholeLayout());

        root.add(chessBoardAI);

        this.setContentPane(root);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("chessboardAI");
        this.setSize(750, 500);
        this.setLocationRelativeTo(null);

    }


}



