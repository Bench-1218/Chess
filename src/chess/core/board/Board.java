package chess.core.board;

import java.util.Map;
import java.util.Set;
import chess.core.pieces.Pieces;
import chess.core.pieces.Position;
import chess.core.player.Player.Alliance;

public class Board {
    public static final int HEIGHT = 8;
    public static final int WIDTH = 8;
    private Alliance turn;
    private boolean whiteKingExist;
    private boolean blackKingExist;
    private Map<Position, Pieces> board;
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

    public Map<Position, Pieces> getBoard() {
        return board;
    }

    public Set<Position> getAvailablPositions(int x, int y){
        return board.get(new Position(x, y)).availablePosition(this);
    }

    public boolean movePiece(int x1, int y1, int x2, int y2, Alliance alliance){
        // alliance try to move the piece in (x1, y1) to (x2, y2)
        // if the operation is valid, return true; otherwise return false
        Position p1 = new Position(x1, y1);
        Position p2 = new Position(x2, y2);
        if(alliance != turn){
            System.out.println("It's not your turn");
            return false;
        }
        if(board.get(p1).availablePosition(this).contains(p2)){
            board.remove(p1);
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

}
