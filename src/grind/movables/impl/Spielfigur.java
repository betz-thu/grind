package grind.movables.impl;

import grind.util.Richtung;
import grind.movables.ISpielfigur;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Spielfigur extends Movable implements ISpielfigur {

    private static final float GESCHWINDIGKEIT = 3f;

    int gold = 0;
    private List<Gegenstand> inventar;

    public Spielfigur(float posX, float posY) {
        super(posX, posY);
        inventar = new ArrayList<>();
    }

    @Override
    public void zeichne(PApplet app) {
        // Zeichne Spielfigur
        app.pushStyle();
        app.fill(80);
        app.color(50, 100, 150);
        app.strokeWeight(2);
        app.ellipse(this.getPosX(), this.getPosY(), 40, 40);
        app.popStyle();

        // Zeichne Inventar Y: 700-750 X: 1000-1150
        app.pushStyle();
        app.fill(255,255,255);
        app.stroke(255,255,255);
        app.strokeWeight(2f);
        app.textSize(24);
        app.text("Inventar", 1120-4*30, 740);
        app.fill(204, 102, 0);
        for (int i = 0; i < 5; i++) {
            app.rect(1120-i*30, 750, 30, 30);
        }
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
    public void bewege() {
        // Spielfigur bewegt sich nicht von selbst
    }

    @Override
    public void erhoeheGold(int betrag) {
        System.out.printf("TODO: Erhöhe Gold um %d.", betrag);
    }

    @Override
    public List<Gegenstand> getInventar(){
        return this.inventar;
    }




}
