package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.welt.impl.DummyLevel;

public abstract class Gegenstand extends Schatz{
    public Gegenstand(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
    }

    @Override
    public void beimSammeln(ISpielfigur figur){
        figur.getInventar().add(this);
    }


    public abstract void beimAnwenden(ISpielfigur figur);

    /**
     * Die Methode beimKaufen fügt dem Inventar des übergebenen Spielers einen Gegenstand hinzu
     * und verringert das Gold des Spielers um den Wert des Gegenstands.
     * @param figur Spielfigur
     */
    public void beimKaufen(ISpielfigur figur){
        figur.getInventar().add(this);
        figur.verringereGold(wert);

    }
}
