package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Schwert extends Waffe{

    int stufe = 1;
    PImage schwertStufe1;

    public Schwert(int x, int y) {
        super(x, y);
    }


    public void zeichneSchwert(PApplet app) {
        app.pushStyle();
        app.imageMode(PConstants.CENTER);
        app.pushMatrix();
        ladeIMGSchwert(app);    //Lade Bild des Schwertes, für Spielfigur in Spielsteuerung setup() implementiert
        //app.translate(this.posX,this.posY);

        int n =1;
        int schwertPositionX = 1;
        int schwertPositionY = 1;
        switch (this.ausrichtung) {
            case N:
                n = 2;
                schwertPositionX =0;
                schwertPositionY =-1;
                break;
            case O:
                n = 3;
                schwertPositionX =1;
                schwertPositionY =0;
                break;
            case S:
                n = 0;
                schwertPositionX =0;
                schwertPositionY =1;
                break;
            case W:
                n = 1;
                schwertPositionX =-1;
                schwertPositionY =0;
        }

        app.translate(getPosX()+40*schwertPositionX,getPosY()+40*schwertPositionY);
        app.rotate(PConstants.HALF_PI*n);

        app.image(schwertStufe1, 0,0, 40, 40);
        app.popMatrix();
        app.popStyle();
    }
    public void ladeIMGSchwert(PApplet app) {
        schwertStufe1 = app.loadImage("schwertStufe1.png");
    }
    @Override
    public int getSchaden() {
        int schaden = 10 * stufe;
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
}
