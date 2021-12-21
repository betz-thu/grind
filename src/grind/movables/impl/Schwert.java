package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.impl.TileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Schwert extends Waffe{

    transient private int stufe;
    transient PImage schwertStufe1;
    transient PImage schwertStufe2;



    public Schwert(int x, int y, int stufe) {
        super(x, y, 40);
        this.stufe=stufe;
    }


    public void zeichneSchwert(PApplet app) {
        app.pushStyle();
        app.imageMode(PConstants.CENTER);
        app.pushMatrix();

        //ladeIMGSchwert(app);    //Lade Bild des Schwertes, für Spielfigur in Spielsteuerung setup() implementiert
        PImage schwert = schwertStufe1;
        if (stufe == 1) {
            schwert = app.loadImage("newSword1.png");
        }
        else if (stufe == 2) {
            schwert = app.loadImage("newSword2.png");
        }

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



        ladeBild(schwert, app);

        app.popMatrix();
        app.popStyle();
    }



    public void ladeBild(PImage schwert, PApplet app){
        app.image(schwert, 0,0, 40, 40);
    }
    @Override
    public int getSchaden() {
        /**
         * Berechnet den Schaden, welchen ein Schwert anrichtet.
         */
        int schaden = 10* this.stufe;
        return schaden;
    }

    @Override
    public int getStufe() {
        return stufe;
    }

    @Override
    public void zeichne(PApplet app) {
        zeichneSchwert(app);
    }


    public int getGroesse(){
        return 40;
    }

    @Override
    public void beimAnwenden(ISpielfigur figur) {

    }



//    @Override
//    public boolean kollision(){
//
//    }
}
