package chess.core.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessBoard extends JPanel {

    BufferedImage chessBoard;


    public ChessBoard() {
        try {
            chessBoard = ImageIO.read(new File("./src/chess/core/gui/image/chessboard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //TODO 这貌似是移动布局器就会调用，但跟我想点击就调用不太一样
    // 现在是移动和点击都可以了
    @Override
    protected void paintComponent(Graphics g) {
        PieceImage pieceImage = new PieceImage();
        Move move = new Move(this);
        BufferedImage[][] piecesImage = pieceImage.pieceGetImage(move.pieces);
        move.piecesMove();
/**棋子画的时候是根据bufferedImage的二维数组来画的，你得保证上面会改
 * 虽然我不明白为什么，但是上面四行不要动，就能跑了*/

        System.out.println("重画被调用了");
        super.paintComponent(g);


        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(chessBoard, 0, 0, getWidth(), getHeight(), null);


        System.out.println("棋子重画被调用了");

        double gridLength = 0.92*getHeight()/8;
        double xStart = 0.055*getHeight();
        double yStart = 0.043*getHeight();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piecesImage[i][j] != null) {
                    g.drawImage(piecesImage[i][j], (int)(xStart+i *gridLength),
                            (int)(yStart+j *gridLength),
                            getHeight()/12,getHeight()/11, null);
                }
            }

        }
    }

}

