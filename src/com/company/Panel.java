package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.company.Main.*;

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
    ImageIcon pole = new ImageIcon("src/Grafika/pole.png");

    static int myszkaX = 0;
    static int myszkaY = 0;

    static boolean pierwRuch = true;
    static boolean Gra = false;
    static boolean rightClick = false;

    public Panel(){
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            for (int i = 0; i < wysokoscPlanszy; i++) {
                for (int j = 0; j < szerokoscPlanszy; j++) {
                    //if(plansza[j][i].getCzyJestMina()) g.drawImage(mina.getImage(), j* wielkoscKomorki, i * wielkoscKomorki, null);
                    g.drawImage(pole.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                }
            }


        Miny(g);
        tworzenieFlag(g);

        repaint();
    }


    public void Miny(Graphics g)
    {
        if (plansza[myszkaX][myszkaY].getCzyJestMina())
            g.drawImage(mina.getImage(), myszkaX * wielkoscKomorki, myszkaY * wielkoscKomorki, null);
        repaint();
    }

    public void tworzenieFlag(Graphics g)
    {
        if(!plansza[myszkaX][myszkaY].getCzyJestFlaga() && !plansza[myszkaX][myszkaY].getCzyJestOdkryte() && !plansza[myszkaX][myszkaY].getCzyJestMina()) {
            g.drawImage(flaga.getImage(), myszkaX * wielkoscKomorki, myszkaY * wielkoscKomorki, null);
            //System.out.println("PRAWY");
            rightClick = !rightClick;
            plansza[myszkaX][myszkaY].zmienFlage();
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        myszkaX = e.getX()/wielkoscKomorki;
        myszkaY = e.getY()/wielkoscKomorki;

        if(myszkaX > szerokoscPlanszy - 1) myszkaX = szerokoscPlanszy-1;
        if(myszkaY > wysokoscPlanszy - 1) myszkaY = wysokoscPlanszy-1;

        if(pierwRuch)
        {
            Gra = true;
            LosowanieMin(myszkaX, myszkaY);
            pierwRuch = !pierwRuch;
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            rightClick = true;
        }



        System.out.println("x: " + myszkaY + " y: " + myszkaY);
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
