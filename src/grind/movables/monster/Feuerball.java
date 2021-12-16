package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Einstellungen;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Math.*;

public class Feuerball extends Monster{
    private final int geschwindigkeit = 5;
    private int deltaX;
    private int deltaY;

    ITileMap tileMap;


    public Feuerball(float posX, float posY, int deltaX, int deltaY, ITileMap tileMap) {
        super(posX, posY);
        int abstand = (int) sqrt(pow(deltaX,2)+pow(deltaY,2));
        this.deltaX =  geschwindigkeit*deltaX/abstand;
        this.deltaY = geschwindigkeit*deltaY/abstand;
    }

    @Override
    public void zeichne(PApplet app) {
        app.pushStyle();
        app.fill(255,100,0);
//      app.ellipse(deltaX, deltaY,(float)Einstellungen.LAENGE_KACHELN_X/2 , (float)Einstellungen.ANZAHL_KACHELN_Y/2);

        app.ellipse(this.getPosX(), this.getPosY(),(float)Einstellungen.GROESSE_FEUERBALL , (float)Einstellungen.GROESSE_FEUERBALL);
        app.popStyle();
    }
    @Override
    public void bewege() {

        int posX = this.getPosX();
        int posY = this.getPosY();

        posX += deltaX;
        posY += deltaY;


        if (posX < 0 || posY < 0 || posX > Einstellungen.ANZAHL_KACHELN_X * Einstellungen.LAENGE_KACHELN_X || posY > Einstellungen.ANZAHL_KACHELN_Y * Einstellungen.LAENGE_KACHELN_Y) {
            this.getSpielmodell().removeMovable(this);

        } else {
                this.setPosition(posX, posY);
            }

    }


    public void beiKollision() {
        //System.out.println("beiKollisionfeuerball");
    }


    @Override
    public void vorBetreten(IKachel kachel) {
        //System.out.println("vorBetretenfeuerball");
    }
}