package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static com.company.Gra.saper;
import static com.company.Main.*;

public class HallOfFame extends JPanel {
    public static JFrame HOF = new JFrame();

    public HallOfFame(){
        setLayout(null);
        add(przyciskLatwy());
        add(przyciskSredni());
        add(przyciskTrudny());
        add(przyciskCustom());
    }


    public static JButton przyciskLatwy(){
        JButton przycisk = new JButton("ŁATWY");
        przycisk.setBounds(20, 30, 230, 50);
        przycisk.setBackground(Color.GREEN);
        JFrame wyn = new JFrame();
        przycisk.addActionListener(one -> {
            if(!wyn.isActive()) wyn.dispose();
            JLabel wyntxt = new JLabel();
            wyn.setBounds(0, 0, 20 + 16, 40 + 16 + 23);
            wyn.setLocationRelativeTo(null);
            wyn.setVisible(true);
            wyn.setResizable(false);
            wyntxt.setFont(new Font("Verdana", 1, 20));
            wyntxt.setText(String.valueOf(NajWynikL));
            wyntxt.setBounds(270, 270, 90, 30);
            wyn.add(wyntxt);
        });
        return przycisk;
    }
    public static JButton przyciskSredni(){
        JButton przycisk = new JButton("ŚREDNI");
        przycisk.setBounds(20, 100, 230, 50);
        przycisk.setBackground(Color.ORANGE);
        JFrame wyn = new JFrame();
        przycisk.addActionListener(one -> {
            if(wyn.isActive()) wyn.dispose();
            JLabel wyntxt = new JLabel();
            wyn.setBounds(0, 0, 20 + 16, 40 + 16 + 23);
            wyn.setLocationRelativeTo(null);
            wyn.setVisible(true);
            wyn.setResizable(false);
            wyntxt.setFont(new Font("Verdana", 1, 20));
            wyntxt.setText(String.valueOf(NajWynikS));
            wyntxt.setBounds(270, 270, 90, 30);
            wyn.add(wyntxt);
        });
        return przycisk;
    }
    public static JButton przyciskTrudny(){
        JButton przycisk = new JButton("TRUDNY");
        przycisk.setBounds(20, 170, 230, 50);
        przycisk.setBackground(Color.RED);
        final JFrame wyn = new JFrame();
        przycisk.addActionListener(one -> {
            if(wyn.isActive()) wyn.dispose();
            JLabel wyntxt = new JLabel();
            wyn.setBounds(0, 0, 20 + 16, 40 + 16 + 23);
            wyn.setLocationRelativeTo(null);
            wyn.setVisible(true);
            wyn.setResizable(false);
            wyntxt.setFont(new Font("Verdana", 1, 20));
            wyntxt.setText(String.valueOf(NajWynikT));
            wyntxt.setBounds(270, 270, 90, 30);
            wyn.add(wyntxt);
        });
        return przycisk;
    }
    public static JButton przyciskCustom(){
        JButton przycisk = new JButton("CUSTOM");
        przycisk.setBounds(20, 240, 230, 50);
        przycisk.setBackground(Color.cyan);
        JFrame wyn = new JFrame();
        przycisk.addActionListener(one -> {
            JLabel wyntxt = new JLabel();
            if(wyn.isActive()) wyn.dispose();
            wyn.setBounds(0, 0, 20 + 16, 40 + 16 + 23);
            wyn.setLocationRelativeTo(null);
            wyn.setVisible(true);
            wyn.setResizable(false);
            wyntxt.setFont(new Font("Verdana", 1, 20));
            wyntxt.setText(String.valueOf(NajWynikC));
            wyntxt.setBounds(270, 270, 90, 30);
            wyn.add(wyntxt);
        });
        return przycisk;
    }


}
