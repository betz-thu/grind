package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.util.Richtung;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MEGAtronik
 * Konstruktor angepasst, erbt nun von überladenem Movable-Konstruktor.
 */
public class Spielfigur extends Movable implements ISpielfigur {

    private final float GESCHWINDIGKEIT = 3f;
    int gold = 5;
    PImage spielfigurOhneWaffe;
    Waffe testwaffe = new Schwert(30,30,1);

    int lebensenergie = 100;//Kapselung?
    final List<Gegenstand> inventar;


    private int inventarGroeße;
    private int guiGroeße;

    Waffe aktiveWaffe;
    /**
     * @MEGAtroniker
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
        super(posX, posY, richtung, Einstellungen.GROESSE_SPIELFIGUR);
        inventar = new ArrayList<>();
        aktiviereWaffe(testwaffe);
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


        gameover(app);
    }

    @Override
    public int getGroesse() {
        return this.groesse = Einstellungen.GROESSE_SPIELFIGUR;
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
        int n = 1;
        int schwertPositionX = 1;
        int schwertPositionY = 1;
        switch (this.ausrichtung) {
            case N:
                n = 0;
                schwertPositionX =0;
                schwertPositionY =-1;
                break;
            case O:
                n = 1;
                schwertPositionX =1;
                schwertPositionY =0;
                break;
            case S:
                n = 2;
                schwertPositionX =0;
                schwertPositionY =1;
                break;
            case W:
                n = 3;
                schwertPositionX =-1;
                schwertPositionY =0;
        }
        app.rotate(PConstants.HALF_PI*n);
        app.image(spielfigurOhneWaffe, 0, 0, groesse, groesse);
        app.popMatrix();
        app.popStyle();



        if (app.key==' '){ //Schwert nur anzeigen, wenn Leertaste gedrückt wurde

            aktiveWaffe.setPosition(this.getPosX()+aktiveWaffe.getGroesse()*schwertPositionX,this.getPosY()+aktiveWaffe.getGroesse()*schwertPositionY);
            aktiveWaffe.setAusrichtung(this.getAusrichtung());
            aktiveWaffe.zeichne(app);
        }



    }

    public void zeichneInventar(PApplet app, int groeße, int startkoordinateX, int startkoordinateY, int guiGroeße){
        // Zeichne Inventar
        int zaehler = 0;

        app.pushStyle();
        app.fill(255,255,255);
        app.stroke(255,255,255);
        app.strokeWeight(2f);
        app.textSize(24);
        //app.fill(204, 102, 0);
        for (int i = 1; i <= groeße; i++) {
            app.fill(211,211,211,200);
            app.rect(startkoordinateX-i*guiGroeße, startkoordinateY, guiGroeße, guiGroeße);


            if(i<=10){
                app.fill(0,0,0);
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

    public void zeichneInventarInhalt(PApplet app, int groeße, int startkoordinateX, int startkoordinateY, int guiGroeße) {
        for (int j = 0; j < inventar.size(); j++) {
            if (j % 10 == 0 && j > 0) {
                startkoordinateY -= guiGroeße;
                startkoordinateX -= guiGroeße * 10;
            }
            if (j < groeße) {
                inventar.get(j).setPosition(startkoordinateX - 7 * guiGroeße / 2 + j * guiGroeße, startkoordinateY + guiGroeße / 2);
                inventar.get(j).zeichne(app);
            }
        }
//        app.popStyle();
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
        app.rect(10,20,lebensenergie,10);
    }

    @Override
    public void erhalteSchaden(int schaden) {
        this.lebensenergie -= schaden;
    }

    /**
     * GameOver
     * */
    public void gameover(PApplet app) {
        if (lebensenergie <= 0) {
            System.out.println("Game Over");
            lebensenergie = 0;
            app.fill(0,0,0);
            app.rect (200,120,800,600);
            app.fill(138,3,3);
            app.textSize(60);
            app.text("Game Over",410,350 );
            app.text("Please Restart",410,450);
        }
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



    /**
     * @MEGAtroniker
     * Die Methode erhoeheGold erhöht den wert der Variable gold um den betrag
     * @param betrag erhöhung des Goldwerts
     */
    @Override
    public void erhoeheGold(int betrag) {
        this.gold += betrag;
        //System.out.printf("TODO: Erhöhe Gold um %d.", betrag);
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

    public void aktiviereWaffe(Waffe waffe){
        if(aktiveWaffe!=null){
            getInventar().add(aktiveWaffe);
        }
        aktiveWaffe = waffe;
    }

    public Waffe getWaffe(){
        return this.testwaffe;
    }

    public void setInventarGroeße(int inventarGroeße) {
        this.inventarGroeße = inventarGroeße;
    }

    public int getInventarGroeße() {
        return inventarGroeße;
    }

    public void playApfelSound(){
        File apfelSound = new File("apple_bite.wav");
        setupSound(apfelSound);
    }
    public void playSwallowSound(){
        File swallowSound = new File("swallow.wav");
        setupSound(swallowSound);
    }
    public void playBackpackOpenSound(){
        File backpackSound = new File("backpack.wav");
        setupSound(backpackSound);
    }
    public void playBackpackCloseSound(){
        File backpackSound = new File("backpack_reverse.wav");
        setupSound(backpackSound);
    }
    private void setupSound(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
