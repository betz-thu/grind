package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Heiltrank extends Nahrung {
    private int punkte = 20;


    public Heiltrank(int posX, int posY) {
        super(posX, posY, Einstellungen.GROESSE_HEILTRANK);
    }

    public int getPunkte() {
        return this.punkte;
    }

    public void zeichne(PApplet app) {
        app.pushStyle();
        app.stroke(104,34,139);
        app.fill(104,34,139);
        app.ellipse(this.getPosX(), this.getPosY(), Einstellungen.GROESSE_HEILTRANK, Einstellungen.GROESSE_HEILTRANK);
        app.popStyle();
    }
    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playSwallowSound();
    }
}
