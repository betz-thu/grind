package grind.core.impl;

import grind.kacheln.impl.DummyHindernis;
import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import grind.kacheln.*;
import grind.util.Einstellungen;

/**
 * @Autor Megatronik
 * Methode eingabe ergänzt; ändert nun zusätzlich die Ausrichtung der Spielfigur passend zur Laufrichtung.
 */
public class Spielsteuerung extends PApplet {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;


    boolean bewegungNordErlaubt=true;
    boolean bewegungOstErlaubt=true;
    boolean bewegungWestErlaubt=true;
    boolean bewegungSuedErlaubt=true;


    ISpielmodell spielmodell;
    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt());
        this.spielmodell.betreteSzene(1);
    }

    @Override
    public void settings() {
        SpielfeldBreite = Einstellungen.LAENGE_KACHELN_X*Einstellungen.ANZAHL_KACHELN_X;
        SpielfeldHoehe = Einstellungen.LAENGE_KACHELN_Y*Einstellungen.ANZAHL_KACHELN_Y;
        size(SpielfeldBreite,SpielfeldHoehe);
    }

    @Override
    public void setup() {


    }

    @Override
    public void draw() {
        eingabe();
        aktualisiere();
        zeichne();
        isHinderniss();
    }

    /**
     * Methode eingabe: ändert nun zusätzlich die Ausrichtung der Spielfigur passend zur Laufrichtung.
     */
    private void eingabe() {
        Spielfigur figur = (Spielfigur) spielmodell.getFigur();
        if (keyPressed) {
            if ((key == 'a' || keyCode == LEFT)&&bewegungWestErlaubt) {
                figur.setAusrichtung(Richtung.W);
                figur.bewege(Richtung.W);
                bewegungNordErlaubt=true;
                bewegungSuedErlaubt=true;
                bewegungOstErlaubt =true;
            } else if ((key == 'w' || keyCode == UP)&&bewegungNordErlaubt) {
                figur.setAusrichtung(Richtung.N);
                figur.bewege(Richtung.N);
                bewegungWestErlaubt=true;
                bewegungSuedErlaubt=true;
                bewegungOstErlaubt =true;
            } else if ((key == 's' || keyCode == DOWN)&&bewegungSuedErlaubt) {
                figur.setAusrichtung(Richtung.S);
                figur.bewege(Richtung.S);
                bewegungWestErlaubt=true;
                bewegungNordErlaubt=true;;
                bewegungOstErlaubt =true;
            } else if ((key == 'd' || keyCode == RIGHT)&&bewegungOstErlaubt) {
                figur.setAusrichtung(Richtung.O);
                figur.bewege(Richtung.O);
                bewegungWestErlaubt=true;
                bewegungNordErlaubt=true;
                bewegungSuedErlaubt=true;
            }
        }
    }

    private void aktualisiere() {
        spielmodell.bewege();

    }

    private void zeichne() {
        spielmodell.zeichne(this);
    }

    @Override
    public void mousePressed() {
        // nur notwendig, falls Maus benötigt wird
    }

    /**
     * Methode getSpielfeldBreite gibt Spielfeldbreite zurück.
     * @return Breite des Spielfelds
     */
    public static int getSpielfeldBreite() {
        return SpielfeldBreite;
    }

    /**
     * Methode getSpielfeldHoehe gibt Spielfeldhoehe zurück.
     * @return Hoehe des Spielfelds
     */
    public static int getSpielfeldHoehe() {
        return SpielfeldHoehe;
    }


    public boolean isHinderniss() {
        Spielfigur figur = (Spielfigur) spielmodell.getFigur();
        ITileMap tileMap = (ITileMap) spielmodell.getTileMap();
        int xKachel = figur.getPosX() / Einstellungen.LAENGE_KACHELN_X;
        int yKachel = figur.getPosY() / Einstellungen.LAENGE_KACHELN_Y;
        IKachel aktuelleKachel = tileMap.getKachel(xKachel, yKachel);

        switch (figur.getAusrichtung()){
            case N:
                aktuelleKachel=tileMap.getKachel(xKachel,yKachel-1);
                if(aktuelleKachel instanceof DummyHindernis){
                    bewegungNordErlaubt=false;
                }
                break;
            case O:
                aktuelleKachel=tileMap.getKachel(xKachel+1,yKachel);
                if(aktuelleKachel instanceof DummyHindernis)
                    bewegungOstErlaubt=false;
                break;
            case S:
                aktuelleKachel=tileMap.getKachel(xKachel,yKachel+1);
                if(aktuelleKachel instanceof DummyHindernis)
                    bewegungSuedErlaubt=false;
                break;
            case W:
                aktuelleKachel=tileMap.getKachel(xKachel-1,yKachel);
                if(aktuelleKachel instanceof DummyHindernis)
                    bewegungWestErlaubt=false;
                break;
        }



        if(aktuelleKachel instanceof DummyHindernis){
            System.out.println("Hinderniss");
            return true;
        }
        return  false;

    }
}
