package chess.core.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class Rook extends Piece{
    private final int[][] DIRECTION = {{0,1},{0,-1},{1,0},{-1,0}};

    private boolean first = false; // whether King has taken the first step

    public Rook(Position position, Alliance alliance) {
        super(position, Type.ROOK, alliance);
    }

    public boolean getFirst(){
        return first;
    }

    @Override
    public void move(int x2, int y2, Board board) {
        super.move(x2, y2, board);
        first = true;
    }

    @Override
    public String toString() {
        return "Rook";
    }

    @Override
    public Set<Position> availablePosition(final Board board) {
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
                    ps.add(new Position(x1,y1));
                }else if(pieces[x1][y1].getAlliance() != this.getAlliance()){
                    ps.add(new Position(x1,y1));
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
    public char toChar() {
        return this.getAlliance() == Alliance.WHITE ? 'R' : 'r';
    }

    @Override
    public void nextTurn() {

    }

    @Override
    public Set<Position> attackPosition(Board board) {
        return availablePosition(board);
    }
    
}
