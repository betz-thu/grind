
package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.Richtung;
import processing.core.PApplet;
import java.util.Random;
import static java.lang.Math.abs;

/**
 * @author MEGAtroniker
 * Die Klasse FeuerMonster definiert ein Monster welches das Monster Feuerball als Geschoss verwendt und
 * in regelmäßigen Abständen in Richtung der Spielfigur schießt. Zusätzlich bewegt es sich dur das Spielfeld.
 * Bein Auftreffen auf ein hinderniss wird die Laufrichtung im Uhrzeigersinn angepasst.
 */
public class FeuerMonster extends Monster{
    final int posX;
    final int posY;
    private final static int GESCHWINDIGKEIT = 2;
    final int deltaX;
    final int deltaY;
    ITileMap tileMap;
    Spielsteuerung steuerung;
    Richtung ausrichtung;
    Feuerball feuerball;
    private int schussZaehler=0;
    Random rand;
    final int feuerRate;
    private boolean hatKollidiert=false;
    private long startTime;
    FeuerModus feuerModus;
    private int schaden = 20;

    /**
     * Konstruktor enthält nun eine ausrichtung für die implementierung des bewegungsalgorthmus.
     * Aus Spielsteuerung erhält man Methoden zur einfachen Implementierung der Bewege Methode
     * @param posX aktuelle X-Position
     * @param posY aktuelle Y-Position
     * @param tileMap  aktuelle TileMap auf der sich das Monster befindet
     * @param steuerung bewegungs Methoden
     * @param ausrichtung   Laufrichtung
     */
    public FeuerMonster(float posX, float posY, ITileMap tileMap,Spielsteuerung steuerung, Richtung ausrichtung,int feuerRate,FeuerModus feuerModus) {
        super(posX, posY,ausrichtung,Einstellungen.GROESSE_FEUERMONSTER);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;
        this.deltaX = -GESCHWINDIGKEIT; // gibt dem FeuerMonster eine Anfangsrichtung und geschwindigkeit
        this.deltaY = -GESCHWINDIGKEIT;
        this.steuerung=steuerung;
        this.ausrichtung=ausrichtung;
        rand = new Random();
        this.feuerRate=feuerRate;
        this.feuerModus=feuerModus;
        setSchaden(schaden);
    }

    /**
     * Die Metode beiKollision, soll änderungen am Monster bzw. der Spielfigur vornehmen
     * @param figur Spielfigur
     */
    @Override
    public void beiKollision(ISpielfigur figur) {
        if(PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY()) < (this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f)&&!hatKollidiert){ // 20 = spielerradius
            System.out.println("Kollision mit FeuerMonster");
            startTime = System.currentTimeMillis();
            hatKollidiert=true;
            figur.erhalteSchaden(this.schaden);
        }
    }

    /**
     * Die Methode zeiche definiert die Darstellung des Feuremonsters
     * @param app  app zur Darstellung
     */
    @Override
    public void zeichne(PApplet app) {
        app.fill(255,100,0);
        app.ellipse(this.getPosX(), this.getPosY(),(float) this.getGroesse(), (float) this.getGroesse());
    }

    /**
     * Die Methode bewege enthält die aktuellen Koordinaten des Feuermonsters.
     * Es bewegt sich solange in die initiale Richtung, bis ein Hinderniss kommt,
     * dann wir die Richtung im Uhrzeigersinn gewechselt.
     */
    @Override
    public void bewege() {
        int posX = this.getPosX();
        int posY = this.getPosY();
        switch (ausrichtung) {
            case W:
                if(steuerung.isErlaubteKoordinate(posX - 2*GESCHWINDIGKEIT, posY)){
                    this.setPosition(posX - GESCHWINDIGKEIT, posY);
                    break;
                }else{
                    ausrichtung=Richtung.N;
                }
            case N:
                if(steuerung.isErlaubteKoordinate(posX , posY - 2*GESCHWINDIGKEIT)){
                    this.setPosition(posX, posY-GESCHWINDIGKEIT);
                    break;
                }else{
                    ausrichtung=Richtung.O;
                }
            case O:
                if(steuerung.isErlaubteKoordinate(posX + 2*GESCHWINDIGKEIT, posY)){
                    this.setPosition(posX+ GESCHWINDIGKEIT, posY);
                    break;
                }else{
                    ausrichtung=Richtung.S;
                }
            case S:
                if(steuerung.isErlaubteKoordinate(posX , posY + 2*GESCHWINDIGKEIT)){
                    this.setPosition(posX, posY+GESCHWINDIGKEIT);
                    break;
                }else{
                    ausrichtung=Richtung.W;
                }
        }
        FeuerModus(this.feuerModus,this.feuerRate);
        if(hatKollidiert){
            resetTimer();
        }
    }

    /**
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
    private void FeuerModus(FeuerModus feuerModus, int feuerRate) {
        if(rand.nextInt(feuerRate)==1 && feuerModus==FeuerModus.RANDOM){
            schießeFeuerball();
        }else if(feuerModus==FeuerModus.SEMIRANDOM){
            if (rand.nextInt(5)==1){
                schussZaehler+=1;
            }
            if(2*schussZaehler >= feuerRate){
                schießeFeuerball();
                schussZaehler=0;
            }
        }else if(feuerModus==FeuerModus.KONSTANT) {
            schussZaehler+=1;
            if (schussZaehler >= feuerRate) {
                schießeFeuerball();
                schussZaehler = 0;
            }
        }
    }

    /**
     * Methode schießeFeuerball ermittelt Abstand zur Figur, generiert Feuerball,
     * der sich in Richtung der Figur bewegt, und fügt diesen dem aktuellen Spielmodell hinzu.
     */
    public void schießeFeuerball(){
        int abstandX=this.spielmodell.getFigur().getPosX()-getPosX();
        int abstandY=this.spielmodell.getFigur().getPosY()-getPosY();
        feuerball = new Feuerball(getPosX(), getPosY(),abstandX,abstandY,this.tileMap,this.steuerung);
        this.spielmodell.addMonster(feuerball);
    }

    /**
     * Methode resetTimer setzt booleand hatKollidiert auf false,
     * wenn in den letzten 2000ms keine Kollision stattgefunden hat.
     * Monster macht bei Kontakt nur alle 2s Schaden, nicht ständig.
     */
    public void resetTimer(){
        long endTime = System.currentTimeMillis();
        if(endTime-startTime>=+2000){
            hatKollidiert=false;
        }
    }

    /**
     * is never used!!!!!!!!!
     * ersetzt durch assoziation zu Spielsteuerung!!!!
     * @param kachel
     */
    @Override
    public void vorBetreten(IKachel kachel) {
        if(!kachel.istBetretbar()){
        }
    }

}