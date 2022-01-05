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
        PImage bluefirering;




        public Spezialattacke(int x, int y, int stufe) {
            super(x, y, 40);
            this.stufe=stufe;
            if (getAusrichtung()==null){
                this.setAusrichtung(Richtung.S);
            }

        }


        public void zeichneSpezialattacke(PApplet app) {
            app.pushStyle();
            app.imageMode(PConstants.CENTER);
            app.pushMatrix();

            PImage spezialattacke = bluefirering;
            if (stufe == 1) {
                spezialattacke = app.loadImage("bluefirering.png");
            }
            else if (stufe == 2) {
                spezialattacke = app.loadImage("bluefirering.png");
            }

            app.translate(this.getPosX(),this.getPosY());

            ladeBild(spezialattacke, app);

            app.popMatrix();
            app.popStyle();
        }



        public void ladeBild(PImage waffe, PApplet app){
           app.image(waffe, 0,0, 220, 132);
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
            zeichneSpezialattacke(app);
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


