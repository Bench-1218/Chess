package chess.core.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChessMain {

    static int frameHeight=500;

    public static void main(String[] args) {

        ChessFrame frame = new ChessFrame();
        FirstFrame firstFrame =new FirstFrame();


        firstFrame.setVisible(true);
        frame.setVisible(false);




        frame.addComponentListener(new ComponentAdapter() {//让窗口响应大小改变事件
            @Override

            public void componentResized(ComponentEvent e) {
                System.out.println("窗口大小改变了~");
                frameHeight =frame.getHeight();
                System.out.println(frameHeight);
            }

        });

        Music music = new Music();
        music.start();

    }
}
