package grind.movables.impl;

import grind.core.impl.Spielmodell;
import grind.core.impl.Spielsteuerung;
import grind.movables.IMovable;
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

    float GESCHWINDIGKEIT = 3f;
    int gold = 5;
    private boolean abgeschossen = false;
    Richtung pfeilrichtung = Richtung.N;
    transient Waffe testwaffe = new Schwert(35,35,1);
    transient Bogen testbogen = new Bogen(35,35,1);
    transient Pfeil testpfeil = new Pfeil(35,35,1);
    transient Spezialattacke testattacke = new Spezialattacke(200,200,1);

    int lebensenergie = 100;//Kapselung?
    final List<Gegenstand> inventar;
    Waffe ausgerüsteteeWaffe =testwaffe;

    private int inventarGuiGroeße;
    private int guiGroeße;
    //public boolean waffeAusgestattet=false;
    Waffe aktiveWaffe = testwaffe;
    Waffe alteWaffe = testwaffe;
    Waffe aktiverPfeil = testpfeil;
    public boolean spezialAktiviert = false;
    private int countSpezialDauer=0;
    //Waffe aktiveWaffe = testbogen;


    public Gegenstand auswahl;
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
        setAktiveWaffe(testwaffe);
        //setAktiveWaffe(testbogen);
        inventarGuiGroeße =10;
        guiGroeße=50;


}

    /**
     * Methode zeichne: zeichnet Bild der Spielfigur, abhängig von Ausrichtung und Position.
     * Dadurch schaut die Spielfigur immer in Laufrichtung.
     * Bild: "SpielfigurOhneWaffe.jpg"
     * @param spielsteuerung Spielsteuerung, für Darstellung in Processing.
     */
    @Override
    public void zeichne(Spielsteuerung spielsteuerung) {
        zeichneSpielfigur(spielsteuerung);
        zeichneLebensbalken(spielsteuerung);
        zeichneKontostand(spielsteuerung);
        zeichneWaffe(spielsteuerung);

        //Zeichne kleines Inventar
        zeichneInventar(spielsteuerung, inventarGuiGroeße, 850, 720, guiGroeße);
        zeichneInventarInhalt(spielsteuerung, inventarGuiGroeße, 550, 720, guiGroeße);
        if(auswahl!=null) {
            auswahl.setPosition(spielsteuerung.mouseX, spielsteuerung.mouseY);
            auswahl.zeichne(spielsteuerung);
        }
        gameover(spielsteuerung);

    }

    @Override
    public int getGroesse() {
        return this.groesse = Einstellungen.GROESSE_SPIELFIGUR;
    }


    public void zeichneWaffe(Spielsteuerung app) {
        if (app.key == ' ' & app.keyPressed) {
            getWaffe().zeichne(app);
        }
        if (aktiveWaffe instanceof Spezialattacke){
            getWaffe().zeichne(app);
        }
//        System.out.println(abgeschossen);
        if (app.key == ' ' & app.keyPressed & getWaffe() instanceof Bogen & !abgeschossen) {
            abgeschossen=true;
        }
        if (abgeschossen){
           getPfeil().zeichne(app);
        }
    }
    /**
     * Methode zeichneSpielfigur, stellt SpielfigurOhneWaffe dar.
     * (zukünftig: stellt SpielfigurOhneWaffe, SpielfigurMitSchwert, SpielfigurMitBogen usw dar.)
     * @param app
     */
    public void zeichneSpielfigur(Spielsteuerung app) {

        app.pushStyle();
        app.imageMode(PConstants.CENTER);
        app.pushMatrix();
        app.translate(this.posX, this.posY);
        int n = 1;
        //int schwertPositionX = 1;
        //int schwertPositionY = 1;
        switch (this.ausrichtung) {
            case N:
                n = 0;
               // schwertPositionX = 0;
                //schwertPositionY = -1;
                break;
            case O:
                n = 1;
                //schwertPositionX = 1;
                //schwertPositionY = 0;
                break;
            case S:
                n = 2;
                //schwertPositionX = 0;
                //schwertPositionY = 1;
                break;
            case W:
                n = 3;
                //schwertPositionX = -1;
                //schwertPositionY = 0;
        }
        app.rotate(PConstants.HALF_PI * n);
        app.image((PImage) app.getImages().get(this.getClass().toString()), 0, 0, groesse, groesse);
        app.popMatrix();
        app.popStyle();

        /*
        //testattacke.zeichne(app);
        if(app.key == ' '& app.keyPressed) { //Schwert nur anzeigen, wenn Leertaste gedrückt wurde

            if (!abgeschossen) {

                 //Wenn der Pfeil noch nicht abgeschossen wurde wird die Pfeilrichtung und die Abschussposition festgelegt.

                if (this.getAusrichtung() == Richtung.N) {
                    testpfeil.setPosition(this.getPosX(), this.getPosY() - this.getGroesse());
                } else if (this.getAusrichtung() == Richtung.O) {
                    testpfeil.setPosition(this.getPosX() + this.getGroesse(), this.getPosY());
                } else if (this.getAusrichtung() == Richtung.S) {
                    testpfeil.setPosition(this.getPosX(), this.getPosY() + this.getGroesse());
                } else {
                    testpfeil.setPosition(this.getPosX() - this.getGroesse(), this.getPosY());
                }

                pfeilrichtung = this.getAusrichtung();
            }
            abgeschossen = true;

            if(!(aktiveWaffe instanceof Spezialattacke)) {
                //aktiveWaffe.setGroesse(40);
                aktiveWaffe.setPosition(this.getPosX() + aktiveWaffe.getGroesse() * schwertPositionX, this.getPosY() + aktiveWaffe.getGroesse() * schwertPositionY);
                aktiveWaffe.setAusrichtung(this.getAusrichtung());
                aktiveWaffe.zeichne(app);

            }

        }
        if (abgeschossen && aktiveWaffe instanceof Bogen) {

             // Der Pfeil fliegt in Blichrichtung der Spielfigur mit in der Klasse Pfeil definierter Geschwindigkeit los.

            testpfeil.zeichne(app);
            if (pfeilrichtung == Richtung.N) {
                testpfeil.setPosition(testpfeil.getPosX() + 0, testpfeil.getPosY() - testpfeil.getGeschwindigkeit());
                this.getPfeil().setAusrichtung(Richtung.N);
            } else if (pfeilrichtung == Richtung.O) {
                testpfeil.setPosition(testpfeil.getPosX() + testpfeil.getGeschwindigkeit(), testpfeil.getPosY() + 0);
                this.getPfeil().setAusrichtung(Richtung.O);
            } else if (pfeilrichtung == Richtung.S) {
                testpfeil.setPosition(testpfeil.getPosX() + 0, testpfeil.getPosY() + testpfeil.getGeschwindigkeit());
                this.getPfeil().setAusrichtung(Richtung.S);
            } else if (pfeilrichtung == Richtung.W) {
                testpfeil.setPosition(testpfeil.getPosX() - testpfeil.getGeschwindigkeit(), testpfeil.getPosY() + 0);
                this.getPfeil().setAusrichtung(Richtung.W);
            }


        }
        if(spezialAktiviert){
            testattacke.setPosition(this.getPosX(),this.getPosY());
            testattacke.setGroesse(150);
            testattacke.zeichne(app);

            if(!(aktiveWaffe instanceof Spezialattacke)){
                alteWaffe = this.getWaffe();}
            setAktiveWaffe(testattacke);
            countSpezialDauer +=1;
            if (countSpezialDauer == 30){
                spezialAktiviert=false;
                setAktiveWaffe(alteWaffe);
                countSpezialDauer=0;
            }
        }

         */

    }

    public void zeichneInventar(Spielsteuerung app, int groeße, int startkoordinateX, int startkoordinateY, int guiGroeße){
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

    public void zeichneInventarInhalt(Spielsteuerung app, int groeße, int startkoordinateX, int startkoordinateY, int guiGroeße) {
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
            else if(inventar.get(position) instanceof Spezialattacke){

                spezialAktiviert=true;
                inventar.remove(position);

            }
            else if(inventar.get(position) instanceof Waffe & !(inventar.get(position) instanceof Spezialattacke)){
                Waffe waffe =  (Waffe) inventar.get(position);
                inventar.add(aktiveWaffe);

                //app.setAktiveWaffe_(waffe);

                this.setAktiveWaffe(waffe);

                inventar.remove(position);
                System.out.println("Neue Waffe ausgerüstet");

            }
        }
    }

    /**
     * Methode zeichneKontostand, stellt Kontostand als Balken oben links an.
     * @param app Spielsteuerung, als Instanz von PApplet.
     */
    private void zeichneKontostand(Spielsteuerung app) {
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

                if (app.keyPressed){
                    if (app.key == 'R' || app.key == 'r'){
                        restart();
                    }
                }
                    if (app.key =='Q' || app.key =='q'){
                        System.exit(0);
                }
            }
        }


    public void restart(){
        System.out.println("restart");


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
     */
    /*public void ladeIMGSpielfigur(PApplet app) {
        spielfigurOhneWaffe = app.loadImage("SpielfigurOhneWaffe.jpg");
    }*/

    /**
     * Die übergebene Waffe wird nun für die Spielfigur zur aktiven Waffe. Das heißt die Waffe kann nun verwendet werden.
     * @param waffe die zu aktivierende Waffe
     */
    public void setAktiveWaffe(Waffe waffe){

        aktiveWaffe = waffe;
    }

    /**
     * Gibt die momentan von der Spielfigur verwendete Waffe zurück.
     * @return aktuell von der Spielfigur verwendete Waffe
     */
    public Waffe getWaffe(){
        return aktiveWaffe;
    }

    /**
     * Gibt den momentan von der SPielfigur verwendeten Pfeil zurück.
     * @return der aktive Pfeil der Spielfigur
     */
    public Waffe getPfeil(){
        //return this.testpfeil;
        return aktiverPfeil;
    }

    /**
     * Setzt einen boolean auf wahr, sobald ein Pfeil abgeschossen worden ist.
     * @param setzteAuf True wenn der Pfeil abgeschossen wurde/ False wenn kein Pfeil abgeschossen wurde.
     */
    public void setPfeilAbgeschossen(boolean setzteAuf){
        this.abgeschossen = setzteAuf;
    }

    public void setInventarGuiGroeße(int inventarGuiGroeße) {
        this.inventarGuiGroeße = inventarGuiGroeße;
    }

    public int getInventarGuiGroeße() {
        return inventarGuiGroeße;
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

    /**
     * @MEGAtroniker
     */
    public void playZombieAttacSound() {
        File zombieAttac = new File("Zombie_attac.wav");
        setupSound(zombieAttac);
    }

    /**
     * @MEGAtroniker
     */
    public void playZombieAroundSound() {
        File zombieAround = new File("Zombie_in_der_Naehe.wav");
        setupSound(zombieAround);
    }

    /**
     * @MEGAtroniker
     */
    public void playFeuerMonsterAroundSound() {
        File feuerMonsterAround = new File("Feuermonster_Around.wav");
        setupSound(feuerMonsterAround);
    }


    /**
     * @MEGAtroniker
     */
    public void playFeuerBallAroundSound() {
        File feuerBallAround = new File("Feuerball__flyBy.wav");
        setupSound(feuerBallAround);
    }

    /**
     * @MEGAtroniker
     */
    public void playGeistAroundSound() {
        File gohstAround = new File("Gohst_Around.wav");
        setupSound(gohstAround);
    }

    /**
     * @MEGAtroniker
     */
    public void playPflanzeAroundSound() {
        File pflanzeAround = new File("Pflanze_Around.wav");
        setupSound(pflanzeAround);
    }

    /**
     * @MEGAtroniker
     */
    public void playPflanzeAttacSound() {
        File pflanzeAttac = new File("Pflanze_Around.wav");
        setupSound(pflanzeAttac);
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

    public void klickItems(int x, int y){
        int invPos = getInvPos(x,y);
        if(invPos>=0) {
            benutze(invPos);
        }
    }


    public int getInvPos(int x, int y){
        boolean xBereich;
        boolean yBereich;
        for(int i=0; i<inventar.size(); i++){
            xBereich = (x<=inventar.get(i).getPosX()+guiGroeße/2 && x>inventar.get(i).getPosX()-guiGroeße/2);
            yBereich = (y<=inventar.get(i).getPosY()+guiGroeße/2 && y>inventar.get(i).getPosY()-guiGroeße/2);
            if(xBereich && yBereich){
                System.out.println("clicked");
                return i;
            }
        }
        return -1;
    }
}
