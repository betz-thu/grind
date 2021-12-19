package grind.welt.impl;

import grind.util.Richtung;
import grind.movables.impl.Apfel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.monster.DornPflanze;
import grind.movables.monster.Geist;
import grind.movables.monster.Zombie;
import grind.util.Einstellungen;
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
import java.util.Random;

/**
 * @Autor Megatronik
 * Instanziierung der Spielfigur angepasst, Spielfigur besitzt nun eine Ausrichtung.
 */
public class DummyLevel implements ILevel {

    ITileMap tileMap;
    ArrayList<IMovable> positionen = new ArrayList<>();


    public DummyLevel (){
        this.tileMap = new TileMap();
        this.movables = getPositionen();

        Random random = new Random();
        positionen.add(new Gold(650, 400, Einstellungen.GROESSE_GOLD));
        positionen.add(new Geist(60,200,tileMap, Einstellungen.GROESSE_GEIST));
        positionen.add(new DornPflanze(200, 50, tileMap, Einstellungen.GROESSE_DORNPFLANZE));
        positionen.add(new DornPflanze(600, 500, tileMap, Einstellungen.GROESSE_DORNPFLANZE));
        positionen.add(new Zombie((float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_X)+1) * Einstellungen.LAENGE_KACHELN_X),
                (float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_Y)+1) * Einstellungen.LAENGE_KACHELN_Y),tileMap, Einstellungen.GROESSE_ZOMBIE));
        positionen.add(new Zombie(350, 600, tileMap, Einstellungen.GROESSE_ZOMBIE));
        positionen.add(new Spielfigur(600, 400,Richtung.N, Einstellungen.GROESSE_SPIELFIGUR));
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
     * Gibt das aktuelle Level zurück
     * @return ILevel
     */
    @Override
    public ILevel getLevel() {
        return this;
    }

    /**
     * Zeichnet die aktuelle Tilemap auf dem Applet app
     * @param app Applet auf dem gezeichnet wird
     */
    @Override
    public void zeichne(PApplet app) {
        tileMap.zeichne(app);
    }

}
