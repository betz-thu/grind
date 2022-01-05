package grind.movables.impl;

import grind.kacheln.impl.TileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Stern extends Gegenstand{

    public static final int DAUERSTERNEVENT = 6000; // in [ms]
    public static final float DAUERGESCHWINDIGKEITSBOOST = 5f;

    public Stern(int posX, int posY) {
        super(posX, posY,Einstellungen.LAENGE_KACHELN_X/2);
    }

    @Override
    public void zeichne(PApplet app) {
        app.pushStyle();
        app.stroke(255,255,140);
        app.fill(255,255,140);
        app.ellipse(this.getPosX(), this.getPosY(), groesse, groesse);
        app.popStyle();
    }


    @Override
    public void beimSammeln(ISpielfigur figur) {
        figur.getInventar().add(this);
    }

    @Override
    public void beimAnwenden(ISpielfigur figur) {
        SpielerWirdImmunUndSchneller(figur);
    }


    public void SpielerWirdImmunUndSchneller(ISpielfigur figur){
        float alteGeschwindigkeit = figur.getGeschwindigkeit();
        float neueGeschwindigkeit = alteGeschwindigkeit+DAUERGESCHWINDIGKEITSBOOST;
        figur.setImmun(true);


        figur.setGeschwindigkeit(neueGeschwindigkeit);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                figur.setGeschwindigkeit(alteGeschwindigkeit);
                figur.setImmun(false);
                timer.cancel();
            }
        }, DAUERSTERNEVENT); // 6 Sekunden höhere Geschwindigkeit und Immunität



    }
}
