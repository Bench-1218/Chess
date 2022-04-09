package chess.core;

import chess.core.board.Board;
import chess.core.player.ai.AI;

public class Control {
    private static Board board = new Board();
    private static AI ai = new AI();

    public static char[][] getCharBoard(){
        // BKNPQR and bknpqr are abbreviations. Notice: N indicates Knight and K indicated King
        // the uppercase means WHITE and the lowercase means BLACK
        return board.getCharBoard();
    }
    public static int[][] getAvailablPositions(int x, int y){
        // return n by 2 matrix or null
        // n: n available positions
        // 2: coordinates of the position 
        return board.getAvailablPositions(x, y);
    }
    public boolean movePiece(int x1, int y1, int x2, int y2){
        // alliance try to move the piece in (x1, y1) to (x2, y2)
        // if the operation is valid, return true; otherwise return false
        return board.movePiece(x1, y1, x2, y2, board.getTurn());
    }
    public boolean isOutOfBound(int x, int y){
        // whether the position (x, y) is out of boundary
        return Board.isOutOfBound(x, y);
    }
    public String ifWin(){
        // output "WHITE" or "BLACK" to represent the winner
        // output "DRAW" to represent the drawn game
        // output "NOTHING" to represent that nothing happens
        return board.ifWin();
    }
    public int[] nextStepAI(int algorithm, int time){
        return AI.nextStepAI(algorithm, time, board);
    }
}
