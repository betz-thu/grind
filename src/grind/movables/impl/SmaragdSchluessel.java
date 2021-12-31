package grind.movables.impl;

import grind.util.Farbe;
import processing.core.PApplet;
import processing.core.PImage;

public class SmaragdSchluessel extends Schlüssel {

    private static transient PImage image = null;

    public SmaragdSchluessel(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    String getFileName() {
        return "key_emerald.png";
    }

    @Override
    public Farbe getFarbe() {
        return Farbe.RUBIN;
    }
}
