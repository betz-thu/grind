package grind.kacheln.impl;

import grind.util.Einstellungen;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileMap implements ITileMap {

    int hoehe;
    int breite;
    IKachel[][] kacheln;
    IKachel baum;
    IKachel fels;
    IKachel wasser;
    IKachel weg;
    IKachel wiese;
    IKachel holzbrücke;
    IKachel levelausgang;
    List<IKachel> kachelarten = new ArrayList<>();
    int zufall = 0;
    Random rand = new Random();

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
        this.levelausgang = new Levelausgang();
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
               this.zufall = rand.nextInt(6);
               if (zufall == 0){
                   this.kacheln[i][j] = this.baum;
               } else if (zufall == 1){
                   this.kacheln[i][j] = this.fels;
               } else if (zufall == 2){
                   this.kacheln[i][j] = this.wasser;
               } else if (zufall == 3){
                   this.kacheln[i][j] = this.weg;
               } else if (zufall == 4){
                   this.kacheln[i][j] = this.wiese;
               } else if (zufall == 5){
                   this.kacheln[i][j] = this.holzbrücke;
               }
            }
        }

        this.kacheln[rand.nextInt(Einstellungen.ANZAHL_KACHELN_Y)][rand.nextInt(Einstellungen.ANZAHL_KACHELN_X)] = this.levelausgang; //Vorerst festgelegtes Levelende
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                if(!kachelarten.contains(kacheln[i][j])){
                    kachelarten.add(kacheln[i][j]);
                }
            }
        }
    }

    @Override
    public int getHoehe() {
        return hoehe;
    }

    @Override
    public int getBreite() {
        return breite;
    }

    @Override
    public IKachel getKachel(int i, int j) {
        return kacheln[i][j];
    }

    @Override
    public List<IKachel> getKachelarten() {
        return this.kachelarten;
    }

    @Override
    public void zeichne(PApplet app) {
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                int y = i * Einstellungen.LAENGE_KACHELN_Y;
                int x = j * Einstellungen.LAENGE_KACHELN_X;

                this.wiese.zeichne(app,x,y); // untere ebene einzeichnen
                this.getKachel(i, j).zeichne(app, x, y);
            }
        }
    }
}
