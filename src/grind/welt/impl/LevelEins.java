package grind.welt.impl;

import grind.kacheln.ITileMap;
import grind.kacheln.impl.TileMap;
import grind.movables.IMovable;
import grind.movables.impl.Gold;
import grind.movables.impl.Spielfigur;
import grind.welt.ILevel;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class LevelEins implements ILevel {

    ITileMap tileMap;

    public LevelEins (){
        this.tileMap = new TileMap();
    }

    @Override
    public ITileMap getTileMap() {
        return this.tileMap;
    }

    @Override
    public List<IMovable> getPositionen() {
        ArrayList<IMovable> positionen = new ArrayList<>();
        positionen.add(new Gold(600, 600));
        positionen.add(new Spielfigur(400, 400));
        return positionen;
    }

    @Override
    public ILevel getLevel() {
        return this;
    }

    @Override
    public void zeichne(PApplet app) {
        tileMap.zeichne(app);
    }
}
