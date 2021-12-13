package grind.movables.impl;

import grind.movables.ISpielfigur;
import processing.core.PApplet;

public class Gold extends Waehrung {

    public Gold(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    int getWert() {
        return 1;
    }



    @Override
    public void zeichne(PApplet app) {
        app.pushStyle();
        app.stroke(200, 225, 75);
        app.fill(175, 125, 75);
        app.ellipse(posX, posY, 20, 20);
        app.popStyle();
    }
}
