package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;


public class DornPflanze extends Monster{
    int deltaX;
    int deltaY;

    public DornPflanze(float posX, float posY, int deltaX, int deltaY) {


        super(posX, posY);
    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(0,255,127);
        app.ellipse(deltaX, deltaY,(float) Einstellungen.LAENGE_KACHELN_X , (float)Einstellungen.ANZAHL_KACHELN_Y);
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
