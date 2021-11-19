package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Pole[][] plansza = new Pole[9][9];
    public static int szerokoscPlanszy = 9;
    public static int wysokoscPlanszy = 9;
    public static int iloscMin = 10;
    public static long StartTime;
    public static long EndTime;
    public static long TimeDifference;
    public static long NajWynik;

    public static void Timer(){
        StartTime = System.currentTimeMillis(); // wykonywane po rozpoczęciu rozgrywki (nie po uruchomieniu programu)
        EndTime = System.currentTimeMillis(); // wykonywane po wygraniu
        TimeDifference = (EndTime - StartTime) / 1000; // podzielone przez 1000, aby byly to sekundy
    }

    public static void OdczytZPliku() throws FileNotFoundException {
        File plik = new File("NajlepszeWyniki.txt");
        Scanner in = new Scanner(plik);
        NajWynik = in.nextLong();
    }

    public static void ZapisDoPliku() throws FileNotFoundException {
        if (TimeDifference < NajWynik) {
            PrintWriter out = new PrintWriter("NajlepszeWyniki.txt");
            out.println(TimeDifference);
            out.close();
        }
    }
    public static void odkrywaniePol(int x, int y)
    {
        if(plansza[x][y].getCzyJestOdkryte()) {return;}
        if(!plansza[x][y].getCzyJestOdkryte()){ plansza[x][y].odkryjPole();}
        if(plansza[x][y].getIloscMin() != 0) {return;}
        if(x<0 || x > 8 || y <0 || y >8){return;}
        odkrywaniePol(x-1,y);
        odkrywaniePol(x+1,y);
        odkrywaniePol(x,y-1);
        odkrywaniePol(x,y+1);
        odkrywaniePol(x-1,y-1);
        odkrywaniePol(x-1,y+1);
        odkrywaniePol(x+1,y-1);
        odkrywaniePol(x+1,y+1);
        odkrywaniePol(x,y);
    }

    public static void LosowanieMin(int x, int y) {
        // x, y - wspolrzedne miejsca w ktore sie kliknie zamienione odpowiednio
        int licznik = 0;
        Random rand = new Random();
        int tempx = 0;
        int tempy = 0;
        while (licznik < 10) {
            tempx = rand.nextInt(9);
            tempy = rand.nextInt(9);
            if((tempx != x || tempy != y) && plansza[tempx][tempy].getCzyJestMina() == false) {
                plansza[tempx][tempy].setCzyJestMina(true);
                licznik++;
            }
        }
    }

    public static void UtworzeniePolPlanszy() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                plansza[i][j] = new Pole(false);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame saper = new JFrame();
        Panel panel = new Panel();
        saper.add(panel);
        panel.setSize(1000, 1000);
        saper.setTitle("Warcaby");
        saper.setBounds(560, 100, 1000 + 16, 1000 + 16 + 23);
        saper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        saper.setVisible(true);
        saper.setResizable(false);

        UtworzeniePolPlanszy();
    }
}