package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.movables.monster.*;
import grind.util.Richtung;
import grind.movables.impl.Movable;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import grind.movables.impl.Spielfigur;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Autor Megatronik
 * Instanziierung der Spielfigur nun mit Ausrichtung.
 */
public class Spielmodell implements ISpielmodell {

    int szeneNr = 0;

    ISpielwelt spielwelt;
    ILevel level;
    ITileMap tileMap;

    ISpielfigur figur = new Spielfigur(0, 0, Richtung.N);
    List<IMovable> movables = new CopyOnWriteArrayList<>();
    List<ISchatz> schaetze = new ArrayList<>();

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
        //addMonster("",200,50);
        addMonster("Feuerball",300,300);

    }

    private void kopiereTilemap() {
        this.tileMap = this.level.getTileMap();
    }

    private void kopiereMovables() {
        this.movables.clear();
        this.schaetze.clear();

        for (IMovable movable : this.level.getPositionen()) {
            if (movable instanceof ISpielfigur) {
                ISpielfigur figur = (ISpielfigur) movable;
                this.figur.setPosition(figur.getPosX(), figur.getPosY());
            } else if (movable instanceof ISchatz) {
                ISchatz schatz = (ISchatz) movable;
                this.schaetze.add(schatz);
                this.movables.add(schatz);
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
            if(movable instanceof IMonster){
                ((IMonster) movable).beiKollision(figur);
            }
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

    @Override
    public void addMonster(String type,float posX,float posY) {
        IMonster monster;
        if (type.equals("Geist"))
             monster = new Geist(posX,posY,this.tileMap);
        else if (type.equals("Zombie")){
            monster = new Zombie(posX,posY,this.tileMap);
        } else if (type.equals("Feuerball")) {
            monster = new Feuerball(posX,posY,1,1,this.tileMap);
        } else {
            monster = new DornPflanze(posX,posY,this.tileMap);
        }
        monster.setSpielmodell(this);
        movables.add(monster);
    }
    public void removeMovable(Monster movable) {
        movables.remove(movable);
    }
}
