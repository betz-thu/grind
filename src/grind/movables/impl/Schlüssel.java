package grind.movables.impl;

import grind.movables.ISchluessel;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.util.Farbe;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public abstract class Schlüssel extends Gegenstand implements ISchluessel {

    public static int GROESSE = (int) (Einstellungen.LAENGE_KACHELN_X * 0.8);

    public Schlüssel(int posX, int posY) {
        super(posX, posY, GROESSE);
    }

    abstract PImage getImage(PApplet app);

    @Override
    public void zeichne(PApplet app) {
        app.pushStyle();
        app.imageMode(PConstants.CENTER);
        app.image(getImage(app), posX, posY, GROESSE, GROESSE);
        app.popStyle();
    }

    @Override
    public void beimAnwenden(ISpielfigur figur) {
        // TODO
    }
}
