package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Panel extends JPanel implements MouseListener {

    ImageIcon num1 = new ImageIcon("src/Grafika/1.png");
    ImageIcon num2 = new ImageIcon("src/Grafika/2.png");
    ImageIcon num3 = new ImageIcon("src/Grafika/3.png");
    ImageIcon num4 = new ImageIcon("src/Grafika/4.png");
    ImageIcon num5 = new ImageIcon("src/Grafika/5.png");
    ImageIcon num6 = new ImageIcon("src/Grafika/6.png");
    ImageIcon num7 = new ImageIcon("src/Grafika/7.png");
    ImageIcon num8 = new ImageIcon("src/Grafika/8.png");
    ImageIcon flaga = new ImageIcon("src/Grafika/flaga.png");
    ImageIcon mina = new ImageIcon("src/Grafika/mina.png");
    ImageIcon odkrytePole = new ImageIcon("src/Grafika/odkryte_pole.png");
    ImageIcon Pole = new ImageIcon("src/Grafika/pole.png");

    static int x = 0;
    static int y = 0;

    static int PoleWielkoscX = 8;
    static int PoleWielkoscY = 8;

    public Panel(){
        addMouseListener(this);
    }

    public static int wielkoscKomorki = 30;


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                g.drawImage(Pole.getImage(), j * 30, i * 30, null);
            }
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX()/wielkoscKomorki;
        y = e.getY()/wielkoscKomorki;

        if(x > PoleWielkoscX) x = PoleWielkoscX;
        if(y > PoleWielkoscY) y = PoleWielkoscY;

        System.out.println("x: " + x + " y: " + y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
