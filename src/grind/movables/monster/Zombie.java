package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.core.impl.Spielsteuerung;
import grind.kacheln.ITileMap;
import grind.util.Einstellungen;
import grind.util.LaufModus;
import grind.util.Richtung;
import java.util.Random;


/**
 * @author MEGAtroniker
 * Die Klasse Zombie ermöglicht es Moster dieser Art zu erstellen.
 * Mithilfe der Konstruktorparameter können drei verschiedene Arten von Zombies erstellt werden
 *  1. Der Zombie läuft vorhersehbar
 *  2. Der Zombie macht ab und zu eine zusätliche, zufällige Bewegung
 *  3. Der Zombi läuft mit hoher Wahrscheinlichkeit in Spielerrichtung, sehr selten läuft er vohersebar
 */
public class Zombie extends Monster{
    transient private int geschwindigkeit = 2;
    transient private int deltaX;
    transient private int deltaY;
    transient private int schaden = 10;
    transient ITileMap tileMap;
    private LaufModus laufModus;
    private boolean hilfsVariable = false;


    /**
     * @author MEGAtroniker
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
        this.deltaX = -getGeschwindigkeit();
        this.deltaY = -getGeschwindigkeit();
        this.laufModus=laufModus;
        setSchaden(schaden);
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewege, führt die Bewegungen der Instanzen je nach Konfiguration des Konstruktors aus
     * und übergibt ständig die aktuelle Instanzposition.
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
     * @author MEGAtroniker
     * Die Methode wieWirdBewegt erkennt die Zombivariante und ruft die entsprechende Laufzykluskombination auf.
     * Der DEFAULT Laufmodus entspricht der bewege Movable Methode und enthält keinen Zusatz,
     * welcher in einem case verarbeitet werden würde.
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


    /**
     * @author MEGAtroniker
     * Getter gerGroesse gibt die Größe des Zombies zurück, diese entspricht der Kachelgröße.
     * @return Größe des Zombies
     */
    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X;
    }


    /**
     * @author MEGAtroniker
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
     * @author MEGAtroniker
     * Die Methode bewege Random basiert auf dem vorhersehbaren Bewegen der Methode bewegeMovable.
     * Zusätzlich wird mit einer geringen Wahrscheinlichkeit die Richtung zufällig geändert.
     * @param random unvorhersehbar machen des normalen Bewegungszyklus
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
     * Die Methode bewegeWestlich ist ein Teil der bewegeMovable Methode.
     * Hier wird die Bewegung in westliche Richtung realisiert. Ist dies wegen eines Hindernisses nicht möglich,
     * wird das Monster nach Norden ausgerichtet.
     * @param nextPosY nächste Y Koordinate in Laufrichtung
     * @param nextPosX nächste X Koordinate in Laufrichtung
     * @param newPosX neue X Koordinate
     * @param newPosY neue Y Koordinate
     * @return eine witere westliche Bewegung ist nicht möglich
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
     * Die Methode bewegeOestlich ist ein Teil der bewegeMovable Methode.
     * Hier wird die Bewegung in östliche Richtung realisiert. Ist dies wegen eines Hindernisses nicht möglich,
     * wird das Monster nach Süden ausgerichtet.
     * @param nextPosY nächste Y Koordinate in Laufrichtung
     * @param nextPosX nächste X Koordinate in Laufrichtung
     * @param newPosX neue X Koordinate
     * @param newPosY neue Y Koordinate
     * @return eine witere östliche Bewegung ist nicht möglich
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