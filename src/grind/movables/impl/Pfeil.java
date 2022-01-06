package grind.movables.impl;

import grind.util.Richtung;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Pfeil extends Waffe{

    private int stufe;
    PImage pfeil1;

    public Pfeil(int posX, int posY, int stufe) {
        super(posX, posY, 40);
        this.stufe = stufe;
        if (getAusrichtung()==null){
            this.setAusrichtung(Richtung.S);
        }
    }

    public void zeichnePfeil(PApplet app) {
        app.pushStyle();
        app.imageMode(PConstants.CENTER);
        app.pushMatrix();

        //ladeIMGSchwert(app);    //Lade Bild des Pfeils, für Spielfigur in Spielsteuerung setup() implementiert
        PImage pfeil = pfeil1;
        if (stufe == 1) {
            pfeil = app.loadImage("pfeil.png");
        }
//        else if (stufe == 2) {
//            schwert = app.loadImage("newSword2.png");
//        }

        int n =1;
        switch (this.ausrichtung) {
            case N:
                n = 2;
                break;
            case O:
                n = 3;
                break;
            case S:
                n = 0;
                break;
            case W:
                n = 1;
        }
//        if (this.getSpielmodell.getFigur().getAusrichtung() == Richtung.N){
//
//        }
        app.translate(this.getPosX(),this.getPosY());
        app.rotate(PConstants.HALF_PI*n);



        ladeBild(pfeil, app);

        app.popMatrix();
        app.popStyle();
    }

    public void ladeBild(PImage pfeil1, PApplet app){
        app.image(pfeil1, 0,0, 40, 40);
    }

    @Override
    public void zeichne(PApplet app) {
        zeichnePfeil(app);
    }

    @Override
    public int getSchaden() {
        int schaden = 15*this.stufe;
        return schaden;
    }

    @Override
    public int getStufe() {
        return stufe;
    }

    public int getGeschwindigkeit(){
        int geschwindigkeit = 5;
        return geschwindigkeit;
    }
}
