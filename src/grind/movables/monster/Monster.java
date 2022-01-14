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
 * In dieser Klasse sind die für alle Monster gleichen Methoden in Bezug auf
 * Kollisonsabfrage und Umgebungsabfrage in Bezug auf die Spielfigur implementieret.
 */
public abstract class Monster extends Movable implements IMonster {
    transient ISpielmodell spielmodell;
    private int lebensenergie = 100;
    private int schaden;
    private int warteZeitMS =2000;
    transient private long startTimeNaehe;
    transient private boolean inDerNaehe=false;
    transient private boolean hatKollidiert=false;
    transient private long startTimeAttac;
    transient int groesse;
    transient int geschwindigkeit = 1;


    /**
     * @author MEGAtroniker
     * Konstruktor 1
     * Dieser Konstruktor kann für weniger aufwändige Monstervarianten verwendet werden
     * bei denen eine initiale Position ausreicht. (Dornpflanze,Geist)
     * und besitz dementsprechend ein Minimum an Parametern
     * @param posX initiale X Position auf dem Spielfeld
     * @param posY initiale Y Position auf dem Spielfeld
     * @param groesse größe des Monsters
     */
    public Monster(float posX, float posY, int groesse) {
        super(posX, posY, groesse);
    }


    /**
     * @author MEGAtroniker
     * Konstroktor 2
     * Dieser Konstruktor soll verwendet werden, wenn die Monstervarianten auf die Spielwelt
     * richtungsabhängig reagieren soll. Z.B. bei den Laufzyklen und der Darstellung.
     * @param posX initiale X Position auf dem Spielfeld
     * @param posY initiale Y Position auf dem Spielfeld
     * @param ausrichtung initiale Aurichtung des Monsters im Spielfeld
     * @param spielsteuerung aktuelle Spielsteuerung
     * @param groesse größe des Monsters
     */
    public Monster(float posX, float posY, Richtung ausrichtung, Spielsteuerung spielsteuerung, int groesse) {
        super(posX,posY,ausrichtung, spielsteuerung,groesse);
    }


    /**
     * @author MEGAtroniker
     * Getter getGeschwindigkeit gibt die Bewegungsgeschwindigkeit des Monsters zurück
     * @return Bewegungsgeschwindigkeit des Monsters
     */
    @Override
    public int getGeschwindigkeit() {
        return this.geschwindigkeit;
    }


    /**
     * @author MEGAtroniker
     * Setter setGeschwindigkeit setzt die neue Bewegungsgeschwindigkeit des Monsters
     * @param geschwindigkeit neue Bewegungsgeschwindigkeit des Monsters
     */
    @Override
    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewege, enthält die für alle Monster relevanten Abläufe für die Kollision
     * und die Umgebung der Spielfigur
     */
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
     * @author MEGAtroniker
     * Getter getLebensenergie gibt die Lebensenergie des Monsters zurück
     * @return aktuelle Lebensenergie des Monsters
     */
    @Override
    public int getLebensenergie() {
        return lebensenergie;
    }


    /**
     * @author MEGAtroniker
     * Getter getGroesse gibt die Größe des Monsters zurück
     * @return Größe des Monsters
     */
    @Override
    public int getGroesse() {
        return this.groesse;
    }


