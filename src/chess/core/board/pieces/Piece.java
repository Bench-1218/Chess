package chess.core.board.pieces;

import java.util.Set;
import chess.core.player.Player.Alliance;
import chess.core.board.Board;
import chess.core.board.Position;

public abstract class Piece {
    public enum Type{
        BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;
        
    }
    private Position position;
    private final Type type;
    private final Alliance alliance;

    public Piece(Position position, Type type, Alliance alliance) {
        this.position = position;
        this.type = type;
        this.alliance = alliance;
    }

    public static Type char2Type(char c){
        switch(c){
            case 'B': case 'b':
            return Type.BISHOP;
            
            case 'K': case 'k':
            return Type.KING;
            
            case 'N': case 'n':
            return Type.KNIGHT;
            
            case 'P': case 'p':
            return Type.PAWN;
            
            case 'Q': case 'q':
            return Type.QUEEN;
            
            case 'R': case 'r':
            return Type.ROOK;

            case 'x':
            return null;

            default:
            System.out.println("invalid character in char2Type");
            return null;
        }
    }

    public static Alliance char2Alliance(char c){
        if(c >= 'a' && c <= 'z') return Alliance.BLACK;
        if(c >= 'A' && c <= 'Z') return Alliance.WHITE;
        System.out.println("invalid character in char2Alliance");
        return null;
    }
    
    public Alliance getAlliance() {
        return alliance;
    }
    public Type getType() {
        return type;
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    
    public abstract Set<Position> availablePosition(Board board);

    @Override
    public abstract String toString();
    public abstract char toChar();
}