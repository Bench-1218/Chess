package chess.core.board.pieces;

import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class Rook extends Piece{

    public Rook(Position position, Alliance alliance) {
        super(position, Type.ROOK, alliance);
    }

    @Override
    public String toString() {
        return "Rook";
    }

    @Override
    public Set<Position> availablePosition(Board board) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public char toChar() {
        return this.getAlliance() == Alliance.WHITE ? 'R' : 'r';
    }
    
}
