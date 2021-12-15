package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PImage;

public class Baum implements IKachel {

    PImage img;
    boolean bildGeladen = false;

    /**
     *
     */
    public Baum(){
        this.img = new PImage();
        //Bild hier laden eager
    }

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
     * @return
     */
    private PImage getImage(PApplet app) {
        if (!bildGeladen){ //Das wäre Lazy
            this.img = app.loadImage("baum.png");
            img.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
            bildGeladen = true;
        }
        return this.img;
    }

    /**
     *
     * @param app
     * @param x
     * @param y
     */
    @Override
    public void zeichne(PApplet app, int x, int y) {
        app.image(getImage(app), x, y);
//        app.pushStyle();
//        app.fill(69,139,0); //Grün
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
        img.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
    }
}
