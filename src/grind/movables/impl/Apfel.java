package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Apfel extends Nahrung{

    private int punkte=5; // Fehlt in Klassendiagramm?
    private int größe;


    public Apfel(int posX, int posY) {
        super(posX, posY, Einstellungen.GROESSE_APFEL);
    }

    public int getPunkte(){
        return this.punkte;
    }

    public void zeichne(PApplet app){
        app.pushStyle();
        app.stroke(255, 0, 0);
        app.fill(255, 0, 0);
        app.ellipse(this.getPosX(), this.getPosY(), Einstellungen.GROESSE_APFEL, Einstellungen.GROESSE_APFEL);
        app.popStyle();

    }
    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playApfelSound();
    }

    @Override
    public int getGroesse() {
        return this.größe = Einstellungen.GROESSE_APFEL;
    }
}
