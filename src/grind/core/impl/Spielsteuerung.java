package grind.core.impl;

import grind.movables.ISchatz;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;

public class Spielsteuerung extends PApplet {

    ISpielmodell spielmodell;

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
        pruefeKollisionen();
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

    }

    private void zeichne() {
        spielmodell.zeichne(this);
        System.out.println(this.spielmodell.getFigur().getInventar());
    }

    @Override
    public void mousePressed() {
        // nur notwendig, falls Maus benötigt wird
    }

    public void pruefeKollisionen(){ // als extra Methode oder zu aktualisiere() dazu?
        int FigurX = this.spielmodell.getFigur().getPosX();
        int FigurY = this.spielmodell.getFigur().getPosY();
        for(ISchatz schatz: this.spielmodell.getSchaetze()){
           if(((FigurX > schatz.getPosX()-30) & (FigurX<schatz.getPosX()+30)) & ((FigurY > schatz.getPosY()-30)) & (FigurY<schatz.getPosY()+30)) {
                schatz.beimSammeln(this.spielmodell.getFigur());
                System.out.println("Eingesammelt!");
           }
        }



    }

}
