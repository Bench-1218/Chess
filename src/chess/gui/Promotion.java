package chess.gui;

import javax.swing.*;

public class Promotion extends JDialog {

char choose;
char c;

    public Promotion(char c){
        this.c=c;

        if(c=='p'){
            choose='q';
        }
        if(c=='P'){
            choose='Q';
        }

        this.setTitle("Promotion");
        this.setModal(true);

        JButton confirmButton = new JButton("yes");
        confirmButton.setBounds(300,250,75,50);

        this.setLayout(null);
        this.add(confirmButton);
        confirmButton.addActionListener((e) -> this.setVisible(false));


        JRadioButton chooseQueen = new JRadioButton("Queen");
        JRadioButton chooseKnight = new JRadioButton("Knight");
        JRadioButton chooseBishop = new JRadioButton("Bishop");
        JRadioButton chooseRook = new JRadioButton("Rook");

        chooseQueen.setSelected(true);

        chooseQueen.addActionListener(e->{
            if(chooseQueen.isSelected()) {
                chooseKnight.setSelected(false);
                chooseBishop.setSelected(false);
                chooseRook.setSelected(false);
                if(c=='p'){
                    choose='q';
                }
                if(c=='P'){
                    choose='Q';
                }
            }
        });


        chooseKnight.addActionListener(e->{
            if(chooseKnight.isSelected()) {
                chooseQueen.setSelected(false);
                chooseBishop.setSelected(false);
                chooseRook.setSelected(false);
                System.out.println(choose);
                if(c=='p'){
                    choose='n';
                }
                if(c=='P'){
                    choose='N';
                }
            }
        });

        chooseBishop.addActionListener(e->{
            if(chooseBishop.isSelected()) {
                chooseKnight.setSelected(false);
                chooseQueen.setSelected(false);
                chooseRook.setSelected(false);
                if(c=='p'){
                    choose='b';
                }
                if(c=='P'){
                    choose='B';
                }
            }
        });

        chooseRook.addActionListener(e->{
            if(chooseRook.isSelected()) {
                chooseKnight.setSelected(false);
                chooseBishop.setSelected(false);
                chooseQueen.setSelected(false);
                if(c=='p'){
                    choose='r';
                }
                if(c=='P'){
                    choose='R';
                }
            }
        });

        this.add(chooseQueen);
        this.add(chooseKnight);
        this.add(chooseBishop);
        this.add(chooseRook);

        chooseQueen.setBounds(250,50,100,50);
        chooseKnight.setBounds(250,100,100,50);
        chooseBishop.setBounds(250,150,100,50);
        chooseRook.setBounds(250,200,100,50);


        JLabel jLabel=new JLabel(new ImageIcon("./src/chess/gui/image/unknown.png"));
        this.add(jLabel);
        jLabel.setBounds(0,0,200,300);

        JLabel label=new JLabel("Please Choose:");
        this.add(label);
        label.setBounds(200,0,100,50);

        this.setLocationRelativeTo(null);
        this.setSize(400, 350);
        this.setVisible(true);
        this.setResizable(false);

    }
}
