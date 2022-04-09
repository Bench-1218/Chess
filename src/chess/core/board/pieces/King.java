package chess.core.board.pieces;

import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class King extends Piece{

    public King(Position position, Alliance alliance) {
        super(position, Type.KING, alliance);
    }

    @Override
    public Set<Position> availablePosition(Board board) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return "King";
    }

    @Override
    public char toChar() {
        return this.getAlliance() == Alliance.WHITE ? 'K' : 'k';
    }
    
}
