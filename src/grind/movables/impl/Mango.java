package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;

public class Mango extends Nahrung{

    public Mango(int posX, int posY) {
        super(posX, posY, Einstellungen.GROESSE_MANGO);
        this.punkte = 10; //  Mango startet mit Wert 10 @Team Leveleditor: gerne ändern
        this.wert = 3;  // jeder Gegenstand startet mit Wert 3 @Team Leveleditor: gerne ändern
    }

    public void zeichne(Spielsteuerung app){
        app.pushStyle();
        app.stroke(225, 100, 34);
        app.fill(225, 100, 34);
        app.ellipse(this.getPosX(), this.getPosY(), Einstellungen.GROESSE_MANGO, Einstellungen.GROESSE_MANGO);
        app.popStyle();
    }
    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playApfelSound();
    }

}
