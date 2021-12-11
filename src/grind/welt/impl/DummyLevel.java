package grind.welt.impl;

import grind.movables.monster.DornPflanze;
import grind.movables.monster.Geist;
import grind.movables.monster.Zombie;
import grind.util.Einstellungen;
import grind.welt.ILevel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.LeereTileMap;
import grind.movables.IMovable;
import grind.movables.impl.Gold;
import grind.movables.impl.Spielfigur;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyLevel implements ILevel {

    ITileMap tileMap;

    public DummyLevel() {
        this.tileMap = new LeereTileMap();
    }

    @Override
    public ITileMap getTileMap() {
        return new LeereTileMap();
    }

    @Override
    public List<IMovable> getPositionen() {
        Random random = new Random();
        ArrayList<IMovable> positionen = new ArrayList<>();
        positionen.add(new Gold(600, 200));
        positionen.add(new Spielfigur(600, 400));
        positionen.add(new Geist(60,200,tileMap));
       positionen.add(new DornPflanze((float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_X)+1) * Einstellungen.LAENGE_KACHELN_X),
               (float)((random.nextInt(Einstellungen.ANZAHL_KACHELN_Y)+1) * Einstellungen.LAENGE_KACHELN_Y), tileMap));
//        positionen.add(new Zombie((random.nextInt(Einstellungen.ANZAHL_KACHELN_X)+1) * Einstellungen.LAENGE_KACHELN_X,
//                (random.nextInt(Einstellungen.ANZAHL_KACHELN_Y)+1) * Einstellungen.LAENGE_KACHELN_Y));


        positionen.add(new DornPflanze(280, 80, tileMap));
        positionen.add(new Zombie(800, 500, tileMap));
        return positionen;
    }

    @Override
    public void zeichne(PApplet app) {
        tileMap.zeichne(app);
    }

}
