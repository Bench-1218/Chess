package chess.core.player.ai;

import chess.core.board.Board;
import chess.core.player.Player;

public class AI implements Player{

    public static int[] nextStepAI(int algorithm, int time, Board board){
        switch(algorithm){
            case 0:
            return AlphaBeta.nextStep(time, board);
            case 1:
            return MonteCarloTree.nextStep(time, board);
            default:
            System.out.println("algorithem should be a number between 0 and 1");
            return null;
        }
        
    }
}
