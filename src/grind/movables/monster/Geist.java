package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import processing.core.PApplet;
import grind.util.Einstellungen;

public class Geist extends Monster{
    private int deltaX = 5;
    private int deltaY = 5;
    ITileMap tileMap;



    public Geist(float posX, float posY, ITileMap tileMap) {
        super(posX, posY);
        this.tileMap = tileMap;



    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(255,255,255);
        app.ellipse(this.getPosX(), this.getPosY(),(float)Einstellungen.LAENGE_KACHELN_X/2 , (float)Einstellungen.LAENGE_KACHELN_Y/2);
    }

    @Override
    public void bewege() {



        int posX = this.getPosX();
        int posY = this.getPosY();

        if (posX < 0) {
            deltaX = 5;
        } else if (posX > Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X) {
            deltaX = -5;
        }

        if (posY < 0) {
            deltaY = 5;
        } else if (posY > Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y) {
            deltaY = -5;
        }


        posX += deltaX;
        posY += deltaY;


        this.setPosition(posX, posY);
    }


    @Override
    public void beiKollision(ISpielfigur figur) {

    }

    @Override
    public void vorBetreten(IKachel kachel) {
        // Ein Geist darf auch durch Wände und in Wänden spawnen

    }
}
