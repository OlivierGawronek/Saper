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

        if(plansza[x][y].getCzyJestOdkryte() || plansza[x][y].getCzyJestMina()) {return;}
        plansza[x][y].odkryjPole();
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
        if(plansza[x][y].getCzyJestMina() && !plansza[x][y].getCzyJestFlaga()) {
            przegrana = true;
        }
    }

    public static void CzyWygrana() {
        if(OdkrytePola == szerokoscPlanszy*wysokoscPlanszy && !przegrana) {
            wygrana = true;
        }
    }
    public static void pole9x9() throws InterruptedException
    {
        Gra gra = new Gra(9,9,10);
    }
    public static void pole16x16() throws InterruptedException
    {
        Gra gra = new Gra(16,16,40);
    }
    public static void pole30x16() throws InterruptedException
    {
        Gra gra = new Gra(30,16,99);
    }
    public static void poleCustome() throws InterruptedException
    {
        int szerokosc = 0;
        int wysokosc = 0;
        int liczba_min = 0;
        Scanner sc = new Scanner(System.in);
        while(1 == 1) {
            System.out.println("Jaką chcesz szerokosc? :");
            szerokosc = sc.nextInt();
            System.out.println("Jaką chcesz wysokosc? :");
            wysokosc = sc.nextInt();
            System.out.println("Ile min chcesz? :");
            liczba_min = sc.nextInt();
            if ((liczba_min > 0 && szerokosc > 0 && wysokosc > 0) && wysokosc * szerokosc > liczba_min) {
                Gra gra = new Gra(szerokosc, wysokosc, liczba_min);
                continue;
            } else System.out.println("Podane złe wartości, spróbuj jeszcze raz!");
        }
    }
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        //OdczytZPliku();
        //ZapisDoPliku();

        //JFrame od wyboru gry
        //On wywoluje Gra gra = new Gra();
        //Gra gra = new Gra(9,9,10);
        pole9x9();
    }

}

