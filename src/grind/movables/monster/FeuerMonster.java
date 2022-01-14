
package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.ITileMap;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.Richtung;
import java.util.Random;

/**
 * @author MEGAtroniker
 * Die Klasse FeuerMonster definiert ein Monster welches das Monster Feuerball als Geschoss verwendt und
 * in regelmäßigen Abständen in Richtung der Spielfigur schießt. Zusätzlich bewegt es sich dur das Spielfeld.
 * Beim Auftreffen auf ein hinderniss wird die Laufrichtung im Uhrzeigersinn angepasst.
 * Mithilfe der Konstruktorparameter können drei verschiedene Arten von Feuermonster erstellt werden,
 * diese unterscheiden sich in dem Feuermodus
 *  1. konstante Feurrate
 *  2. SemiRandom eine quasi vorhersehbare Feuerrate mit wenigen zufälligen extra schüssen
 *  3. Random, zufällige Feuerrate
 *
 * Treffen Spielfigur und Feuermonster aufeinander so erleidet die Spielfugur alle 2 Sekunden Schaden
 */
public class FeuerMonster extends Monster{
    transient final int posX;
    transient final int posY;
    private static final int geschwindigkeit = 2;
    final int deltaX;
    final int deltaY;
    transient ITileMap tileMap;
    transient Richtung ausrichtung;
    transient Feuerball feuerball;
    private int schussZaehler=0;
    transient Random rand;
    final int feuerRate;
    private boolean hatKollidiert=false;
    private long startTime;
    FeuerModus feuerModus;
    transient final int schaden = 20;

    /**
     * @MEGAtroniker
     * Der Feuermonster Konstruktor ermöglicht die Erstellung der oben gennaten Feuermonstervarianten
     * Aus der Spielsteuerung erhält man Methoden zur einfachen Implementierung der Bewege Methode
     * @param posX initiale X-Position
     * @param posY initiale Y-Position
     * @param tileMap  aktuelle TileMap auf der sich das Monster befindet
     * @param steuerung aktuelle Spielsteuerung
     * @param ausrichtung   Laufrichtung
     */
    public FeuerMonster(float posX, float posY, ITileMap tileMap,Spielsteuerung steuerung, Richtung ausrichtung, int feuerRate,FeuerModus feuerModus) {
        super(posX, posY,ausrichtung, steuerung,Einstellungen.LAENGE_KACHELN_X/2);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;
        this.deltaX = -geschwindigkeit;
        this.deltaY = -geschwindigkeit;
        this.ausrichtung=ausrichtung;
        rand = new Random();
        this.feuerRate=feuerRate;
        this.feuerModus=feuerModus;
        setSchaden(schaden);
    }


