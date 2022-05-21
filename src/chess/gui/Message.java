package chess.gui;

import javax.swing.*;


public class Message extends JDialog {
    char type;
    ImageIcon imageIcon;
    public Message(char c){
        type=c;
        if(type=='W'){
            imageIcon = new ImageIcon("./src/chess/gui/image/whiteTurn.png");
        } else if (type == 'B') {
            imageIcon = new ImageIcon("./src/chess/gui/image/blackTurn.png");
        }else if (type == 'x') {
            imageIcon = new ImageIcon("./src/chess/gui/image/noPiece.png");
        }else if (type == 'i') {
            imageIcon = new ImageIcon("./src/chess/gui/image/illegalMove.png");
        }else if(type=='C'){
            imageIcon = new ImageIcon("./src/chess/gui/image/whiteInCheck.png");
        }else if(type=='c'){
            imageIcon = new ImageIcon("./src/chess/gui/image/blackInCheck.png");
        }else if(type=='1'){
            imageIcon = new ImageIcon("./src/chess/gui/image/101.png");
        }else if(type=='2'){
            imageIcon = new ImageIcon("./src/chess/gui/image/102.png");
        }else if(type=='3'){
            imageIcon = new ImageIcon("./src/chess/gui/image/103.png");
        }else if(type=='4'){
            imageIcon = new ImageIcon("./src/chess/gui/image/104.png");
        }

        this.setModal(true);
        this.setTitle("Wrong pieces");

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
        this.setBounds(500,275,400,300);
        this.setVisible(true);
        this.setResizable(false);


    }


}
