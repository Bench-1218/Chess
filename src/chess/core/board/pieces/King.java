package chess.core.board.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class King extends Piece{
    private final int[][] DIRECTION = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
    public King(Position position, Alliance alliance) {
        super(position, Type.KING, alliance);
    }

    @Override
    public Set<Position> availablePosition(Board board) {
        Set<Position> ps = new HashSet<>();
        Piece[][] pieces = board.getBoard();
        Position p = this.getPosition();
        int x = p.getX();
        int y = p.getY();
        for(int i = 0; i < DIRECTION.length; i++){
            int x1 = x + DIRECTION[i][0];
            int y1 = y + DIRECTION[i][1];
            if(Board.isOutOfBound(x1, y1)) continue;
            if(pieces[x1][y1] == null || pieces[x1][y1].getAlliance() != this.getAlliance()){
                ps.add(new Position(x1, y1));
            }
        }
        // TODO
        return ps;
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
