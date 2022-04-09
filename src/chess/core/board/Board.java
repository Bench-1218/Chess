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
    private int round;

    private void resetSteps(){
        // TODO
    }
    private void resetStatus(){
        whiteKingExist = true;
        blackKingExist = true;
        turn = Alliance.WHITE;
        round = 1;
    }
    private void resetBoard(){
        // TODO
    }

    public Board(){
        reset();
    }
    
    public void reset(){
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

    public Piece[][] getBoard(){
        return this.board;
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


    public char ifWin(){
        // output "W" or "B" to represent the winner
        // output "D" to represent the drawn game
        // output "N" to represent that nothing happens
        if(turn == Alliance.BLACK){
            if(!blackKingExist) return 'W';
        }else{
            if(!whiteKingExist) return 'B';
        }
        // TODO draw
        return 'N';
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
        if(turn == null) System.out.println("Turn is not set");
        return this.turn;
    }
    public char getTurnChar(){
        if(this.turn == Alliance.BLACK) return 'B';
        if(this.turn == Alliance.WHITE) return 'W';
        System.out.println("Turn is not set");
        return 'N';
    }

    //-------------------------------------------------for usage of debug-------------------------------------------------------
    public void printBoard() {
        char[][] charBoard = this.getCharBoard();
        for(int y = 0; y < Board.HEIGHT; y++){
            for(int x = 0; x < Board.WIDTH; x++){
                System.out.print(charBoard[x][y]==0 ? charBoard[x][y] : ' ');
            }
            System.out.println();
        }
    }
    public void printAvailablePositions(int x, int y) {
        int[][] ps = getAvailablPositions(x, y);
        if(ps == null){
            System.out.println("No available position");
        }else{
            for(int y1 = 0; y1 < Board.HEIGHT; y1++){
                for(int x1 = 0; x1 < Board.WIDTH; x1++){
                    boolean available = true;
                    for(int n = 0; n < ps.length; n++){
                        if(x1 == ps[n][0] && y1 == ps[n][1]){
                            available = false;
                            break;
                        }
                    }
                    System.out.print(available ? 'x' : ' ');
                }
                System.out.println();
            }
        }
    }
}
