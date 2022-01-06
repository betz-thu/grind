package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
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

    @Override
    public void beimAnwenden(ISpielfigur figur){
        super.beimAnwenden(figur);
        figur.playSwallowSound();
    }
}
