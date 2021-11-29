package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {


    public static int szerokoscPlanszy = 9;
    public static int wysokoscPlanszy = 9;
    public static int iloscMin = 10;
    public final static int wielkoscKomorki = 30;
    public static int OdkrytePola = 0;
    public static int iloscFlag = 0;

    public static Pole[][] plansza = new Pole[szerokoscPlanszy][wysokoscPlanszy];

    public static int NajWynik;
    public static int Time;
    public static Boolean przegrana = false;
    public static Boolean wygrana = false;


    public static void OdczytZPliku() throws FileNotFoundException {
        File plik = new File("NajlepszeWyniki.txt");
        Scanner in = new Scanner(plik);
        NajWynik = in.nextInt();
    }

    public static void ZapisDoPliku() throws FileNotFoundException {
        if (Time < NajWynik && wygrana) {
            PrintWriter out = new PrintWriter("NajlepszeWyniki.txt");
            out.println(Time);
            out.close();
        }
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
                if(x== 0 && y ==0) continue;
                if (Pole.czyPoleIstnieje(x+i, y+j))
                    odkrywaniePol(x+i,y+j);

            }
        }

    }

    public static void LosowanieMin(int x, int y) {
        // x, y - wspolrzedne miejsca w ktore sie kliknie zamienione odpowiednio
        int licznik = 0;
        Random rand = new Random(10);
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

    public static void CzyWygrana() {
        if(OdkrytePola == szerokoscPlanszy*wysokoscPlanszy - iloscMin && przegrana == false) {
            wygrana = true;
        }
    }

    public static Gra gra;
    public static void main(String[] args) throws FileNotFoundException {
        //OdczytZPliku();
        //ZapisDoPliku();

        //JFrame od wyboru gry
        JFrame menu = new JFrame();
        PanelMenu panelMenu = new PanelMenu();
        menu.add(panelMenu);
        panelMenu.setSize(9 * wielkoscKomorki, 10 * wielkoscKomorki);
        menu.setTitle("Menu");
        menu.setBounds(560, 100, 9 * wielkoscKomorki + 16, 10 * wielkoscKomorki + 16 + 23);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
        menu.setResizable(false);

        //Timer
        while (przegrana == false && wygrana == false) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Time++;
            Gra.ustawTimer();
        }
    }

}

