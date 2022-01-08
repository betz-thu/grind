package grind.welt.impl;

import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.welt.ILevel;
import grind.welt.ISiedlung;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DummySiedlung implements ISiedlung {

    ITileMap tileMap;
    ArrayList<IMovable> positionen = new ArrayList<>();

    @Override
    public ITileMap getTileMap() {
        return this.tileMap;
    }

    @Override
    public List<IMovable> getPositionen() {
        return this.positionen;
    }

    @Override
    public void setTilemap(ITileMap tileMap) {
        this.tileMap = tileMap;
    }

    @Override
    public void addPosition(IMovable movable) {
        this.positionen = positionen;
    }

    @Override
    public void clearPosition() {
        this.positionen.clear();
    }

    @Override
    public void zeichne(PApplet app) {
        this.tileMap.zeichne(app);
    }
}
