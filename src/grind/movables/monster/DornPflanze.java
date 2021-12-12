package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.welt.impl.DummyLevel;
import processing.core.PApplet;

public class DornPflanze extends Monster {
    int deltaX;
    int deltaY;
    int x = this.getPosX() + Einstellungen.LAENGE_KACHELN_X/2;
    int y = this.getPosY() + Einstellungen.LAENGE_KACHELN_Y/2;

    private ITileMap tileMap;

    public DornPflanze(float posX, float posY, ITileMap tileMap) {

        super(posX, posY);
        this.tileMap = tileMap;
        IKachel kachel = tileMap.getKachel((int) posX, (int) posY);


    }

    @Override
    public void zeichne(PApplet app) {

        app.fill(0, 255, 127);
        app.ellipse(x, y, Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
        IKachel kachel = tileMap.getKachel(x/Einstellungen.LAENGE_KACHELN_Y, x/Einstellungen.LAENGE_KACHELN_X);
        vorBetreten(kachel);

    }

    @Override
    public void bewege() {
        // stationär

    }

    @Override
    public void beiKollision(ISpielfigur figur) {

    }

    @Override
    public void vorBetreten(IKachel kachel) {
        if (kachel.istHindernis()) {
            x += Einstellungen.LAENGE_KACHELN_X;
            y += Einstellungen.LAENGE_KACHELN_Y;
        }
    }
}

