package chess.core.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class Queen extends Piece{
    private final int[][] DIRECTION = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
    public Queen(Position position, Alliance alliance) {
        super(position, Type.QUEEN, alliance);
    }

    @Override
    public Set<Position> availablePosition(Board board) {
        Set<Position> ps = new HashSet<>();
        Piece[][] pieces = board.getBoard();
        Position p = this.getPosition();
        int x = p.getX();
        int y = p.getY();
        for(int i = 0; i < DIRECTION.length; i++){
            for(int s = 1;; s++){
                int x1 = x + s*DIRECTION[i][0];
                int y1 = y + s*DIRECTION[i][1];
                if(Board.isOutOfBound(x1, y1)) break;
                if(pieces[x1][y1] == null){
                    ps.add(new Position(x1, y1));
                }else if(pieces[x1][y1].getAlliance() != this.getAlliance()){
                    ps.add(new Position(x1, y1));
                    break;
                }else if(pieces[x1][y1].getAlliance() == this.getAlliance()){
                    break;
                }else{
                    System.out.println("availablePosition failed at " + p + this.toChar());
                }
            }
        }
        
        return ps;
    }

    @Override
    public String toString() {
        return "Queen";
    }

    @Override
    public char toChar() {
        return this.getAlliance() == Alliance.WHITE ? 'Q' : 'q';
    }

    @Override
    public void nextTurn() {
        
    }

    @Override
    public Set<Position> attackPosition(Board board) {
        return availablePosition(board);
    }
    
}
