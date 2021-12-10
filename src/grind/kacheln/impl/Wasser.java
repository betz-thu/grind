package grind.kacheln.impl;

import grind.kacheln.IKachel;
import processing.core.PApplet;
import processing.core.PImage;

public class Wasser implements IKachel {
    PImage img;
    @Override
    public boolean istBetretbar() {
        return false;
    }

    @Override
    public boolean istHindernis() {
        return false;
    }

    @Override
    public void zeichne(PApplet app, int x, int y) {
        app.pushStyle();
        app.fill(28,134,238);//Hellblau
        app.stroke(120);
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
