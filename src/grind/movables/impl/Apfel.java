package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Apfel extends Nahrung{




    public Apfel(int posX, int posY, int punkte, int wert) {
        super(posX, posY, Einstellungen.LAENGE_KACHELN_X/2);
        this.punkte = punkte;
        this.wert = wert;
    }


    public void zeichne(PApplet app){
        app.pushStyle();
        app.stroke(255, 0, 0);
        app.fill(255, 0, 0);
        app.ellipse(this.getPosX(), this.getPosY(), groesse, groesse);
        app.popStyle();

    }
    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playApfelSound();
    }

    @Override
    public int getGroesse() {
        return this.groesse = Einstellungen.LAENGE_KACHELN_X/2;
    }
}
