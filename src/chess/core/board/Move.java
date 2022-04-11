package chess.core.board;

import chess.core.pieces.Piece;
import chess.core.pieces.Piece.Type;

public class Move {
    private Position p1, p2; // p1 to p2
    private Type type;
    public Move(Position p1, Position p2, Type type) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
    }
    public Move(int x1, int y1, int x2, int y2, Type type){
        this.p1 = new Position(x1, y1);
        this.p2 = new Position(x2, y2);
        this.type = type;
    }
    public Move(String s){
        // the String is expected to be like "(x1, y1) (x2, y2) type"
        String[] ss = s.split(", "); // ss is expected to be like {"(x1", "y1) (x2", "y2) type"}
        int x1 = Integer.parseInt(ss[0].substring(1));
        int y1 = Integer.parseInt(ss[1].substring(0, ss[1].indexOf(")")));
        int x2 = Integer.parseInt(ss[1].substring(ss[1].indexOf("(") + 1));
        int y2 = Integer.parseInt(ss[2].substring(0, ss[2].indexOf(")")));
        char t = ss[2].charAt(ss[2].length() - 1);
        this.p1 = new Position(x1, y1);
        this.p2 = new Position(x2, y2);
        this.type = Piece.char2Type(t);
    }
    public Position getP1() {
        return p1;
    }
    public void setP1(Position p1) {
        this.p1 = p1;
    }
    public Position getP2() {
        return p2;
    }
    public void setP2(Position p2) {
        this.p2 = p2;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return p1 + " " + p2 + " " + type;
    }
    
}
