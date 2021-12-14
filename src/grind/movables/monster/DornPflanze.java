package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.welt.impl.DummyLevel;
import processing.core.PApplet;

public class DornPflanze extends Monster {
    private int posX;
    private int posY;



    private ITileMap tileMap;

    public DornPflanze(float posX, float posY, ITileMap tileMap) {
        super(posX, posY);
        this.tileMap = tileMap;


    }

    @Override
    public void zeichne(PApplet app) {

        app.fill(0, 255, 127);
        app.ellipse(this.getPosX(), this.getPosY(), Einstellungen.GROESSE_DORNPFLANZE, Einstellungen.GROESSE_DORNPFLANZE);
        IKachel kachel = tileMap.getKachel(this.getPosY()/Einstellungen.LAENGE_KACHELN_Y, this.getPosX()/Einstellungen.LAENGE_KACHELN_X);
        vorBetreten(kachel);

    }

    @Override
    public void bewege() {
        // stationär

    }

    @Override
    public void beiKollision(ISpielfigur figur) {
        if(PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY()) < (Einstellungen.GROESSE_DORNPFLANZE/2f + 20)){
            // System.out.println("Kollision mit DornPflanze");
        }
    }

    @Override
    public void vorBetreten(IKachel kachel) {
        if (kachel.istHindernis()) {
            posX += Einstellungen.LAENGE_KACHELN_X;
            posY += Einstellungen.LAENGE_KACHELN_Y;
        }
    }
}

