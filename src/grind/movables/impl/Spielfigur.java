package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.core.impl.Spielsteuerung.*;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.TileMap;
import grind.util.Richtung;
import grind.movables.ISpielfigur;
import grind.welt.impl.DummyLevel;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.awt.print.Paper;

/**
 * @Autor Megatronik
 * Konstruktor angepasst, erbt nun von überladenem Movable-Konstruktor
 * Methode zeichne: zeichnet nun die Spielfigur als Bild, mit Ausrichtung in Laufrichtung.
 */
public class Spielfigur extends Movable implements ISpielfigur {

    private static final float GESCHWINDIGKEIT = 3f;

    int gold = 0;

    /**
     * Konstruktor Spielfigur
     * @param posX gibt X-Position der Spielfigur an.
     * @param posY gibt Y-Position der Spielfigur an.
     * @param richtung gibt Ausrichtung der Spielfigur an.
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
                if (isBetretbar(this.posX,this.posY-GESCHWINDIGKEIT)){
                    this.posY -= GESCHWINDIGKEIT;
                }
                break;
            case O:
                if (isBetretbar(this.posX+GESCHWINDIGKEIT,this.posY)){
                    this.posX += GESCHWINDIGKEIT;
                }
                break;
            case S:
                if (isBetretbar(this.posX,this.posY+GESCHWINDIGKEIT)){
                    this.posY += GESCHWINDIGKEIT;
                }
                break;
            case W:
                if (isBetretbar(this.posX-GESCHWINDIGKEIT,this.posY)){
                    this.posX -= GESCHWINDIGKEIT;
                }
                break;
        }
    }

    public boolean isBetretbar(float Xpos, float Ypos){
        IKachel currentKachel = TileMap.
                currentKachel(Xpos,Ypos);
        System.out.println(currentKachel.istBetretbar());
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
