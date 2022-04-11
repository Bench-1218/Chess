package chess.debug;

import java.util.Scanner;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import chess.core.board.Board;

public class Debug {
    Board board = new Board();
    public static final String WHITE_FG =  "\u001B[38;5;255m";
    public static final String BLACK_FG = "\u001B[38;5;232m";
    public static final String WHITE_BG = "\u001B[48;5;203m";
    public static final String GRAY_BG = "\u001B[48;5;171m";
    public static final String BLACK_BG = "\u001B[48;5;131m";
    public static final String RESET = "\u001B[0m";

    public void run() {
        rich();
    }

    public void rich(){
        // WASD to move the curser
        // J to select a chess. press again to determine its destination
        // 3 to saveGame
        // 4 to loadGame
        new KeyListener();
    }

    public void poor(){
        // without UI
        // 0: display the board
        // 1 x1 y1: display availablePosition for the chess at (x1, y1)
        // 2 x1 y1 x2 y2: move chess(x1, y1) to chess(x2, y2)
        Scanner sc = new Scanner(System.in);
        while(true){
            int code = sc.nextInt();
            if(code == 0) printBoard();
            else if(code == 1){
                int x = sc.nextInt();
                int y = sc.nextInt();
                printAvailablePositions(x, y);
            }else if(code == 2){
                board.movePiece(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                printBoard();
                System.out.println();
            }else break;
        }
        sc.close();
    }


    public void printBoard() {
        printBoard(-1, -1);
    }

    public void printBoard(int x1, int y1){
        char[][] charBoard = board.getCharBoard();
        System.out.printf("round %d: %s\n", board.getRound(), board.getTurn());
        for(int y = 0; y < Board.HEIGHT; y++){
            for(int x = 0; x < Board.WIDTH; x++){
                String bg;
                if((x+y)%2 == 0){
                    bg = WHITE_BG;
                }else{
                    bg = BLACK_BG;
                }
                if(x == x1 && y == y1){
                    bg = GRAY_BG;
                }
                System.out.print(bg + " ");
                if(charBoard[x][y] == 'x'){
                    System.out.print(' ');
                }else{
                    if(charBoard[x][y] >= 'a') System.out.print(BLACK_FG);
                    else System.out.print(WHITE_FG);
                    System.out.print(charBoard[x][y]);
                }
                System.out.print(bg + " " + RESET);
            }
            System.out.println();
        }
        System.out.println();       
    }

    // private char char2pic(char c){
    //     switch(c){
    //         case 'B': case 'b':
    //         return '♗';
    //         case 'K': case 'k':
    //         return '♔';
    //         case 'N': case 'n':
    //         return '♘';
    //         case 'P': case 'p':
    //         return '♙';
    //         case 'Q': case 'q':
    //         return '♕';
    //         case 'R': case 'r':
    //         return '♖';
    //         case 'x':
    //         return ' ';
    //         default:
    //         return ' ';
    //     }
    // }
    public void printAvailablePositions(int x, int y) {
        int[][] ps = board.getAvailablePositions(x, y);
        char[][] charBoard = board.getCharBoard();
        if(ps == null){
            System.out.println("No available position");
        }else{
            for(int y1 = 0; y1 < Board.HEIGHT; y1++){
                for(int x1 = 0; x1 < Board.WIDTH; x1++){
                    boolean available = false;
                    for(int n = 0; n < ps.length; n++){
                        if(x1 == ps[n][0] && y1 == ps[n][1]){
                            available = true;
                            break;
                        }
                    }
                    String bg;
                    if((x1+y1)%2 == 0){
                        bg = WHITE_BG;
                    }else{
                        bg = BLACK_BG;
                    }
                    if((x1 == x && y1 == y) || available){
                        bg = GRAY_BG;
                    }
                    System.out.print(bg + " ");
                    if(charBoard[x1][y1] == 'x'){
                        System.out.print(' ');
                    }else{
                        if(charBoard[x1][y1] >= 'a') System.out.print(BLACK_FG);
                        else System.out.print(WHITE_FG);
                        System.out.print(charBoard[x1][y1]);
                    }
                    System.out.print(bg + " " + RESET);
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    class KeyListener extends Frame{
        // see rich
        private int x = 0;
        private int y = 0;
        private boolean select;
        private int x1;
        private int y1;
        public KeyListener(){
            this.setVisible(true);
            this.setBounds(10,10,100,100);
            this.addKeyListener(new KeyAdapter(){
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch(keyCode){
                        case 87: // W
                            if(y > 0){
                                y -= 1;
                                printBoard(x, y);
                            }
                            break;
                        case 83: // S
                            if(y < Board.HEIGHT - 1){
                                y += 1;
                                printBoard(x, y);
                            }
                            break;
                        case 65: // A
                            if(x > 0){
                                x -= 1;
                                printBoard(x, y);;
                            }
                            break;
                        case 68: // D
                            if(x < Board.WIDTH - 1){
                                x += 1;
                                printBoard(x, y);
                            }
                            break;
                        case 74: // J
                            if(!select){
                                select = true;
                                x1 = x;
                                y1 = y;
                                printAvailablePositions(x, y);
                            }else{
                                select = false;
                                board.movePiece(x1, y1, x, y);
                                printBoard(x, y);
                            }
                            break;
                        case 51: // 3
                            board.saveGame("C:/myFolder/codes/java/game1.dat", 18);
                            System.out.println("save");
                            break;
                        case 52: // 4
                            board.loadGame("C:/myFolder/codes/java/game1.dat");
                            System.out.println("load");
                            printBoard(x, y);
                            break;

                    }
                }
            });
        }
    }
}
