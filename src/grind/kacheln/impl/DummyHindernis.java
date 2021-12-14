package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PImage;

public class DummyHindernis implements IKachel {
    PImage img;

    @Override
    public boolean istBetretbar() {
        return false;
    }

    @Override
    public boolean istHindernis() {
        return true;
    }

    @Override
    public void zeichne(PApplet app, int x, int y) {
        app.pushStyle();
        app.fill(220, 75, 25);
        app.stroke(120);
        app.strokeWeight(2f);
        app.rect(x, y, 39,39);
        app.popStyle();
    }

    @Override
    public void ladeDatei(String dateiname, PApplet app) {
        this.img = app.loadImage(dateiname);
        this.img.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
    }
}
