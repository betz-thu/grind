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




        public Spezialattacke(int x, int y, int stufe) {
            super(x, y, 40);
            this.stufe=stufe;
            if (getAusrichtung()==null){
                this.setAusrichtung(Richtung.S);
            }

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

        public int getGroesse(){
            return 40;
        }

        @Override
        public void beimAnwenden(ISpielfigur figur) {

        }

    }


