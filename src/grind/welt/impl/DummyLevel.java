package grind.welt.impl;

import grind.kacheln.ITileMap;
import grind.kacheln.impl.TileMap;
import grind.movables.IMovable;
import grind.movables.impl.Gold;
import grind.movables.impl.Heiltrank;
import grind.movables.impl.Mango;
import grind.movables.impl.Spielfigur;
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

        this.tileMap = new TileMap();

        Random random = new Random();
        positionen.add(new Gold(650, 400));
        positionen.add(new Geist(60,200,tileMap));
        positionen.add(new Heiltrank(700, 400));
        positionen.add(new Mango(750, 400));
        positionen.add(new Spielfigur(600, 400,Richtung.N));
    }

    @Override
    public ITileMap getTileMap() {
        return this.tileMap;
    }

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

    @Override
    public ILevel getLevel() {
        return this;
    }

    @Override
    public void zeichne(PApplet app) {
        tileMap.zeichne(app);
    }

}
