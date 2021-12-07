package grind.core.impl;

import grind.allgemein.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.LeereSpielwelt;
import processing.core.PApplet;

public class Spielsteuerung extends PApplet {

    ISpielmodell spielmodell;

    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new LeereSpielwelt());
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

    private void eingabe() {
        if (keyPressed) {
            if (key == 'a' || keyCode == LEFT) {
                spielmodell.getFigur().bewege(Richtung.W);
            } else if (key == 'w' || keyCode == UP) {
                spielmodell.getFigur().bewege(Richtung.N);
            } else if (key == 's' || keyCode == DOWN) {
                spielmodell.getFigur().bewege(Richtung.S);
            } else if (key == 'd' || keyCode == RIGHT) {
                spielmodell.getFigur().bewege(Richtung.O);
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

    public static void main(String[] args) {
        String[] params = {"Beispiel 1"};
        PApplet.runSketch(params, new Spielsteuerung());
    }
}
