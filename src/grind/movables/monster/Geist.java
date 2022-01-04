package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import processing.core.PApplet;
import grind.util.Einstellungen;

public class Geist extends Monster{
    private int geschwindigkeit = 5;
    transient private int deltaX = geschwindigkeit;
    transient private int deltaY = geschwindigkeit;
    transient private int schaden = 15;


    transient ITileMap tileMap;


    public Geist(float posX, float posY,ITileMap tileMap) {

        super(posX, posY, Einstellungen.GROESSE_GEIST);
        setSchaden(schaden);
    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(255, 255, 255);
//      app.ellipse(deltaX, deltaY,(float)Einstellungen.LAENGE_KACHELN_X/2 , (float)Einstellungen.ANZAHL_KACHELN_Y/2);

        app.ellipse(this.getPosX(), this.getPosY(), (float) Einstellungen.GROESSE_GEIST, (float) Einstellungen.GROESSE_GEIST);
    }
    @Override
    public void bewege() {



        int posX = this.getPosX();
        int posY = this.getPosY();

        if (posX < 0) {
            deltaX = geschwindigkeit;
        } else if (posX > Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X) {
            deltaX = -geschwindigkeit;
        }

        if (posY < 0) {
            deltaY = geschwindigkeit;
        } else if (posY > Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y) {
            deltaY = -geschwindigkeit;
        }


        posX += deltaX;
        posY += deltaY;


        this.setPosition(posX, posY);
    }




    @Override
    public void vorBetreten(IKachel kachel) {
        // Ein Geist darf auch durch Wände gehen

    }

}