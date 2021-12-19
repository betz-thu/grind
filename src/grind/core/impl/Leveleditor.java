package grind.core.impl;

import grind.core.ISpielmodell;
import grind.movables.impl.Spielfigur;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummySpielwelt;
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

    ISpielmodell spielmodell;


    public Leveleditor(){
        this.spielmodell = new Spielmodell(new DummySpielwelt());
    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Leveleditor() {
        this.dateiService = new DateiService();
        this.welt = dateiService.ladeSpielmodell("spielwelt.json");
        System.out.println("Geladen");
    }


//    public void speichereWelt(ISpielmodell welt){
//        dateiService.speicheSpielmodell(welt,"spielmodell.json");
//    }



}