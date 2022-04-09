package chess.core.board;

import chess.core.pieces.Piece.Type;

public class Move {
    private Position p1, p2; // p1 to p2
    private Type type;
    public Move(Position p1, Position p2, Type type) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
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
