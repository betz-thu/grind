package grind.welt.impl;

import grind.welt.ILevel;
import grind.welt.kacheln.ITileMap;
import grind.allgemein.Einstellungen;
import grind.welt.kacheln.impl.LeereTileMap;
import grind.welt.movables.IMovable;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class LeererLevel implements ILevel {

    ITileMap tileMap;

    public LeererLevel() {
        this.tileMap = new LeereTileMap();
    }

    @Override
    public ITileMap getTileMap() {
        return new LeereTileMap();
    }

    @Override
    public List<IMovable> getPositionen() {
        return new ArrayList<>();
    }

    @Override
    public void zeichne(PApplet app) {
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                int y = i * Einstellungen.LAENGE_KACHELN_Y;
                int x = j * Einstellungen.LAENGE_KACHELN_X;
                tileMap.getKachel(i, j).zeichne(app, x, y);
            }
        }
    }
}
