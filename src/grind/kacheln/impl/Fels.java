package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PImage;

public class Fels implements IKachel {
    PImage img;
    boolean bildGeladen = false;

    /**
     *
     * @return
     */
    @Override
    public boolean istBetretbar() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean istHindernis() {
        return true;
    }

    /**
     *
     * @param app
     * @param x
     * @param y
     */
    @Override
    public void zeichne(PApplet app, int x, int y) {
        if (!bildGeladen){
            this.img = app.loadImage("fels.png");
            img.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
            bildGeladen = true;
        }
        app.image(img, x, y);
//        app.pushStyle();
//        app.fill(91,58,41); //Braun
//        app.stroke(120);
//        app.strokeWeight(2f);
//        app.rect(x, y, 39, 39);
//        app.popStyle();
    }

    /**
     *
     * @param dateiname
     * @param app
     */
    @Override
    public void ladeDatei(String dateiname, PApplet app) {
        this.img = app.loadImage(dateiname);
        this.img.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
    }
}
