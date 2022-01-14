package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;

public class Mango extends Nahrung{

    public Mango(int posX, int posY, int punkte, int wert) {
        super(posX, posY, Einstellungen.LAENGE_KACHELN_X/2);
        this.punkte = punkte;
        this.wert = wert;
    }


    /*public void zeichne(Spielsteuerung app){
        app.pushStyle();
        app.stroke(225, 100, 34);
        app.fill(225, 100, 34);
        app.ellipse(this.getPosX(), this.getPosY(), groesse, groesse);
        app.popStyle();
    }*/
    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playSound("Apfel");
    }

}
