package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.util.Einstellungen;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * @author MEGAtroniker
 * Die Klasse Feuerbell beschreibt ein Monster welches asl geschoss des Monsters "Feuermonster" verwendet wird.
 * Es wird ausschießlich im Feuermonster instanziiert und wird in richtung der Spielfugur geschossen.
 * Trifft es die Spielfigur, so erleidet diese schaden, der Feuerball wird gelöscht.
 * Trifft der Feuerball die Spielfigur nicht, so wird diese bei Verlassen des Spielfelds gelöscht.
 */
public class Feuerball extends Monster{
    transient int geschwindigkeit = 5;
    private float deltaX;
    private float deltaY;
    private Spielsteuerung steuerung;
    final int schaden = 2;


    /**
     * @author MEGAtroniker
     * Der Konstruktor die Erstellung der Instanzen des Feuerballs
     * @param posX aktuelle X-Position des zugehörigen Feuermonsters
     * @param posY aktuelle Y-Position des zugehörigen Feuermonsters
     * @param deltaX deltaX
     * @param deltaY deltaY
     */
    public Feuerball(float posX, float posY, int deltaX, int deltaY, Spielsteuerung steuerung) {
        super(posX, posY,Einstellungen.LAENGE_KACHELN_X/4);
        float abstand = (float) sqrt(pow(deltaX, 2) + pow(deltaY, 2)) / geschwindigkeit;
        this.deltaX = deltaX / abstand;
        this.deltaY = deltaY / abstand;
        this.steuerung=steuerung;
        setSchaden(schaden);
    }


    /**
     * @author MEGAtroniker
     * Die Methode zeiche definiert die Darstellung des Feuremonsters
     * @param app  app zur Darstellung
     */
    @Override
    public void zeichne(Spielsteuerung app) {
        app.pushStyle();
        app.noStroke();
        app.fill(255,100,0,200);
        app.ellipse(this.getPosX(), this.getPosY(),(float)groesse , (float)groesse);
        app.popStyle();
    }


    /**
     * @author MEGAtroniker
     * Die Methode bewege bewegt den Feuerball in die richtung des Spielers (zum zeitbunkt des Abschusses).
     * Wir dieser getroffen greift die Methode beiKollision, ggf. auch die Methode inDerNaehe.
     * Sonst verlässt der Fuerball das Spielfeld, und wird aus der liste der movables gelöscht.
     */
    @Override
    public void bewege() {
        super.bewege();
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
     * @author MEGAtroniker
     * Getter getGroesse gibt die Größe des Feuerballs zurück, diese entspricht eienm Viertel der Kachelgröße.
     * @return Größe des Feuerballs
     */
    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X/4;
    }


    /**
     * @author MEGAtroniker
     * Getter getDeltaX gibt die Schrittweite des Feuerballs in X-Richtung zurück.
     * @return Schrittweite des Feuerballs in X-Richtung
     */
    public float getDeltaX() {
        return deltaX;
    }


    /**
     * @author MEGAtroniker
     * Getter getDeltaY gibt die Schrittweite des Feuerballs in Y-Richtung zurück.
     * @return Schrittweite des Feuerballs in Y-Richtung
     */
    public float getDeltaY() {
        return deltaY;
    }
}