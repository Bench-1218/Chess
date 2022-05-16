package chess.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class WholeLayout extends LayoutAdapter {
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
       /* System.out.println("我是布局管理器,我被调用啦啦啦啦啦");*/
        int width = parent.getWidth();
        int height = parent.getHeight();

        // set chessboard
        list.get(0).setBounds(0,0,width,height);

    }
}

//abstract class LayoutAdapter implements LayoutManager2 {
//    @Override
//    public Dimension maximumLayoutSize(Container target) {
//        return null;
//    }
//
//    @Override
//    public float getLayoutAlignmentX(Container target) {
//        return 0;
//    }
//
//    @Override
//    public float getLayoutAlignmentY(Container target) {
//        return 0;
//    }
//
//    @Override
//    public void invalidateLayout(Container target) {
//
//    }
//
//    @Override
//    public void addLayoutComponent(String name, Component comp) {
//
//    }
//
//
//
//    @Override
//    public Dimension preferredLayoutSize(Container parent) {
//        return null;
//    }
//
//    @Override
//    public Dimension minimumLayoutSize(Container parent) {
//        return null;
//    }
//
//
//}