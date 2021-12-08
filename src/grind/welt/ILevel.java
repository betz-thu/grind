package grind.welt;

import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import processing.core.PApplet;

import java.util.List;

public interface ILevel extends ISzene {

    ITileMap getTileMap();
    List<IMovable> getPositionen();
    void zeichne(PApplet app);
}
