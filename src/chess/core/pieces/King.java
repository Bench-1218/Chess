package chess.core.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.core.board.Board;
import chess.core.board.Position;
import chess.core.player.Player.Alliance;

public class King extends Piece{
    private final int[][] DIRECTION = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
    private static boolean castling_NoPieceBetween = true;
    private static boolean castling_NotInCheck = true;
    private static boolean castling_SafeRoute = true;
    private static boolean castling_NeverMoved = true;

    private boolean first = false; // whether King has taken the first step
    private boolean hasCastled = false;

    public King(Position position, Alliance alliance) {
        super(position, Type.KING, alliance);
    }

    private boolean noPieceBetween(int x1, int y1, int x2, int y2, Board board){
        boolean flag = true;
        Piece[][] pieces = board.getBoard();
        if(x1 == x2){
            if(y1 < y2){
                for(int y = y1 + 1; y < y2; y++){
                    if(pieces[x1][y] != null) flag = false;
                }
            }else{
                for(int y = y2 + 1; y < y1; y++){
                    if(pieces[x1][y] != null) flag = false;
                }
            }
        }else if(y1 == y2){
            if(x1 < x2){
                for(int x = x1 + 1; x < x2; x++){
                    if(pieces[x][y1] != null) flag = false;
                }
            }else{
                for(int x = x2 + 1; x < x1; x++){
                    if(pieces[x][y1] != null) flag = false;
                }
            }
        }else flag = false;
        // System.out.println("noPieceBetween");
        // System.out.println(flag); // debug
        return flag;
    }
    private boolean notInCheck(Board board){
        // System.out.println("notInCheck");
        // System.out.println(!board.isInCheck(this.getAlliance().alliance2char())); // debug
        return !board.isInCheck(this.getAlliance().alliance2char());
    }
    private boolean safeRoute(int x1, int y1, int x2, int y2, Board board){
        boolean flag = true;
        if(x1 == x2){
            if(y1 < y2){
                for(int y = y1 + 1; y <= y1 + 2; y++){
                    if(y > Board.HEIGHT - 1) return false;
                    if(board.isInDanger(x1, y, this.getAlliance())) flag = false;
                }
            }else{
                for(int y = y1 - 1; y >= y1 - 2; y--){
                    if(y < 0) return false;
                    if(board.isInDanger(x1, y, this.getAlliance())) flag = false;
                }
            }
        }else if(y1 == y2){
            if(x1 < x2){
                for(int x = x1 + 1; x <= x1 + 2; x++){
                    if(x > Board.WIDTH - 1) return false;
                    if(board.isInDanger(x, y1, this.getAlliance())) flag = false;
                }
            }else{
                for(int x = x1 - 1; x >= x1 - 2; x--){
                    if(x < 0) return false;
                    if(board.isInDanger(x, y1, this.getAlliance())) flag = false;
                }
            }
        }else flag = false;
        // System.out.println("safeRoute");
        // System.out.println(flag); // debug
        return flag;
    }

    private boolean neverMoved(int x2, int y2, Board board){
        Piece[][] pieces = board.getBoard();
        // System.out.println("neverMoved");
        // System.out.println(!this.first && !((Rook)pieces[x2][y2]).getFirst()); // debug
        return !this.first && !((Rook)pieces[x2][y2]).getFirst();
    }

    public static void setCastlingRule(boolean b1, boolean b2, boolean b3, boolean b4){
        castling_NoPieceBetween = b1;
        castling_NotInCheck = b2;
        castling_SafeRoute = b3;
        castling_NeverMoved = b4;
    }

    @Override
    public void move(int x2, int y2, Board board) {
        Piece[][] pieces = board.getBoard();
        Position  pos = this.getPosition();
        int x1 = pos.getX();
        int y1 = pos.getY();
        if(x2 - x1 == 2){
            Piece p = null;
            for(int x = x1 + 1; x < Board.WIDTH; x++){
                if(pieces[x][y1] != null && pieces[x][y1].getAlliance() == this.getAlliance()){
                    p = pieces[x][y1];
                }
            }
            p.move(x1 + 1, y1, board);
        }else if(x2 - x1 == -2){
            Piece p = null;
            for(int x = x1 -1; x >= 0; x--){
                if(pieces[x][y1] != null && pieces[x][y1].getAlliance() == this.getAlliance()){
                    p = pieces[x][y1];
                }
            }
            p.move(x1 - 1, y1, board);
        }else if(y2 - y1 == 2){
            Piece p = null;
            for(int y = y1 + 1; y < Board.HEIGHT; y++){
                if(pieces[x1][y] != null && pieces[x1][y].getAlliance() == this.getAlliance()){
                    p = pieces[x1][y];
                }
            }
            p.move(x1, y1 + 1, board);
        }else if(y2 - y1 == -2){
            Piece p = null;
            for(int y = y1 - 1; y >= 0; y--){
                if(pieces[x1][y] != null && pieces[x1][y].getAlliance() == this.getAlliance()){
                    p = pieces[x1][y];
                }
            }
            p.move(x1, y1 - 1, board);
        }

        super.move(x2, y2, board);
        first = true;
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
        if(!hasCastled){
            for(int x1 = 0; x1 < Board.WIDTH; x1++){
                if(pieces[x1][y] != null && pieces[x1][y].getType() == Type.ROOK && pieces[x1][y].getAlliance() == this.getAlliance()){
                    if((!castling_NoPieceBetween || noPieceBetween(x, y, x1, y, board)) && (!castling_NotInCheck || notInCheck(board)) &&
                        (!castling_SafeRoute || safeRoute(x, y, x1, y, board)) && (!castling_NeverMoved || neverMoved(x1, y, board))){
                            if(x1 > x){
                                ps.add(new Position(x + 2, y));
                            }else{
                                ps.add(new Position(x - 2, y));
                            }
                        }
                }
            }
            for(int y1 = 0; y1 < Board.WIDTH; y1++){
                if(pieces[x][y1] != null && pieces[x][y1].getType() == Type.ROOK && pieces[x][y1].getAlliance() == this.getAlliance()){
                    if((!castling_NoPieceBetween || noPieceBetween(x, y, x, y1, board)) && (!castling_NotInCheck || notInCheck(board)) &&
                        (!castling_SafeRoute || safeRoute(x, y, x, y1, board)) && (!castling_NeverMoved || neverMoved(x, y1, board))){
                            if(y1 > y){
                                ps.add(new Position(x, y + 2));
                            }else{
                                ps.add(new Position(x, y - 2));
                            }
                        }
                }
            }
        }
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

    @Override
    public void nextTurn() {
        
    }

    @Override
    public Set<Position> attackPosition(Board board) {
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
        return ps;
    }
    
}
