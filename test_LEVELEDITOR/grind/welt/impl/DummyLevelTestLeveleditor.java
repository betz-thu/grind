package grind.welt.impl;

import grind.kacheln.ITileMap;
import grind.kacheln.impl.TileMap;
import grind.movables.IMovable;
import grind.movables.impl.Apfel;
import grind.movables.impl.Gold;
import grind.welt.ILevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DummyLevelTestLeveleditor {

    ITileMap tileMap;
    ITileMap tempTilemap;
    ArrayList<IMovable> positionen = new ArrayList<>();
    IMovable tempApfel;
    IMovable tempGold;

    @Before
    public void setUp() throws Exception {
        this.tileMap = new TileMap();
        this.tempApfel = new Apfel(0,0,0,0);
        this.tempGold = new Gold(0,0);
        this.positionen.add(tempApfel);
        this.positionen.add(tempGold);
    }

//    @Test
//    public void getTileMap() {
//        Assert.assertEquals();
//    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPositionen() {
        Assert.assertEquals(tempApfel, positionen.get(0));
        Assert.assertEquals(tempGold, positionen.get(1));
        Assert.assertNull(positionen.get(2));
    }

//    @Test
//    public void setTilemap() {
//
//    }

    @Test
    public void addPosition() {
        positionen.add(new Gold(0,0));
        Assert.assertEquals(3, positionen.size());
    }

    @Test
    public void clearPosition() {
        positionen.clear();
        Assert.assertEquals(0, positionen.size());
    }

    @Test
    public void zeichne() {

    }



}
//
//
//    @Test
//    public void getSzene() {
//        Assert.assertEquals(levelTest, szenen.get(0));
//    }
//
//    @Test
//    public void addSzene(){
//        szenen.add(new DummyLevel());
//        int tempAnzahl = szenen.size();
//        Assert.assertEquals(anzahlSzenen + 1, tempAnzahl);
//    }
//
//    @Test
//    public void getSzenenanzahl() {
//        Assert.assertEquals(anzahlSzenen, szenen.size());
//    }
//
//    @Test
//    public void removeSzenen() {
//        szenen.clear();
//        Assert.assertEquals(0,szenen.size());
//        szenen.add(new DummyLevel());
//        Assert.assertEquals(1, szenen.size());
//
//    }
//package grind.welt.impl;
//
//
//        import grind.welt.ILevel;
//        import grind.kacheln.ITileMap;
//        import grind.movables.IMovable;
//        import grind.movables.impl.*;
//        import grind.movables.monster.DornPflanze;
//        import grind.movables.monster.Geist;
//        import grind.movables.monster.Zombie;
//        import grind.util.Einstellungen;
//        import grind.util.Richtung;
//        import grind.welt.ILevel;
//        import processing.core.PApplet;
//
//        import java.util.ArrayList;
//        import java.util.List;
//        import java.util.Random;
//
///**
// * @Autor Megatronik
// * Instanziierung der Spielfigur angepasst, Spielfigur besitzt nun eine Ausrichtung.
// */
//public class DummyLevel implements ILevel {
//
//    ITileMap tileMap;
//    ArrayList<IMovable> positionen = new ArrayList<>();
//
//
//    public DummyLevel (){
//
//    }
//
//    /**
//     * Gibt die Tilemap des aktuellen Levels zurück
//     * @return Tilemap des aktuellen Levels
//     */
//    @Override
//    public ITileMap getTileMap() {
//        return this.tileMap;
//    }
//
//    /**
//     * Gibt eine Liste mit den Positionen der Movables zum Szenenstart zurück
//     * @return List<IMovable> Liste mit allen Positionen der Movables
//     */
//    @Override
//    public List<IMovable> getPositionen() {
//        return positionen;
//    }
//
//    /**
//     * Setzt die übergebene Tilemap im Level
//     * @param tileMap Die zu setzende Tilemap
//     */
//    @Override
//    public void setTilemap(ITileMap tileMap) {
//        this.tileMap = tileMap;
//    }
//
//    /**
//     * Fügt ein Movable der Liste Positionen zu
//     * @param movable Das Movable welches hinzugefügt werden soll
//     */
//    @Override
//    public void addPosition(IMovable movable) {
//        this.positionen.add(movable);
//    }
//
//    /**
//     * Löscht alle Movables aus Positionen
//     */
//    @Override
//    public void clearPosition() {
//        this.positionen.clear();
//    }
//
//    /**
//     * Zeichnet die aktuelle Tilemap auf dem Applet app
//     * @param app Applet auf dem gezeichnet wird
//     */
//    @Override
//    public void zeichne(PApplet app) {
//        this.tileMap.zeichne(app);
//    }
//
//}

