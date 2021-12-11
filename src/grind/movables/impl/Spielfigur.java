package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.movables.ISpielfigur;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * @Autor Megatronik
 * Konstruktor angepasst, erbt nun von überladenem Movable-Konstruktor
 * Methode zeichne: zeichnet nun die Spielfigur als Bild, mit Ausrichtung in Laufrichtung.
 */
public class Spielfigur extends Movable implements ISpielfigur {

    public float getGESCHWINDIGKEIT() {
        return GESCHWINDIGKEIT;
    }

    private final float GESCHWINDIGKEIT = 3f;


    int gold = 0;

    /**
     * Konstruktor Spielfigur
     * @param posX gibt X-Position der Spielfigur an.
     * @param posY gibt Y-Position der Spielfigur an.
     */
    public Spielfigur(float posX, float posY, Richtung richtung) {
        super(posX, posY, richtung);
    }

    /**
     * Methode zeichne: zeichnet Bild der Spielfigur, abhängig von Ausrichtung und Position.
     * Dadurch schaut die Spielfigur immer in Laufrichtung.
     * Bild: "SpielfigurOhneWaffe.jpg"
     * @param app PApplet, für Darstellung in Processing.
     */
    @Override
    public void zeichne(PApplet app) {
        PImage spielfigurOhneWaffe = app.loadImage("SpielfigurOhneWaffe.jpg");
        app.imageMode(PConstants.CENTER);
        app.pushMatrix();
        app.translate(this.getPosX(), this.getPosY());
        int n =1;
        switch (this.getAusrichtung()) {
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
        app.rotate(PConstants.HALF_PI*n);
        app.image(spielfigurOhneWaffe, 0, 0, 40, 40);
        app.popMatrix();


    }


    @Override
    public void bewege(Richtung richtung) {

        switch (richtung) {
            case N:
                if (isSpielfeldrand(this.posX,this.posY-GESCHWINDIGKEIT)) {
                    this.posY -= GESCHWINDIGKEIT;
                }
                break;
            case O:
                if (isSpielfeldrand(this.posX+GESCHWINDIGKEIT,this.posY)){
                    this.posX += GESCHWINDIGKEIT;
                }
                break;
            case S:
                if (isSpielfeldrand(this.posX,this.posY+GESCHWINDIGKEIT)){
                    this.posY += GESCHWINDIGKEIT;
                }
                break;
            case W:
                if (isSpielfeldrand(this.posX-GESCHWINDIGKEIT,this.posY)){
                    this.posX -= GESCHWINDIGKEIT;
                }
                break;
        }
    }

    /**
     * Methode isSpielfeldrand: blockiert die Laufbewegung falls man außerhalb des Spielfelds möchte
     * @param Xpos
     * @param Ypos
     * @return
     */
    public boolean isSpielfeldrand(float Xpos, float Ypos){
        if (Xpos <=20 || Xpos >= Spielsteuerung.getSpielfeldBreite()-20 ){
            return false;
        } else if (Ypos <=20 || Ypos >= Spielsteuerung.getSpielfeldHoehe()-20){
            return false;
        } else return true;
    }







    @Override
    public void bewege() {
        // Spielfigur bewegt sich nicht von selbst
    }


    @Override
    public void erhoeheGold(int betrag) {
        System.out.printf("TODO: Erhöhe Gold um %d.", betrag);
    }







}
