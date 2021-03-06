package chess.gui;

import chess.core.Control;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ChessBoard extends JPanel {

    int i = 0;

    //TODO： 可能AI的时间要和正常的分开计时
    // end窗口上的重置面板的时间无法恢复;
    int blackTime = 30;
    int whiteTime = 30;
    JLabel wTime = new JLabel();
    JLabel bTime = new JLabel();

    BufferedImage chessBoardBackground;
    BufferedImage rightBackground;
    BufferedImage possibleImage;
    boolean isBright = true;
    boolean isHint = false;
    boolean isPossible = false;


    //Move类
    int x;
    int y;
    PieceImage pieceImage = new PieceImage();
    static char[][] pieces = Control.getCharBoard();
    static List<Integer> twoPoint = new ArrayList<>();
    static List<Integer> twoPointWrong = new ArrayList<>();

    //基础设定
    Sound sound = new Sound();
    JButton changeRight = new JButton(new ImageIcon("./src/chess/gui/image/default.png"));
    JButton restart = new JButton(new ImageIcon("./src/chess/gui/image/restart.png"));
    Status status = new Status();
    Round round = new Round();
    JButton retract = new JButton(new ImageIcon("./src/chess/gui/image/retract.png"));
    JButton load = new JButton(new ImageIcon("./src/chess/gui/image/load.png"));
    JButton save = new JButton(new ImageIcon("./src/chess/gui/image/save.png"));
    Timer timer;
    JButton wrongLoad = new JButton(new ImageIcon("./src/chess/gui/image/wrongLoad.png"));
    JButton hint = new JButton(new ImageIcon("./src/chess/gui/image/prompt.png"));
    JButton possible = new JButton(new ImageIcon("./src/chess/gui/image/help.png"));


    public ChessBoard() {
        System.out.println("普通模块");
        basicSettings();

        this.setLayout(new ChessBoardLayout());
        this.add(changeRight);
        this.add(restart);
        this.add(status);
        this.add(round);
        this.add(retract);
        this.add(wTime);
        this.add(bTime);
        this.add(load);
        this.add(save);
        this.add(wrongLoad);
        this.add(hint);
        this.add(possible);


        this.addMouseListener(new MouseMove(this) {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("我真的点击了吗");
                int chessBoardHeight = FirstFrame.frameHeight;
                double gridLength = 0.874 * chessBoardHeight / 8;
                double xStart = 0.036 * chessBoardHeight;
                double yStart = 0.036 * chessBoardHeight;


                x = (int) ((e.getX() - xStart) / gridLength);
                y = (int) ((e.getY() - yStart) / gridLength);

                if (x >= 8) {
                    x = 7;
                }
                if (x < 0) {
                    x = 0;
                }
                if (y >= 8) {
                    y = 7;
                }
                if (y <= 0) {
                    y = 0;
                }

                piecesTwoPoint();
                piecesTwoPointWrong();


                if (twoPoint.size() >= 3) {
                    int x1 = twoPoint.get(0);
                    int y1 = twoPoint.get(1);
                    int x2 = twoPoint.get(2);
                    int y2 = twoPoint.get(3);


                    if (Control.movePiece(x1, y1, x2, y2)) {
                        if (Control.ifWin() != 'N') {
                            End end = new End(Control.ifWin(), chessBoard);
                        }

                        if (pieces[x1][y1] == 'p' && y2 == 7) {
                            Promotion promotion = new Promotion('p');
                            Control.setPiece(x2, y2, promotion.choose);
                            System.out.println(promotion.choose);

                        } else if (pieces[x1][y1] == 'P' && y2 == 0) {
                            Promotion promotion = new Promotion('P');
                            Control.setPiece(x2, y2, promotion.choose);

                        } /*else {
                            Control.setPiece(x2, y2, pieces[x1][y1]);
                        }
                        Control.setPiece(x1, y1, 'x');*/
                        pieces = Control.getCharBoard();
                        blackTime = 30;
                        whiteTime = 30;
                        sound.run();
                    } else {
                        System.out.println(twoPoint);
                        Message message = new Message('i');
                    }
                }

                ifPiecesMoveWrong();
                pieceImage.pieceGetImage(pieces);
                repaint();


                if (Control.isInCheck('W') && Control.getTurn() == 'W' && twoPoint.size() == 4) {
                    Message message = new Message('C');
                }
                if (Control.isInCheck('B') && Control.getTurn() == 'B' && twoPoint.size() == 4) {
                    Message message = new Message('c');
                }

            }
        });

    }

    private void piecesTwoPoint() {

        if (twoPoint.size() >= 4) {
            twoPoint.clear();
        }
        //选择另一个己方棋子，就替换掉 如果是敌方棋子或者正确的空白地方，就添加到第二个坐标
        //如果存在第一组坐标，第一个一定是棋子了
        if (twoPoint.size() == 2) {
            if (Control.getTurn() == 'W') {
                if (pieces[x][y] >= 65 && pieces[x][y] <= 90) {
                    isPossible=false;
                    twoPoint.clear();
                }
                twoPoint.add(x);
                twoPoint.add(y);
            } else if (Control.getTurn() == 'B') {
                if (pieces[x][y] >= 97 && pieces[x][y] <= 119) {
                    isPossible=false;
                    twoPoint.clear();
                }
                twoPoint.add(x);
                twoPoint.add(y);
            }
        }

        if (twoPoint.size() == 0 && pieces[x][y] != 'x') {
            if (Control.getTurn() == 'W') {
                if (pieces[x][y] >= 65 && pieces[x][y] <= 90) {
                    twoPoint.add(x);
                    twoPoint.add(y);
                }
            } else if (Control.getTurn() == 'B') {
                if (pieces[x][y] >= 97 && pieces[x][y] <= 119) {
                    twoPoint.add(x);
                    twoPoint.add(y);
                }
            }
        }
    }

    private void piecesTwoPointWrong() {
        if (twoPointWrong.size() == 4) {
            twoPointWrong.clear();
        }

        if (twoPointWrong.size() == 2) {
            if (pieces[x][y] == 'x') {
                twoPointWrong.clear();
                twoPointWrong.add(x);
                twoPointWrong.add(y);
            } else if (Control.getTurn() == 'B') {
                if (pieces[x][y] <= 90 && pieces[x][y] >= 65) {
                    twoPointWrong.clear();
                }
                twoPointWrong.add(x);
                twoPointWrong.add(y);
            } else if (Control.getTurn() == 'W') {
                if (pieces[x][y] <= 119 && pieces[x][y] >= 97) {
                    twoPointWrong.clear();
                }
                twoPointWrong.add(x);
                twoPointWrong.add(y);
            }
        }

        if (twoPointWrong.size() == 0 && twoPoint.size() == 0) {
            if (pieces[x][y] == 'x') {
                twoPointWrong.add(x);
                twoPointWrong.add(y);
            } else if (Control.getTurn() == 'B') {
                if (pieces[x][y] <= 90 && pieces[x][y] >= 65) {
                    twoPointWrong.add(x);
                    twoPointWrong.add(y);
                }
            } else if (Control.getTurn() == 'W') {
                if (pieces[x][y] <= 119 && pieces[x][y] >= 97) {
                    twoPointWrong.add(x);
                    twoPointWrong.add(y);
                }
            }
        }

    }

    public void ifPiecesMoveWrong() {

        if (twoPointWrong.size() == 2) {
            int tempX = twoPointWrong.get(0);
            int tempY = twoPointWrong.get(1);
            if (pieces[tempX][tempY] == 'x') {
                Message message = new Message('x');
            }
            if (Control.getTurn() == 'W') {
                if (pieces[tempX][tempY] <= 119 && pieces[tempX][tempY] >= 97) {
                    System.out.println(twoPoint);
                    System.out.println(twoPointWrong);
                    System.out.println(Control.getTurn());
                    Message message = new Message('W');
                }
            } else if (Control.getTurn() == 'B') {
                if (pieces[tempX][tempY] <= 90 && pieces[tempX][tempY] >= 65) {
                    System.out.println(twoPoint);
                    System.out.println(twoPointWrong);
                    System.out.println(Control.getTurn());
                    Message message = new Message('B');
                }
            }
        }
    }

    //AI
    public ChessBoard(int i) {
        this.i = i;
        System.out.println("为什么构造器在被一直调用");
        basicSettings();

        this.setLayout(new ChessBoardLayout());
        this.add(changeRight);
        this.add(restart);
        this.add(status);
        this.add(round);
        this.add(retract);
        this.add(wTime);
        this.add(bTime);
        this.add(load);
        this.add(save);
        this.add(wrongLoad);
        this.add(hint);
        this.add(possible);

        this.addMouseListener(new MouseMove(this) {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("我真的点击了吗");
                if (Control.getTurn() == 'W') {
                    int chessBoardHeight = FirstFrame.frameHeight;
                    double gridLength = 0.874 * chessBoardHeight / 8;
                    double xStart = 0.036 * chessBoardHeight;
                    double yStart = 0.036 * chessBoardHeight;


                    x = (int) ((e.getX() - xStart) / gridLength);
                    y = (int) ((e.getY() - yStart) / gridLength);

                    if (x >= 8) {
                        x = 7;
                    }
                    if (x < 0) {
                        x = 0;
                    }
                    if (y >= 8) {
                        y = 7;
                    }
                    if (y <= 0) {
                        y = 0;
                    }

                    piecesTwoPoint();
                    piecesTwoPointWrong();
                    if (twoPoint.size() >= 3) {
                        int x1 = twoPoint.get(0);
                        int y1 = twoPoint.get(1);
                        int x2 = twoPoint.get(2);
                        int y2 = twoPoint.get(3);


                        if (Control.movePiece(x1, y1, x2, y2)) {
                            if (Control.ifWin() != 'N') {
                                End end = new End(Control.ifWin(), chessBoard);
                            }
                            if (pieces[x1][y1] == 'p' && y2 == 7) {

                                Promotion promotion = new Promotion('p');
                                Control.setPiece(x2, y2, promotion.choose);
                                System.out.println(promotion.choose);

                            } else if (pieces[x1][y1] == 'P' && y2 == 0) {
                                Promotion promotion = new Promotion('P');
                                Control.setPiece(x2, y2, promotion.choose);

                            } /*else {
                                Control.setPiece(x2, y2, pieces[x1][y1]);
                            }
                            Control.setPiece(x1, y1, 'x');*/
                            pieces = Control.getCharBoard();
                            blackTime = 30;
                            whiteTime = 30;
                            sound.run();
                        } else {
                            Message message = new Message('i');
                        }
                    }
                    ifPiecesMoveWrong();
                }
                moveAI(chessBoard);

                pieceImage.pieceGetImage(pieces);
                repaint();

                if (Control.isInCheck('W') && Control.getTurn() == 'W' && twoPoint.size() == 4) {
                    Message message = new Message('C');
                }
                if (Control.isInCheck('B') && Control.getTurn() == 'B' && twoPoint.size() == 4) {
                    Message message = new Message('c');
                }
            }
        });
    }

    private void moveAI(ChessBoard chessBoard) {
        if (Control.getTurn() == 'B') {
            int[] coordinateAI = new int[4];
            System.out.println("黑色AI被调用了");
            {
                System.out.println("AI被调用了---------");
                if (Rule.choose == 0) {
                    coordinateAI = Control.nextStepAI(0, 1);
                    System.out.println("0");
                } else if (Rule.choose == 1) {
                    coordinateAI = Control.nextStepAI(1, 1);
                    System.out.println("1");
                } else if (Rule.choose == 2) {
                    coordinateAI = Control.nextStepAI(1, 2);
                    System.out.println("2");
                } else if (Rule.choose == 3) {
                    coordinateAI = Control.nextStepAI(1, 3);
                    System.out.println("3");
                }

                int x1 = coordinateAI[0];
                x = coordinateAI[0];
                int y1 = coordinateAI[1];
                y = coordinateAI[1];
                piecesTwoPoint();
                piecesTwoPointWrong();
                int x2 = coordinateAI[2];
                x = coordinateAI[2];
                int y2 = coordinateAI[3];
                y = coordinateAI[3];
                piecesTwoPoint();
                piecesTwoPointWrong();

                System.out.println(x1 + "|" + y1 + "|" + x2 + "|" + y2);

                if (Control.movePiece(x1, y1, x2, y2)) {
                    if (Control.ifWin() != 'N') {
                        End end = new End(Control.ifWin(), chessBoard);
                    }
                    if (pieces[x1][y1] == 'p' && y2 == 7) {
                        Promotion promotion = new Promotion('p');
                        Control.setPiece(x2, y2, promotion.choose);
                        System.out.println(promotion.choose);
                    } else if (pieces[x1][y1] == 'P' && y2 == 0) {
                        Promotion promotion = new Promotion('P');
                        Control.setPiece(x2, y2, promotion.choose);

                    } else {
                        Control.setPiece(x2, y2, pieces[x1][y1]);
                    }
                    Control.setPiece(x1, y1, 'x');
                    pieces = Control.getCharBoard();
                    blackTime = 30;
                    whiteTime = 30;
                    sound.run();
                }
            }
            pieceImage.pieceGetImage(pieces);
            repaint();
        }
    }

    private void basicSettings() {

        try {
            chessBoardBackground = ImageIO.read(new File("./src/chess/gui/image/chessboard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //换皮肤

        changeRight.addActionListener(e -> {
            /*System.out.println("换皮肤被点击了");*/
            isBright = !isBright;
            System.out.println(isBright);
            repaint();
        });

        //重置
        restart.setText("restart the game");
        restart.addActionListener(e -> {
            /*System.out.println("重置被点击了");*/
            twoPoint.clear();
            Control.restart();
            pieces = Control.getCharBoard();
            repaint();
            blackTime = 30;
            whiteTime = 30;
        });

        //撤回
        retract.setText("last step");
        retract.addActionListener(e -> {
            if(i==1){
                Control.lastStep();
                Control.lastStep();
            }else if(i==0){
                Control.lastStep();
            }
            pieces = Control.getCharBoard();
            repaint();
            blackTime = 30;
            whiteTime = 30;
        });

        timer = new Timer(1000, e -> {
            if (Control.getTurn() == 'W') {
                whiteTime--;
            } else if (Control.getTurn() == 'B') {
                blackTime--;
            }
            wTime.setText("WHITE left time: " + whiteTime + "s");
            bTime.setText("BLACK left time: " + blackTime + "s");

            if (blackTime == 0) {
                Control.nextTurn();
                isHint=false;
                isPossible=false;
                repaint();
                blackTime = 30;
            } else if (whiteTime == 0) {
                Control.nextTurn();
                System.out.println(Control.getTurn());
                isHint=false;
                isPossible=false;
                if(i==1){
                    moveAI(this);
                }
                repaint();
                whiteTime = 30;
            }

        });

        //TODO： 不能放在这里，要进入第二个窗口才能开始计时
        timer.start();

        //加载
        load.addActionListener(e -> {
            System.out.println("加载");
            JFileChooser loadChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("棋盘", "dat");
            loadChooser.setFileFilter(filter);
            int ret = loadChooser.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = loadChooser.getSelectedFile();
                Control.loadGame(file.getPath());
            }
            pieces = Control.getCharBoard();
            if (Control.getTurn() == 'W') {
                whiteTime = Control.getLeftTime();
                blackTime = 30;
            } else if (Control.getTurn() == 'B') {
                blackTime = Control.getLeftTime();
                whiteTime = 30;
            }
            repaint();
        });

        //储存
        save.addActionListener(e -> {
            System.out.println("点了");
            int time = 30;
            if (Control.getTurn() == 'W') {
                time = whiteTime;
            } else if (Control.getTurn() == 'B') {
                time = blackTime;
            }
            JFileChooser saveChooser = new JFileChooser();
            int ret = saveChooser.showSaveDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = saveChooser.getSelectedFile();
                Control.saveGame(file.getPath(), time);
            }
            System.out.println(time);
        });

        wrongLoad.setText("wrong load");
        wrongLoad.addActionListener(e -> {
            JFileChooser wrongLoadChooser = new JFileChooser();
            int ret = wrongLoadChooser.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = wrongLoadChooser.getSelectedFile();
                int number=Control.loadFormatBoard(file);
                if(number==101){
                    Message message=new Message('1');
                }else if(number==102){
                    Message message=new Message('2');
                }else if(number==103){
                    Message message=new Message('3');
                }else if(number==104){
                    System.out.println(104);
                    Message message=new Message('4');
                }
            }
        });

        hint.addActionListener(e -> {
            isHint = !isHint;
            isPossible = false;
            twoPoint.clear();
            repaint();
        });

        possible.addActionListener(e -> {
            isPossible = !isPossible;
            repaint();
        });


    }

    @Override
    protected void paintComponent(Graphics g) {


        PieceImage pieceImage = new PieceImage();
        /*System.out.println("重画被调用了");*/
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(chessBoardBackground, 0, 0, getHeight(), getHeight(), null);

        BufferedImage[][] piecesImage = pieceImage.pieceGetImage(pieces);
        /*System.out.println("棋子重画被调用了");*/

        double gridLength = 0.92 * getHeight() / 8;
        double xStart = 0.055 * getHeight();
        double yStart = 0.043 * getHeight();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (piecesImage[i][j] != null) {
                    g.drawImage(piecesImage[i][j], (int) (xStart + i * gridLength), (int) (yStart + j * gridLength), getHeight() / 12, getHeight() / 11, null);
                }
            }

        }


        int chessBoardHeight = FirstFrame.frameHeight;
        double xStart2 = 0.035 * chessBoardHeight;
        double yStart2 = 0.033 * chessBoardHeight;
        double gridLength2 = 0.8755 * chessBoardHeight / 8;

        //选中效果
        if (twoPoint.size() == 2) {
            int x = twoPoint.get(0);
            int y = twoPoint.get(1);

            if (Control.getTurn() == 'W') {
                if (pieces[x][y] >= 65 && pieces[x][y] <= 90) {
                    g.setColor(new Color(253, 24, 104));
                    g.drawOval((int) (xStart2 + x * gridLength2), (int) (yStart2 + y * gridLength2),
                            (int) gridLength2, (int) gridLength2);
                }

            } else if (Control.getTurn() == 'B') {
                if (pieces[x][y] >= 97 && pieces[x][y] <= 119) {
                    g.setColor(new Color(253, 24, 104));
                    g.drawOval((int) (xStart2 + x * gridLength2), (int) (yStart2 + y * gridLength2),
                            (int) gridLength2, (int) gridLength2);
                }
            }

        }

