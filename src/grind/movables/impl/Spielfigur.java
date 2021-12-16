package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.movables.ISpielfigur;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autor Megatronik
 * Konstruktor angepasst, erbt nun von überladenem Movable-Konstruktor.
 */
public class Spielfigur extends Movable implements ISpielfigur {

    private final float GESCHWINDIGKEIT = 3f;
    private int Lebensenergie = 85;
    int gold = 5;
    private int größe;
    PImage spielfigurOhneWaffe;


    int lebensenergie = 100;
    private List<Gegenstand> inventar;
    /**
     * Methode getGeschwindigkeit, Getter für die Geschwindigkeit.
     * @return GESCHWINDIGKEIT
     */
    public float getGESCHWINDIGKEIT() {
        return GESCHWINDIGKEIT;
    }

    /**
     * Konstruktor Spielfigur
     * @param posX gibt X-Position der Spielfigur an.
     * @param posY gibt Y-Position der Spielfigur an.
     */
    public Spielfigur(float posX, float posY, Richtung richtung, int groesse) {
        super(posX, posY, richtung, groesse);
        inventar = new ArrayList<>();
}

    /**
     * Methode zeichne: zeichnet Bild der Spielfigur, abhängig von Ausrichtung und Position.
     * Dadurch schaut die Spielfigur immer in Laufrichtung.
     * Bild: "SpielfigurOhneWaffe.jpg"
     * @param app PApplet, für Darstellung in Processing.
     */
    @Override
    public void zeichne(PApplet app) {
        zeichneSpielfigur(app);
        zeichneLebensbalken(app);
        zeichneKontostand(app);
        zeichneInventar(app);
    }

    @Override
    public int getGroesse() {
        return this.größe = Einstellungen.GROESSE_FIGUR;
    }

    /**
     * Methode zeichneSpielfigur, stellt SpielfigurOhneWaffe dar.
     * (zukünftig: stellt SpielfigurOhneWaffe, SpielfigurMitSchwert, SpielfigurMitBogen usw dar.)
     * @param app
     */
    private void zeichneSpielfigur(PApplet app) {
        app.pushStyle();
        app.imageMode(PConstants.CENTER);
        app.pushMatrix();
        app.translate(this.posX, this.posY);
        int n =1;
        switch (this.ausrichtung) {
            case N:
                n = 0;
                break;
            case O:
                n = 1;
                break;
            case S:
                n = 2;
                break;
            case W:
                n = 3;
        }
        app.rotate(PConstants.HALF_PI*n);
        app.image(spielfigurOhneWaffe, 0, 0, größe, größe);
        app.popMatrix();
        app.popStyle();
    }

    public void zeichneInventar(PApplet app){
        // Zeichne Inventar
        app.pushStyle();
        app.fill(255,255,255);
        app.stroke(255,255,255);
        app.strokeWeight(2f);
        app.textSize(24);
        app.text("Inventar", 1120-4*30, 740);
        app.fill(204, 102, 0);
        for (int i = 0; i < 5; i++) {
            app.rect(1120-i*30, 750, 30, 30);
        }
        for(int j = 0;j<inventar.size(); j++){
            if(j<5) {
                inventar.get(j).setPosition(1015 + j * 30, 765);
                inventar.get(j).zeichne(app);
            }
        }
        app.popStyle();

    }

    /**
     * Methode zeichneKontostand, stellt Kontostand als Balken oben links an.
     * @param app Spielsteuerung, als Instanz von PApplet.
     */
    private void zeichneKontostand(PApplet app) {
        app.fill(255,215,0);
        app.rect(10,5,gold*5,10);
        app.text(Integer.toString(gold),20+gold*5,15);
    }


    /**
     * Methode zeichneLebensbalken, stellt Lebensbalken links oben dar.
     * @param app Spielsteuerung, als Instanz von PApplet.
     */
    private void zeichneLebensbalken(PApplet app) {
        app.fill(150);
        app.rect(10,20,100,10);
        app.fill(0,150,0);
        app.rect(10,20,Lebensenergie,10);
    }

    @Override
    public void erhalteSchaden(int schaden){
        this.lebensenergie -= schaden;
    }
    /**
     * Methode bewege, setzt neue Koordinaten der Figur.
     * @param richtung enum für die Richtungsangabe.
     */
    @Override
    public void bewege(Richtung richtung) {
        switch (richtung) {
            case N:
                this.posY -= GESCHWINDIGKEIT;
                break;
            case O:
                this.posX += GESCHWINDIGKEIT;
                break;
            case S:
                this.posY += GESCHWINDIGKEIT;
                break;
            case W:
                this.posX -= GESCHWINDIGKEIT;
                break;
        }
    }


    @Override
    public void bewege() {
        // Spielfigur bewegt sich nicht von selbst
    }

    @Override
    public void erhoeheGold(int betrag) {
        this.gold += betrag;
        //System.out.printf("TODO: Erhöhe Gold um %d.", betrag);
    }

    @Override
    public List<Gegenstand> getInventar(){
        return this.inventar;
    }

    /**
     * Methode ladeIMGSpielfigur, lädt Darstellung der Spielfigur.
     * (zukünftig: lädt spielfigurOhneWaffe, SpielfigurMitSchwert, SpielfigurMitBogen,...)
     * @param app
     */
    public void ladeIMGSpielfigur(PApplet app) {
        spielfigurOhneWaffe = app.loadImage("SpielfigurOhneWaffe.jpg");
    }
}
