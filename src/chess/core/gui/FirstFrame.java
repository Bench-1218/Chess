package chess.core.gui;

import javax.swing.*;

public class FirstFrame extends JFrame {

    ChessFrame chessFrame=new ChessFrame();

    public FirstFrame() {
        setTitle("firstChess");

        JLayeredPane layeredPane =new JLayeredPane();
        ImageIcon image=new ImageIcon("./src/chess/core/gui/image/Background.png");//背景图片
        JLabel jl=new JLabel(image); //图片添加到label
        jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());//设置容器大小和位置

        JButton jb1=new JButton("Ugly");//按钮
        jb1.setBounds(550,400,100,50);//设置按钮大小和位置

        layeredPane.add(jl,JLayeredPane.DEFAULT_LAYER); //将jl放到最底层
        layeredPane.add(jb1,JLayeredPane.MODAL_LAYER); //将jb1放到高一层的地方
        jb1.addActionListener((e) ->{this.setVisible(false);chessFrame.setVisible(true);} );

        this.setLayeredPane(layeredPane); //窗体设置JLayeredPane容器
        this.setSize(image.getIconWidth(),image.getIconHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

}



