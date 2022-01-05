package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import grind.util.LaufModus;
import grind.util.Richtung;
import processing.core.PApplet;

import java.util.Random;

/**
 * @author MEGAtroniker
 * Klasse wurde am 04.01.2022 großteils Überarbeitet
 * Mithilfe der Konstruktorparameter können drei verschiedene Arten von Zombies erstellt werden
 *  1. Der Zombie läuft vorhersehbar
 *  2. Der Zombie macht ab und zu eine zusätliche, zufällige Bewegung
 *  3. Der Zombi läuft mit hoher Wahrscheinlichkeit in Spielerrichtung, sehr selten läuft er vohersebar
 */
public class Zombie extends Monster{
    private int posX;
    private int posY;
    private final static int GESCHWINDIGKEIT = 1;
    private int deltaX;
    private int deltaY;
    private ITileMap tileMap;
    private int schaden = 2;
    private Spielsteuerung steuerung;
    private LaufModus laufModus;
    private Richtung ausrichtung;
    private boolean hatKollidiert=false;
    private long startTime;
    private boolean hilfsVariable = false;
   // private long startTimeNaehe;
    //private boolean inDerNaehe=false;

    /**
     * @MEGAtroniker
     * Der Zombie Konstruktor ermöglicht die Erstellung der oben gennaten Zobievarianten
     * @param posX initiale X Position
     * @param posY initiale X Position
     * @param tileMap aktuelle Map
     * @param ausrichtung initiale Ausrichtung
     * @param steuerung aktuelle Spielsteuerung
     * @param laufModus DEFAULT:1.Zombie; RANDOM:2.Zombie; JAGT:3.Zombie;
     */
    public Zombie(float posX, float posY, ITileMap tileMap, Richtung ausrichtung, Spielsteuerung steuerung, LaufModus laufModus) {
        super(posX, posY, Einstellungen.GROESSE_ZOMBIE);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;
        this.deltaX = -GESCHWINDIGKEIT;
        this.deltaY = -GESCHWINDIGKEIT;
        this.ausrichtung = ausrichtung;
        this.steuerung=steuerung;
        this.laufModus=laufModus;
        setSchaden(schaden);
    }


