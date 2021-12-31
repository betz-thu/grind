package grind.movables.impl;

import grind.util.Farbe;
import processing.core.PApplet;
import processing.core.PImage;

public class SaphirSchluessel extends Schlüssel {

    private static transient PImage image = null;

    public SaphirSchluessel(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public Farbe getFarbe() {
        return Farbe.RUBIN;
    }

    @Override
    String getFileName() {
        return "key_sapphire.png";
    }
}
