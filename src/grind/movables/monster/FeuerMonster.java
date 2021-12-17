
package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.util.Richtung;
import processing.core.PApplet;

/**
 * @author MEGAtroniker
 * Die Klasse FeuerMonster definiert ein Monster welches das Monster Feuerball als Geschoss verwendt und
 * in regelmäßigen Abständen in Richtung der Spielfigur schießt. Zusätzlich bewegt es sich dur das Spielfeld.
 * Bein Auftreffen auf ein hinderniss wird die Laufrichtung im Uhrzeigersinn angepasst.
 */
public class FeuerMonster extends Monster{
    private int posX;
    private int posY;
    private final static int GESCHWINDIGKEIT = 2;
    private int deltaX;
    private int deltaY;
    ITileMap tileMap;
    Spielsteuerung steuerung;
    Richtung ausrichtung;

    /**
     * Konstruktor enthält nun eine ausrichtung für die implementierung des bewegungsalgorthmus.
     * Aus Spielsteuerung erhält man Methoden zur einfachen Implementierung der Bewege Methode
     * @param posX aktuelle X-Position
     * @param posY aktuelle Y-Position
     * @param tileMap  aktuelle TileMap auf der sich das Monster befindet
     * @param steuerung bewegungs Methoden
     * @param ausrichtung   Laufrichtung
     */
    public FeuerMonster(float posX, float posY, ITileMap tileMap,Spielsteuerung steuerung, Richtung ausrichtung) {
        super(posX, posY,ausrichtung);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;
        this.deltaX = -GESCHWINDIGKEIT; // gibt dem FeuerMonster eine Anfangsrichtung und geschwindigkeit
        this.deltaY = -GESCHWINDIGKEIT;
        this.steuerung=steuerung;
        this.ausrichtung=ausrichtung;
    }

    /**
     * Die Metode beiKollision, soll änderungen am Monster bzw. der Spielfigur vornehmen
     * @param figur Spielfigur
     */
    @Override
    public void beiKollision(ISpielfigur figur) {
        if(PApplet.dist(figur.getPosX(), figur.getPosY(), this.getPosX(), this.getPosY()) < (Einstellungen.GROESSE_FEUERMONSTER/2f + 20)){ // 20 = spielerradius
            // System.out.println("Kollision mit FeuerMonster");
        }
    }

    /**
     * Die Methode zeiche definiert die Darstellung des Feuremonsters
     * @param app  app zur Darstellung
     */
    @Override
    public void zeichne(PApplet app) {
        app.fill(255,100,0);
        app.ellipse(this.getPosX(), this.getPosY(),(float) Einstellungen.GROESSE_FEUERMONSTER , (float)Einstellungen.GROESSE_FEUERMONSTER);
    }

    /**
     * Die Methode bewege enthält die aktuellen Koordinaten des Feuermonsters.
     * Es bewegt sich solange in die initiale Richtung, bis ein Hinderniss kommt,
     * dann wir die Richtung im Uhrzeigersinn gewechselt.
     */
    @Override
    public void bewege() {
        int posX = this.getPosX();
        int posY = this.getPosY();
        switch (ausrichtung) {
            case W:
                if(steuerung.isErlaubteKoordinate(posX - 2*GESCHWINDIGKEIT, posY)){
                    this.setPosition(posX - GESCHWINDIGKEIT, posY);
                    break;
                }else{
                    ausrichtung=Richtung.N;
                }
            case N:
                if(steuerung.isErlaubteKoordinate(posX , posY - 2*GESCHWINDIGKEIT)){
                    this.setPosition(posX, posY-GESCHWINDIGKEIT);
                    break;
                }else{
                    ausrichtung=Richtung.O;
                }
            case O:
                if(steuerung.isErlaubteKoordinate(posX + 2*GESCHWINDIGKEIT, posY)){
                    this.setPosition(posX+ GESCHWINDIGKEIT, posY);
                    break;
                }else{
                    ausrichtung=Richtung.S;
                }
            case S:
                if(steuerung.isErlaubteKoordinate(posX , posY + 2*GESCHWINDIGKEIT)){
                    this.setPosition(posX, posY+GESCHWINDIGKEIT);
                    break;
                }else{
                    ausrichtung=Richtung.W;
                }
        }
    }





    /**
     * In nerer useed!!!!!!!!!
     * ersetzt durch assoziation zu Spielsteuerung!!!!
     * @param kachel
     */
    @Override
    public void vorBetreten(IKachel kachel) {
        if(!kachel.istBetretbar()){
            deltaX = -deltaX;
            deltaY = -deltaY;
            posX += deltaX ;
            posY += deltaY ;

        }
    }

}