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

    /**
     * Die Methode beimAnwenden erhöht die Lebensenergie des übergebenen Spielfigur um
     * den Punkte Wert der Nahrung.
     * @param figur Spielfigur
     */
    public void beimAnwenden(ISpielfigur figur){
        figur.setLebensenergie((figur.getLebensenergie()+this.getPunkte()));
    }

    /**
     * @return String in Format:
     * Heilung: "wert der Heilung"
     * wird für die Darstellung in der Siedlung benötigt
     */
    @Override
    public String toString(){
        return "Heilung: "+ getPunkte();
    }

}
