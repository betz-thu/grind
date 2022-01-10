package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class DornPflanze extends Monster {
    transient private int posX;
    transient private int posY;
    transient private int schaden = 20;




    private ITileMap tileMap;

    public DornPflanze(float posX, float posY, ITileMap tileMap) {
        super(posX, posY,Einstellungen.LAENGE_KACHELN_X);
        this.tileMap = tileMap;
        setSchaden(schaden);


    }


    @Override
    public void bewege() {
        // stationär
    }

    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X;
    }

    @Override
    public void vorBetreten(IKachel kachel) {
        if (kachel.istHindernis()) {
            int posX = this.getPosX() + Einstellungen.LAENGE_KACHELN_X;
            int posY = this.getPosY() + Einstellungen.LAENGE_KACHELN_Y;
            this.setPosition(posX, posY);
        }
    }

    @Override
    public ISpielmodell getSpielmodell() {
        return this.spielmodell;
    }

    @Override
    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }

    @Override
    public void setGeschwindigkeit(int xGeschwindigkeit) {
    }

    @Override
    public int getGeschwindigkeit() {
        return 0;
    }


}

