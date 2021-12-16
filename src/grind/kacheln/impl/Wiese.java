package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PImage;

public class Wiese implements IKachel {
    transient PImage img;
    transient boolean bildGeladen = false;
    String kachelTyp = "Wiese";

    public Wiese(){
        this.img = new PImage();
        //Bild hier laden eager
    }

    /**
     * Gibt einen Boolean zurück ob die Kachel betretbar ist
     * @return Betretbar Ja/Nein
     */
    @Override
    public boolean istBetretbar() {
        return true;
    }

    /**
     * Gibt einen Boolean zurück ob die Kachel ein Hindernis ist
     * @return Hindernis Ja/Nein
     */
    @Override
    public boolean istHindernis() {
        return false;
    }

    /**
     * Lädt die Bilddatei in ein PImage und gibt dieses dann zurück
     * @param app Das Applet mit welchem das Bild geladen werden soll
     * @return Das geladene PImage
     */
    private PImage getImage(PApplet app) {
        if (!bildGeladen){ //Das wäre Lazy
            this.img = app.loadImage("gras.png");
            img.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
            bildGeladen = true;
        }
        return this.img;
    }

    /**
     * Zeichnet die aktuelle Kachel mit dem lazy geladenen Bild
     * @param app Auf das zu zeichnende Applet
     * @param x Koordinate auf dem Applet
     * @param y Koordinate auf dem Applet
     */
    @Override
    public void zeichne(PApplet app, int x, int y) {
        app.image(getImage(app), x, y);
//        app.pushStyle();
//        app.fill(127,255,0);
//        app.stroke(120);
//        app.strokeWeight(2f);
//        app.rect(x, y, 39, 39);
//        app.popStyle();
    }
}
