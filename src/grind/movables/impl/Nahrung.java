package grind.movables.impl;

import grind.movables.ISpielfigur;

public abstract class Nahrung extends Gegenstand{
    protected int punkte;

    public Nahrung(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
    }

    public int getPunkte() {
        return this.punkte;
    }

    public void beimAnwenden(ISpielfigur figur){
        figur.setLebensenergie((figur.getLebensenergie()+this.getPunkte()));
    }
    @Override
    public String toString(){
        return "Heilung: "+ getPunkte();
    }

}
