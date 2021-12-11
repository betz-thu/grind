package grind.core.impl;

import grind.kacheln.impl.DummyHindernis;
import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import grind.kacheln.*;
import grind.util.Einstellungen;

import java.lang.invoke.SwitchPoint;

/**
 * @Autor Megatronik
 * Methode eingabe ergänzt; ändert nun zusätzlich die Ausrichtung der Spielfigur passend zur Laufrichtung.
 */
public class Spielsteuerung extends PApplet {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;
    private Spielfigur Spieler;
    private int SpielerGeschwindigkeit;
    private ITileMap tileMap;

    /*
    boolean bewegungNordErlaubt=true;
    boolean bewegungOstErlaubt=true;
    boolean bewegungWestErlaubt=true;
    boolean bewegungSuedErlaubt=true;
     */

    ISpielmodell spielmodell;
    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt());
        this.spielmodell.betreteSzene(1);
        this.Spieler = (Spielfigur) spielmodell.getFigur();
        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
        this.tileMap = (ITileMap) spielmodell.getTileMap();
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
        //isHinderniss();
    }

    /**
     * Methode eingabe: ändert nun zusätzlich die Ausrichtung der Spielfigur passend zur Laufrichtung.
     */
    private void eingabe() {
        int x = Spieler.getPosX();
        int y = Spieler.getPosY();
        if (keyPressed) {
            if (key == 'a' || keyCode == LEFT) {
                Spieler.setAusrichtung(Richtung.W);
                if(isErlaubteKoordinate(x-SpielerGeschwindigkeit-20,y-20) && isErlaubteKoordinate(x-SpielerGeschwindigkeit-20,y+20)){
                    Spieler.bewege(Richtung.W);
                }
            } else if (key == 'w' || keyCode == UP) {
                Spieler.setAusrichtung(Richtung.N);
                if(isErlaubteKoordinate(x-20,y-SpielerGeschwindigkeit-20) && isErlaubteKoordinate(x+20,y-SpielerGeschwindigkeit-20)){
                    Spieler.bewege(Richtung.N);
                }
            } else if (key == 's' || keyCode == DOWN) {
                Spieler.setAusrichtung(Richtung.S);
                if(isErlaubteKoordinate(x-20,y+SpielerGeschwindigkeit+20) && isErlaubteKoordinate(x+20,y+SpielerGeschwindigkeit+20)){
                    Spieler.bewege(Richtung.S);
                }
            } else if (key == 'd' || keyCode == RIGHT) {
                Spieler.setAusrichtung(Richtung.O);
                if(isErlaubteKoordinate(x+SpielerGeschwindigkeit+20,y-20) && isErlaubteKoordinate(x+SpielerGeschwindigkeit+20,y+20)){
                    Spieler.bewege(Richtung.O);
                }
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


    /**
     * Für alle movables modifizieren-> dann könnten monster random laufen
     * alle movables brauchen eine geschwindigkeit -> schatz hat 0
     * @return
     */
    /*
    public boolean isHinderniss() {
        Spielfigur figur = (Spielfigur) spielmodell.getFigur();
        ITileMap tileMap = (ITileMap) spielmodell.getTileMap();
        int xKachel = figur.getPosX() / Einstellungen.LAENGE_KACHELN_X;
        int yKachel = figur.getPosY() / Einstellungen.LAENGE_KACHELN_Y;
        System.out.println(xKachel+" "+yKachel);
        IKachel aktuelleKachel = tileMap.getKachel(xKachel, yKachel);
        figur.getGESCHWINDIGKEIT();

        switch (figur.getAusrichtung()){
            case N:
                aktuelleKachel=tileMap.getKachel(xKachel,yKachel-1);
                if(!aktuelleKachel.istBetretbar()){
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

    }*/

    public IKachel getKachelByCoordinates(int x, int y) {
        x = (int) x/Einstellungen.LAENGE_KACHELN_X;
        y = (int) y/Einstellungen.LAENGE_KACHELN_Y;
        return tileMap.getKachel(x,y);
    }

    public boolean isSpielfeldrand(int Xpos, int Ypos){
        return Xpos <= 0 || Xpos >= SpielfeldBreite || Ypos <= 0 || Ypos >= SpielfeldHoehe;
    }

    public boolean isErlaubteKoordinate(int x, int y) {
        if(!isSpielfeldrand(x,y)){
            return getKachelByCoordinates(x,y).istBetretbar();
        } else return false;
    }

}
