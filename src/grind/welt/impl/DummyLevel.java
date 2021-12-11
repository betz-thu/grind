package grind.welt.impl;

import grind.movables.impl.Apfel;
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
        positionen.add(new Apfel(400,200));
        positionen.add(new Apfel(300,200));
        positionen.add(new Apfel(200,200));
        positionen.add(new Apfel(100,200));
        positionen.add(new Apfel(250,200));
        positionen.add(new Apfel(10,200));
        positionen.add(new Apfel(50,200));
        positionen.add(new Apfel(20,200));
        return positionen;
    }

    @Override
    public void zeichne(PApplet app) {
        tileMap.zeichne(app);
    }

}
