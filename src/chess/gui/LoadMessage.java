package chess.gui;

import javax.swing.*;

public class LoadMessage extends JDialog{
    char type;
    ImageIcon imageIcon;
    public LoadMessage(char c){
        type=c;

        if(type=='1'){
            imageIcon = new ImageIcon("./src/chess/gui/image/whiteTurn.png");
        } else if (type == '2') {
            imageIcon = new ImageIcon("./src/chess/gui/image/blackTurn.png");
        }else if (type == '3') {
            imageIcon = new ImageIcon("./src/chess/gui/image/noPiece.png");
        }else if (type == '4') {
            imageIcon = new ImageIcon("./src/chess/gui/image/illegalMove.png");
        }

        this.setModal(true);
        this.setTitle("Wrong Load");

        JLayeredPane layeredPane = new JLayeredPane();
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(0, 0, 400, 300);


        JButton confirmButton = new JButton("yes");
        confirmButton.setBounds(150, 225, 100, 50);




        layeredPane.add(jLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(confirmButton, JLayeredPane.MODAL_LAYER);

        confirmButton.addActionListener((e) -> {
            this.setVisible(false);
        });

        this.add(layeredPane);
        this.setLocationRelativeTo(null);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setResizable(false);


    }

}
