package chess.debug;

import java.util.Scanner;

import chess.core.board.Board;

public class Debug {
    Board board = new Board();

    public void run() {
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
        char[][] charBoard = board.getCharBoard();
        System.out.printf("round %d: %s\n", board.getRound(), board.getTurn());
        for(int y = 0; y < Board.HEIGHT; y++){
            for(int x = 0; x < Board.WIDTH; x++){
                System.out.print(charBoard[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
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
                    System.out.print(available ? ' ' : charBoard[x1][y1]);
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
