package chess.core.board.pieces;

import java.util.Set;
import chess.core.player.Player.Alliance;
import chess.core.board.Board;
import chess.core.board.Position;

public abstract class Piece {
    public enum Type{
        BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;
        
    }
    private final Position position;
    private final Type type;
    private final Alliance alliance;

    public Piece(Position position, Type type, Alliance alliance) {
        this.position = position;
        this.type = type;
        this.alliance = alliance;
    }

    public abstract Set<Position> availablePosition(Board board);

    public Alliance getAlliance() {
        return alliance;
    }
    public Type getType() {
        return type;
    }
    public Position getPosition(){
        return position;
    }

    @Override
    public abstract String toString();
    public abstract char toChar();
}