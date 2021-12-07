package grind.welt.kacheln.impl;

import grind.allgemein.Einstellungen;
import grind.welt.kacheln.IKachel;
import grind.welt.kacheln.ITileMap;

public class LeereTileMap implements ITileMap {

    int hoehe;
    int breite;
    IKachel leereKachel = new LeereKachel();
    IKachel hindernis = new DummyHindernis();

    public LeereTileMap() {
        this.hoehe = Einstellungen.ANZAHL_KACHELN_Y;
        this.breite = Einstellungen.ANZAHL_KACHELN_X;
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
    public IKachel getKachel(int x, int y) {
        if (x == 10 && y < 10) {
            return hindernis;
        } else {
            return leereKachel;
        }
    }
}
