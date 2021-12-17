package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Richtung;

public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie;
    ISpielmodell spielmodell;


    /**
     * Konstruktor 1
     * @param posX
     * @param posY
     */
    public Monster(float posX, float posY) {
        super(posX, posY);
    }

    /**
     * Konstruktor 2 wenn die Ausrichtung(N,S,W,O) für die Fortbewegung relevant ist
     * @param posX
     * @param posY
     * @param ausrichtung
     */
    public Monster(float posX, float posY, Richtung ausrichtung) {
        super(posX,posY,ausrichtung);
    }

    @Override
    public int getLebensenergie() {
        return lebensenergie;
    }

    @Override
    public void beiKollision(ISpielfigur figur) {

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

