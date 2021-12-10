package grind.kacheln.impl;

import grind.kacheln.IKachel;
import processing.core.PApplet;

public class DummyHindernis implements IKachel {

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

}
