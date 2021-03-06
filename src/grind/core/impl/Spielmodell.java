package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import grind.movables.impl.Gegenstand;
import grind.movables.impl.Spielfigur;
import grind.movables.monster.DornPflanze;
import grind.movables.monster.FeuerMonster;
import grind.movables.monster.IMonster;
import grind.movables.monster.Zombie;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.LaufModus;
import grind.util.Richtung;
import grind.welt.ILevel;
import grind.welt.ISiedlung;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import grind.welt.impl.DummyLevel;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    @MEGAtroniker
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



    /**
     *Why??
     */
    public void entferneToteMonster(){

    }

    /**
     * Lädt die neue Szene und wählt, aus ob es sich um ein Level oder eine Siedlung handelt
     * @param n die gewünschte Szenennummer
     */
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
     * Lädt die Objekte im gewünschten Level in das Level von Spielmodell
     * @param level Das zu ladende Level
     */
    private void betreteLevel(ILevel level) {
        Random random = new Random();
        this.level = level;
        kopiereTilemap();
        kopiereMovables();
    }

    /**
     * Kopiert die Tilemap vom aktuellen Level in die Tilemap von Spielmodell
     */
    private void kopiereTilemap() {
        this.tileMap = this.level.getTileMap();
    }

    /**
     * Fügt die Movalbes in die entsprechende Liste in Spielmodell hinzu
     */
    private void kopiereMovables() {
        this.movables.clear();
        this.schaetze.clear();
        this.monster.clear();

        for (IMovable movable : this.level.getPositionen()) {
            if (movable instanceof ISpielfigur) {
                ISpielfigur figur = (ISpielfigur) movable;
                this.figur.setPosition(figur.getPosX(), figur.getPosY());
            // Gegenstände: Apfel, Schwert...
            } else if (movable instanceof ISchatz) {
                ISchatz schatz = (ISchatz) movable;
                this.schaetze.add(schatz);
                this.movables.add(schatz);
            } else if (movable instanceof IMonster){
                IMonster monster = (IMonster) movable;
                monster.setSpielmodell(this);
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

    /**
     * Zeichnet das Level, Spielfigur und Movables
     * @param spielsteuerung Das Applet auf das gezeichnet wird,
     * muss Spielsteuerung sein um auf geladene liste der MovableBilder zugreifen zu können.
     */
    @Override
    public void zeichne(Spielsteuerung spielsteuerung) {

        if (this.level != null) {
            this.level.zeichne(spielsteuerung);
        }

        for (IMovable movable : this.movables) {
            movable.zeichne(spielsteuerung);
        }

        if(getSzene() instanceof ISiedlung){
            eigenschaftenInSiedlungen(spielsteuerung);
        }

        this.figur.zeichne(spielsteuerung);

    }


    /**
     * Zeichnet in Siedlungen den Wert und Eigenschaften von Gegenständen die erworben werden können
     * @param spielsteuerung
     */
    private void eigenschaftenInSiedlungen(Spielsteuerung spielsteuerung) {
        for (IMovable movable : this.movables) {
            spielsteuerung.fill(0,0,0);
            spielsteuerung.textSize(14);
            if(movable instanceof Gegenstand) {
                spielsteuerung.text(movable.toString(), movable.getPosX() - movable.getGroesse(), movable.getPosY() + movable.getGroesse());
                spielsteuerung.text(("Wert: " + ((Gegenstand) movable).getWert()), movable.getPosX() - movable.getGroesse(), movable.getPosY()-movable.getGroesse());

            }
        }
    }

    /**
     * Gibt die aktuelle Figur von Spielmodell zurück
     * @return die aktuelle Figur
     */
    @Override
    public ISpielfigur getFigur() {
        return this.figur;
    }

    /**
     * Gibt die aktuelle Tilemap zurück
     * @return die aktuelle Tilemap
     */
    public ITileMap getTileMap() {
        return this.tileMap;
    }


    /**
     * Gibt die aktuelle Szene zurück
     * @return die aktuelle Szene
     */
    public ISzene getSzene(){
        return this.spielwelt.getSzene(getSzeneNr());
    }

    /**
     * Gibt die aktuelle Szenennummer zurück
     * @return
     */
    public int getSzeneNr(){
        return this.szeneNr;
    }

    /**
     * Setzt die Szenennummer auf eine gewünschte Nummer
     * @param szeneNR die gewünschte Szenennummer
     */
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
     * @MEGAtroniker
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
     * Gibt die Spielwelt zurück
     * @return ISpielwelt
     */
    public ISpielwelt getSpielwelt(){
        return this.spielwelt;
    }

    /**
     * Setzt die übergebene Spielwelt
     * @param spielwelt Die zu setzende Spielwelt
     */
    public void setSpielwelt(ISpielwelt spielwelt){
        this.spielwelt = spielwelt;
    }

    /**
     * @MEGAtroniker
     * Die Methode removeMovable löscht die aktuelle Movable Instanz aus der Liste movables
     * @param movable Liste aller movables im Spiel
     */
    public void removeMovable(IMovable movable) {
        try {
            movables.remove(movable);
        } catch (Exception e) {}

    }
}
