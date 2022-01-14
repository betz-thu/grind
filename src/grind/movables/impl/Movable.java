package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.IMovable;
import grind.util.Richtung;

/**
 * @Autor Megatroniker
 * Überladener Konstruktor, um Spielfigur und anderer Movables eine Ausrichtung zu geben.
 * getAusrichtung und setAusrichtung, um Ausrichtung zu setzen oder zu übergeben.
 * zeichne ist einheitliche Funktion, um movables zu zeichnen, die per Bild dargestellt werden.
 */
import grind.movables.ISpielfigur;
import processing.core.PConstants;
import processing.core.PImage;

public abstract class Movable implements IMovable {

    float posX;
    float posY;
    int groesse;
    Richtung ausrichtung;
    transient private Spielsteuerung spielsteuerung;

    public Movable(float posX, float posY, int groesse) {
        this.posX = posX;
        this.posY = posY;
        this.groesse = groesse;
    }

    /**
     * @MEGAtroniker
     * Konstruktor Movable
     * Überladener Konstruktor, um Spielfigur und anderer Movables eine Ausrichtung zu geben.
     * @param posX gibt X-Position des Movables an.
     * @param posY gibt Y-Position des Movables an.
     * @param ausrichtung gibt Ausrichtung des Movables mithilfe der enmus an.
     */
    public Movable(float posX, float posY, Richtung ausrichtung, int groesse) {
        this.ausrichtung = ausrichtung;
        this.posX = posX;
        this.posY = posY;
        this.groesse = groesse;
    }

    public Movable(float posX, float posY, Richtung ausrichtung, Spielsteuerung spielsteuerung, int groesse) {
        this.ausrichtung = ausrichtung;
        this.posX = posX;
        this.posY = posY;
        this.groesse = groesse;
        this.spielsteuerung = spielsteuerung;
    }

    /**
     * @Autor MEGAtroniker
     * zeichne
     * Holt aus den im Dictionary images der Klasse Spielsteuerung geladenen Bildern das passende Bild, und zeigt dieses
     * in der passenden Ausrichtung an der Position des Movables an.
     * Der jeweilige Key des passenden Bildes ist der String aus Klasse und Stufe des Movables.
      * @param spielsteuerung Spielsteuerung (extends PApplet): hiervon werden die Bilder geholt,
     *                       und hier werden sie auch angezeigt.
     */
    public void zeichne(Spielsteuerung spielsteuerung) {
        spielsteuerung.pushStyle();
        spielsteuerung.imageMode(PConstants.CENTER);
        spielsteuerung.pushMatrix();
        spielsteuerung.translate(this.posX, this.posY);
        if(this.ausrichtung!=null){
            int n = 1;
            switch (this.ausrichtung) {
                case N:
                    n = 0;
                    break;
                case O:
                    n = 1;
                    break;
                case S:
                    n = 2;
                    break;
                case W:
                    n = 3;
            }
            spielsteuerung.rotate(PConstants.HALF_PI*n);
        }
        PImage img;
        String a = this.getClass().toString()+this.getStufe();
        if(spielsteuerung.getImages().get(a)==null) {
            img = (PImage) spielsteuerung.getImages().get("class grind.movables.monster.Zombie0");
            System.out.println("Bild für "+this.getClass().toString()+this.getStufe()+" fehlt noch.");
        } else {
            img = (PImage) spielsteuerung.getImages().get(a);
        }
        spielsteuerung.image(img, 0, 0, this.getGroesse(), this.getGroesse());
        spielsteuerung.popMatrix();
        spielsteuerung.popStyle();
    }

    public int getStufe() {
        return 0;
    }

    @Override
    public int getPosX() {
        return (int) posX;
    }

    @Override
    public int getPosY() {
        return (int) posY;
    }

    @Override
    public int getGroesse() {
        return groesse;
    }

    @Override
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }


    /**
     * @MEGAtroniker
     * Methode getAusrichtung
     * Gibt die aktuelle Ausrichtung des Movables zurück.
     * @return Richtung in Form der enum
     */
    @Override
    public Richtung getAusrichtung() {
        return ausrichtung;
    }

    /**
     * @MEGAtroniker
     * Methode setAusrichtung
     * Setzt die aktuelle Ausrichtung auf den Übergabewert.
     * @param ausrichtung ist die neue Ausrichtung.
     */
    @Override
    public void setAusrichtung(Richtung ausrichtung) {
        this.ausrichtung = ausrichtung;
    }



    public Spielsteuerung getSpielsteuerung() {
        return spielsteuerung;
    }


}
