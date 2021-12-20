package grind.movables.impl;

import grind.util.Einstellungen;
import processing.core.PApplet;

public class Apfel extends Nahrung{


    transient private int punkte; // Fehlt in Klassendiagramm?
    private int größe;


    public Apfel(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
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
    public int getGroesse() {
        return this.größe = Einstellungen.GROESSE_APFEL;
    }
}
