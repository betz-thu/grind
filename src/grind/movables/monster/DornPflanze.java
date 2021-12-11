package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class DornPflanze extends Monster {
    int deltaX;
    int deltaY;
    int x = this.getPosX() + 19;
    int y = this.getPosY() + 19;

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
        while (kachel.istHindernis()) {
            new DornPflanze(x + 10, y + 10, tileMap);
        }
    }
}

