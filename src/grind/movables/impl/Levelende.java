package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Levelende extends Gegenstand{
    public Levelende(int posX, int posY) {
        super(posX, posY, Einstellungen.LAENGE_KACHELN_X/2);
    }

    public void zeichne(Spielsteuerung app){
        app.pushStyle();
        app.stroke(255, 255, 255);
        app.fill(255, 255, 255);
        app.ellipse(this.getPosX(), this.getPosY(), groesse, groesse);
        app.popStyle();
    }
    public void beimAnwenden(ISpielfigur figur){
        // TODO: TEAM 3

    }
}
