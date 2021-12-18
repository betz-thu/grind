package grind.movables.impl;

import grind.movables.ISpielfigur;
import processing.core.PApplet;

public class Levelende extends Gegenstand{
    public Levelende(int posX, int posY) {
        super(posX, posY);
    }

    public void zeichne(PApplet app){
        app.pushStyle();
        app.stroke(255, 255, 255);
        app.fill(255, 255, 255);
        app.ellipse(this.getPosX(), this.getPosY(), 20, 20);
        app.popStyle();
    }
    public void beimAnwenden(ISpielfigur figur){
        // TODO: TEAM 3

    }
}
