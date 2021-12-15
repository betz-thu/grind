package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.util.Einstellungen;
import processing.core.PApplet;


/* Der Levelausgang wird als (grüne)schwarze Kachel im Level dargestellt. */
public class Levelausgang implements IKachel {
    /**
     *
     * @return
     */
    @Override
    public boolean istBetretbar() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean istHindernis() {
        return false;
    }

    /**
     *
     * @param app
     * @param x
     * @param y
     */
    @Override
    public void zeichne(PApplet app, int x, int y) {
        app.pushStyle();
        app.fill(0,0,0);
        app.stroke(120);
        app.strokeWeight(2f);
        app.rect(x, y, 39, 39);
        app.popStyle();
    }

    /**
     *
     * @param dateiname
     * @param app
     */
    @Override
    public void ladeDatei(String dateiname, PApplet app) {
        //TBD
    }
}
