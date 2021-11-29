package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static com.company.Main.*;

public class PanelMenu extends JPanel{

    ImageIcon logo = new ImageIcon("src/Grafika/Logo.png");

    public PanelMenu(){
        setLayout(null);
        add(przyciskLatwy());
        add(przyciskSredni());
        add(przyciskTrudny());
        add(przyciskCustom());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logo.getImage(), 20,0,240,120,null);
    }

    public static JButton przyciskLatwy(){
        JButton przycisk = new JButton("ŁATWY");
        przycisk.setBounds(20, 150, 100, 50);
        przycisk.addActionListener(one -> {
            gra = new Gra(9,9,10);
             });
        return przycisk;
    }

    public static JButton przyciskSredni(){
        JButton przycisk = new JButton("ŚREDNI");
        przycisk.setBounds(150, 150, 100, 50);
        przycisk.addActionListener(one -> {
            gra = new Gra(16,16,40);
        });
        return przycisk;
    }

    public static JButton przyciskTrudny(){
        JButton przycisk = new JButton("TRUDNY");
        przycisk.setBounds(20, 220, 100, 50);
        przycisk.addActionListener(one -> {
            gra = new Gra(30,16,99);
        });
        return przycisk;
    }

    public static JButton przyciskCustom(){
        JButton przycisk = new JButton("CUSTOM");
        przycisk.setBounds(150, 220, 100, 50);
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
        return przycisk;
    }

}
