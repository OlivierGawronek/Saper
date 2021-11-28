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

    public Panel(){
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
            for (int i = 0; i < wysokoscPlanszy; i++) {
                for (int j = 0; j < szerokoscPlanszy; j++) {
                    g.drawImage(pole.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                    if (plansza[j][i].getCzyJestOdkryte()) {
                        g.drawImage(odkrytePole.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                        wyswietlPola14(i, j, g);
                        wyswietlPola58(i, j, g);
                    }
                    else if (plansza[j][i].getCzyJestFlaga())
                        g.drawImage(flaga.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                    if (!Gra && !pierwRuch && plansza[j][i].getCzyJestMina())
                        g.drawImage(mina.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                }
            }
        repaint();
    }

    public void wyswietlPola14(int i, int j, Graphics g) {
        switch (plansza[j][i].getIloscMin()) {
            case 1:
                g.drawImage(num1.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
            case 2:
                g.drawImage(num2.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
            case 3:
                g.drawImage(num3.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
            case 4:
                g.drawImage(num4.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
        }
    }

    public void wyswietlPola58(int i, int j, Graphics g) {
        switch (plansza[j][i].getIloscMin()) {
            case 5:
                g.drawImage(num5.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
            case 6:
                g.drawImage(num6.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
            case 7:
                g.drawImage(num7.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
            case 8:
                g.drawImage(num8.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                break;
        }
    }

    public void tworzenieFlag(Graphics g)
    {
        if(!plansza[myszkaX][myszkaY].getCzyJestFlaga() && !plansza[myszkaX][myszkaY].getCzyJestOdkryte() && !plansza[myszkaX][myszkaY].getCzyJestMina()) {
            g.drawImage(flaga.getImage(), myszkaX * wielkoscKomorki, myszkaY * wielkoscKomorki, null);
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
            //Timer();
            Gra = true;
            LosowanieMin(myszkaX, myszkaY);
            obliczanieWartosci();
            pierwRuch = false;

        }
        //CzyPrzegrana(myszkaX, myszkaY);
        if (e.getModifiers()  == MouseEvent.BUTTON1_MASK && !plansza[myszkaX][myszkaY].getCzyJestFlaga()) {
            odkrywaniePol(myszkaX, myszkaY);
            if(plansza[myszkaX][myszkaY].getCzyJestMina()) {
                Gra = false;
                przegrana = true;
            } else {
                OdkrytePola++;
            }
        }
        CzyWygrana();
        if (e.getModifiers()  == MouseEvent.BUTTON3_MASK)
            plansza[myszkaX][myszkaY].zmienFlage();

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
