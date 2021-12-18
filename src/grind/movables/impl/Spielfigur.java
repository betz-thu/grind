package grind.movables.impl;

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
    private int lebensenergie = 85;
    int gold = 5;
    PImage spielfigurOhneWaffe;
    private int inventarGroeße;
    private int guiGroeße;

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
    public Spielfigur(float posX, float posY, Richtung richtung) {
        super(posX, posY, richtung);
        inventar = new ArrayList<>();
        inventarGroeße=10;
        guiGroeße=50;
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

        //Zeichne kleines Inventar
        zeichneInventar(app, inventarGroeße, 850, 720, guiGroeße);
        zeichneInventarInhalt(app, inventarGroeße, 550, 720, guiGroeße);



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
        app.image(spielfigurOhneWaffe, 0, 0, 40, 40);
        app.popMatrix();
        app.popStyle();
    }

    public void zeichneInventar(PApplet app, int groeße, int startkoordinateX, int startkoordinateY, int guiGroeße){
        // Zeichne Inventar
        int zaehler = 0;
        zeichneInventarInhalt(app, groeße, startkoordinateX, startkoordinateY, guiGroeße);
        app.pushStyle();
        app.fill(255,255,255);
        app.stroke(255,255,255);
        app.strokeWeight(2f);
        app.textSize(24);
        //app.fill(204, 102, 0);
        for (int i = 1; i <= groeße; i++) {
            app.fill(204, 102, 0);
            app.rect(startkoordinateX-i*guiGroeße, startkoordinateY, guiGroeße, guiGroeße);


            if(i<=10){
                app.fill(200,0,0);
                app.text(zaehler,startkoordinateX-i*guiGroeße,startkoordinateY+20);
                if(zaehler==0){
                    zaehler=10;
                }
                zaehler--;

            }
            if(i%10==0 && i>0){
                startkoordinateY-=guiGroeße;
                startkoordinateX+=guiGroeße*10;
            }

        }


        app.popStyle();


    }

    public void zeichneInventarInhalt(PApplet app, int groeße, int startkoordinateX, int startkoordinateY, int guiGroeße){
        for(int j = 0;j<inventar.size(); j++){
            if(j<groeße) {
                inventar.get(j).setPosition(startkoordinateX-7*guiGroeße/2+ j*guiGroeße, startkoordinateY+guiGroeße/2);
                inventar.get(j).zeichne(app);
            }
            if(j%10==0 && j>0){
                startkoordinateY-=guiGroeße;
                startkoordinateX+=guiGroeße*10;
            }
        }
    }

    //Methode zum benutzen oder ausrüsten von Gegenständen
    public void benutze(int position){
        if (inventar.size() > position) {
            if (inventar.get(position) instanceof Nahrung) {
                inventar.get(position).beimAnwenden(this);
                inventar.remove(position);
            }
        }
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
        app.rect(10,20, lebensenergie,10);
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
    }

    @Override
    public List<Gegenstand> getInventar(){
        return this.inventar;
    }

    @Override
    public int getLebensenergie(){
        return this.lebensenergie;
    }

    public void setLebensenergie(int neueLebensenergie){
        this.lebensenergie = neueLebensenergie;
    }



    /**
     * Methode ladeIMGSpielfigur, lädt Darstellung der Spielfigur.
     * (zukünftig: lädt spielfigurOhneWaffe, SpielfigurMitSchwert, SpielfigurMitBogen,...)
     * @param app
     */
    public void ladeIMGSpielfigur(PApplet app) {
        spielfigurOhneWaffe = app.loadImage("SpielfigurOhneWaffe.jpg");
    }

    public void setInventarGroeße(int inventarGroeße) {
        this.inventarGroeße = inventarGroeße;
    }

    public int getInventarGroeße() {
        return inventarGroeße;
    }
}
