package grind.core.impl;

import grind.core.ISpielmodell;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.movables.impl.Movable;
import grind.movables.monster.Geist;
import grind.movables.monster.Monster;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import grind.movables.impl.Spielfigur;
import processing.core.PApplet;
import grind.movables.monster.IMonster;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autor Megatronik
 * Instanziierung der Spielfigur nun mit Ausrichtung.
 */
public class Spielmodell implements ISpielmodell {

    int szeneNr = 0;

    ISpielwelt spielwelt;
    ILevel level;
    ITileMap tileMap;

    ISpielfigur figur = new Spielfigur(0, 0, Richtung.N, Einstellungen.GROESSE_SPIELFIGUR);
    List<IMovable> movables = new ArrayList<>();
    List<ISchatz> schaetze = new ArrayList<>();
    List<IMonster> monster = new ArrayList<>();

    public Spielmodell(ISpielwelt spielwelt) {
        this.spielwelt = spielwelt;
    }

    @Override
    public void betreteSzene(int n) {
        ISzene szene = this.spielwelt.getSzene(n);
        if (szene instanceof ILevel) {
            ILevel level = (ILevel) szene;
            betreteLevel(level);
        } else {
            throw new UnsupportedOperationException("Siedlungen sind noch nicht implementiert.");
        }
    }

    private void betreteLevel(ILevel level) {
        this.level = level;
        kopiereTilemap();
        kopiereMovables();
    }

    private void kopiereTilemap() {
        this.tileMap = this.level.getTileMap();
    }

    private void kopiereMovables() {
        this.movables.clear();
        this.schaetze.clear();
        this.monster.clear();

        for (IMovable movable : this.level.getPositionen()) {
            if (movable instanceof ISpielfigur) {
                ISpielfigur figur = (ISpielfigur) movable;
                this.figur.setPosition(figur.getPosX(), figur.getPosY());
            } else if (movable instanceof ISchatz) {
                ISchatz schatz = (ISchatz) movable;
                this.schaetze.add(schatz);
                this.movables.add(schatz);
            } else if (movable instanceof IMonster){
                IMonster monster = (IMonster) movable;
                this.monster.add(monster);
                this.movables.add(monster);
            } else {
                this.movables.add(movable);
            }
        }
    }

    @Override
    public void bewege() {
        // die Spielfigur bewegt sich nicht von selbst
        for(IMovable movable: movables){
            movable.bewege();

        }

    }

    @Override
    public void zeichne(PApplet app) {

        if (this.level != null) {
            this.level.zeichne(app);
        }

        for (IMovable movable : this.movables) {
            movable.zeichne(app);
        }

        this.figur.zeichne(app);
    }

    @Override
    public ISpielfigur getFigur() {
        return this.figur;
    }

    public ITileMap getTileMap() {
        return this.tileMap;
    }



    public ISzene getSzene(){
        return this.spielwelt.getSzene(getSzeneNr());
    }

    public int getSzeneNr(){
        return this.szeneNr;
    }

    public void setSzeneNr(int szeneNR){
        this.szeneNr = szeneNR;
    }

    // nicht sicher ob wir das so machen wollen

    public List<ISchatz> getSchaetze() {
        return schaetze;
    }

    public List<IMovable> getMovables() {
        return movables;
    }


}
