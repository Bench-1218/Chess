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
        //change
        list.get(0).setBounds(   height,0,40,40);
        //save
        list.get(8).setBounds(height+40,0,40,40);
        //turn
        list.get(2).setBounds(height+20,height/4,200,50);
        //round
        list.get(3).setBounds(height+15,height/4+35,200,50);
        //load
        list.get(7).setBounds(height+80,0,40,40);
        //wTime
        list.get(5).setBounds(height+70,height/4+75,200,25);
        //bTime
        list.get(6).setBounds(height+70,height/4+105,200,25);
        //retract
        list.get(4).setBounds(height+30,height*2/3 ,200,50);
        //restart
        list.get(1).setBounds(height+30,height*2/3 +50,200,50);
        //wrong load
        list.get(9).setBounds(height+30,height*2/3 +100,200,50);
        //hint
        list.get(10).setBounds(height+120,0,40,40);
        //possible
        list.get(11).setBounds(height+160,0,40,40);
    }
}
