package grind.welt.impl;

import grind.movables.monster.DornPflanze;
import grind.movables.monster.Geist;
import grind.movables.monster.Zombie;
import grind.welt.ILevel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.LeereTileMap;
import grind.movables.IMovable;
import grind.movables.impl.Gold;
import grind.movables.impl.Spielfigur;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DummyLevel implements ILevel {

    ITileMap tileMap;

    public DummyLevel() {
        this.tileMap = new LeereTileMap();
    }

    @Override
    public ITileMap getTileMap() {
        return new LeereTileMap();
    }

    @Override
    public List<IMovable> getPositionen() {
        ArrayList<IMovable> positionen = new ArrayList<>();
        positionen.add(new Gold(600, 200));
        positionen.add(new Spielfigur(600, 400));
        Geist geist = new Geist(60, 200, tileMap);
        positionen.add(geist);

        positionen.add(new DornPflanze(300, 100, tileMap));
        positionen.add(new Zombie(800, 500, tileMap));
        return positionen;
    }

    @Override
    public void zeichne(PApplet app) {
        tileMap.zeichne(app);
    }

}
