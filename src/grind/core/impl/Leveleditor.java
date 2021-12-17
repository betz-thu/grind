package grind.core.impl;

import grind.core.ISpielmodell;
import grind.movables.impl.Spielfigur;
import grind.welt.ISpielwelt;
import processing.core.PApplet;

public class Leveleditor extends PApplet {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;
    private Spielfigur Spieler;
    private int SpielerGeschwindigkeit;
    // private ITileMap tileMap;

    boolean pressed = false;
    boolean levelBeendet = false;
    DateiService dateiService;
    ISpielwelt welt;


    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Leveleditor() {
        this.dateiService = new DateiService();
    }

//    public void speichereWelt(ISpielmodell welt){
//        dateiService.speicheSpielmodell(welt,"spielmodell.json");
//    }



}