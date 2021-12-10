package grind.kacheln.impl;

import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import processing.core.PApplet;

public class TileMap implements ITileMap {

    int hoehe;
    int breite;
    IKachel[][] kacheln;

    public TileMap() {
        this.hoehe = Einstellungen.ANZAHL_KACHELN_Y;
        this.breite = Einstellungen.ANZAHL_KACHELN_X;
        this.kacheln = new IKachel[this.hoehe][this.breite];
    }

    @Override
    public int getHoehe() {
        return hoehe;
    }

    @Override
    public int getBreite() {
        return breite;
    }

    @Override
    public IKachel getKachel(int i, int j) {
        return kacheln[i][j];
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
