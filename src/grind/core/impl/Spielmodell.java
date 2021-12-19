package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.movables.monster.*;
import grind.util.FeuerModus;
import grind.util.Einstellungen;
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
    Spielsteuerung steuerung;
    ILevel level;
    ITileMap tileMap;
    /*
    Wichtig die Liste movables ist nun eien CopyOnArrayList
    Grund: diese Listenart ermöglicht es, trotz durchiterieren modifikationen an der Liste vorzunehmen.
    Dies isrt wichtig wenn movables gelöschet werden müssen
    ->Feuerball verlässt Spielfeld
    -> Monster stirbt
     */
    ISpielfigur figur = new Spielfigur(0, 0, Richtung.N);
    List<IMovable> movables = new CopyOnWriteArrayList<>();
    List<ISchatz> schaetze = new ArrayList<>();
    List<IMonster> monster = new ArrayList<>();



    public Spielmodell(ISpielwelt spielwelt, Spielsteuerung steuerung) {
        this.spielwelt = spielwelt;
        this.steuerung=steuerung;

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

    /**
     * TODO eingentlich sollte alles hier sein aber wie ist des dann mit den einzelnen Szenen?
     * TODO Weil jetz sind die Monster in jeder Szene gleich
     * TODO neue tilemap aber danach dieselben Movables
     * Gefühlt definieren wir in x klassen dasselbe und des gilt auch IMMER egal welche szene
     * dann ändern sich nur die Kacheln
     * @param level
     */
    private void betreteLevel(ILevel level) {
        this.level = level;
        kopiereTilemap();
        kopiereMovables();

        IMonster feuerMonster = new FeuerMonster(300,300,this.tileMap,this.steuerung,Richtung.N,100, FeuerModus.KONSTANT);
        addMonster(feuerMonster);
        //addMonster("Feuerball",300,300);
        //addMonster("FeuerMonster",300,300);

    }

    /**
     * TODO Jede teilmap braucht ne eigene liste sonst gibts überall dieselben movables
     */
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

    /**
     * Die Methode addMonster ermöglicht die Instanziierung aller Monster,
     * direkt im Spielmodell in der Methode betreteLevel.
     * Die erzeugten Monster werden der Liste movables zugeordet
     */
    @Override
    public void addMonster(IMonster monster) {
        monster.setSpielmodell(this);
        movables.add(monster);
    }

    /**
     * Die Methode removeMovable löscht die aktuelle Movable Instanz aus der Liste movables
     * @param movable Liste aller movables im Spiel
     */
    public void removeMovable(IMovable movable) {
        movables.remove(movable);
    }

    /**
     * TODO: überkompliziert, da jedes mal neue Liste erstellt wird. Lösung: originalliste ist nun eine CopyOnWriteList.
     *
     * Löscht Movable aus Liste der Positionen und aus Liste der movables
     * z. B. Für das Einsammeln eines Schatzes
     * @param movable
     */
    /*public void removeMovable(IMovable movable){

       List<IMovable> positionen = level.getPositionen();

       positionen.remove(movable);
       movables.remove(movable);

    }*/
}
