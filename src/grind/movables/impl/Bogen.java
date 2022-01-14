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

    /**
     * Konstruktor des Bogens. Legt die Anfangsbedingungen fest.
     * @param x Gibt an, an welcher y-Position sich der Bogen befindet.
     * @param y Gibt an, an welcher y-Position sich der Bogen befindet.
     * @param stufe Gibt an, welche Stufe der Bogen hat.
     */
    public Bogen(int x, int y, int stufe, int wert) {
        super(x, y, 40);
        this.stufe=stufe;
        if (getAusrichtung()==null){
            this.setAusrichtung(Richtung.S);
        }
        this.wert = wert;

    }

    /**
     * Getter für den Schaden des Bogens. Der Schaden ist immer 0, da ein Bogen allein (ohne Pfeil) keinen Schaden anrichten kann.
     * @return Gibt den SChaden des Bogens zurück (0).
     */
    @Override
    public int getSchaden() {
        int schaden = 0* this.stufe;
        return schaden;
    }

    /**
     * Getter für die Stufe des Bogens.
     * @return Gibt die Stufe des Bogens zurück.
     */
    @Override
    public int getStufe() {
        return stufe;
    }

    /**
     * Getter für die Größe des Bogens.
     * @return Gibt die Größe des Bogens zurück.
     */
    public int getGroesse(){
        return 40;
    }

    /**
     * Die Methode beim Anwenden wir in der Klasse Bogen nicht verwendet.
     * @param figur
     */
    @Override
    public void beimAnwenden(ISpielfigur figur) {

    }
}
