package chess.core.pieces;

import java.util.Set;
import chess.core.player.Player.Alliance;
import chess.core.board.Board;
import chess.core.board.Position;

public abstract class Piece {
    public enum Type{
        BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;
        
    }
    
    private final Type type;
    private final Alliance alliance;

    public Piece(Type type, Alliance alliance) {
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
    
    @Override
    public abstract String toString();
}