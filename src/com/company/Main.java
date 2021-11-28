package com.company;

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
        if(plansza[x][y].getIloscMin() != 0) {return;}

        //if(x<0 || x > szerokoscPlanszy || y < 0 || y >wysokoscPlanszy){return;}

/*
        odkrywaniePol(x-1,y);
        odkrywaniePol(x+1,y);
        odkrywaniePol(x,y-1);
        odkrywaniePol(x,y+1);
        odkrywaniePol(x-1,y-1);
        odkrywaniePol(x-1,y+1);
        odkrywaniePol(x+1,y-1);
        odkrywaniePol(x+1,y+1);*/

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

    public static void CzyPrzegrana(int x, int y){
        if(plansza[x][y].getCzyJestMina() && plansza[x][y].getCzyJestFlaga() == false) {
            przegrana = true;
        }
    }

    public static void CzyWygrana() {
        if(OdkrytePola == szerokoscPlanszy*wysokoscPlanszy && przegrana == false) {
            wygrana = true;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        //OdczytZPliku();
        //ZapisDoPliku();

        //JFrame od wyboru gry
        Gra gra = new Gra();
    }

}

