package chess.core.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class Pawn extends Piece{

    private boolean first; // if the Pawn has took the first step
    private boolean frenzy; // if the Pawn is taking the first step
    private int cnt = 2;

    public Pawn(Position position, Alliance alliance) {
        super(position, Type.PAWN, alliance);
        this.first = false;
        this.frenzy = false;
    }

    public Pawn(Position position, Alliance alliance, boolean first, boolean frenzy){
        super(position, Type.PAWN, alliance);
        this.first = first;
        this.frenzy = frenzy;
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
                    if(!first && !Board.isOutOfBound(x, y+2) && (pieces[x][y+2]==null || pieces[x][y+2].getAlliance() == Alliance.WHITE)){
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
            if(!Board.isOutOfBound(x+1, y) && pieces[x+1][y] != null && pieces[x+1][y].getAlliance() == Alliance.WHITE &&
                pieces[x+1][y].getType() == Type.PAWN && ((Pawn)pieces[x+1][y]).getFrenzy()){
                Position newP = new Position(x+1, y+1);
                boolean has = false;
                for(Position p : ps){
                    if(p.equals(newP)) has = true;
                }
                if(!has) ps.add(newP);
            }
            if(!Board.isOutOfBound(x-1,y) && pieces[x-1][y] != null && pieces[x-1][y].getAlliance() == Alliance.WHITE &&
                pieces[x-1][y].getType() == Type.PAWN && ((Pawn)pieces[x-1][y]).getFrenzy()){
                Position newP = new Position(x-1, y+1);
                boolean has = false;
                for(Position p : ps){
                    if(p.equals(newP)) has = true;
                }
                if(!has) ps.add(newP);
            }
            

        }else{
            if(!Board.isOutOfBound(x, y-1)){
                if(pieces[x][y-1] == null){
                    ps.add(new Position(x, y-1));
                    if(!first && !Board.isOutOfBound(x, y-2) && (pieces[x][y-2]==null || pieces[x][y-2].getAlliance() == Alliance.BLACK)){
                        ps.add(new Position(x, y-2));
                    }
                } else if(pieces[x][y-1].getAlliance() == Alliance.BLACK){
                    ps.add(new Position(x, y-1));
                }
            }

            if(!Board.isOutOfBound(x+1,y-1) && pieces[x+1][y-1] != null && pieces[x+1][y-1].getAlliance() == Alliance.BLACK){
                ps.add(new Position(x+1, y-1));
            }
            if(!Board.isOutOfBound(x-1,y-1) && pieces[x-1][y-1] != null && pieces[x-1][y-1].getAlliance() == Alliance.BLACK){
                ps.add(new Position(x-1, y-1));
            }
            if(!Board.isOutOfBound(x+1, y) && pieces[x+1][y] != null && pieces[x+1][y].getAlliance() == Alliance.BLACK &&
                pieces[x+1][y].getType() == Type.PAWN && ((Pawn)pieces[x+1][y]).getFrenzy()){
                Position newP = new Position(x+1, y-1);
                boolean has = false;
                for(Position p : ps){
                    if(p.equals(newP)) has = true;
                }
                if(!has) ps.add(newP);
            }
            if(!Board.isOutOfBound(x-1,y) && pieces[x-1][y] != null && pieces[x-1][y].getAlliance() == Alliance.BLACK &&
                pieces[x-1][y].getType() == Type.PAWN && ((Pawn)pieces[x-1][y]).getFrenzy()){
                Position newP = new Position(x-1, y-1);
                boolean has = false;
                for(Position p : ps){
                    if(p.equals(newP)) has = true;
                }
                if(!has) ps.add(newP);
            }
        }
        
        
        return ps;
    }

    public void setFirst(boolean m){
        this.first = m;
    }
    public boolean getFirst(){
        return this.first;
    }
    public void setFrenzy(boolean f){
        this.frenzy = f;
    }
    public boolean getFrenzy(){
        return this.frenzy;
    }

    @Override
    public String toString() {
        return "Pawn";
    }

    @Override
    public char toChar() {
        return this.getAlliance() == Alliance.WHITE ? 'P' : 'p';
    }

    @Override
    public void nextTurn() {
        if(frenzy && --cnt == 0){
            frenzy = false;
        }
    }

    @Override
    public void move(int x2, int y2, Board board){
        Position p = this.getPosition();
        int x1 = p.getX();
        int y1 = p.getY();
        Piece[][] ps = board.getBoard();
        if(Board.isDiagonal(x1, y1, x2, y2)){
            if(this.getAlliance() == Alliance.WHITE){
                if(ps[x2][y2+1] != null && ps[x2][y2+1].getType() == Type.PAWN && ((Pawn)ps[x2][y2+1]).frenzy){
                    ps[x2][y2+1] = null;
                }
            }else{
                if(ps[x2][y2-1] != null && ps[x2][y2-1].getType() == Type.PAWN && ((Pawn)ps[x2][y2-1]).frenzy){
                    ps[x2][y2-1] = null;
                }
            }
        }
        if(!first){
            first = true;
            if(y2-y1 == 2 || y2-y1 == -2){
                frenzy = true;
            }
        }
        
        super.move(x2, y2, board);
    }
    
}
