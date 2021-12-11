package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

import java.util.Random;


public class DornPflanze extends Monster{

    private ITileMap tileMap;
    Random random = new Random();

    public DornPflanze(float posX, float posY, ITileMap tileMap) {

        super(posX, posY);
        this.tileMap = tileMap;
        IKachel kachel = tileMap.getKachel((int)posX, (int)posY);


    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(0,255,127);
        app.ellipse(this.getPosX(), this.getPosY(),(float) Einstellungen.LAENGE_KACHELN_X , (float)Einstellungen.LAENGE_KACHELN_Y);
    }

    @Override
    public void bewege() {
        // Eine DornPflanze kann sich nicht bewegen
    }

    @Override
    public void beiKollision(ISpielfigur figur) {

    }

    @Override
    public void vorBetreten(IKachel kachel) {


    }
}
