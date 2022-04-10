package chess.debug;

import java.util.Scanner;

import chess.core.board.Board;

public class Debug {
    public void run() {
        Scanner sc = new Scanner(System.in);
        Board board = new Board();

        while(true){
            int code = sc.nextInt();
            if(code == 0) board.printBoard();
            else if(code == 1){
                int x = sc.nextInt();
                int y = sc.nextInt();
                board.printAvailablePositions(x, y);
            }else if(code == 2){
                board.movePiece(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                board.printBoard();
                System.out.println();
            }else break;
        }
        sc.close();
    }
}
