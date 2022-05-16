package chess.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardLayout extends LayoutAdapter{
    List<Component> list = new ArrayList<>();
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        list.add(comp);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        list.remove(comp);
    }

    @Override
    public void layoutContainer(Container parent) {
        //选好图片再来改自适应
        int width = parent.getWidth();
        int height = parent.getHeight();
        list.get(0).setBounds(600,0,100,50);
        list.get(1).setBounds(600,50,100,50);
        list.get(2).setBounds(500,100,200,50);
        list.get(3).setBounds(500,150,200,50);
        list.get(4).setBounds(500,200,200,50);
        list.get(5).setBounds(500,250,200,25);
        list.get(6).setBounds(500,275,200,25);
        list.get(7).setBounds(500,300,200,50);
        list.get(8).setBounds(500,350,200,50);
        list.get(9).setBounds(500,400,200,50);
    }
}
