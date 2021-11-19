package com.company;

public class Pole {

    private int iloscMin = 0;
    private Boolean czyJestMina = false;
    private Boolean czyJestFlaga = false;
    private Boolean czyJestOdkryte = false;

    public Pole(Boolean czyJestMina)
    {
        this.czyJestMina = czyJestMina;
    }

    public static Boolean czyPoleIstnieje(int x, int y)
    {
        return false;
    }

    public int getIloscMin() {
        return iloscMin;
    }

    public void setIloscMin(int iloscMin) {
        this.iloscMin = iloscMin;
    }

    public Boolean getCzyJestMina() {
        return czyJestMina;
    }

    public void setCzyJestMina(Boolean czyJestMina) {
        this.czyJestMina = czyJestMina;
    }

    public Boolean getCzyJestFlaga() {
        return czyJestFlaga;
    }

    // Dodaje/usuwa flage na danym polu
    public void zmienFlage(){
        czyJestFlaga = !czyJestFlaga;
    }

    public Boolean getCzyJestOdkryte() {
        return czyJestOdkryte;
    }

    public void odkryjPole(){
        czyJestOdkryte = true;
    }
}