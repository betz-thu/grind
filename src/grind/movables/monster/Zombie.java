package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
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
    transient private int posX;
    transient private int posY;
    transient private static int geschwindigkeit = 2;
    transient private int deltaX;
    transient private int deltaY;
    transient private int schaden = 10;
    transient ITileMap tileMap;
    transient private LaufModus laufModus;
    private boolean hilfsVariable = false;


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
        super(posX, posY,ausrichtung, steuerung, Einstellungen.LAENGE_KACHELN_X);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;
        this.deltaX = -geschwindigkeit;
        this.deltaY = -geschwindigkeit;
        this.laufModus=laufModus;
        setSchaden(schaden);
    }

//    @Override
//    public void beiKollision(ISpielfigur figur) {
//        if(PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY()) < (Einstellungen.GROESSE_ZOMBIE/2f + 20)){ // 20 = spielerradius
//            //System.out.println("Kollision mit Zombie");
//        }
//    }

    /**
     * @MEGAtroniker nicht von uns
     * @param app
     */
    /*@Override
    public void zeichne(PApplet app) {
        app.fill(0, 127, 127);
        app.ellipse(this.getPosX(), this.getPosY(), groesse, groesse);



    /**
     * @MEGAtroniker
     * Die Methode Bewege fürd die Bewegungen der Instanzen je nach Konfiguratin des Konstruktors aus
     * und übergint ständig diie aktuelle Instanzposition
     */
    @Override
    public void bewege() {
        super.bewege();
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

    }

    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X;
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
                        setAusrichtung(Richtung.O);
                    }else{
                        setAusrichtung(Richtung.W);
                    }
                }else{
                    if(abstandY>0){
                        setAusrichtung(Richtung.S);
                    }else{
                        setAusrichtung(Richtung.N);
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
                setAusrichtung(Richtung.randomRichtung());
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
        switch (getAusrichtung()) {
            case W:
                if (bewegeWestlich(posY, posX - 2 * geschwindigkeit, posX - geschwindigkeit, posY)) break;
            case N:
                if (bewegeNoerdlich(posX, posY - 2 * geschwindigkeit, posX, posY - geschwindigkeit)) break;
            case O:
                if (bewegeOestlich(posY, posX + 2 * geschwindigkeit, posX + geschwindigkeit, posY)) break;
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
        if(getSpielsteuerung().isErlaubteKoordinate(posX, posY + 2*geschwindigkeit)){
            this.setPosition(posX, posY +geschwindigkeit);
            return;
        }else{
            setAusrichtung(Richtung.W);
        }
    }


    /**
     *
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
     *
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
     *
     * @param posY
     * @param i
     * @param i2
     * @param posY2
     * @return
     */
    private boolean bewegeWestlich(int posY, int i, int i2, int posY2) {
        if (getSpielsteuerung().isErlaubteKoordinate(i, posY)) {
            this.setPosition(i2, posY2);
            return true;
        } else {
            setAusrichtung(Richtung.N);
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


    @Override
    public void setGeschwindigkeit(int xGeschwindigkeit) {
        this.geschwindigkeit = xGeschwindigkeit;
    }

    @Override
    public int getGeschwindigkeit() {
        return this.geschwindigkeit;
    }


}