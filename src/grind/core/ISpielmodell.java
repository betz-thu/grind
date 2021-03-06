package grind.core;

import grind.core.impl.Spielmodell;
import grind.core.impl.Spielsteuerung;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.impl.Movable;
import grind.movables.monster.IMonster;
import grind.movables.ISpielfigur;
import grind.movables.monster.IMonster;
import grind.welt.ISpielwelt;
import grind.movables.monster.Monster;
import grind.welt.ISzene;
import processing.core.PApplet;

import java.util.List;

public interface ISpielmodell {

    void betreteSzene(int n);
    void bewege();
    void zeichne(Spielsteuerung spielsteuerung);

    ISpielfigur getFigur();
    ITileMap getTileMap();
    List<ISchatz> getSchaetze();
    List<IMovable> getMovables();

    int getSzeneNr();
    void setSzeneNr(int szeneNR);

    ISzene getSzene();

    void addMonster(IMonster monster);
    void removeMovable(IMovable movable);

    void entferneToteMonster();

    ISpielwelt getSpielwelt();

    void setSpielwelt(ISpielwelt spielwelt);

}
