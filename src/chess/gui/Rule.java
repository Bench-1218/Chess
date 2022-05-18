package chess.gui;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Rule extends JDialog {
    ImageIcon imageIcon;
    JLabel jLabel;
    static int choose; //不行就换static

    public Rule(int i) {
        this.setModal(true);
        this.setTitle("rule");
        this.setLayout(null);
        this.setBounds(200,200,600,500);
        if (i == 1) {
            JButton confirm = new JButton("yes");
            imageIcon = new ImageIcon("./src/chess/gui/image/rule1.GIF");
            confirm.addActionListener(e -> {
                ChessFrame chessFrame = new ChessFrame();
                this.setVisible(false);
                chessFrame.setFocusable(true);
                chessFrame.setVisible(true);

                chessFrame.addComponentListener(new ComponentAdapter() {//让窗口响应大小改变事件
                    @Override
                    public void componentResized(ComponentEvent e) {
                        System.out.println("窗口大小改变了~");
                        FirstFrame.frameHeight = chessFrame.getHeight();
                        System.out.println(FirstFrame.frameHeight);
                    }

                });

            });
            confirm.setBounds(100, 400, 70, 50);
            this.add(confirm);

            //normal
        } else if (i == 2) {
            //ai
            imageIcon = new ImageIcon("./src/chess/gui/image/aiRule.png");
            JButton random = new JButton("random");
            this.add(random);
            random.setBounds(100, 250, 100, 50);
            random.addActionListener(e -> {
                choose = 0;
                this.setVisible(false);
                openChessBoard();
            });

            JButton level1 = new JButton("level1");
            this.add(level1);
            level1.setBounds(100, 300, 100, 50);
            level1.addActionListener(e -> {
                choose = 1;
                this.setVisible(false);
                openChessBoard();
            });

            JButton level2 = new JButton("level2");
            this.add(level2);
            level2.setBounds(100, 350, 100, 50);
            level2.addActionListener(e -> {
                choose = 2;
                this.setVisible(false);
                openChessBoard();
            });

            JButton level3 = new JButton("level3");
            this.add(level3);
            level3.setBounds(100, 400, 100, 50);
            level3.addActionListener(e -> {
                choose = 3;
                this.setVisible(false);
                openChessBoard();
            });

        }

        jLabel = new JLabel(imageIcon);
        jLabel.setBounds(300, 0, 300, 500);


        this.add(jLabel);
        this.setLocationRelativeTo(null);
//        this.setSize(600, 500);
        this.setVisible(true);
        this.setResizable(false);

    }

    public void openChessBoard() {
        ChessFrame chessFrameAI = new ChessFrame(1);
        chessFrameAI.setFocusable(true);
        chessFrameAI.setVisible(true);

        chessFrameAI.addComponentListener(new ComponentAdapter() {//让窗口响应大小改变事件
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("窗口大小改变了~");
                FirstFrame.frameHeight = chessFrameAI.getHeight();
                System.out.println(FirstFrame.frameHeight);
            }

        });
    }
}
