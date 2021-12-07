package grind.welt.movables.impl;

import grind.allgemein.Richtung;
import grind.welt.movables.ISpielfigur;
import processing.core.PApplet;

public class Spielfigur implements ISpielfigur {

    private static final float GESCHWINDIGKEIT = 3f;

    float posX;
    float posY;
    int gold = 0;

    @Override
    public int getPosX() {
        return (int) posX;
    }

    @Override
    public int getPosY() {
        return (int) posY;
    }

    @Override
    public void zeichne(PApplet app) {
        app.pushStyle();
        app.fill(80);
        app.color(50, 100, 150);
        app.strokeWeight(2);
        app.ellipse(posX, posY, 40, 40);
        app.popStyle();
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
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
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
