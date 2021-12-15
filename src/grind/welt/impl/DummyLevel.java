package grind.welt.impl;

import grind.movables.impl.Apfel;
import grind.kacheln.impl.Levelausgang;
import grind.welt.ILevel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.TileMap;
import grind.movables.IMovable;
import grind.movables.impl.Gold;
import grind.movables.impl.Spielfigur;
import grind.welt.ILevel;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DummyLevel implements ILevel {

    ITileMap tileMap;

    /**
     *
     */
    public DummyLevel (){
        this.tileMap = new TileMap();
    }

    /**
     *
     * @return
     */
    @Override
    public ITileMap getTileMap() {
        return this.tileMap;
    }

    /**
     *
     * @return
     */
    @Override
    public List<IMovable> getPositionen() {
        ArrayList<IMovable> positionen = new ArrayList<>();
        positionen.add(new Gold(600, 200));
        positionen.add(new Spielfigur(600, 400));
        positionen.add(new Apfel(400,200));
        return positionen;
    }

    /**
     *
     * @return
     */
    @Override
    public ILevel getLevel() {
        return this;
    }

    /**
     *
     * @param app
     */
    @Override
    public void zeichne(PApplet app) {
        tileMap.zeichne(app);
    }
}
