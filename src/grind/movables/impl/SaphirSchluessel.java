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
    PImage getImage(PApplet app) {
        if (image == null) {
            image = app.loadImage("key_sapphire.png");
        }
        return image;
    }
}
