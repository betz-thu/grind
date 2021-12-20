package grind.movables.impl;

import grind.movables.ISpielfigur;

public abstract class Nahrung extends Gegenstand{

    public Nahrung(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
    }

    public abstract int getPunkte();

    public void beimAnwenden(ISpielfigur figur){
        figur.setLebensenergie((figur.getLebensenergie()+this.getPunkte()));

    }

}
