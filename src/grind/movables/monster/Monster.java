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
    private long startTimeNaehe;
    public boolean inDerNaehe=false;




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



    public void inDerNaehe(ISpielfigur figur, IMovable monster){
        float kollisionsDistantz=(this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f);
        float aktuelleDistanz=PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY());
        if(aktuelleDistanz>kollisionsDistantz&&aktuelleDistanz<120&&!inDerNaehe){
            playMonsterSound(figur, monster);
            startTimeNaehe = System.currentTimeMillis();
            setInDerNaehe(true);
        }
    }

    private void playMonsterSound(ISpielfigur figur,IMovable monster) {
        if(monster instanceof Zombie){
            figur.playZombieAroundSound();
        }if(monster instanceof FeuerMonster){
            figur.playFeuerMonsterAroundSound();
        }if(monster instanceof Feuerball){
             figur.playFeuerBallFlyBy();
        }if(monster instanceof Geist) {
             figur.playGeistAround();
        }
    }

    private void setInDerNaehe(boolean inDerNaehe) {
        this.inDerNaehe = inDerNaehe;
    }


    public void resetTimerNaehe(){
        long endTime = System.currentTimeMillis();
        if(endTime-startTimeNaehe>=2000){
            setInDerNaehe(false);
        }
    }

}

