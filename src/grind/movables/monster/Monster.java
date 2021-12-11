package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.movables.impl.Movable;

public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie;





    public Monster(float posX, float posY) {

        super(posX, posY);
    }

    public int getLebensenergie() {
        return lebensenergie;
    }




}
