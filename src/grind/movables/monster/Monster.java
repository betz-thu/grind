package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.core.impl.Spielsteuerung;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Richtung;
import grind.util.Einstellungen;
import processing.core.PApplet;

/**
 * @author MEGAtroniker
 * Die abstrakt Klasse Monster dient als Grundlage für alle Monstervarianten.
 *
 */
public abstract class Monster extends Movable implements IMonster {
    transient ISpielmodell spielmodell;
    private int lebensenergie = 100;
    private int schaden;
    transient private long startTimeNaehe;
    transient private boolean inDerNaehe=false;
    transient private boolean hatKollidiert=false;
    transient private long startTimeAttac;
    transient int groesse;
    transient int geschwindigkeit = 1;


    /**
     * @MEGAtroniker
     * Konstruktor 1
     * Dieser Konstruktor kann für nichtbewegliche Monstervarianten verwendet werden
     * Zudem weden er für Geister verwendet
     * und besitz dementsprechend ein Minimum an Parametern
     * @param posX initiale X Position auf dem Spielfeld
     * @param posY initiale Y Position auf dem Spielfeld
     */
    public Monster(float posX, float posY, int groesse) {
        super(posX, posY, groesse);
    }


    /**
     * @MEGAtroniker
     * Konstruktor
     * Dieser Konstruktor soll für alle beweglichen Monstervarianten verwendet werden,
     * da für deren Funktionstüchtigkeit die Ausrichtung relativ zum Spielfeld wichtig ist
     * @param posX initiale X Position auf dem Spielfeld
     * @param posY initiale Y Position auf dem Spielfeld
     * @param ausrichtung Ausrichting der Instanz entsprechen der Enum Richtung

    public Monster(float posX, float posY, Richtung ausrichtung,int groesse) {
        super(posX,posY,ausrichtung,groesse);
    }*/


    /**
     *
     * @param posX
     * @param posY
     * @param ausrichtung
     * @param spielsteuerung
     * @param groesse
     */
    public Monster(float posX, float posY, Richtung ausrichtung, Spielsteuerung spielsteuerung, int groesse) {
        super(posX,posY,ausrichtung, spielsteuerung,groesse);
    }



    /**
     * @MEGAtroniker
     * @return
     */
    @Override
    public int getGeschwindigkeit() {
        return this.geschwindigkeit;
    }


    /**
     * @MEGAtroniker
     * @param geschwindigkeit
     */
    @Override
    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
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

    /**
     * Getter für die Lebensenergie eines Monsters
     * @return Gibt die aktuelle Lebensenergie eines Monsters zurück.
     */
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

    /**
     * Reduziert die Lebensenergie des Monsters um den übergebenen Schaden und löscht
     * das Monster aus dem Spiel, wenn keine Lebensenergie mehr vorhanden ist.
     * @param schaden Schaden welcher dem Monster von einer Waffe zugeführt wird.
     */
    @Override
    public void reduziereLebensenergie(int schaden) {
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
            figur.playSound(this.getClass().toString()+"Around");
            startTimeNaehe = System.currentTimeMillis();
            setInDerNaehe(true);
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
            figur.playSound(this.getClass().toString()+"Attack");
            startTimeAttac = System.currentTimeMillis();
            setHatKollidiert(true);

        }
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

    public int getSchaden() {
        return schaden;
    }

    public long getStartTimeNaehe() {
        return startTimeNaehe;
    }

    public long getStartTimeAttac() {
        return startTimeAttac;
    }
}