    /**
     * @author MEGAtroniker
     * Getter gerGroesse gibt die Größe des Feuermonsters zurück, diese entspricht der halben Kachelgröße.
     * @return Größe des Feuermonsters
     */
    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X/2;
    }

    /**
     * @MEGAtroniker
     * Die Methode bewege enthält die aktuellen Koordinaten des Feuermonsters.
     * Es bewegt sich solange in die initiale Richtung, bis ein Hinderniss kommt,
     * dann wir die Richtung im Uhrzeigersinn gewechselt.
     */
    @Override
    public void bewege() {
        super.bewege();
        int posX = this.getPosX();
        int posY = this.getPosY();
        bewegeMovable(posX, posY);
        FeuerModus(this.feuerModus,this.feuerRate);
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewegeMovable bewegt das Monster vorhersehbar, bei einem Hindernis wird immer nach rechts Abgebogen.
     * Damit Kann man mithilfe von Hindernissen auch ein Patrouillieren ermöglichen.
     * @param posX aktuelle X Position
     * @param posY aktuelle Y Position
     */
    private void bewegeMovable(int posX, int posY) {
        switch (getAusrichtung()) {
            case W:
                if (bewegeWestlich(posY, posX - 2 * getGeschwindigkeit(), posX - getGeschwindigkeit(), posY))
                    break;
            case N:
                if (bewegeNoerdlich(posX, posY - 2 * getGeschwindigkeit(), posX, posY - getGeschwindigkeit()))
                    break;
            case O:
                if (bewegeOestlich(posY, posX + 2 * getGeschwindigkeit(), posX + getGeschwindigkeit(), posY))
                    break;
            case S:
                bewegeSuedlich(posX, posY);
        }
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewegeSuedlich ist ein Teil der bewegeMovable Methode.
     * Hier wird die Bewegung in südliche Richtung realisiert. Ist dies wegen eines Hindernisses nicht möglich,
     * wird das Monster nach Westen ausgerichtet.
     * @param posX aktuelle X Position
     * @param posY aktuelle Y Position
     */
    private void bewegeSuedlich(int posX, int posY) {
        if(getSpielsteuerung().isErlaubteKoordinate(posX, posY + 2*geschwindigkeit)){
            this.setPosition(posX, posY +geschwindigkeit);
            return;
        }else{
            setAusrichtung(Richtung.W);
        }
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewegeOestlich ist ein Teil der bewegeMovable Methode.
     * Hier wird die Bewegung in östliche Richtung realisiert. Ist dies wegen eines Hindernisses nicht möglich,
     * wird das Monster nach Süden ausgerichtet.
     * @param nextPosY nächste Y Koordinate in Laufrichtung
     * @param nextPosX nächste X Koordinate in Laufrichtung
     * @param newPosX neue X Koordinate
     * @param newPosY neue Y Koordinate
     * @return eine witere östliche Bewegung ist nicht möglich
     */
    private boolean bewegeOestlich(int nextPosY, int nextPosX, int newPosX, int newPosY) {
        if (getSpielsteuerung().isErlaubteKoordinate(nextPosX, nextPosY)) {
            this.setPosition(newPosX, newPosY);
            return true;
        } else {
            setAusrichtung(Richtung.S);
        }
        return false;
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewegeNoerdlich ist ein Teil der bewegeMovable Methode.
     * Hier wird die Bewegung in nördliche Richtung realisiert. Ist dies wegen eines Hindernisses nicht möglich,
     * wird das Monster nach Osten ausgerichtet.
     * @param nextPosX nächste X Koordinate in Laufrichtung
     * @param nextPosY nächste Y Koordinate in Laufrichtung
     * @param newPosX neue X Koordinate
     * @param newPosY neue Y Koordinate
     * @return eine witere nördliche Bewegung ist nicht möglich
     */
    private boolean bewegeNoerdlich(int nextPosX, int nextPosY, int newPosX, int newPosY) {
        if (getSpielsteuerung().isErlaubteKoordinate(nextPosX, nextPosY)) {
            this.setPosition(newPosX, newPosY);
            return true;
        } else {
            setAusrichtung(Richtung.O);
        }
        return false;
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewegeWestlich ist ein Teil der bewegeMovable Methode.
     * Hier wird die Bewegung in westliche Richtung realisiert. Ist dies wegen eines Hindernisses nicht möglich,
     * wird das Monster nach Norden ausgerichtet.
     * @param nextPosY nächste Y Koordinate in Laufrichtung
     * @param nextPosX nächste X Koordinate in Laufrichtung
     * @param newPosX neue X Koordinate
     * @param newPosY neue Y Koordinate
     * @return eine witere westliche Bewegung ist nicht möglich
     */
    private boolean bewegeWestlich(int nextPosY, int nextPosX, int newPosX, int newPosY) {
        if (getSpielsteuerung().isErlaubteKoordinate(nextPosX, nextPosY)) {
            this.setPosition(newPosX, newPosY);
            return true;
        } else {
            setAusrichtung(Richtung.N);
        }
        return false;
    }


        /**
         * @MEGAtroniker
         * Methode FeuerModus ermöglicht dem Feuermonster in 3 verschiedenen Modi Feuerbälle zu schießen.
         * Modus wird bei Instanziierung des Feuermonsters
         * durch die Übergabe der feuerRate und des feuerModus im Konstruktor festgelegt.
         *
         * 1. Random: Monster schießt in rein zufälligen Abständen.
         * 2. Semirandom: Die Abstände der Schüsse sind statistisch gleichmäßiger, aber nicht komplett einheitlich.
         * 3. Konstant: Feuerbälle werden in äquidistanten Zeitabstanden abgefeuert.
         * @param feuerModus s.o.
         * @param feuerRate Integer zum Einstellen der Zeitabstände
         */
        private void FeuerModus (FeuerModus feuerModus,int feuerRate){
            if (rand.nextInt(feuerRate) == 1 && feuerModus == FeuerModus.RANDOM) {
                shootFeuerball();
            } else if (feuerModus == FeuerModus.SEMIRANDOM) {
                if (rand.nextInt(5) == 1) {
                    schussZaehler += 1;
                }
                if (2 * schussZaehler >= feuerRate) {
                    shootFeuerball();
                    schussZaehler = 0;
                }
            } else if (feuerModus == FeuerModus.KONSTANT) {
                schussZaehler += 1;
                if (schussZaehler >= feuerRate) {
                    shootFeuerball();
                    schussZaehler = 0;
                }
            }
        }

        /**
         * @MEGAtroniker
         * Methode schießeFeuerball ermittelt Abstand zur Figur, generiert Feuerball,
         * der sich in Richtung der Figur bewegt, und fügt diesen dem aktuellen Spielmodell hinzu.
         */
        public void shootFeuerball() {
            int abstandX = this.spielmodell.getFigur().getPosX() - getPosX();
            int abstandY = this.spielmodell.getFigur().getPosY() - getPosY();
            feuerball = new Feuerball(getPosX(), getPosY(), abstandX, abstandY, this.getSpielsteuerung());
            this.spielmodell.addMonster(feuerball);
        }

    /**
     * @MEGAtroniker
     * Setter setHastKollidiert für testing und kapselung
     * @param hatKollidiert
     */
    public void setHatKollidiert(boolean hatKollidiert) {
        this.hatKollidiert = hatKollidiert;
    }



    /**
     * @MEGAtroniker
     * Getter, notwendig für die tests und kapselung
     * @return
     */
    public boolean isHatKollidiert() {
        return hatKollidiert;
    }





}

