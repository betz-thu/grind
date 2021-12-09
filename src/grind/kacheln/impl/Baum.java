package grind.kacheln.impl;

import grind.kacheln.IKachel;
import processing.core.PApplet;

public class Baum implements IKachel {

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
        app.fill(69,139,0); //Grün
        app.stroke(120);
        app.strokeWeight(2f);
        app.rect(x, y, 39, 39);
        app.popStyle();
    }
}
