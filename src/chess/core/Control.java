package chess.core;

import chess.core.board.Board;
import chess.core.player.ai.AI;

public class Control {
    private static Board board = new Board();

    public static char[][] getCharBoard(){
        // x means no piece on the grid
        // BKNPQR and bknpqr are abbreviations. Notice: N indicates Knight and K indicated King
        // the uppercase means WHITE and the lowercase means BLACK
        return board.getCharBoard();
    }
    public static int[][] getAvailablePositions(int x, int y){
        // return n by 2 matrix or null
        // n: n available positions
        // 2: coordinates of the position 
        return board.getAvailablePositions(x, y);
    }
    public static boolean isAvailable(int x1, int y1, int x2, int y2){
        // whether it is valid to move the piece at (x1,y1) to (x2,y2) in this turn
        return board.isAvailable(x1, y1, x2, y2);
    }
    public static boolean movePiece(int x1, int y1, int x2, int y2){
        // alliance try to move the piece in (x1, y1) to (x2, y2)
        // if the operation is valid, return true; otherwise return false
        return board.movePiece(x1, y1, x2, y2);
    }
    public static boolean isOutOfBound(int x, int y){
        // whether the position (x, y) is out of boundary
        return Board.isOutOfBound(x, y);
    }
    public static char ifWin(){
        // output "W" or "B" to represent the winner, White or Black
        // output "D" to represent the drawn game
        // output "N" to represent that nothing happens
        return board.ifWin();
    }
    public static int[] nextStepAI(int algorithm, int time){
        // algorithm 0: random
        //           1: AlphaBeta
        //           2: MonteCarloTree
        // time: in terms of seconds
        return AI.nextStepAI(algorithm, time, board);
    }
    public static void restart(){
        // restart the game
        board.reset();
    }
    public static char getTurn(){
        // If it is turn for white, then output 'W'
        // If it is turn for black, then output 'B'
        // If some problem is encountered, then output 'N'. Please contact with JAKE_XU when this happens.
        return board.getTurnChar();
    }
    public static int getRound(){
        // return the number of round
        return board.getRound();
    }
}
