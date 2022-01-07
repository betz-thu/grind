package grind.welt.impl;


import grind.welt.ILevel;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.impl.*;
import grind.movables.monster.DornPflanze;
import grind.movables.monster.Geist;
import grind.movables.monster.Zombie;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.welt.ILevel;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Autor Megatronik
 * Instanziierung der Spielfigur angepasst, Spielfigur besitzt nun eine Ausrichtung.
 */
public class DummyLevel implements ILevel {

    ITileMap tileMap;
    ArrayList<IMovable> positionen = new ArrayList<>();


    public DummyLevel (){

    }

    /**
     * Gibt die Tilemap des aktuellen Levels zurück
     * @return Tilemap des aktuellen Levels
     */
    @Override
    public ITileMap getTileMap() {
        return this.tileMap;
    }

    /**
     * Gibt eine Liste mit den Positionen der Movables zum Szenenstart zurück
     * @return List<IMovable> Liste mit allen Positionen der Movables
     */
    @Override
    public List<IMovable> getPositionen() {
        return positionen;
    }

    /**
     * Setzt die übergebene Tilemap im Level
     * @param tileMap Die zu setzende Tilemap
     */
    @Override
    public void setTilemap(ITileMap tileMap) {
        this.tileMap = tileMap;
    }

    /**
     * Fügt ein Movable der Liste Positionen zu
     * @param movable Das Movable welches hinzugefügt werden soll
     */
    @Override
    public void addPosition(IMovable movable) {
        this.positionen.add(movable);
    }

    /**
     * Löscht alle Movables aus Positionen
     */
    @Override
    public void clearPosition() {
        this.positionen.clear();
    }

    /**
     * Zeichnet die aktuelle Tilemap auf dem Applet app
     * @param app Applet auf dem gezeichnet wird
     */
    @Override
    public void zeichne(PApplet app) {
        this.tileMap.zeichne(app);
    }

}
