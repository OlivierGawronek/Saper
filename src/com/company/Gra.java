package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Main.*;

public class Gra {

    public Gra ()
    {
        szerokoscPlanszy = 9;
        wysokoscPlanszy = 9;
        iloscMin = 10;
        zacznijGre();
    }
    public Gra (int sz, int w, int m)
    {
        szerokoscPlanszy = sz;
        wysokoscPlanszy = w;
        iloscMin = m;
        zacznijGre();
    }

    static JLabel label1 = new JLabel();
    public static void zacznijGre() {
        plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];
        JFrame saper = new JFrame();
        Panel panel = new Panel();
        saper.add(panel);
        panel.setSize(szerokoscPlanszy * wielkoscKomorki, (wysokoscPlanszy + 1) * wielkoscKomorki);
        saper.setTitle("Saper");
        saper.setBounds(560, 100, szerokoscPlanszy * wielkoscKomorki + 16, (wysokoscPlanszy + 1) * wielkoscKomorki + 16 + 23);
        saper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        saper.setVisible(true);
        saper.setResizable(false);

        UtworzeniePolPlanszy();

        label1.setFont(new Font("Verdana", 1, 20));
        label1.setText("0");
        Time = 0;
        panel.add(label1);
    }

    public static void ustawTimer(){
        label1.setText(String.valueOf(Time));
    }
}
