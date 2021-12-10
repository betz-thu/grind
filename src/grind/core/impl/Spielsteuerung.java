package grind.core.impl;

import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @Autor Megatronik
 * Methode eingabe ergänzt; ändert nun zusätzlich die Ausrichtung der Spielfigur passend zur Laufrichtung.
 */
public class Spielsteuerung extends PApplet {

    ISpielmodell spielmodell;
    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt());
        this.spielmodell.betreteSzene(1);
    }

    @Override
    public void settings() {
        size(1200, 800);
    }

    @Override
    public void setup() {


    }

    @Override
    public void draw() {
        eingabe();
        aktualisiere();
        zeichne();
    }

    /**
     * Methode eingabe: ändert nun zusätzlich die Ausrichtung der Spielfigur passend zur Laufrichtung.
     */
    private void eingabe() {
        Spielfigur figur = (Spielfigur) spielmodell.getFigur();
        if (keyPressed) {
            if (key == 'a' || keyCode == LEFT) {
                figur.setAusrichtung(Richtung.W);
                figur.bewege(Richtung.W);
            } else if (key == 'w' || keyCode == UP) {
                figur.setAusrichtung(Richtung.N);
                figur.bewege(Richtung.N);
            } else if (key == 's' || keyCode == DOWN) {
                figur.setAusrichtung(Richtung.S);
                figur.bewege(Richtung.S);
            } else if (key == 'd' || keyCode == RIGHT) {
                figur.setAusrichtung(Richtung.O);
                figur.bewege(Richtung.O);
            }
        }
    }

    private void aktualisiere() {
        spielmodell.bewege();

    }

    private void zeichne() {
        spielmodell.zeichne(this);
    }

    @Override
    public void mousePressed() {
        // nur notwendig, falls Maus benötigt wird
    }

}
