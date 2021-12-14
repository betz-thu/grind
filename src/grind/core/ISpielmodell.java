package grind.core;

import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import processing.core.PApplet;

public interface ISpielmodell {

    void betreteSzene(int n);

    void bewege();

    void zeichne(PApplet app);

    ISpielfigur getFigur();

    ITileMap getTileMap();


    List<ISchatz> getSchaetze();

    List<IMovable> getMovables();


    int getSzeneNr();

    void setSzeneNr(int szeneNR);

    ISzene getSzene();
}
