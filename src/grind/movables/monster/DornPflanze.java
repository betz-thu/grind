package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.welt.impl.DummyLevel;
import processing.core.PApplet;

public class DornPflanze extends Monster {
    transient private int posX;
    transient private int posY;
    private int schaden = 1;




    transient private ITileMap tileMap;

    public DornPflanze(float posX, float posY, ITileMap tileMap, int groesse) {
        super(posX, posY, groesse);
        this.tileMap = tileMap;
        setSchaden(schaden);


    }

    @Override
    public void zeichne(PApplet app) {

        app.fill(0, 255, 127);
        app.ellipse(this.getPosX(), this.getPosY(), this.getGroesse(), this.getGroesse());
        IKachel kachel = tileMap.getKachel(this.getPosY()/Einstellungen.LAENGE_KACHELN_Y, this.getPosX()/Einstellungen.LAENGE_KACHELN_X);
        vorBetreten(kachel);

    }

    @Override
    public void bewege() {
        // stationär

    }







    @Override
    public void vorBetreten(IKachel kachel) {
        if (kachel.istHindernis()) {
            posX += Einstellungen.LAENGE_KACHELN_X;
            posY += Einstellungen.LAENGE_KACHELN_Y;
        }
    }
}

