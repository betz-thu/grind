package grind.core.impl;

import grind.kacheln.impl.Levelausgang;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;

public class Spielsteuerung extends PApplet {

    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;


    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt());
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
        //Abfrage ob der aktuelle Standpunkt der Spielfigur eine Kachel vom Typ Levelausgang ist.
        if (spielmodell.getSzene().getLevel().getTileMap().getKachel(spielmodell.getFigur().getPosY()/Einstellungen.LAENGE_KACHELN_Y,spielmodell.getFigur().getPosX()/Einstellungen.LAENGE_KACHELN_X) instanceof Levelausgang){
            System.out.println(spielmodell.getSzene().getLevel().getTileMap().getKachel(spielmodell.getFigur().getPosY()/39,spielmodell.getFigur().getPosX()/39));
            //levelBeendet = true;

//        else if(spielmodell.getInventar().contains("Levelende Bedingung")){
//            System.out.println("Levelende Bedingung wurde gefunden");
            spielmodell.setSzeneNr(spielmodell.getSzeneNr()+1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
            levelBeendet = true;
        }
        return levelBeendet;
    }

}
