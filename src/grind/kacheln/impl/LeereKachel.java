package grind.kacheln.impl;

import grind.kacheln.IKachel;
import processing.core.PApplet;
import processing.core.PImage;

public class LeereKachel implements IKachel {
    PImage img;
    @Override
    public boolean istBetretbar() {
        return true;
    }

    @Override
    public boolean istHindernis() {
        return false;
    }

    @Override
    public void zeichne(PApplet app, int x, int y) {
        app.pushStyle();
        app.fill(25);
        app.stroke(255);
        app.strokeWeight(2f);
        app.rect(x, y, 39, 39);
        app.popStyle();
    }
//    @Override
//    public void ladeDatei(String dateiname, PApplet app, int breite, int hoehe) {
//        img = app.loadImage(dateiname);
//        img.resize(breite, hoehe);
//    }
}
