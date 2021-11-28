package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static com.company.Main.*;

public class PanelMenu extends JPanel{

    public PanelMenu(){
    }

    int n = 0;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(n == 0) {
            add(przyciskLatwy());
            add(przyciskSredni());
            add(przyciskTrudny());
            add(przyciskCustom());
            n = 1;
        }
    }

    public static JPanel przyciskLatwy(){
        JPanel panelDoPrzycisku = new JPanel();
        panelDoPrzycisku.setVisible(true);
        panelDoPrzycisku.setBounds(20, 150, 100, 50);
        JButton przycisk = new JButton("ŁATWY");
        przycisk.setBounds(0, 0 , 100, 50);
        panelDoPrzycisku.add(przycisk);
        przycisk.addActionListener(one -> {
            gra = new Gra(9,9,10);
             });
        return panelDoPrzycisku;
    }

    public static JPanel przyciskSredni(){
        JPanel panelDoPrzycisku = new JPanel();
        panelDoPrzycisku.setVisible(true);
        panelDoPrzycisku.setBounds(120, 150, 100, 50);
        JButton przycisk = new JButton("ŚREDNI");
        przycisk.setBounds(0, 0, 100, 50);
        panelDoPrzycisku.add(przycisk);
        przycisk.addActionListener(one -> {
            gra = new Gra(16,16,40);
        });
        return panelDoPrzycisku;
    }

    public static JPanel przyciskTrudny(){
        JPanel panelDoPrzycisku = new JPanel();
        panelDoPrzycisku.setVisible(true);
        panelDoPrzycisku.setBounds(20, 200, 100, 50);
        JButton przycisk = new JButton("TRUDNY");
        przycisk.setBounds(0, 0, 100, 50);
        panelDoPrzycisku.add(przycisk);
        przycisk.addActionListener(one -> {
            gra = new Gra(30,16,99);
        });
        return panelDoPrzycisku;
    }

    public static JPanel przyciskCustom(){
        JPanel panelDoPrzycisku = new JPanel();
        panelDoPrzycisku.setVisible(true);
        panelDoPrzycisku.setBounds(120, 200, 100, 50);
        JButton przycisk = new JButton("CUSTOM");
        przycisk.setBounds(0, 0, 100, 50);
        panelDoPrzycisku.add(przycisk);
        przycisk.addActionListener(one -> {
            Scanner sc = new Scanner(System.in);
            System.out.print("Podaj szerokość: ");
            int sz = sc.nextInt();
            System.out.print("Podaj wysokość: ");
            int w = sc.nextInt();
            System.out.print("Podaj ilość min: ");
            int m = sc.nextInt();
            gra = new Gra(sz, w, m);
        });
        return panelDoPrzycisku;
    }

}
