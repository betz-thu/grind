package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import processing.core.PApplet;
import grind.util.Einstellungen;

public class Geist extends Monster {

    transient private float deltaX = getGeschwindigkeit()*5;
    transient private float deltaY = getGeschwindigkeit()*5;
    transient private int schaden = 15;


    transient ITileMap tileMap;


    public Geist(float posX, float posY, ITileMap tileMap) {

        super(posX, posY, Einstellungen.LAENGE_KACHELN_X / 2);
        setSchaden(schaden);
    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(255, 255, 255);
//      app.ellipse(deltaX, deltaY,(float)Einstellungen.LAENGE_KACHELN_X/2 , (float)Einstellungen.ANZAHL_KACHELN_Y/2);

        app.ellipse(this.getPosX(), this.getPosY(), (float) groesse, (float) groesse);
    }

    @Override
    public void bewege() {

        int posX = this.getPosX();
        int posY = this.getPosY();

        if (posX < 0) {
            deltaX = getGeschwindigkeit()*5;
        } else if (posX > Einstellungen.ANZAHL_KACHELN_X * Einstellungen.LAENGE_KACHELN_X) {
            deltaX = -getGeschwindigkeit()*5;
        }

        if (posY < 0) {
            deltaY = getGeschwindigkeit()*5;
        } else if (posY > Einstellungen.ANZAHL_KACHELN_Y * Einstellungen.LAENGE_KACHELN_Y) {
            deltaY = -getGeschwindigkeit()*5;
        }


        posX += deltaX;
        posY += deltaY;


        this.setPosition(posX, posY);
    }



    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X / 2;
    }

    @Override
    public void vorBetreten(IKachel kachel) {
        // Ein Geist darf auch durch Wände gehen
    }

    @Override
    public void setGeschwindigkeit(int xGeschwindigkeit) {
        this.geschwindigkeit= xGeschwindigkeit;
        }

    @Override
    public int getGeschwindigkeit() {
        return this.geschwindigkeit;
    }



}
