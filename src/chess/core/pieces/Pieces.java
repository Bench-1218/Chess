package chess.core.pieces;

import java.util.Set;
import chess.core.player.Player.Alliance;
import chess.core.board.Board;

public abstract class Pieces {
    enum Type{
        BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK;
    }
    
    private final Type type;
    private final Alliance alliance;

    public Pieces(Type type, Alliance alliance) {
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
}