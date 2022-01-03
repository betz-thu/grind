package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.util.Richtung;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.impl.TileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.util.Richtung;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
public class Bogen extends Waffe{


    private int stufe;
    PImage Bogen1;
    PImage schwertStufe2;



    public Bogen(int x, int y, int stufe) {
        super(x, y, 40);
        this.stufe=stufe;
        if (getAusrichtung()==null){
            this.setAusrichtung(Richtung.S);
        }

    }


    public void zeichneBogen(PApplet app) {
        app.pushStyle();
        app.imageMode(PConstants.CENTER);
        app.pushMatrix();

        //ladeIMGSchwert(app);    //Lade Bild des Schwertes, für Spielfigur in Spielsteuerung setup() implementiert
        PImage bogen = Bogen1;
        if (stufe == 1) {
            bogen = app.loadImage("Bogen1.png");
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

        app.translate(this.getPosX(),this.getPosY());
        app.rotate(PConstants.HALF_PI*n);



        ladeBild(bogen, app);

        app.popMatrix();
        app.popStyle();
    }



    public void ladeBild(PImage bogen, PApplet app){
        app.image(bogen, 0,0, 40, 40);
    }
    @Override
    public int getSchaden() {
        /**
         * Berechnet den Schaden, welchen ein Schwert anrichtet.
         */
        int schaden = 0* this.stufe;
        return schaden;
    }

    @Override
    public int getStufe() {
        return stufe;
    }

    @Override
    public void zeichne(PApplet app) {
        zeichneBogen(app);
    }


    public int getGroesse(){
        return 40;
    }

    @Override
    public void beimAnwenden(ISpielfigur figur) {

    }

//    @Override
//    public void setAusrichtung(Richtung ausrichtung) {
//        super.setAusrichtung(ausrichtung);
//    }
//    @Override
//    public boolean kollision(){
//
//    }
}
