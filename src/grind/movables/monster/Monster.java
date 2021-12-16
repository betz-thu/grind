package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;

public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie;
    ISpielmodell spielmodell;




    public Monster(float posX, float posY) {

        super(posX, posY);
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

