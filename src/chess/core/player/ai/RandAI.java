package chess.core.player.ai;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.pieces.Piece;

public class RandAI {

    public static int[] nextStep(int time, Board board) {
        Piece[][] pieces = board.getBoard();
        ArrayList<Piece> myPieces = new ArrayList<>();
        for(int x = 0; x < Board.WIDTH; x++){
            for(int y = 0; y < Board.HEIGHT; y++){
                if(pieces[x][y] != null && pieces[x][y].getAlliance() == board.getTurn() && !pieces[x][y].availablePosition(board).isEmpty()){
                    myPieces.add(pieces[x][y]);
                }
            }
        }
        if(myPieces.isEmpty()){
            System.out.println("nowhere to move");
            return null;
        }
        Random rand = new Random();
        int r = rand.nextInt(myPieces.size());
        Piece chosenPiece = myPieces.get(r);
        Set<Position> poss=chosenPiece.availablePosition(board);
        int rPos = rand.nextInt(poss.size());
        Position chosenPos = null;
        for(Position p : poss){
            if(rPos--==0){
                chosenPos = p;
            }
        }
        int[] ret  = new int[4];
        ret[0] = chosenPiece.getPosition().getX();
        ret[1] = chosenPiece.getPosition().getY();
        ret[2] = chosenPos.getX();
        ret[3] = chosenPos.getY();
        return ret;
    }
    
}
