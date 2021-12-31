package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.util.Einstellungen;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileMap implements ITileMap {

    int hoehe;
    int breite;
    IKachel[][] kacheln;
    transient IKachel baum;
    transient IKachel fels;
    transient IKachel wasser;
    transient IKachel weg;
    transient IKachel wiese;
    transient IKachel holzbrücke;
    transient IKachel levelausgang;
    transient IKachel dummyHindernis;
    List<IKachel> kachelarten = new ArrayList<>();
    transient Random rand = new Random();

    /**
     *
     */
    public TileMap() {
        this.hoehe = Einstellungen.ANZAHL_KACHELN_Y;
        this.breite = Einstellungen.ANZAHL_KACHELN_X;
        this.kacheln = new IKachel[this.hoehe][this.breite];
        this.fels = new Fels();
        this.baum = new Baum();
        this.wasser = new Wasser();
        this.weg = new Weg();
        this.wiese = new Wiese();
        this.holzbrücke = new Holzbrücke();
        this.dummyHindernis = new DummyHindernis();
        this.levelausgang = new Levelausgang();
        zufaelligeTileMap();
        erstelleListeKachelarten();
    }

    /**
     * Erstellt eine zufällige Tilemap zu Testzwecken
     */
    private void zufaelligeTileMap() {
        int levelausgangI = rand.nextInt(Einstellungen.ANZAHL_KACHELN_Y);
        int levelausgangJ = rand.nextInt(Einstellungen.ANZAHL_KACHELN_X);
        int zufall = 0;

        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                zufall = rand.nextInt(6);
                if (zufall == 0) {
                    //this.kacheln[i][j] = this.baum;
                    this.kacheln[i][j] = this.wiese;
                } else if (zufall == 1) {
//                   this.kacheln[i][j] = this.fels;
                    this.kacheln[i][j] = this.wiese;
                } else if (zufall == 2) {
//                   this.kacheln[i][j] = this.wasser;
                    this.kacheln[i][j] = this.wiese;
                } else if (zufall == 3) {
                    this.kacheln[i][j] = this.weg;
                } else if (zufall == 4) {
                    this.kacheln[i][j] = this.wiese;
                } else if (zufall == 5) {
                    this.kacheln[i][j] = this.holzbrücke;
                }
            }
        }

        this.kacheln[levelausgangI][levelausgangJ] = this.levelausgang;
        if (levelausgangI>0) {
            this.kacheln[levelausgangI-1][levelausgangJ] = new SmaragdTor();
        }
        if (levelausgangJ>0) {
            this.kacheln[levelausgangI][levelausgangJ-1] = new SaphirTor();
        }
    }

    /**
     * Fügt der Liste Kachelarten alle Kachelarten der akutellen Tilemap hinzu.
     */
    private void erstelleListeKachelarten() {
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                if (!kachelarten.contains(kacheln[i][j])) {
                    kachelarten.add(kacheln[i][j]);
                }
            }
        }
    }

    /**
     * Gibt die aktuelle Höhe der Tilemap zurück.
     *
     * @return hoehe der Tilemap in Feldern
     */
    @Override
    public int getHoehe() {
        return hoehe;
    }

    /**
     * Gibt die aktuelle Breite der Tilemap zurück.
     *
     * @return breite der Tilemap in Feldern
     */
    @Override
    public int getBreite() {
        return breite;
    }

    /**
     * Gibt die Kachel mit den Koordinaten i,j zurück.
     *
     * @param i Array Spalte in y-Richtung
     * @param j Array Spalte in x-Richtung
     * @return Die Kachel an Stelle [i][j]
     */
    @Override
    public IKachel getKachel(int i, int j) {
        if (i >= 0 && j >= 0 && i < kacheln.length && j < kacheln[i].length) {
            return kacheln[i][j];
        }
        return this.dummyHindernis;
    }

    /**
     * Gibt eine Liste aller Kachelarten der aktuellen Tilemap zurück.
     *
     * @return ArrayList mit Kacheln aus aktueller Tilemap
     */
    @Override
    public List<IKachel> getKachelarten() {
        return this.kachelarten;
    }

    /**
     * Zeichnet die aktuelle Tilemap auf das Applet.
     * Zusätzlich wird noch eine Unterebene eingezeichnet um hintergrundfreie Assets benutzen zu können.
     *
     * @param app PApplet auf welches gezeichnet werden soll
     */
    @Override
    public void zeichne(PApplet app) {
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                int y = i * Einstellungen.LAENGE_KACHELN_Y;
                int x = j * Einstellungen.LAENGE_KACHELN_X;

                this.wiese.zeichne(app, x, y); // untere ebene einzeichnen
                this.getKachel(i, j).zeichne(app, x, y);
            }
        }
    }


}
