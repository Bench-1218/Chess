package chess.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PieceImage extends JComponent {
    BufferedImage[][] piecesImage = new BufferedImage[8][8];


//传入一个最新的char表代表棋盘，并附上棋子的照片,返回图像的二维数组
    public BufferedImage[][] pieceGetImage(char[][] pieces) {
        char c;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                c = pieces[i][j];
                if (true) {
                    if (c == 'N') {
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/N1.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(c=='p'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/1p.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='k'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/1k.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='n'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/1n.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='P'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/P1.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='K') {
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/K1.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='Q'){
                            try {
                                piecesImage[i][j] = ImageIO.read(
                                        new File("./src/chess/gui/image/Q1.png"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }else if (c=='q'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/1q.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='R'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/R1.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='r'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/1r.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='B'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/B1.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if (c=='b'){
                        try {
                            piecesImage[i][j] = ImageIO.read(
                                    new File("./src/chess/gui/image/1b.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }

        }
        return piecesImage;
    }
}


