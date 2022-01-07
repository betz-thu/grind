package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Apfel extends Nahrung{

    private int größe;


    public Apfel(int posX, int posY) {
        super(posX, posY, Einstellungen.GROESSE_APFEL);
        this.punkte = 5; //  jeder Apfel startet mit Wert 5 @Team Leveleditor: gerne ändern
        this.wert = 3;  // jeder Gegenstand startet mit Wert 3 @Team Leveleditor: gerne ändern
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
