package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.movables.impl.Waffe;
import grind.util.Einstellungen;
import processing.core.PApplet;

public abstract class Monster extends Movable implements IMonster {
    private int lebensenergie;




    public Monster(float posX, float posY, int groesse) {

    public Monster(float posX, float posY) {

        super(posX, posY);
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
    public void reduziereLebensenergie(int reduziereUm) {

        if (lebensenergie - reduziereUm <= 0) {
            lebensenergie = 0;
        } else {
            lebensenergie -= reduziereUm;
        }
    }
}

