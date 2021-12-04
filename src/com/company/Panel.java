package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import static com.company.Gra.*;
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

    ImageIcon buzkaNormal = new ImageIcon("src/Grafika/BuzkaNormal.png");
    ImageIcon buzkaPrzegrana = new ImageIcon("src/Grafika/BuzkaPrzegrana.png");
    ImageIcon buzkaWygrana = new ImageIcon("src/Grafika/BuzkaWygrana.png");

    static int myszkaX = 0;
    static int myszkaY = 0;

    static boolean pierwRuch = true;
    static boolean czyGraTrwa = false;

    static JLabel pozostalaIloscMinLabel = new JLabel();

    static JButton buzkaPrzycisk;
    public Panel(){
        addMouseListener(this);
        setLayout(null);
        utworzIloscMinLabel();
        add(pozostalaIloscMinLabel);
        buzkaPrzycisk = buzkaPrzycisk();
        add(buzkaPrzycisk);
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
                    if (!czyGraTrwa && !pierwRuch && plansza[j][i].getCzyJestMina() && przegrana)
                        g.drawImage(mina.getImage(), j * wielkoscKomorki, i * wielkoscKomorki, null);
                }
            }
            aktualizujIloscMin();
            buzkaPrzycisk.setIcon(ktoraBuzka());
        repaint();
    }

    private void aktualizujIloscMin(){
        pozostalaIloscMinLabel.setText(String.valueOf(iloscMin-iloscFlag));
    }

    private void utworzIloscMinLabel() {
        pozostalaIloscMinLabel.setFont(new Font("Verdana", 1, 20));
        pozostalaIloscMinLabel.setBounds(wielkoscKomorki, wysokoscPlanszy * wielkoscKomorki, wielkoscKomorki * 3, wielkoscKomorki);
        aktualizujIloscMin();
    }

    private JButton buzkaPrzycisk() {
        JButton przycisk = new JButton();
        przycisk.setBounds((szerokoscPlanszy/2) * wielkoscKomorki, wysokoscPlanszy * wielkoscKomorki,wielkoscKomorki, wielkoscKomorki);
        przycisk.setIcon(ktoraBuzka());
        przycisk.addActionListener(one -> {
            saper.dispose();
            Gra gra = new Gra(szerokoscPlanszy, wysokoscPlanszy, iloscMin);
        });
        return przycisk;
    }

    private ImageIcon ktoraBuzka(){
        if (wygrana)
            return buzkaWygrana;
        else if(przegrana)
            return buzkaPrzegrana;
        return buzkaNormal;
    }

    private void wyswietlPola14(int i, int j, Graphics g) {
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

    private void wyswietlPola58(int i, int j, Graphics g) {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        myszkaX = e.getX()/wielkoscKomorki;
        myszkaY = e.getY()/wielkoscKomorki;

        if (!Pole.czyPoleIstnieje(myszkaX, myszkaY))
            return;

        if(pierwRuch)
            jesliPierwszyRuch();

        KlikanieMyszki(e);
        try {
            CzyWygrana();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        odkryjPoWygranej();
    }

    private void jesliPierwszyRuch(){
        //Timer();
        czyGraTrwa = true;
        Time = 0;
        LosowanieMin(myszkaX, myszkaY);
        obliczanieWartosci();
        pierwRuch = false;
    }

    private void odkryjPoWygranej()
    {
        if (wygrana){
            czyGraTrwa = false;
            for (int i = 0; i < wysokoscPlanszy; i++) {
                for (int j = 0; j < szerokoscPlanszy; j++) {
                    if (!plansza[j][i].getCzyJestOdkryte() && !plansza[j][i].getCzyJestFlaga())
                        plansza[j][i].zmienFlage();
                }
            }
        }
    }

    public void KlikanieMyszki(MouseEvent e){
        if (e.getModifiers()  == MouseEvent.BUTTON1_MASK && !plansza[myszkaX][myszkaY].getCzyJestFlaga() && czyGraTrwa && !wygrana && !przegrana) {
            if(plansza[myszkaX][myszkaY].getCzyJestMina()) {
                czyGraTrwa = false;
                przegrana = true;
            } else {
                odkrywaniePol(myszkaX, myszkaY);
            }
            CzyPrzegrana(myszkaX, myszkaY);
        }
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK && !plansza[myszkaX][myszkaY].getCzyJestOdkryte() && czyGraTrwa && !wygrana && !przegrana) {
            plansza[myszkaX][myszkaY].zmienFlage();
            if (plansza[myszkaX][myszkaY].getCzyJestFlaga())
                iloscFlag++;
            else
                iloscFlag--;
        }
        czyChord(e);
    }

    private void czyChord(MouseEvent e){
        if (e.getModifiers()  == MouseEvent.BUTTON2_MASK && plansza[myszkaX][myszkaY].getCzyJestOdkryte() && czyGraTrwa && !wygrana && !przegrana){
            int iloscFlag = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (Pole.czyPoleIstnieje(myszkaX+i, myszkaY+j))
                        if (plansza[myszkaX+i][myszkaY+j].getCzyJestFlaga())
                            iloscFlag++;
                }
            }
            chord(iloscFlag);
            try {
                CzyWygrana();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void chord(int iloscFlag){
        if (iloscFlag == plansza[myszkaX][myszkaY].getIloscMin()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (Pole.czyPoleIstnieje(myszkaX + i, myszkaY + j)) {
                        CzyPrzegrana(myszkaX + i, myszkaY + j);
                        if (przegrana)
                            czyGraTrwa = false;
                        if (!plansza[myszkaX + i][myszkaY + j].getCzyJestOdkryte() && !plansza[myszkaX + i][myszkaY + j].getCzyJestFlaga() && !plansza[myszkaX + i][myszkaY + j].getCzyJestMina()) {
                            if (plansza[myszkaX + i][myszkaY + j].getIloscMin() == 0)
                                odkrywaniePol(myszkaX + i, myszkaY + j);
                            else {
                                plansza[myszkaX + i][myszkaY + j].odkryjPole();
                                OdkrytePola++;
                            }
                        }
                    }
                }
            }
        }
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
