
package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.Richtung;

import java.util.Random;

/**
 * @author MEGAtroniker
 * Die Klasse FeuerMonster definiert ein Monster welches das Monster Feuerball als Geschoss verwendt und
 * in regelmäßigen Abständen in Richtung der Spielfigur schießt. Zusätzlich bewegt es sich dur das Spielfeld.
 * Bein Auftreffen auf ein hinderniss wird die Laufrichtung im Uhrzeigersinn angepasst.
 * Treffen Spielfigur und Feuermonster aufeinander so erleidet die Spielfugur alle 2 Sekunden Schaden
 */
public class FeuerMonster extends Monster{
    transient final int posX;
    transient final int posY;
    private static int geschwindigkeit = 2;
    final int deltaX;
    final int deltaY;
    transient ITileMap tileMap;
    transient Richtung ausrichtung;
    transient Feuerball feuerball;
    private int schussZaehler=0;
    transient Random rand;
    final int feuerRate;
    private boolean hatKollidiert=false;

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

    private long startTime;
    FeuerModus feuerModus;
    transient final int schaden = 20;

    /**
     * @MEGAtroniker
     * Konstruktor enthält nun eine ausrichtung für die implementierung des bewegungsalgorthmus.
     * Aus Spielsteuerung erhält man Methoden zur einfachen Implementierung der Bewege Methode
     * @param posX aktuelle X-Position
     * @param posY aktuelle Y-Position
     * @param tileMap  aktuelle TileMap auf der sich das Monster befindet
     * @param steuerung bewegungs Methoden
     * @param ausrichtung   Laufrichtung
     */
    public FeuerMonster(float posX, float posY, ITileMap tileMap,Spielsteuerung steuerung, Richtung ausrichtung, int feuerRate,FeuerModus feuerModus) {
        super(posX, posY,ausrichtung, steuerung,Einstellungen.LAENGE_KACHELN_X/2);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;
        this.deltaX = -geschwindigkeit; // gibt dem FeuerMonster eine Anfangsrichtung und geschwindigkeit
        this.deltaY = -geschwindigkeit;
        this.ausrichtung=ausrichtung;
        rand = new Random();
        this.feuerRate=feuerRate;
        this.feuerModus=feuerModus;
        setSchaden(schaden);
    }

//    /**
//     * @MEGAtroniker
//     * Die Metode beiKollision, soll änderungen am Monster bzw. der Spielfigur vornehmen
//     * @param figur Spielfigur
//     */
//    @Override
//    public void beiKollision(ISpielfigur figur) {
//        if(PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY()) < (this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f)&&!hatKollidiert){ // 20 = spielerradius
//            System.out.println("Kollision mit FeuerMonster");
//            startTime = System.currentTimeMillis();
//            //hatKollidiert=true;
//            setHatKollidiert(true);
//            figur.erhalteSchaden(this.schaden);
//        }
//    }

    /**
     * @MEGAtroniker
     * Die Methode zeiche definiert die Darstellung des Feuremonsters
     * @param /app  app zur Darstellung
     */
//    @Override
//    public void zeichne(PApplet app) {
//        app.fill(255,100,0);
//        app.ellipse(this.getPosX(), this.getPosY(),(float) groesse, (float) groesse);
//    }

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
        switch (getAusrichtung()) {
            case W:
                if (bewegeWestlich(posX, posY)) break;
            case N:
                if (bewegeNoerdlich(posX, posY - 2 * geschwindigkeit, posX, posY - geschwindigkeit)) break;
            case O:
                if (bewegeOestlich(posY, posX + 2 * geschwindigkeit, posX + geschwindigkeit, posY)) break;
            case S:
                bewegeSuedlich(posX, posY + 2 * geschwindigkeit, posX, posY + geschwindigkeit);
                break;
        }
        FeuerModus(this.feuerModus,this.feuerRate);


    }


    /**
     * @MEGAtroniker
     * @param posX
     * @param posY
     * @return
     */
    private boolean bewegeWestlich(int posX, int posY) {
        if(getSpielsteuerung().isErlaubteKoordinate(posX - 2*geschwindigkeit, posY)){
            this.setPosition(posX - geschwindigkeit, posY);
            return true;
        }else{
            setAusrichtung(Richtung.N);
        }
        return false;
    }


    /**
     * @MEGAtroniker
     * @param posX
     * @param i
     * @param posX2
     * @param i2
     * @return
     */
    private boolean bewegeNoerdlich(int posX, int i, int posX2, int i2) {
        if (getSpielsteuerung().isErlaubteKoordinate(posX, i)) {
            this.setPosition(posX2, i2);
            return true;
        } else {
            setAusrichtung(Richtung.O);
        }
        return false;
    }


    /**
     * @MEGAtroniker
     * @param posY
     * @param i
     * @param i2
     * @param posY2
     * @return
     */
    private boolean bewegeOestlich(int posY, int i, int i2, int posY2) {
        if (getSpielsteuerung().isErlaubteKoordinate(i, posY)) {
            this.setPosition(i2, posY2);
            return true;
        } else {
            setAusrichtung(Richtung.S);
        }
        return false;
    }


    /**
     * @MEGAtroniker
     * @param posX
     * @param i
     * @param posX2
     * @param i2
     */
    private boolean bewegeSuedlich(int posX, int i, int posX2, int i2) {
        if (getSpielsteuerung().isErlaubteKoordinate(posX, i)) {
            this.setPosition(posX2, i2);
            return true;
        } else {
            setAusrichtung(Richtung.W);
        }return false;
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

    @Override
    public void vorBetreten(IKachel kachel) {

    }
}

