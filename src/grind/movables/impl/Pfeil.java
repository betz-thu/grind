package grind.movables.impl;

import grind.util.Richtung;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Pfeil extends Waffe{

    private int stufe;
    private int geschwindigkeit = 10;
    PImage pfeil1;

    public Pfeil(int posX, int posY, int stufe) {
        super(posX, posY, 40);
        this.stufe = stufe;
        if (getAusrichtung()==null){
            this.setAusrichtung(Richtung.S);
        }
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
        return geschwindigkeit;
    }
}
