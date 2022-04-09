package chess.core.board.pieces;

import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

class Knight extends Piece{

    public Knight(Position position, Alliance alliance) {
        super(position, Type.KNIGHT, alliance);
    }

    @Override
    public Set<Position> availablePosition(Board board) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return "Knight";
    }

    @Override
    public char toChar() {
        return this.getAlliance() == Alliance.WHITE?'N':'n';
    }

}