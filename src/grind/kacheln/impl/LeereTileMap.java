package grind.kacheln.impl;

import grind.util.Einstellungen;
import grind.kacheln.IKachel;
import processing.core.PApplet;

public class LeereTileMap extends TileMap {

    int hoehe;
    int breite;
    IKachel leereKachel = new LeereKachel();
    IKachel hindernis = new DummyHindernis();

    @Override
    public int getHoehe() {
        return hoehe;
    }

    @Override
    public int getBreite() {
        return breite;
    }

    @Override
    public IKachel getKachel(int x, int y) {
        if ((x == 10 && y < 10) || (y == 20 && x<10)){
            return hindernis;
        } else {
            return leereKachel;
        }

    }

    @Override
    public void zeichne(PApplet app) {
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                int y = i * Einstellungen.LAENGE_KACHELN_Y;
                int x = j * Einstellungen.LAENGE_KACHELN_X;
                this.getKachel(i, j).zeichne(app, x, y);
            }
        }
    }
}
