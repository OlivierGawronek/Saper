package com.company;

import javax.swing.*;
import java.awt.*;

import static com.company.Main.*;

public class Gra {

    public Gra () throws InterruptedException
    {
        zacznijGre();
    }

    private void zacznijGre() throws InterruptedException
    {
        JFrame saper = new JFrame();
        Panel panel = new Panel();
        saper.add(panel);
        panel.setSize(szerokoscPlanszy*wielkoscKomorki, (wysokoscPlanszy+1)*wielkoscKomorki);
        saper.setTitle("Saper");
        saper.setBounds(560, 100, szerokoscPlanszy * wielkoscKomorki + 16, (wysokoscPlanszy + 1) * wielkoscKomorki + 16 + 23);
        saper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        saper.setVisible(true);
        saper.setResizable(false);
        UtworzeniePolPlanszy();
        JLabel label1 = new JLabel();
        label1.setFont(new Font("Verdana",1,20));
        label1.setText("0");

        panel.add(label1);

        Thread timer = new Thread();

        while(przegrana == false && wygrana == false) {
            timer.sleep(1000);
            Time++;
            label1.setText(String.valueOf(Time));
        }
    }
}
