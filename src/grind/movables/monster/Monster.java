package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Richtung;
import grind.movables.impl.Schwert;
import grind.movables.impl.Waffe;
import grind.util.Einstellungen;
import processing.core.PApplet;

public abstract class Monster extends Movable implements IMonster {
    ISpielmodell spielmodell;
    private int lebensenergie = 100;
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
    public void reduziereLebensenergie(int schaden) {
        /**
         * Reduziert die Lebensenergie des Monsters um den übergebenen Schaden und setzt
         * monsterGestorben auf true, wenn das Monster keine Lebensenergie mehr hat.
         */
        if (lebensenergie>0){
            lebensenergie-= schaden;
        }
        if (lebensenergie<=0){
            this.getSpielmodell().removeMovable(this);
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

