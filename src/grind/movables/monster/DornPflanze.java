package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class DornPflanze extends Monster {
    private int posX;
    private int posY;
    private int schaden = 1;




    private ITileMap tileMap;

    public DornPflanze(float posX, float posY, ITileMap tileMap) {
        super(posX, posY,Einstellungen.GROESSE_DORNPFLANZE);
        this.tileMap = tileMap;
        setSchaden(schaden);


    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(0, 255, 127);
        app.ellipse(this.getPosX(), this.getPosY(), this.getGroesse(), this.getGroesse());
        IKachel kachel = tileMap.getKachel(this.getPosY() / Einstellungen.LAENGE_KACHELN_Y, this.getPosX() / Einstellungen.LAENGE_KACHELN_X);
        vorBetreten(kachel);
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
}

