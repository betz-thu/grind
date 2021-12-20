package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * @author MEGAtroniker
 * Die Klasse Feuerbell beschreibt ein Monster welches asl geschoss des Monsters "Feuermonster" verwendet wird.
 * es entsteht im Feuermonster und wird in richtung der Spielfugur geschossen, trifft es die Spielfigur,
 * so erleidet diese schaden, der Feuerball wird gelöscht. Trifft der Feuerball die Spielfigur nicht,
 * so wird diese bei verlassen des Spielfelds gelöscht.
 */
public class Feuerball extends Monster{
    final int GESCHWINDIGKEIT= 5;
    float deltaX;
    float deltaY;
    Spielsteuerung steuerung;
    final int schaden = 5;


    /**
     * Konstruktor
     * @param posX aktuelle X-Position des zugehörigen Feuermonsters
     * @param posY aktuelle Y-Position des zugehörigen Feuermonsters
     * @param deltaX deltaX
     * @param deltaY deltaY
     */
    public Feuerball(float posX, float posY, int deltaX, int deltaY, Spielsteuerung steuerung) {
        super(posX, posY,Einstellungen.GROESSE_FEUERBALL);
        float abstand = (float) sqrt(pow(deltaX, 2) + pow(deltaY, 2)) / GESCHWINDIGKEIT;
        this.deltaX = deltaX / abstand;// geschwindigkeit*deltaX/abstand;
        this.deltaY = deltaY / abstand;
        this.steuerung=steuerung;
        setSchaden(schaden);
    }

    /**
     * Die Methode zeiche definiert die Darstellung des Feuremonsters
     * @param app  app zur Darstellung
     */
    @Override
    public void zeichne(PApplet app) {
        app.pushStyle();
        app.fill(255,100,0);
        app.ellipse(this.getPosX(), this.getPosY(),(float)Einstellungen.GROESSE_FEUERBALL , (float)Einstellungen.GROESSE_FEUERBALL);
        app.popStyle();
    }

    /**
     * @MEGAtroniker
     * Die Methode bewege bewegt den Feuerball in richtung des Spielers.
     * Wir dieser getroffen greift die Methode beiKollision
     * Sonst verlässt der Fuerball das Spielfeld und  wird aus der liste der movables gelöscht
     */
    @Override
    public void bewege() {
        int posX = this.getPosX();
        int posY = this.getPosY();
        posX += deltaX;
        posY += deltaY;
        if (this.steuerung.isSpielfeldrand(posX,posY)) {
            this.getSpielmodell().removeMovable(this);
        } else {
            this.setPosition(posX, posY);
        }
    }

    /**
     * @MEGAtroniker
     * Die Metode beiKollision, soll änderungen am Monster bzw. der Spielfigur vornehmen
     * @param figur Spielfigur
     */
    public void beiKollision(ISpielfigur figur) {
        if(PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY()) < (Einstellungen.GROESSE_FEUERBALL/2f + 20)){ // 20 = spielerradius
            this.getSpielmodell().removeMovable(this);
            System.out.println("Treffer!");
            figur.erhalteSchaden(this.schaden);
        }
    }

    /**
     * @MEGAtroniker
     * Is never used!!!!!!!!!
     * ersetzt durch assoziation zu Spielsteuerung!!!!
     * @param kachel nope
     */
    @Override
    public void vorBetreten(IKachel kachel) {

    }

    @Override
    public ISpielmodell getSpielmodell() {
        return spielmodell;
    }

    @Override
    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }
}