//右边背景图片
        if (isBright) {
            try {
                rightBackground = ImageIO.read(new File("./src/chess/gui/image/bright.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                rightBackground = ImageIO.read(new File("./src/chess/gui/image/dark.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(rightBackground, getHeight(), 0, this.getWidth() - getHeight(), this.getHeight(), null);

        //一层透明膜
        g.setColor(new Color(255, 255, 255, 175));
        g.fillRect(getHeight() + (getWidth() - getHeight()) / 20, getHeight() / 4, (getWidth() - getHeight()) * 9 / 10, getHeight() / 3);

//提示
        if (isHint && twoPoint.size() == 0) {
            int[] AIHint = Control.nextStepAI(1, 3);
            int x1 = AIHint[0];
            int y1 = AIHint[1];
            int x2 = AIHint[2];
            int y2 = AIHint[3];
            System.out.println(x1 + "," + y1 + "  " + x2 + "," + y2);

            g.setColor(new Color(45, 236, 154));
            g.drawOval((int) (xStart2 + x1 * gridLength2), (int) (yStart2 + y1 * gridLength2),
                    (int) gridLength2, (int) gridLength2);
            g.drawOval((int) (xStart2 + x2 * gridLength2), (int) (yStart2 + y2 * gridLength2),
                    (int) gridLength2, (int) gridLength2);
        }

        if (twoPoint.size() != 0) {
            isHint = false;
        }


//合法落字点
        if (isPossible && twoPoint.size() == 2) {

            /*g.setColor(new Color(250, 124, 124, 150));*/
//TODO: 如果可移动的位置
            if (Control.getAvailablePositions(twoPoint.get(0), twoPoint.get(1)) != null &&
                    Control.getAvailablePositions(twoPoint.get(0), twoPoint.get(1)).length != 0) {
                int[][] possibleCoordinate = Control.getAvailablePositions(twoPoint.get(0), twoPoint.get(1));
                for (int j = 0; j < possibleCoordinate.length; j++) {

                    try {
                        possibleImage = ImageIO.read(new File("./src/chess/gui/image/possible.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    g.drawImage(possibleImage, (int) (0.045 * chessBoardHeight + possibleCoordinate[j][0] * gridLength2),
                            (int) (yStart2 + possibleCoordinate[j][1] * gridLength2),
                            getHeight() / 11, getHeight() / 11, null);



                   /* g.fillOval((int) (xStart2 + possibleCoordinate[j][0] * gridLength2),
                            (int) (yStart2 + possibleCoordinate[j][1] * gridLength2),
                            (int) gridLength2, (int) gridLength2);*/
                }

            }
        }

        if (twoPoint.size() == 4) {
            isPossible = false;
        }
    }
}

//