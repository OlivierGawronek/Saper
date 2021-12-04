package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.renderable.ParameterBlock;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.company.Main.*;
import static com.company.Gra.*;

public class PanelMenu extends JPanel{

    ImageIcon logo = new ImageIcon("src/Grafika/Logo.png");

    public PanelMenu(){
        setLayout(null);
        add(przyciskLatwy());
        add(przyciskSredni());
        add(przyciskTrudny());
        add(przyciskCustom());
        add(HallOfFame());
        add(przyciskResetHOF());
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(logo.getImage(), 20,0,240,120,null);
    }

    public static JButton przyciskLatwy(){
        JButton przycisk = new JButton("ŁATWY");
        przycisk.setBounds(20, 125, 100, 50);
        przycisk.setBackground(Color.GREEN);
        przycisk.addActionListener(one -> {
            if (saper != null)
                saper.dispose();
            gra = new Gra(9,9,10);
            JakiPoziom = "l";
             });
        return przycisk;
    }

    public static JButton przyciskSredni(){
        JButton przycisk = new JButton("ŚREDNI");
        przycisk.setBounds(150, 125, 100, 50);
        przycisk.setBackground(Color.ORANGE);
        przycisk.addActionListener(one -> {
            if (saper != null)
                saper.dispose();
            gra = new Gra(16,16,40);
            JakiPoziom = "s";
        });
        return przycisk;
    }

    public static JButton przyciskTrudny(){
        JButton przycisk = new JButton("TRUDNY");
        przycisk.setBounds(20, 185, 100, 50);
        przycisk.setBackground(Color.RED);
        przycisk.addActionListener(one -> {
            if (saper != null)
                saper.dispose();
            gra = new Gra(30,16,99);
            JakiPoziom = "t";
        });
        return przycisk;
    }

    public static JButton przyciskCustom(){
        JButton przycisk = new JButton("CUSTOM");
        przycisk.setBounds(150, 185, 100, 50);
        przycisk.setBackground(Color.cyan);
        przycisk.addActionListener(one -> {
            if (saper != null)
                saper.dispose();
            Scanner sc = new Scanner(System.in);
            System.out.print("Podaj szerokość: ");
            int sz = sc.nextInt();
            if (sz < 2)
                sz = 2;
            System.out.print("Podaj wysokość: ");
            int w = sc.nextInt();
            if (w < 2)
                w = 2;
            System.out.print("Podaj ilość min: ");
            int m = sc.nextInt();
            if (m >= sz * w){
                m=sz*w-1;
            }
            gra = new Gra(sz, w, m);
            JakiPoziom = "c";
        });
        return przycisk;
    }


    public static JFrame HOF = new JFrame();
    public static JButton HallOfFame(){
        JButton przycisk = new JButton("HOF");
        przycisk.setBounds(20, 240, 100, 50);
        przycisk.setBackground(Color.DARK_GRAY);
        przycisk.setForeground(Color.YELLOW);
        przycisk.addActionListener(one -> {
            if(!HOF.isActive()) HOF.dispose();
            HOF = new JFrame();
            HallOfFame hallOfFame = new HallOfFame();
            HOF.add(hallOfFame);
            HOF.setTitle("HALL OF FAME");
            HOF.setBounds(0, 0, 270 + 16, 300 + 16 + 23);
            HOF.setLocationRelativeTo(null);
            HOF.setVisible(true);
            HOF.setResizable(false);
            hallOfFame.setLayout(null);
        });
        return przycisk;
    }

    public static JButton przyciskResetHOF(){
        JButton przycisk = new JButton("RESET HOF");
        przycisk.setBounds(150, 240, 100, 50);
        przycisk.setBackground(Color.BLACK);
        przycisk.setForeground(Color.PINK);
        przycisk.addActionListener(one -> {
            try {
                ResetPliku();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        return przycisk;
    }



}
