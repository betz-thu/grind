package grind.core.impl;

import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.Spielwelt;
import processing.core.PApplet;

public class Spielsteuerung extends PApplet {

    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;


    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new Spielwelt());
        this.spielmodell.betreteSzene(this.spielmodell.getSzeneNr());
    }

    @Override
    public void settings() {
        size(1200, 800);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        eingabe();
        aktualisiere();
        zeichne();
    }

    private void eingabe() {

        if (keyPressed) {
            if (key == 'a' || keyCode == LEFT) {
                spielmodell.getFigur().bewege(Richtung.W);
            } else if (key == 'w' || keyCode == UP) {
                spielmodell.getFigur().bewege(Richtung.N);
            } else if (key == 's' || keyCode == DOWN) {
                spielmodell.getFigur().bewege(Richtung.S);
            } else if (key == 'd' || keyCode == RIGHT) {
                spielmodell.getFigur().bewege(Richtung.O);
            }
        }

        //F12 neue Szene
        if (keyPressed && !pressed){
            if (keyCode == 123) {
                pressed = true;
            }
        } else if(!keyPressed && pressed){
            pressed = false;
            System.out.println("F12");
            spielmodell.setSzeneNr(spielmodell.getSzeneNr()+1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
        }
    }

    private void aktualisiere() {
        spielmodell.bewege();
        ueberpruefeLevelende();

    }

    private void zeichne() {
        spielmodell.zeichne(this);
    }

    @Override
    public void mousePressed() {
        // nur notwendig, falls Maus benötigt wird
    }

    public boolean ueberpruefeLevelende() {

        if(spielmodell.getFigur().getPosY()>10*39 && spielmodell.getFigur().getPosX()>29*39 && spielmodell.getFigur().getPosY()<11*39){
            System.out.println("Das Ende des Levels wurde erreicht");
            levelBeendet = true;
        }
//        else if(spielmodell.getInventar().contains("Levelende Bedingung")){
//            System.out.println("Levelende Bedingung wurde gefunden");
//            levelBeendet = true;
//        }
        return levelBeendet;
    }

}
