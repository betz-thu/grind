package grind.kacheln.impl;

import grind.kacheln.IKachel;
import processing.core.PApplet;

public class Fels implements IKachel {

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
        app.fill(91,58,41); //Braun
        app.stroke(120);
        app.strokeWeight(2f);
        app.rect(x, y, 39, 39);
        app.popStyle();
    }
}
