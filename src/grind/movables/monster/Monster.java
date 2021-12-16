package grind.movables.monster;

import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;


public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie;
    private int schaden;

    public Monster(float posX, float posY, int groesse) {

        super(posX, posY, groesse);
    }

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
}

