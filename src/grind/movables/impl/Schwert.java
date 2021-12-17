package grind.movables.impl;

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
        app.translate(this.posX, this.posY);
        int n =1;
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
        app.rotate(PConstants.HALF_PI*n);
        app.image(schwertStufe1, 0, 0, 40, 40);
        app.popMatrix();
        app.popStyle();
    }
    public void ladeIMGSpielfigur(PApplet app) {
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
