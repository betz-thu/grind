package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Einstellungen;
import processing.core.PApplet;

public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie;





    public Monster(float posX, float posY, int groesse) {

        super(posX, posY, groesse);
    }

    public int getLebensenergie() {
        return lebensenergie;
    }

    @Override
    public void beiKollision(ISpielfigur figur) {

    }
}

