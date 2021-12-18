package grind.movables.impl;

import processing.core.PApplet;

public class Mango extends Nahrung{
    private int punkte=10; // Fehlt in Klassendiagramm?


    public Mango(int posX, int posY) {
        super(posX, posY);
    }

    public int getPunkte(){
        return this.punkte;
    }

    public void zeichne(PApplet app){
        app.pushStyle();
        app.stroke(225, 100, 34);
        app.fill(225, 100, 34);
        app.ellipse(this.getPosX(), this.getPosY(), 20, 20);
        app.popStyle();
    }
}
