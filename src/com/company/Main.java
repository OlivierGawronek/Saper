package com.company;

import javax.swing.*;
import javax.swing.plaf.synth.SynthColorChooserUI;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static int szerokoscPlanszy = 9;
    public static int wysokoscPlanszy = 9;
    public static int iloscMin = 10;
    public static int wielkoscKomorki = 30;

    public static Pole[][] plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];

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
        if(plansza[x][y].getIloscMin() != 0) {return;}
        if(!plansza[x][y].czyPoleIstnieje(x, y)){return;}

        if(x<0 || x > szerokoscPlanszy || y < 0 || y >wysokoscPlanszy){return;}


        odkrywaniePol(x-1,y);
        odkrywaniePol(x+1,y);
        odkrywaniePol(x,y-1);
        odkrywaniePol(x,y+1);
        odkrywaniePol(x-1,y-1);
        odkrywaniePol(x-1,y+1);
        odkrywaniePol(x+1,y-1);
        odkrywaniePol(x+1,y+1);
       /*
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <=1; j++) {
                if(x== 0 && y ==0) continue;
                odkrywaniePol(x+i,y+j);
            }
        }
        */
    }

    public static void LosowanieMin(int x, int y) {
        // x, y - wspolrzedne miejsca w ktore sie kliknie zamienione odpowiednio
        int licznik = 0;
        Random rand = new Random();
        int tempx = 0;
        int tempy = 0;
        while (licznik < iloscMin) {
            tempx = rand.nextInt(szerokoscPlanszy);
            tempy = rand.nextInt(wysokoscPlanszy);
            if((tempx != x || tempy != y) && !plansza[tempx][tempy].getCzyJestMina()) {
                plansza[tempx][tempy].setCzyJestMina(true);
                licznik++;
            }
        }
    }

    public static void obliczanieWartosci()
    {
        for (int i = 0; i < szerokoscPlanszy; i++) {
            for (int j = 0; j < wysokoscPlanszy; j++) {
                if(plansza[i][j].getCzyJestMina())
                {
                    zwiekszenieWartosci(i,j);
                }
            }
        }
    }
    public static void zwiekszenieWartosci(int x, int y)
    {
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <=1; j++) {
                if(Pole.czyPoleIstnieje(x+i, y+j) && !plansza[x+i][y+j].getCzyJestMina() && !(i == 0 && j == 0))
                    plansza[x+i][y+j].zwiekszIloscMin();
            }
        }
    }


    public static void UtworzeniePolPlanszy() {
        for (int i = 0; i < szerokoscPlanszy; i++) {
            for (int j = 0; j < wysokoscPlanszy; j++) {
                plansza[i][j] = new Pole(false);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
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

        Thread watek = new Thread();

        for (int i = 1; i < 1000; i++) {
            watek.sleep(1000);
            label1.setText(String.valueOf(i));
        }

    }

}

