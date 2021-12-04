package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import static com.company.Panel.*;

public class Main {


    public static int szerokoscPlanszy = 9;
    public static int wysokoscPlanszy = 9;
    public static int iloscMin = 10;
    public final static int wielkoscKomorki = 30;
    public static int OdkrytePola = 0;
    public static int iloscFlag = 0;
    public static String JakiPoziom;

    public static Pole[][] plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];

    public static int NajWynikL;
    public static int NajWynikS;
    public static int NajWynikT;
    public static int NajWynikC;
    public static int Time;
    public static Boolean przegrana = false;
    public static Boolean wygrana = false;


    public static void OdczytZPliku() throws FileNotFoundException {
        File plik = new File("NajlepszeWyniki.txt");
        Scanner in = new Scanner(plik);
        if(in.hasNext()) {
            NajWynikL = in.nextInt();
            NajWynikS = in.nextInt();
            NajWynikT = in.nextInt();
            NajWynikC = in.nextInt();
        }
    }

    public static void ZapisDoPliku() throws FileNotFoundException {
        switch(JakiPoziom) {
            case "l":
                if (Time < NajWynikL) {
                    NajWynikL = Time;
                }
                break;
            case "s":
                if (Time < NajWynikS) {
                    NajWynikS = Time;
                }
                break;
            case "t":
                if (Time < NajWynikT) {
                    NajWynikT = Time;
                }
                break;
            case "c":
                if (Time < NajWynikC) {
                    NajWynikC = Time;
                }
                break;
        }
        PrintWriter out = new PrintWriter("NajlepszeWyniki.txt");
        out.println(NajWynikL + "\n" + NajWynikS + "\n" + NajWynikT + "\n" + NajWynikC);
        out.close();

    }

    public static void ResetPliku() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("NajlepszeWyniki.txt");
        out.flush();
        out.close();

    }


    public static void odkrywaniePol(int x, int y)
    {
        if(plansza[x][y].getCzyJestOdkryte()) {return;}
        plansza[x][y].odkryjPole();
        OdkrytePola++;
        if(plansza[x][y].getCzyJestFlaga()){
            plansza[x][y].zmienFlage();
            iloscFlag--;
        }
        if(plansza[x][y].getIloscMin() != 0) {return;}

        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <=1; j++) {
                if(i== 0 && j ==0) continue;
                if (Pole.czyPoleIstnieje(x+i, y+j))
                    odkrywaniePol(x+i,y+j);

            }
        }

    }

    public static void LosowanieMin(int x, int y) {
        // x, y - wspolrzedne miejsca w ktore sie kliknie zamienione odpowiednio
        int licznik = 0;
        Random rand = new Random(); // fajny seed, nie usuwać: 12342737
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

    public static void CzyPrzegrana(int x, int y){
        if(plansza[x][y].getCzyJestMina() && plansza[x][y].getCzyJestFlaga() == false) {
            przegrana = true;
        }
    }

    public static void CzyWygrana() throws FileNotFoundException {
        if(OdkrytePola == szerokoscPlanszy*wysokoscPlanszy - iloscMin && przegrana == false) {
            wygrana = true;
            System.out.println(Time);
            ZapisDoPliku();
        }
    }

    public static Gra gra;
    public static void main(String[] args) throws FileNotFoundException {
        OdczytZPliku();

        //JFrame od wyboru gry
        JFrame menu = new JFrame();
        PanelMenu panelMenu = new PanelMenu();
        menu.add(panelMenu);
        panelMenu.setSize(9 * wielkoscKomorki, 10 * wielkoscKomorki);
        menu.setTitle("Menu");
        menu.setBounds(0, 0, 9 * wielkoscKomorki + 16, 10 * wielkoscKomorki + 16 + 23);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
        menu.setResizable(false);

        //Timer
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (czyGraTrwa) {
                Time++;
                Gra.ustawTimer();
            }
        }
    }

}

