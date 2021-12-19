package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Richtung;
import processing.core.PApplet;

public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie;
    ISpielmodell spielmodell;
    private int schaden;


    /**
     * Konstruktor 1
     * @param posX
     * @param posY
     */

    public Monster(float posX, float posY, int groesse) {

        super(posX, posY, groesse);
    }

    /**
     * Konstruktor 2 wenn die Ausrichtung(N,S,W,O) für die Fortbewegung relevant ist
     * @param posX
     * @param posY
     * @param ausrichtung
     */
    public Monster(float posX, float posY, Richtung ausrichtung,int groesse) {
        super(posX,posY,ausrichtung,groesse);
    }

    @Override
    public int getLebensenergie() {
        return lebensenergie;
    }

    @Override
    public void beiKollision(ISpielfigur figur) {

        figur.erhalteSchaden(this.schaden);

    }

    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }
    @Override
    public ISpielmodell getSpielmodell() {
        return spielmodell;
    }

    @Override
    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }
}

