package chess.gui;

import java.awt.event.MouseAdapter;

public class MouseMove extends MouseAdapter {
    ChessBoard chessBoard;

    public MouseMove(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
}
