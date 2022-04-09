package chess.core.board;

import java.util.Set;

import chess.core.board.pieces.Piece;
import chess.core.player.Player.Alliance;

public class Board {
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;
    private Alliance turn;
    private boolean whiteKingExist;
    private boolean blackKingExist;
    private Piece[][] board;
    private Steps steps;

    private void resetSteps(){
        // TODO
    }
    private void resetStatus(){
        whiteKingExist = true;
        blackKingExist = true;
        turn = Alliance.WHITE;
    }
    private void resetBoard(){
        // TODO
    }

    public Board(){
        resetStatus();
        resetBoard();
        resetSteps();
    }

    public char[][] getCharBoard() {
        // BKNPQR and bknpqr are abbreviations. Notice: N indicates Knight and K indicated King
        // the uppercase means WHITE and the lowercase means BLACK
        char[][] charBoard = new char[Board.HEIGHT][Board.WIDTH];
        for(int y = 0; y < Board.HEIGHT; y++){
            for(int x = 0; x < Board.WIDTH; x++){
                charBoard[x][y] = board[x][y].toChar();
            }
        }
        return charBoard;
    }

    

    public int[][] getAvailablPositions(int x, int y){
        // return n-by-2 matrix or null
        Set<Position> ps = board[x][y].availablePosition(this);
        if(ps.size()==0) return null;
        int[][] psInt = new int[ps.size()][2];
        int i = 0;
        for(Position p : ps){
            psInt[i][0] = p.getX();
            psInt[i][1] = p.getY();
            i++;
        }
        return psInt;
    }


    public boolean movePiece(int x1, int y1, int x2, int y2, Alliance alliance){
        // alliance try to move the piece in (x1, y1) to (x2, y2)
        // if the operation is valid, return true; otherwise return false
        Position p2 = new Position(x2, y2);

        if(alliance != turn){
            System.out.println("It's not your turn");
            return false;
        }
        if(board[x1][y1].availablePosition(this).contains(p2)){
            board[x1][y1] = null;
            // TODO
            return true;
        }else{
            System.out.println("the position "+ p2 +" is not available");
            return false;
        }

    }


    public String ifWin(){
        // output "WHITE" or "BLACK" to represent the winner
        // output "DRAW" to represent the drawn game
        // output "NOTHING" to represent that nothing happens
        if(turn == Alliance.BLACK){
            if(!blackKingExist) return "WHITE";
        }else{
            if(!whiteKingExist) return "BLACK";
        }
        // TODO draw
        return "NOTHING";
    }

    public static boolean isOutOfBound(int x, int y){
        if(x >= 0 && x < Board.WIDTH && y >= 0 && y < Board.HEIGHT)
            return false;
        else return true;
    }
    public static boolean isOutOfBound(Position p){
        return isOutOfBound(p.getX(), p.getY());
    }
    public Alliance getTurn(){
        return this.turn;
    }
}
