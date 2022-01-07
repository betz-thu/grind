package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.core.impl.Spielsteuerung;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Richtung;
import grind.util.Einstellungen;
import processing.core.PApplet;

public abstract class Monster extends Movable implements IMonster {
    transient ISpielmodell spielmodell;
    private int lebensenergie = 100;
    private int schaden;
    transient private long startTimeNaehe;
    transient private boolean inDerNaehe=false;            //kaplselung
    transient private boolean hatKollidiert=false;         //kaplselung
    transient private long startTimeAttac;



    transient int groesse;
    transient int geschwindigkeit = 1;


    /**
     * Konstruktor 1
     * @param posX
     * @param posY
     */

    public Monster(float posX, float posY, int groesse) {

        super(posX, posY, groesse);
    }

    @Override
    public int getGeschwindigkeit() {
        return this.geschwindigkeit;
    }

    @Override
    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
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

    public Monster(float posX, float posY, Richtung ausrichtung, Spielsteuerung spielsteuerung, int groesse) {
        super(posX,posY,ausrichtung, spielsteuerung,groesse);
    }


    @Override
    public void bewege() {
        if(isHatKollidiert()){
            resetTimerAttac();
        }
        if(isInDerNaehe()){
            resetTimerNaehe();
        }
    }

    @Override
    public int getLebensenergie() {
        return lebensenergie;
    }

//    @Override
//    public void beiKollision(ISpielfigur figur) {
//
//        figur.erhalteSchaden(this.schaden);
//
//    }

    @Override
    public int getGroesse() {
        return this.groesse;
    }

    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }

    public boolean isInDerNaehe() {
        return inDerNaehe;
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

//----------------------------------------------------------------------------------------------------------------------

    public void inDerNaehe(ISpielfigur figur, IMovable monster){
        float kollisionsDistantz=(this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f);
        float aktuelleDistanz=PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY());
        if(aktuelleDistanz>kollisionsDistantz&&aktuelleDistanz<120&&!inDerNaehe){
            playMonsterAroundSound(figur, monster);
            startTimeNaehe = System.currentTimeMillis();
            setInDerNaehe(true);
        }
    }

    private void playMonsterAroundSound(ISpielfigur figur, IMovable monster) {
        if(monster instanceof Zombie){
            figur.playZombieAroundSound();
        }if(monster instanceof FeuerMonster){
            figur.playFeuerMonsterAroundSound();
        }if(monster instanceof Feuerball){
             figur.playFeuerBallAroundSound();
        }if(monster instanceof Geist) {
             figur.playGeistAroundSound();
        }if(monster instanceof DornPflanze) {
        figur.playPflanzeAroundSound();
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
//----------------------------------------------------------------------------------------------------------------------

    /**
     * @MEGAtroniker
     * Die Metode beiKollision, soll änderungen am Monster bzw. der Spielfigur vornehmen
     * bisher bekommt nur der Spieler schaden
     * Sofern einmal Schaden verursacht muss eine gewisse Zeit gewartet werden,
     * bis dasselbe Monster der Figur erneut Schaden zufügen kann.
     * @param figur Spielfigur
     */
    public void beiKollision(ISpielfigur figur,IMovable monster) {
        float kollisionsDistantz=(this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f);
        float aktuelleDistanz=PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY());
        if(aktuelleDistanz < kollisionsDistantz&&!hatKollidiert){
            playMonsterAttacSound(figur, monster);
            startTimeAttac = System.currentTimeMillis();
            setHatKollidiert(true);

        }
    }

    private void playMonsterAttacSound(ISpielfigur figur,IMovable monster) {
        if(monster instanceof Zombie){
            figur.playZombieAttacSound();
        }if(monster instanceof FeuerMonster){
            figur.playFeuerMonsterAroundSound();
        }if(monster instanceof Feuerball){
            figur.playFeuerBallAroundSound();
        }if(monster instanceof Geist) {
            figur.playGeistAroundSound();
        }if(monster instanceof DornPflanze) {
            figur.playPflanzeAttacSound();
        }
        figur.erhalteSchaden(this.schaden);
    }

    public void setHatKollidiert(boolean hatKollidiert) {
        this.hatKollidiert = hatKollidiert;
    }

    public boolean isHatKollidiert() {
        return hatKollidiert;
    }

    /**
     * @MEGAtroniker
     * Methode resetTimer setzt booleand hatKollidiert auf false,
     * wenn in den letzten 2000ms keine Kollision stattgefunden hat.
     * Monster macht bei Kontakt nur alle 2s Schaden, nicht ständig.
     */
    public void resetTimerAttac(){
        long endTime = System.currentTimeMillis();
        if(endTime- startTimeAttac >=2000){
            setHatKollidiert(false);
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    @Override
    public ISpielmodell getSpielmodell() {
        return this.spielmodell;
    }

    @Override
    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }

}