    /**
     * @MEGAtroniker
     * Die Metode beiKollision, soll änderungen am Monster bzw. der Spielfigur vornehmen
     * bisher bekommt nur der Spieler schaden
     * Sofern einmal Schaden verursacht muss eine gewisse Zeit gewartet werden,
     * bis dasselbe Monster der Figur erneut Schaden zufügen kann.
     * @param figur Spielfigur
     */
    @Override
    public void beiKollision(ISpielfigur figur) {
        float kollisionsDistantz=(this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f);
        float aktuelleDistanz=PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY());
        if(aktuelleDistanz < kollisionsDistantz&&!hatKollidiert){
            figur.playZombieAttacSound();
            System.out.println("Zombie atakke");
            startTime = System.currentTimeMillis();
            setHatKollidiert(true);
            figur.erhalteSchaden(this.schaden);
        }
    }

    @Override
    public void inDerNaehe(ISpielfigur figur, IMovable monster){
        super.inDerNaehe(figur, monster);
        /*float kollisionsDistantz=(this.getGroesse()/2f + Einstellungen.GROESSE_SPIELFIGUR/2f);
        float aktuelleDistanz=PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY());
        if(aktuelleDistanz>kollisionsDistantz&&aktuelleDistanz<120&&!inDerNaehe){
            figur.playZombieAroundSound();
            System.out.println("Zombie in der nähe");
            startTimeNaehe = System.currentTimeMillis();
            setInDerNaehe(true);
        }*/
    }



    /**
     * @MEGAtroniker nicht von uns
     * @param app
     */
    @Override
    public void zeichne(PApplet app) {
        app.fill(0, 127, 127);
        app.ellipse(this.getPosX(), this.getPosY(), this.getGroesse(), this.getGroesse());
    }


    /**
     * @MEGAtroniker
     * Die Methode Bewege fürd die Bewegungen der Instanzen je nach Konfiguratin des Konstruktors aus
     * und übergint ständig diie aktuelle Instanzposition
     */
    @Override
    public void bewege() {
        Random random = new Random();
        int posX = this.getPosX();
        int posY = this.getPosY();
        wieWirdBewegt(random, posX, posY);
    }


    /**
     * @MEGAtroniker
     * Die Methode wieWirdBewegt erkennt die Zombivariante und ruft die entsprechende Laufzykluskombination auf.
     * Zusätlich wird nach einer Kollision der resetTimer gestartet
     * @param random benötigt für den Random und Jagdt Laufzyklus
     * @param posX aktuelle X Position
     * @param posY aktuelle Y Position
     */
    private void wieWirdBewegt(Random random, int posX, int posY) {
        switch (this.laufModus){
            case RANDOM:
                bewegeRandom(random, posX, posY);
                break;
            case JAGDT:
                jageSpielfigur(random, posX, posY);
                break;
        }
        bewegeMovable(posX, posY);
        if(hatKollidiert){
            resetTimer();
        }
        if(inDerNaehe){
            resetTimerNaehe();
        }
    }


    /**
     * @MEGAtroniker
     * Die Methode jageSpielfigur bestimmt den Abstand zur Spielfigur jeweils in X und Y- Richtung.
     * Für den betragsmäßig größeren Abstand wir die Richtung bestimmt und mit einer hohen Wahrscheinlichkeit,
     * wird in diese Richtung bewegt.
     * Die Wahrscheinlichkeit dient dazu, dass sonst vorhersehbar bewegt wird
     * und sich der Zombie nicht so schnell "Festfährt".
     * @param random soll ein "Festfahren" verhindern
     * @param posX aktuelle X Position
     * @param posY aktuelle Y Position
     */
    private void jageSpielfigur(Random random, int posX, int posY) {
            int zufall= random.nextInt(10);
            if(zufall==0){
                int abstandX=this.spielmodell.getFigur().getPosX()-getPosX();
                int abstandY=this.spielmodell.getFigur().getPosY()-getPosY();
                if(Math.abs(abstandX)>Math.abs(abstandY)){
                    if(abstandX>0){
                        ausrichtung=Richtung.O;
                    }else{
                        ausrichtung=Richtung.W;
                    }
                }else{
                    if(abstandY>0){
                        ausrichtung=Richtung.S;
                    }else{
                        ausrichtung=Richtung.N;
                    }
                }
                bewegeMovable(posX, posY);
            }
    }


    /**
     * @MEGAtroniker
     * Die Methode bewege Random basiert auf dem vorhersehbaren Bewegen.
     * Zusätzlich wird mit einer geringen Wahrscheinlichkeit die Richtung zufällig geändert.
     * @param random unvorhersehbar machen des normalen Bewegungszyklusses
     * @param posX aktuelle X Position
     * @param posY aktuelle Y Position
     */
    private void bewegeRandom(Random random,  int posX, int posY) {
            int zufall= random.nextInt(50);
            if(zufall==0){
                ausrichtung=Richtung.randomRichtung();;
                bewegeMovable(posX, posY);
                }
    }


    /**
     * @MEGAtroniker
     * Die Methode bewegeMovable bewegt das Monster vorhersehbar, bei einem Hindernis wird immer nach rechts Abgebogen.
     * Damit Kann man mithilfe von Hindernissen auch ein Patrouillieren ermöglichen
     * @param posX aktuelle X Position
     * @param posY aktuelle Y Position
     */
    private void bewegeMovable(int posX, int posY) {
        switch (ausrichtung) {
            case W:
                if (bewegeWestlich(posY, posX - 2 * GESCHWINDIGKEIT, posX - GESCHWINDIGKEIT, posY, Richtung.N)) break;
            case N:
                if (bewegeNoerdlich(posX, posY - 2 * GESCHWINDIGKEIT, posX, posY - GESCHWINDIGKEIT, Richtung.O)) break;
            case O:
                if (bewegeOestlich(posY, posX + 2 * GESCHWINDIGKEIT, posX + GESCHWINDIGKEIT, posY, Richtung.S)) break;
            case S:
                bewegeSuedlich(posX, posY);
        }
    }


    /**
     * @MEGAtroniker
     * @param posX aktuelle X Position
     * @param posY aktuelle Y Position
     */
    private void bewegeSuedlich(int posX, int posY) {
        if(steuerung.isErlaubteKoordinate(posX, posY + 2*GESCHWINDIGKEIT)){
            this.setPosition(posX, posY +GESCHWINDIGKEIT);
            return;
        }else{
            ausrichtung=Richtung.W;
        }
    }


    /**
     *
     * @param posY
     * @param i
     * @param i2
     * @param posY2
     * @param s
     * @return
     */
    private boolean bewegeOestlich(int posY, int i, int i2, int posY2, Richtung s) {
        if (steuerung.isErlaubteKoordinate(i, posY)) {
            this.setPosition(i2, posY2);
            return true;
        } else {
            ausrichtung = s;
        }
        return false;
    }


    /**
     *
     * @param posX
     * @param i
     * @param posX2
     * @param i2
     * @param o
     * @return
     */
    private boolean bewegeNoerdlich(int posX, int i, int posX2, int i2, Richtung o) {
        if (steuerung.isErlaubteKoordinate(posX, i)) {
            this.setPosition(posX2, i2);
            return true;
        } else {
            ausrichtung = o;
        }
        return false;
    }


    /**
     *
     * @param posY
     * @param i
     * @param i2
     * @param posY2
     * @param n
     * @return
     */
    private boolean bewegeWestlich(int posY, int i, int i2, int posY2, Richtung n) {
        if (steuerung.isErlaubteKoordinate(i, posY)) {
            this.setPosition(i2, posY2);
            return true;
        } else {
            ausrichtung = n;
        }
        return false;
    }


    /**
     * @MEGAtroniker nicht von uns||wird nur bei DornPflanze Aufgerufen???
     * @param kachel
     */
    @Override
    public void vorBetreten(IKachel kachel) {
        if(!kachel.istBetretbar()){
            deltaX = -deltaX;
            deltaY = -deltaY;
            posX += deltaX ;
            posY += deltaY ;
            hilfsVariable = true; // Eine Kachel wurde getroffen und Richtung umgekehrt.

        }
    }


    /**
     * @MEGAtroniker nicht von uns||
     * @return
     */
    @Override
    public ISpielmodell getSpielmodell() {
        return this.spielmodell;
    }


    /**
     * @MEGAtroniker nicht von uns
     * @param spielmodell
     */
    @Override
    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }



    /**
     * @MEGAtroniker
     * Methode resetTimer setzt booleand hatKollidiert auf false,
     * wenn in den letzten 2000ms keine Kollision stattgefunden hat.
     * Monster macht bei Kontakt nur alle 2s Schaden, nicht ständig.
     */
    public void resetTimer(){
        long endTime = System.currentTimeMillis();
        if(endTime-startTime>=2000){
            setHatKollidiert(false);
        }
    }
/*
    public void resetTimerNaehe(){
        long endTime = System.currentTimeMillis();
        if(endTime-startTimeNaehe>=2000){
            setInDerNaehe(false);
        }
    }*/




/*
    private void setInDerNaehe(boolean inDerNaehe) {
        this.inDerNaehe = inDerNaehe;
    }*/


    /**
     * @MEGAtroniker
     * Setter setHastKollidiert für testing und kapselung
     * @param hatKollidiert
     */
    public void setHatKollidiert(boolean hatKollidiert) {
        this.hatKollidiert = hatKollidiert;
    }




}