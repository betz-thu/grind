package grind.core;

import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import processing.core.PApplet;

public interface ISpielmodell {

    void betreteSzene(int n);

    void bewege();

    void zeichne(PApplet app);

    ISpielfigur getFigur();

    ITileMap getTileMap();
}
