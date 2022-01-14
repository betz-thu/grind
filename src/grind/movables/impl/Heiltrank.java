package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Heiltrank extends Nahrung {



    public Heiltrank(int posX, int posY, int punkte, int wert) {
        super(posX, posY, Einstellungen.LAENGE_KACHELN_X/2);
        this.punkte = punkte;
        this.wert = wert;
    }



    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playSwallowSound();
    }
}