    /**
     * @author MEGAtroniker
     * Setter setSchaden setzt den neuen Schaden des Monsters entsprechend dem übergebenen Wert
     * @param schaden neuer Schadenswert
     */
    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }


    /**
     * @author MEGAtroniker
     * Getter isInDerNaehe gibt den Wahrheitswert zurück, ob sich die Spielfigur in der Nähe befindet
     * @return Wahrheitswert, ob die Spielfigur in der Nähe ist
     */
    public boolean isInDerNaehe() {
        return inDerNaehe;
    }

    /**
     * @author MEGAtroniker
     * Setter setInDerNaehe setzt den Wahrheitswert, ob sich die Spielfigur in der Nähe befindet
     * @param inDerNaehe  Wahrheitswert, ob sich die Spielfigur in der Nähe befindet
     */
    private void setInDerNaehe(boolean inDerNaehe) {
        this.inDerNaehe = inDerNaehe;
    }


    /**
     * @author MEGAtroniker
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


    /**
     * @author MEGAtroniker
     * Die Methode inDerNaehe prüft, ob Sich die Spielfigur innerhalb eines bestimmten Radius
     * und außerhalb des Kollisionsradius aufhält. Ist dies der Fall wir die Methode playMonsterAroundSound ausgeführt
     * und für eine bestimmte Zeit wird eine erneute positive Abfrage der Methode unterbunden.
     * Ohne Unterbindung würde die enthaltene Methode sonst zu oft bzw. zu schnell hintereinander ausgeführt werden.
     * @param figur aktuelle Spielfigur
     * @param monster aktuelles Monster
     */
    public void inDerNaehe(ISpielfigur figur, IMovable monster){
        float kollisionsDistantz=(this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f);
        float aktuelleDistanz=PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY());
        if(aktuelleDistanz>kollisionsDistantz&&aktuelleDistanz<120&&!inDerNaehe){
            figur.playSound(this.getClass().toString()+"Around");
            startTimeNaehe = System.currentTimeMillis();
            setInDerNaehe(true);
        }
    }


    /**
     * @author MEGAtroniker
     * Die Methode resetTimerNaehe unterbindet entsprechend der Variable warteZeitMS ein erneutes Ausführen
     * der "inDerNaehe" entahltenen Methode.
     */
    public void resetTimerNaehe(){
        long endTime = System.currentTimeMillis();
        if(endTime-startTimeNaehe>= warteZeitMS){
            setInDerNaehe(false);
        }
    }


    /**
     * @author MEGAtroniker
     * Die Metode beiKollision, prüft ob sich die Spielfigut in einem bestimmten Radius
     * mit dem aktuellen Monster als Zentrum aufhält. Ist die der Fall wir der Anfgriffsound des Monsters abgespielt
     * und die Spielfigur erleidet Schaden.
     * Sofern einmal Schaden verursacht muss eine gewisse Zeit gewartet werden,
     * bis dasselbe Monster der Figur erneut Schaden zufügen kann.
     * @param figur aktuelle Spielfigur
     * @param monster aktuelles Monster
     */
    public void beiKollision(ISpielfigur figur,IMovable monster) {
        float kollisionsDistantz=(this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f);
        float aktuelleDistanz=PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY());
        if(aktuelleDistanz < kollisionsDistantz&&!hatKollidiert){
            figur.playSound(this.getClass().toString()+"Attack");
            figur.erhalteSchaden(this.schaden);
            startTimeAttac = System.currentTimeMillis();
            setHatKollidiert(true);

        }
    }


    /**
     * @author MEGAtroniker
     * Setter hatKollidiert setzt den Wahrheitswert, ob sich die Spielfigur im Kollisionsradius befindet
     * @param hatKollidiert Wahrheitswert, ob sich die Spielfigur im Kollisionsradius befindet
     */
    public void setHatKollidiert(boolean hatKollidiert) {
        this.hatKollidiert = hatKollidiert;
    }


    /**
     * @author MEGAtroniker
     * Getter isHatKollidiert gibt den Wahrheitswert zurück, ob sich die Spielfigur im Kollisionsradius befindet
     * @return Wahrheitswert, ob sich die Spielfigur im Kollisionsradius befindet
     */
    public boolean isHatKollidiert() {
        return hatKollidiert;
    }


    /**
     * @author MEGAtroniker
     * Die Methode resetTimerAttac unterbindet entsprechend der Variable warteZeitMS ein erneutes Ausführen
     * der "beiKollision" entahltenen Methode.
     */
    public void resetTimerAttac(){
        long endTime = System.currentTimeMillis();
        if(endTime- startTimeAttac >=2000){
            setHatKollidiert(false);
        }
    }


    /**
     * @author MEGAtroniker
     * Getter getSpielmodell gibt das aktuelle Spielmodell zurück
     * @return aktuelles Spielmodell
     */
    @Override
    public ISpielmodell getSpielmodell() {
        return this.spielmodell;
    }


    /**
     * @author MEGAtroniker
     * Setter getSpielmodell setzt das aktuelle Spielmodell
     * @param spielmodell aktuelles Spielmodell
     */
    @Override
    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }


    /**
     * @author MEGAtroniker
     * Getter getSchaden gibt den aktuellen Schadenswert des aktuellen Monsters zurück
     * @return aktueller Schadenswert des aktuellen Monsters
     */
    public int getSchaden() {
        return schaden;
    }


    /**
     * @author MEGAtroniker
     * Getter getStartTimeNaehe gibt die aktuellen Timerzeit der inDerNaehe Methode zurück.
     * Dieser Getter dient bisher nur für Tests.
     * @return aktuellen Timerzeit der inDerNaehe Methode
     */
    public long getStartTimeNaehe() {
        return startTimeNaehe;
    }


    /**
     * @author MEGAtroniker
     * Getter getStartTimeAttac gibt die aktuellen Timerzeit der beiKollision Methode zurück.
     * Dieser Getter dient bisher nur für Tests.
     * @return aktuellen Timerzeit der inDerNaehe Methode
     */
    public long getStartTimeAttac() {
        return startTimeAttac;
    }
}

