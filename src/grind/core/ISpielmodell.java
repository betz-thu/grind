package grind.core;

import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.impl.Movable;
import grind.movables.monster.IMonster;
import grind.movables.ISpielfigur;
import grind.movables.monster.IMonster;
import grind.movables.monster.Monster;
import grind.welt.ISzene;
import processing.core.PApplet;

import java.util.List;

public interface ISpielmodell {

    void betreteSzene(int n);

    void bewege();

    void zeichne(PApplet app);

    ISpielfigur getFigur();

    ITileMap getTileMap();


    List<ISchatz> getSchaetze();

    List<IMovable> getMovables();

    void removeMovable(IMovable movable);


    int getSzeneNr();

    void setSzeneNr(int szeneNR);

    ISzene getSzene();

    void addMonster(IMonster monster);
    void removeMovable(Monster movable);
}
