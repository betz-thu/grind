package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.movables.ISpielfigur;
import processing.core.PApplet;
import grind.util.Einstellungen;

public class Geist extends Monster{
    int deltaX = 10;
    int deltaY;

    public Geist(float posX, float posY) {
        super(posX, posY);
    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(255,255,255);
        app.ellipse(deltaX, deltaY,(float)Einstellungen.LAENGE_KACHELN_X/2 , (float)Einstellungen.ANZAHL_KACHELN_Y/2);
    }

    @Override
    public void bewege() {

    }

    @Override
    public void beiKollision(ISpielfigur figur) {

    }

    @Override
    public void vorBetreten(IKachel kachel) {

    }
}
