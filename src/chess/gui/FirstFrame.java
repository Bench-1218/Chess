package chess.gui;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FirstFrame extends JFrame {


    static int frameHeight = 500;

    public FirstFrame() {
        setTitle("firstChess");

        JLayeredPane layeredPane = new JLayeredPane();
        ImageIcon image = new ImageIcon("./src/chess/gui/image/Background.png");//背景图片
        JLabel jl = new JLabel(image); //图片添加到label
        jl.setBounds(0, 0, 600, 400);//设置容器大小和位置

        JButton normal = new JButton(new ImageIcon("./src/chess/gui/image/normal.png"));
        normal.setBounds(220, 150, 150, 55);//设置按钮大小和位置

        layeredPane.add(jl, JLayeredPane.DEFAULT_LAYER); //将jl放到最底层
        layeredPane.add(normal, JLayeredPane.MODAL_LAYER); //将jb1放到高一层的地方
        normal.addActionListener((e) -> {
                    /*ChessFrame chessFrame = new ChessFrame();
                    this.setVisible(false);
                    chessFrame.setFocusable(true);
                    chessFrame.setVisible(true);

                    chessFrame.addComponentListener(new ComponentAdapter() {//让窗口响应大小改变事件
                        @Override
                        public void componentResized(ComponentEvent e) {
                            System.out.println("窗口大小改变了~");
                            frameHeight = chessFrame.getHeight();
                            System.out.println(frameHeight);
                        }

                    });*/
            this.setVisible(false);
            Rule rule=new Rule(1);
                }
        );


        JButton AIStart = new JButton(new ImageIcon("./src/chess/gui/image/aistart.png"));//按钮
        AIStart.setBounds(220, 240, 150, 50);//设置按钮大小和位置

        layeredPane.add(AIStart, JLayeredPane.MODAL_LAYER);

        AIStart.addActionListener((e) -> {
            this.setVisible(false);
            Rule rule=new Rule(2);
            /*ChessFrame chessFrameAI = new ChessFrame(1);
            this.setVisible(false);
            chessFrameAI.setFocusable(true);
            chessFrameAI.setVisible(true);

            chessFrameAI.addComponentListener(new ComponentAdapter() {//让窗口响应大小改变事件
                @Override
                public void componentResized(ComponentEvent e) {
                    System.out.println("窗口大小改变了~");
                    frameHeight = chessFrameAI.getHeight();
                    System.out.println(frameHeight);
                }

            });*/
        });


        this.setLayeredPane(layeredPane); //窗体设置JLayeredPane容器
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

}



