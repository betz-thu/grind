package grind.movables.impl;

import grind.util.Richtung;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Pfeil extends Waffe{

    private int stufe;
    private int geschwindigkeit = 10;
    PImage pfeil1;

    /**
     * Konstruktor des Pfeils. Legt die Anfangsbedingungen fest.
     * @param posX legt die Anfängliche x-Position des Pfeils fest
     * @param posY legt die Anfängliche y-Pos des Pfeils fest
     * @param stufe gibt an welche Stufe der Pfeil hat
     */
    public Pfeil(int posX, int posY, int stufe) {
        super(posX, posY, 40);
        this.stufe = stufe;
        if (getAusrichtung()==null){
            this.setAusrichtung(Richtung.S);
        }
    }

    /**
     * Berechnet den Schaden welchen der Pfeil anrichtet mithilfe der Stufe.
     * @return gibt den Schaden als int Wert zurück.
     */
    @Override
    public int getSchaden() {
        int schaden = 15*this.stufe;
        return schaden;
    }

    /**
     * Getter für die Stufe des Pfeils.
     * @return Stufe des Pfeils.
     */
    @Override
    public int getStufe() {
        return stufe;
    }

    /**
     * Getter für die Geschwindigkeit des Pfeils.
     * @return gibt die Geschwindigkeit des Pfeils zurück.
     */
    public int getGeschwindigkeit(){
        return geschwindigkeit;
    }
}
