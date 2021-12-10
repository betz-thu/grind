package grind.core.impl;

import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;

public class Spielsteuerung extends PApplet {

    ISpielmodell spielmodell;
    boolean levelBeendet = false;


    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt());
        this.spielmodell.betreteSzene(1);
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
