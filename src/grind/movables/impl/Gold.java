package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Gold extends Waehrung {

    public Gold(int posX, int posY) {
        super(posX, posY, Einstellungen.GROESSE_GOLD);
    }

    @Override
    public int getWert(){
        return 1;
    }
}
