package grind.welt.impl;

import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.welt.ILevel;
import processing.core.PApplet;

import java.util.List;

public class LevelZwei implements ILevel {

    @Override
    public ITileMap getTileMap() {
        return null;
    }

    @Override
    public List<IMovable> getPositionen() {
        return null;
    }

    @Override
    public ILevel getLevel() {
        return this;
    }

    @Override
    public void zeichne(PApplet app) {

    }

}
