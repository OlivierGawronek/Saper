package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Main.*;
import static com.company.Panel.*;

public class Gra {

    public Gra ()
    {
        szerokoscPlanszy = 9;
        wysokoscPlanszy = 9;
        iloscMin = 10;
        ustawienia();
        zacznijGre();
    }
    public Gra (int sz, int w, int m)
    {
        szerokoscPlanszy = sz;
        wysokoscPlanszy = w;
        iloscMin = m;
        ustawienia();
        zacznijGre();
    }

    private void ustawienia(){
        pierwRuch = true;
        czyGraTrwa = false;
        OdkrytePola = 0;
        iloscFlag = 0;
        przegrana = false;
        wygrana = false;
        Time = 0;
    }

    static JLabel timerLabel = new JLabel();
    public static JFrame saper;
    public static void zacznijGre() {
        plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];
        saper = new JFrame();
        Panel panel = new Panel();
        saper.add(panel);
        panel.setSize(szerokoscPlanszy * wielkoscKomorki, (wysokoscPlanszy + 1) * wielkoscKomorki);
        saper.setTitle("Saper");
        saper.setBounds(560, 100, szerokoscPlanszy * wielkoscKomorki + 16, (wysokoscPlanszy + 1) * wielkoscKomorki + 16 + 23);
        saper.setVisible(true);
        saper.setResizable(false);
        panel.setLayout(null);

        UtworzeniePolPlanszy();

        timerLabel.setFont(new Font("Verdana", 1, 20));
        timerLabel.setText("0");
        timerLabel.setBounds((szerokoscPlanszy - 2) * wielkoscKomorki, wysokoscPlanszy * wielkoscKomorki, 90, 30);
        panel.add(timerLabel);
    }

    public static void ustawTimer(){
        timerLabel.setText(String.valueOf(Time));
    }
}
