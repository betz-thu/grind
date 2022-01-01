package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Tor implements IKachel {

    transient PImage imgGeschlossen = null;
    transient PImage imgOffen = null;
    boolean offen = false;

    public Tor(){

    }

    abstract String getFileNameOpen();
    abstract String getFileNameClosed();

    /**
     * Gibt einen Boolean zurück ob die Kachel betretbar ist
     * @return Betretbar Ja/Nein
     */
    @Override
    public boolean istBetretbar() {
        return offen;
    }

    /**
     * Gibt einen Boolean zurück ob die Kachel ein Hindernis ist
     * @return Hindernis Ja/Nein
     */
    @Override
    public boolean istHindernis() {
        return !offen;
    }

    /**
     * Lädt die Bilddatei in ein PImage und gibt dieses dann zurück
     * @param app Das Applet mit welchem das Bild geladen werden soll
     * @return Das geladene PImage
     */
    private PImage getImageGeschlossen(PApplet app) {
        if (imgGeschlossen == null){
            imgGeschlossen = app.loadImage(getFileNameClosed());
            imgGeschlossen.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
        }
        return imgGeschlossen;
    }

    private PImage getImageOffen(PApplet app) {
        if (imgOffen == null){
            imgOffen = app.loadImage(getFileNameOpen());
            imgOffen.resize(Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
        }
        return imgGeschlossen;
    }

    /**
     * Zeichnet die aktuelle Kachel mit dem lazy geladenen Bild
     * @param app Auf das zu zeichnende Applet
     * @param x Koordinate auf dem Applet
     * @param y Koordinate auf dem Applet
     */
    @Override
    public void zeichne(PApplet app, int x, int y) {
        if (offen) {
            app.image(getImageOffen(app), x, y);
        } else {
            app.image(getImageGeschlossen(app), x, y);
        }
    }
}
