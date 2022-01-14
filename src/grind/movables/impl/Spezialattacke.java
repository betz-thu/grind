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

    public class Spezialattacke extends Waffe{

        private int stufe;
        transient PApplet app;


        /**
         * Konstruktor der Spezialattacke. Legt die Anfangsbedingungen fest.
         * @param x Gibt an, an welcher x-Position sich die Spezialattacke befindet.
         * @param y Gibt an, an welcher y-Position sich die Spezialattacke befindet.
         * @param stufe Gibt an, welche Stufe die Spezialattacke hat.
         */
        public Spezialattacke(int x, int y, int stufe) {
            super(x, y, 40);
            this.stufe=stufe;
            if (getAusrichtung()==null){
                this.setAusrichtung(Richtung.S);
            }

        }

        /**
         * Getter für den Schaden der Spezialattacke. Der Schaden wird über die Stufe berehcnet.
         * @returnGeibt den berechneten Wert des Schadens zurück.
         */
        @Override
        public int getSchaden() {
            int schaden = 60* this.stufe;
            return schaden;
        }

        /**
         * Getter für die Stufe der Spezialattacke.
         * @return Gibt die Stufe der Spezialattacke zurück.
         */
        @Override
        public int getStufe() {
            return stufe;
        }

        /**
         * Getter für die Größe der Spezialattake.
         * @return Gibt die Größe der Spezialattacke zurück.
         */
        public int getGroesse(){
            return groesse;
        }

        /**
         * Setter für die Größe der Spaezialattacke.
         * @param neue_groesse Die neu gesetzte Größe der Spezialattacke.
         */
        public void setGroesse(int neue_groesse){
            groesse=neue_groesse;
        }

        /**
         * Methode beim Anwenden wird in der Klasse Spezialattacke nicht verwendet.
         * @param figur
         */
        @Override
        public void beimAnwenden(ISpielfigur figur) {


        }


    }


