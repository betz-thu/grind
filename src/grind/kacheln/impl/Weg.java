package grind.kacheln.impl;

import grind.kacheln.IKachel;
import processing.core.PApplet;

public class Weg implements IKachel {

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
        app.fill(168,168,168);
        app.stroke(120);
        app.strokeWeight(2f);
        app.rect(x, y, 39, 39);
        app.popStyle();
    }
}
