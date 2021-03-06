package chess.core;

import java.io.File;

import chess.core.board.Board;
import chess.core.network.Client;
import chess.core.network.Server;
import chess.core.pieces.King;
import chess.core.player.ai.AI;

public class Control {
    private static Board board = new Board();
    private static final Control control = new Control();
    @Deprecated
    private static Server server;
    @Deprecated
    private static Client client;

    private Control(){}

    public static Control getControl(){ // singletonization
        return control;
    }

    // MULTIPLAYER MODULE
    @Deprecated
    public static void createServer(int port){
        // create a Server bounded to a spesific port
        server = new Server(port);
    }
    @Deprecated
    public static void createServer(){
        // create a default Server
        server = new Server(8088);
    }
    @Deprecated
    public static void closeServer(){
        server.close();
    }
    @Deprecated
    public static void connectToServer(String ip, int port){
        // connect to Server with ip and port
        client = new Client(ip, port);
    }
    @Deprecated
    public static void connectToServer(){
        // connect to the default Server
        client = new Client("localhost", 8088);
    }

    // SAVE AND LOAD MODULE
    public static void saveGame(String filePath, int leftTime){
        // pass leftTime to save how much time left for this turn
        // if this file has exited, it will be covered by the new file
        board.saveGame(filePath, leftTime);
    }
    public static void saveGame(String filePath){
        // saveGame default version
        board.saveGame(filePath, 30);
    }

    public static boolean loadGame(String filePath){
        // please ensure that the file exists
        return board.loadGame(filePath);
    }
    public static int loadFormatBoard(File f){
        // return 0 if successfully load the board
        // return 101,102,103,104 for errors
        return board.loadFormatBoard(f);
    }
    public static int getLeftTime(){
        // how much time left for you in the last game
        // use after loadGame
        return board.getStatus().getLastLeftTime();
    }

    // BOARD CONTROL
    public static void lastStep(){
        board.lastStep();
    }
    public static char[][] getCharBoard(){
        // x means no piece on the grid
        // BKNPQR and bknpqr are abbreviations. Notice: N indicates Knight and K indicated King
        // the uppercase means WHITE and the lowercase means BLACK
        return board.getCharBoard();
    }
    public static int[][] getAvailablePositions(int x, int y){
        // return n by 2 matrix or null
        // n: n available positions
        // 2: coordinates of the position
        return board.getAvailablePositions(x, y);
    }
    public static boolean isAvailable(int x1, int y1, int x2, int y2){
        // whether it is valid to move the piece at (x1,y1) to (x2,y2) in this turn
        return board.isAvailable(x1, y1, x2, y2);
    }
    public static boolean movePiece(int x1, int y1, int x2, int y2){
        // alliance try to move the piece in (x1, y1) to (x2, y2)
        // if the operation is valid, return true; otherwise return false
        return board.movePiece(x1, y1, x2, y2);
    }
    public static boolean isOutOfBound(int x, int y){
        // whether the position (x, y) is out of boundary
        return Board.isOutOfBound(x, y);
    }
    public static char ifWin(){
        // output "W" or "B" to represent the winner, White or Black
        // output "D" to represent the drawn game
        // output "N" to represent that nothing happens
        return board.ifWin();
    }
    public static char getTurn(){
        // If it is turn for white, then output 'W'
        // If it is turn for black, then output 'B'
        // If some problem is encountered, then output 'N'. Please contact with JAKE_XU when this happens.
        return board.getTurnChar();

    }
    public static void nextTurn(){
        // nextTurn
        board.nextTurn();
    }
    public static int getRound(){
        // return the number of round
        return board.getRound();
    }
    public static void setPiece(int x, int y, char type){
        // change the piece at (x, y) to what you want
        // type can be BKNPQRbknpqrx where x represent null
        // in another word input 'x' means remove the chess on (x, y)
        // this function is meant to provide alternatives for Promotion of Pawn
        board.setPiece(x, y, type);
    }
    public static void setCastlingRule(boolean noPieceBetween, boolean notInCheck,
                                       boolean safeRoute, boolean neverMoved){
        // if enable the corresponding restrictions (default: all enabled)
        // note: SafeRoute means the king does not pass through or land on any square attacked by an enemy piece
        King.setCastlingRule(noPieceBetween, notInCheck, safeRoute, neverMoved);
    }
    public static boolean isInCheck(char alliance){
        // alliance can be 'W'(White) or 'B'(Black) to check if the player is in check
        return board.isInCheck(alliance);
    }

    // AI MODULE
    public static int[] nextStepAI(int algorithm, int depth){
        // algorithm 0: random
        //           1: AlphaBeta
        // for AlphaBeta, the deeper the stronger (if using random, input any integer)
        // output 4 numbers: {x1, y1, x2, y2} meaning that move chess(x1,y1) to (x2,y2)
        return AI.nextStepAI(algorithm, depth, board);
    }
    public static void restart(){
        // restart the game
        board.reset();
    }
}
