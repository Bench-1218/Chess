package chess.core.gui;

import chess.core.Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class Move {

    ChessBoard chessBoard;
    PieceImage pieceImage = new PieceImage();
    List<Integer> twoPoint = new ArrayList<>();


    char[][] pieces = Control.getCharBoard();

    int x;
    int y;

    public Move(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    //TODO 我希望每次调用这个方法，能获取坐标，并导致char表改变，下一步希望根据char表重画
    public void piecesMove() {
        //int chessBoardHeight = ChessMain.frameHeight;
        //int gridLength = chessBoardHeight / 8;

        chessBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int chessBoardHeight = ChessMain.frameHeight;
                int gridLength = chessBoardHeight / 8;
                x = e.getX() / gridLength;
                y = e.getY() / gridLength;
                piecesTwoPoint();
                System.out.println("[" + x + "," + y + "]");
                System.out.println(e.getPoint());
                System.out.println(twoPoint);


                if (twoPoint.size() >= 3) {
                    int x1 = twoPoint.get(0);
                    int y1 = twoPoint.get(1);
                    int x2 = twoPoint.get(2);
                    int y2 = twoPoint.get(3);


                    if (Control.movePiece(x1, y1, x2, y2)) {
                        Control.setPiece(x2, y2, pieces[x1][y1]);
                        Control.setPiece(x1, y1, 'x');
                        pieces = Control.getCharBoard();
                    } else {

                    }
                    System.out.println("x1=" + x1 + ",y1=" + y1 + ",x2=" + x2 + ",y2=" + y2);
                 /*   System.out.println(pieces[x1][y1]);
                    System.out.println(pieces[x2][y2]);*/
                }

                pieceImage.pieceGetImage(pieces);
                chessBoard.repaint();
            }
        });
    }

    //这个方法是供上一个方法获取坐标的
    private void piecesTwoPoint() {

        if (twoPoint.size() >= 4) {
            twoPoint.clear();
        }
        //选择另一个己方棋子，就替换掉 如果是敌方棋子或者正确的空白地方，就添加到第二个坐标
        //如果存在第一组坐标，第一个一定是棋子了
        if (twoPoint.size() == 2) {

            if (Control.getTurn() == 'W') {
                if(pieces[x][y]>=65&&pieces[x][y]<=90){
                    twoPoint.clear();
                }
                twoPoint.add(x);
                twoPoint.add(y);
            } else if (Control.getTurn() == 'B') {
                if(pieces[x][y]>=97&&pieces[x][y]<=119){
                    twoPoint.clear();
                }
                twoPoint.add(x);
                twoPoint.add(y);
            }
        }

        if (twoPoint.size() == 0 && pieces[x][y] != 'x') {
            twoPoint.add(x);
            twoPoint.add(y);
        }
    }

    public char[][] getPieces() {
        return pieces;
    }
}

