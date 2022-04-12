package chess.core.pieces;

import java.util.Set;
import chess.core.player.Player.Alliance;
import chess.core.board.Board;
import chess.core.board.Position;

public abstract class Piece {
    public enum Type{
        BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;
        @Override
        public String toString() {
            switch(this.name()){
                case "BISHOP":
                    return "B";
                case "KING":
                    return "K";
                case "KNIGHT":
                    return "N";
                case "PAWN":
                    return "P";
                case "QUEEN":
                    return "Q";
                case "ROOK":
                    return "R";
                default:
                    return " ";
            }
        }
    }
    private Position position;
    private final Type type;
    private final Alliance alliance;

    public static Piece pieceGenerator(char type, int x, int y){
        Piece.Type t = Piece.char2Type(type);
        Alliance alli = Piece.char2Alliance(type);
        if(t == null) return null;
        switch(t){
            case BISHOP:
                return new Bishop(new Position(x, y), alli);
            case KING:
                return new King(new Position(x,y), alli);
            case KNIGHT:
                return new Knight(new Position(x,y), alli);
            case PAWN:
                return new Pawn(new Position(x,y), alli);
            case QUEEN:
                return new Queen(new Position(x,y), alli);
            case ROOK:
                return new Rook(new Position(x,y), alli);
            default:
                System.out.println("fail to generate piece");
                return null;
        }
    }

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

    public void move(int x2, int y2, Board board){
        // what will happen when move this piece to (x2, y2)
        Piece[][] ps = board.getBoard();
        Position p = this.getPosition();
        int x1 = p.getX();
        int y1 = p.getY();

        ps[x2][y2] = this;
        ps[x1][y1] = null;
        this.setPosition(new Position(x2, y2));
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
    
    @Override
    public abstract String toString();
    public abstract Set<Position> availablePosition(Board board);
    public abstract Set<Position> attackPosition(Board board);
    public abstract char toChar();
    public abstract void nextTurn(); // this function is invoked at the end of every turn

}