package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class DornPflanze extends Monster {

    transient private int schaden = 10;



    transient private ITileMap tileMap;

    public DornPflanze(float posX, float posY, ITileMap tileMap) {
        super(posX, posY,Einstellungen.LAENGE_KACHELN_X);
        this.tileMap = tileMap;
        setSchaden(schaden);



    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(0, 255, 127);
        app.ellipse(this.getPosX(), this.getPosY(), groesse, groesse);
        IKachel kachel = tileMap.getKachel(this.getPosY() / Einstellungen.LAENGE_KACHELN_Y, this.getPosX() / Einstellungen.LAENGE_KACHELN_X);
        vorBetreten(kachel);
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

