package chess.core.board.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class Bishop extends Piece{
    private final int[][] DIRECTION = {{1,1},{1,-1},{-1,1},{-1,-1}};
    public Bishop(Position position, Type type, Alliance alliance) {
        super(position, type, alliance);
    }

    @Override
    public Set<Position> availablePosition(Board board) {
        Set<Position> ps = new HashSet<>();
        char[][] charBoard = board.getCharBoard();
        Position p = this.getPosition();
        int x = p.getX();
        int y = p.getY();
        for(int i = 0; i < 4; i++){
            for(int s = 0;; s++){
                int x1 = x + s*DIRECTION[i][0];
                int y1 = y + s*DIRECTION[i][1];
                if(Board.isOutOfBound(x1, y1) || board[x1][y1].getAlliance() != this.getAlliance()) break;
                if(charBoard[x1][y1] == 0){
                    ps.add(new Position(x1, y1));
                }
            }
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "Bishop";
    }

    @Override
    public char toChar() {
        return ? this.getAlliance() == Alliance.WHITE: 
    }
    
}
