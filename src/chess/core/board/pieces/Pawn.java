package chess.core.board.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class Pawn extends Piece{

    private boolean march; // if the Pawn has jumped over two grids

    public Pawn(Position position, Alliance alliance) {
        super(position, Type.PAWN, alliance);
        this.march = false;
    }

    public Pawn(Position position, Alliance alliance, boolean march){
        super(position, Type.PAWN, alliance);
        this.march = march;
    }

    @Override
    public Set<Position> availablePosition(Board board) {
        Set<Position> ps = new HashSet<>();
        Position pos = this.getPosition();
        int x = pos.getX();
        int y = pos.getY();
        Piece[][] pieces = board.getBoard();

        if(this.getAlliance() == Alliance.BLACK){
            if(!Board.isOutOfBound(x, y+1)){
                if(pieces[x][y+1] == null){
                    ps.add(new Position(x, y+1));
                    if(!march && !Board.isOutOfBound(x, y+2) && (pieces[x][y+2]==null || pieces[x][y+2].getAlliance() == Alliance.WHITE)){
                        ps.add(new Position(x, y+2));
                    }
                } else if(pieces[x][y+1].getAlliance() == Alliance.WHITE){
                    ps.add(new Position(x, y+1));
                }
            }

            if(!Board.isOutOfBound(x+1,y+1) && pieces[x+1][y+1] != null && pieces[x+1][y+1].getAlliance() == Alliance.WHITE){
                ps.add(new Position(x+1, y+1));
            }
            if(!Board.isOutOfBound(x-1,y+1) && pieces[x-1][y+1] != null && pieces[x-1][y+1].getAlliance() == Alliance.WHITE){
                ps.add(new Position(x-1, y+1));
            }

        }else{
            if(!Board.isOutOfBound(x, y-1)){
                if(pieces[x][y-1] == null){
                    ps.add(new Position(x, y-1));
                    if(!march && !Board.isOutOfBound(x, y-2) && (pieces[x][y-2]==null || pieces[x][y-2].getAlliance() == Alliance.BLACK)){
                        ps.add(new Position(x, y-2));
                    }
                } else if(pieces[x][y-1].getAlliance() == Alliance.BLACK){
                    ps.add(new Position(x, y-1));
                }
            }

            if(!Board.isOutOfBound(x+1,y-1) && pieces[x+1][y-1] != null && pieces[x+1][y-1].getAlliance() == Alliance.WHITE){
                ps.add(new Position(x+1, y-1));
            }
            if(!Board.isOutOfBound(x-1,y-1) && pieces[x-1][y-1] != null && pieces[x-1][y-1].getAlliance() == Alliance.WHITE){
                ps.add(new Position(x-1, y-1));
            }
        }
        
        
        return ps;
    }

    public void setMarch(boolean m){
        this.march = m;
    }

    public boolean getMarch(){
        return this.march;
    }

    @Override
    public String toString() {
        return "Pawn";
    }

    @Override
    public char toChar() {
        return this.getAlliance() == Alliance.WHITE ? 'P' : 'p';
    }
    
}
