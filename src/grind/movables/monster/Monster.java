package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.movables.impl.Schwert;
import grind.movables.impl.Waffe;
import grind.util.Einstellungen;
import processing.core.PApplet;

public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie = 100;
    private int schaden;
    boolean monsterGestorben = false;




    public Monster(float posX, float posY, int groesse) {

//    public Monster(float posX, float posY) {

        super(posX, posY,groesse);
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

    @Override
    public void reduziereLebensenergie(int schaden) {
        if (lebensenergie>0){
            lebensenergie-= schaden;
        }
        if (lebensenergie<=0){
            monsterGestorben = true;
        }
    }

}

