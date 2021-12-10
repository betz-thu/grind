package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.util.Richtung;
import grind.movables.ISpielfigur;
import processing.core.PApplet;
import processing.core.PImage;

public class Spielfigur extends Movable implements ISpielfigur {

    private static final float GESCHWINDIGKEIT = 3f;

    int gold = 0;

    public Spielfigur(float posX, float posY) {
        super(posX, posY
        );
    }

    @Override
    public void zeichne(PApplet app) {
        PImage spielfigurOhneWaffe = app.loadImage("SpielfigurOhneWaffe.jpg");
        app.image(spielfigurOhneWaffe,this.getPosX(), this.getPosY(),40, 40);
        /*app.pushStyle();
        app.fill(80);
        app.color(50, 100, 150);
        app.strokeWeight(2);
        app.ellipse(this.getPosX(), this.getPosY(), 40, 40);
        app.popStyle();*/
    }

    @Override
    public void bewege(Richtung richtung) {
        switch (richtung) {
            case N:
                this.posY -= GESCHWINDIGKEIT;
                break;
            case O:
                this.posX += GESCHWINDIGKEIT;
                break;
            case S:
                this.posY += GESCHWINDIGKEIT;
                break;
            case W:
                this.posX -= GESCHWINDIGKEIT;
                break;
        }
    }


    @Override
    public void bewege() {
        // Spielfigur bewegt sich nicht von selbst
    }

    @Override
    public void erhoeheGold(int betrag) {
        System.out.printf("TODO: Erhöhe Gold um %d.", betrag);
    }




}
