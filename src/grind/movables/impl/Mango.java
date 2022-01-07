package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;

public class Mango extends Nahrung{
    private int punkte=10; // Fehlt in Klassendiagramm?


    public Mango(int posX, int posY) {
        super(posX, posY, Einstellungen.GROESSE_MANGO);
    }

    public int getPunkte(){
        return this.punkte;
    }

    public void zeichne(Spielsteuerung app){
        app.pushStyle();
        app.stroke(225, 100, 34);
        app.fill(225, 100, 34);
        app.ellipse(this.getPosX(), this.getPosY(), Einstellungen.GROESSE_MANGO, Einstellungen.GROESSE_MANGO);
        app.popStyle();
    }
    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playApfelSound();
    }

}
