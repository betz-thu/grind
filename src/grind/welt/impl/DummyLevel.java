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

//        this.tileMap = new TileMap();
//
//        Random random = new Random();
//        positionen.add(new Gold(650, 400));
//        positionen.add(new Geist(60,200,tileMap));
//        positionen.add(new DornPflanze(200, 50, tileMap));
//        positionen.add(new DornPflanze(600, 500, tileMap));
//        positionen.add(new Heiltrank(700, 400));
//        positionen.add(new Mango(750, 400));
//        float ZombiePosX = (float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_X)+1) * Einstellungen.LAENGE_KACHELN_X);
//        float ZombiPosY = (float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_Y)+1) * Einstellungen.LAENGE_KACHELN_Y);
//        positionen.add(new Zombie(ZombiePosX,ZombiPosY,tileMap));
//        positionen.add(new Zombie(350, 600, tileMap));
//        positionen.add(new Spielfigur(600, 400,Richtung.N));
//        positionen.add(new Schwert(650,450,1));

//        Random random = new Random();
//        positionen.add(new Gold(650, 400, Einstellungen.GROESSE_GOLD));
//        positionen.add(new Heiltrank(700, 400, Einstellungen.GROESSE_HEILTRANK));
//        positionen.add(new Mango(750, 400, Einstellungen.GROESSE_MANGO));
//        positionen.add(new Geist(60,200,tileMap, Einstellungen.GROESSE_GEIST));
//        positionen.add(new DornPflanze(200, 50, tileMap, Einstellungen.GROESSE_DORNPFLANZE));
//        positionen.add(new DornPflanze(600, 500, tileMap, Einstellungen.GROESSE_DORNPFLANZE));
//        positionen.add(new Zombie((float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_X)+1) * Einstellungen.LAENGE_KACHELN_X),
//                (float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_Y)+1) * Einstellungen.LAENGE_KACHELN_Y),tileMap, Einstellungen.GROESSE_ZOMBIE));
//        positionen.add(new Zombie(350, 600, tileMap, Einstellungen.GROESSE_ZOMBIE));
//        positionen.add(new Spielfigur(600, 400,Richtung.N, Einstellungen.GROESSE_SPIELFIGUR));
//      positionen.add(new Stern(800,400));
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
        /*Random random = new Random();
        ArrayList<IMovable> positionen = new ArrayList<>();
        positionen.add(new Gold(600, 200));
        positionen.add(new Spielfigur(600, 400, Richtung.N ));
        positionen.add(new Geist(60,200,tileMap));
        positionen.add(new DornPflanze(200, 50, tileMap));
        positionen.add(new DornPflanze(600, 500, tileMap));
        positionen.add(new Zombie((float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_X)+1) * Einstellungen.LAENGE_KACHELN_X),
                (float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_Y)+1) * Einstellungen.LAENGE_KACHELN_Y),tileMap));
        positionen.add(new Zombie(350, 600, tileMap));
        //positionen.add(new Spielfigur(600, 400,Richtung.N));*/
        return positionen;
    }

    /**
     * Gibt das aktuelle Level zurück
     * @return ILevel
     */
    @Override
    public ILevel getLevel() {
        return this;
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
