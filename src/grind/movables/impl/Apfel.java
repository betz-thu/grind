package grind.movables.impl;

import processing.core.PApplet;

public class Apfel extends Nahrung{


    private int punkte; // Fehlt in Klassendiagramm?


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
        app.ellipse(this.getPosX(), this.getPosY(), 20, 20);
        app.popStyle();
    }



}
