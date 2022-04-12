package chess.core.player.ai;

import java.util.ArrayList;

import chess.core.board.Board;
import chess.core.board.Move;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class AlphaBeta {
    private static final double[][] pawnEvalWhite = {
            { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
            { 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0 },
            { 1.0, 1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0 },
            { 0.5, 0.5, 1.0, 2.5, 2.5, 1.0, 0.5, 0.5 },
            { 0.0, 0.0, 0.0, 2.0, 2.0, 0.0, 0.0, 0.0 },
            { 0.5, -0.5, -1.0, 0.0, 0.0, -1.0, -0.5, 0.5 },
            { 0.5, 1.0, 1.0, -2.0, -2.0, 1.0, 1.0, 0.5 },
            { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }
    };

    private static final double[][] pawnEvalBlack = reverseArray(pawnEvalWhite);

    private static final double[][] knightEval = {
            { -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 },
            { -4.0, -2.0, 0.0, 0.0, 0.0, 0.0, -2.0, -4.0 },
            { -3.0, 0.0, 1.0, 1.5, 1.5, 1.0, 0.0, -3.0 },
            { -3.0, 0.5, 1.5, 2.0, 2.0, 1.5, 0.5, -3.0 },
            { -3.0, 0.0, 1.5, 2.0, 2.0, 1.5, 0.0, -3.0 },
            { -3.0, 0.5, 1.0, 1.5, 1.5, 1.0, 0.5, -3.0 },
            { -4.0, -2.0, 0.0, 0.5, 0.5, 0.0, -2.0, -4.0 },
            { -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 }
    };

    private static final double[][] bishopEvalWhite = {
            { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0 },
            { -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0 },
            { -1.0, 0.0, 0.5, 1.0, 1.0, 0.5, 0.0, -1.0 },
            { -1.0, 0.5, 0.5, 1.0, 1.0, 0.5, 0.5, -1.0 },
            { -1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, -1.0 },
            { -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0 },
            { -1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5, -1.0 },
            { -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0 }
    };

    private static final double[][] bishopEvalBlack = reverseArray(bishopEvalWhite);

    private static final double[][] rookEvalWhite = {
            { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
            { 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5 },
            { -0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5 },
            { -0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5 },
            { -0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5 },
            { -0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5 },
            { -0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -0.5 },
            { 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0 }
    };

    private static final double[][] rookEvalBlack = reverseArray(rookEvalWhite);

    private static final double[][] evalQueen = {
            { -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0 },
            { -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -1.0 },
            { -1.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0 },
            { -0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5 },
            { 0.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, -0.5 },
            { -1.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.0, -1.0 },
            { -1.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0, -1.0 },
            { -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0 }
    };

    private static final double[][] kingEvalWhite = {
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
            { -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
            { -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0 },
            { -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0 },
            { 2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0 },
            { 2.0, 3.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0 }
    };

    private static final double[][] kingEvalBlack = reverseArray(kingEvalWhite);
    
    public static final double[][] reverseArray(final double[][] a) {
        double[][] newA = new double[8][8];
        for (int i = 0; i < 8; i++) {
            newA[i] = a[7 - i];
        }
        return newA;
    }

    public static char[][] reverseArray(final char[][] a){
        char[][] newA = new char[8][8];
        for(int i = 0; i < 8; i ++){
            newA[i] = a[7 - i];
        }
        return newA;
    }

    public static double getPieceValue(char piece, int x, int y) {
        boolean isWhite;
        if (piece >= 'a') {
            isWhite = false;
        } else {
            isWhite = true;
            piece += 32;
        }
        if (piece == 'p'){
            return 10 + (isWhite ? pawnEvalWhite[y][x] : pawnEvalBlack[y][x]);
        }else if(piece =='r') {
            return 50 + (isWhite ? rookEvalWhite[y][x] : rookEvalBlack[y][x]);
        }else if(piece =='n') {
            return 30 + knightEval[y][x];
        }else if(piece =='b') {
            return 30 + (isWhite ? bishopEvalWhite[y][x] : bishopEvalBlack[y][x]);
        }else if(piece =='q') {
            return 90 + evalQueen[y][x];
        }else if(piece =='k') {
            return 900 + (isWhite ? kingEvalWhite[y][x] : kingEvalBlack[y][x]);
        }
        return 0;
    }

    public static double evaluate(Board board){
        return evaluate(board, board.getTurn());
    }

    public static double evaluate(Board board, Alliance alli){
        char[][] charBoard = board.getCharBoard();
        double ret = 0;
        if(alli == Alliance.BLACK) {
            charBoard = reverseArray(charBoard);
        }
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                ret += getPieceValue(charBoard[x][y], x, y);
            }
        }
        return ret;
    }


    public static double maxValue(Board board, double alpha, double beta, int depth){
        if(depth == 0){
            return evaluate(board);
        }
        double clf = Double.MIN_VALUE;
        for(Move m : board.getAvailableMoves()){
            board.movePiece(m);
            clf = Math.max(clf, minValue(board, alpha, beta, depth - 1));
            board.lastStep();
            if(clf >= beta){
                return clf;
            }
            alpha = Math.max(alpha, clf);
        }
        return clf;
    }
    public static double minValue(Board board, double alpha, double beta, int depth){
        if(depth == 0){
            return evaluate(board);
        }
        double clf = Double.MAX_VALUE;
        for(Move m : board.getAvailableMoves()){
            board.movePiece(m);
            clf = Math.min(clf, maxValue(board, alpha, beta, depth - 1));
            board.lastStep();
            if(clf <= alpha){
                return clf;
            }
            beta = Math.min(beta, clf);
        }
        return clf;
    }


    public static int[] nextStep(int depth, Board board) {
        ArrayList<Move> moves = board.getAvailableMoves();
        double max = Double.MIN_VALUE;
        Move chooseMove = null;
        for(Move m : moves){
            board.movePiece(m);
            if(maxValue(board, Double.MIN_VALUE, Double.MAX_VALUE, depth - 1) > max){
                chooseMove = m;
            }
            board.lastStep();
        }
        Position p1 = chooseMove.getP1();
        Position p2 = chooseMove.getP2();
        int[] ret = new int[4];
        ret[0] = p1.getX();
        ret[1] = p1.getY();
        ret[2] = p2.getX();
        ret[3] = p2.getY();
        return ret;
    }

    
}
