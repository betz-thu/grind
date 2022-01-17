package grind.movables.impl;

import grind.core.impl.Spielsteuerung;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import grind.util.Richtung;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.*;

/**
 * @author MEGAtroniker
 * Konstruktor angepasst, erbt nun von überladenem Movable-Konstruktor.
 */
public class Spielfigur extends Movable implements ISpielfigur {

    public static final int IMMUNITÄTSDAUERNACHSCHADEN = 2000; // in [ms]
    private boolean isImmun = false;
    private boolean isSternAngewandt = false;

    private float GESCHWINDIGKEIT = 3f;
    int gold = 5;
    private boolean abgeschossen = false;
    Richtung pfeilrichtung = Richtung.N;
    transient Waffe testwaffe = new Schwert(35, 35, 1, 3);
    transient Bogen testbogen = new Bogen(35, 35, 1, 3);
    transient Pfeil testpfeil = new Pfeil(35, 35, 1);
    transient Spezialattacke testattacke = new Spezialattacke(200, 200, 1);

    int lebensenergie = 100;//Kapselung?
    transient final List<Gegenstand> inventar;
    Waffe ausgerüsteteeWaffe =testwaffe;

    private int inventarGuiGroeße;
    private int guiGroeße;
    //public boolean waffeAusgestattet=false;
    transient Waffe aktiveWaffe = testwaffe;
    transient Waffe alteWaffe = testwaffe;
    transient Waffe aktiverPfeil = testpfeil;
    public boolean spezialAktiviert = false;
    private int countSpezialDauer=0;

    //Waffe aktiveWaffe = testbogen;

    Dictionary<String, File> Sounds = new Hashtable<String, File>();

    public Gegenstand auswahl;

    /**
     * @Autor MEGAtroniker
     * Methode getGeschwindigkeit, Getter für die Geschwindigkeit.
     * @return GESCHWINDIGKEIT
     */
    public float getGESCHWINDIGKEIT() {
        return GESCHWINDIGKEIT;
    }

    /**
     * @Autor MEGAtroniker
     * Konstruktor Spielfigur
     * Spielfigur besitzt Ausrichtung.
     * @param posX X-Position
     * @param posY Y-Position
     * @param richtung Ausrichtung
     */
    public Spielfigur(float posX, float posY, Richtung richtung) {
        super(posX, posY, richtung, Einstellungen.GROESSE_SPIELFIGUR);
        inventar = new ArrayList<>();
        setAktiveWaffe(testwaffe);
        //setAktiveWaffe(testbogen);
        inventarGuiGroeße =10;
        guiGroeße=50;
        setSounds();
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
        spielsteuerung.gameover();
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

    /**
     * Zeichnet Quadrate,ausgehend von den x-y Startkoordinaten (von rechts nach links), mit Seitenlängen der guiGröße
     * Bei jedem 10. Quadrat wird ein Zeilenumbruch nach oben (-y Richtung) gezeichnet
     *
     * @param app Main PApplet von Processing
     * @param groeße Inventargröße die gezeichnet werden soll
     * @param startkoordinateX x-Startposition von der aus das Inventar gezeichnet wird
     * @param startkoordinateY y-Startposition von der aus das Inventar gezeichnet wird
     * @param guiGroeße Größe des grafischen Overlays
     */

    public void zeichneInventar(Spielsteuerung app, int groeße, int startkoordinateX, int startkoordinateY, int guiGroeße) {
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

    /**
     * Methode verschiebt Gegenstände aus dem Inventar an x-y-Koordinaten
     * Gegenstände werden mit Abstand der guiGröße gezeichnet
     * Bei jedem 10. Gegenstand wird ein Zeilenumbruch nach oben (-y Richtung) gezeichnet
     *
     * @param app Main PApplet von Processing
     * @param groeße Inventargröße die ausgefüllt werden soll
     * @param startkoordinateX x-Startposition von der aus das Inventar gezeichnet wird
     * @param startkoordinateY y-Startposition von der aus das Inventar gezeichnet wird
     * @param guiGroeße Größe des grafischen Overlays
     */

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

    /**
     * Methode benutze
     * Überprüft die Inverntargröße und entfernt den benutzten Gegenstand aus der Inventar Liste
     * @param position int Wert zum überprüfen der Inventargröe / Wert der Position eines Gegenstands
     */
    //Methode zum benutzen oder ausrüsten von Gegenständen
    public void benutze(int position) {
        if (inventar.size() > position) {
            if (inventar.get(position) instanceof Nahrung || inventar.get(position) instanceof Stern) {
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
     * @Autor MEGAtroniker
     * Methode zeichneKontostand
     * Stellt Kontostand als Balken oben links an.
     * @param app Spielsteuerung, als Instanz von PApplet.
     */
    private void zeichneKontostand(Spielsteuerung app) {
        app.fill(255,215,0);
        app.rect(10,5,gold*5,10);
        app.text(Integer.toString(gold),20+gold*5,15);
    }


    /**
     * @Autor MEGAtroniker
     * Methode zeichneLebensbalken
     * Stellt Lebensbalken links oben dar.
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

        if(!isImmun && !isSternAngewandt){
            this.lebensenergie -= schaden;
            setImmun(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    /**
                     * Dient dazu, dass Spieler Immunität beibehält, wenn er in der Immunität ein Stern benutzt
                     * */
                    if(isSternAngewandt){
                        setImmun(true);
                        Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                setImmun(false);
                                timer2.cancel();
                            }
                        }, Stern.DAUERSTERNEVENT) ;
                    }

                    setImmun(false);
                    timer.cancel();
                }

            }, IMMUNITÄTSDAUERNACHSCHADEN);
            /**
             * nach 2 Sekunden setzt er Immunität wieder auf falsch --> Spielfigur ist nicht mehr immun
             * */
        }
    }

    @Override
    public void setSternAngewandt(boolean angewandt){
        this.isSternAngewandt = angewandt;
    }
    @Override
    public boolean isSternAngewandt(){
        return this.isSternAngewandt;
    }

    @Override
    public void setImmun(boolean isImmun) {
        this.isImmun = isImmun;
    }
    @Override
    public boolean isImmun() {
        return this.isImmun;
    }

    @Override
    public void setGeschwindigkeit(float immunGeschwindigkeit) {
        this.GESCHWINDIGKEIT = immunGeschwindigkeit;
    }
    @Override
    public float getGeschwindigkeit() {
        return this.GESCHWINDIGKEIT;
    }

    /**
     * @Autor MEGAtroniker
     * Methode bewege
     * Setzt neue Koordinaten der Figur.
     * Ob die Koordinaten auf belaufbarem Feld liegen, wird schon vor Aufruf in der Spielsteuerung getestet.
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
     * @Autor MEGAtroniker
     * Methode erhoeheGold
     * Erhöht den Wert der Variable gold um betrag.
     * @param betrag Erhöhung des Goldwerts
     */
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

    public int setLebensenergie(int neueLebensenergie){
        this.lebensenergie = neueLebensenergie;
        return neueLebensenergie;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }



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

    /**
     * @Autor MEGAtroniker
     * Methode setSounds
     * Lädt die Sounds in das Dictionary Sounds.
     * Der Key ist bei Monstern ein String aus Klassenname mit Around bzw Attack,
     * und bei den anderen Sounds ein gewählter String.
     */
    private void setSounds(){
        this.Sounds.put("class grind.movables.monster.ZombieAround",new File("Zombie_in_der_Naehe.wav"));
        this.Sounds.put("class grind.movables.monster.ZombieAttack",new File("Zombie_attac.wav"));
        this.Sounds.put("class grind.movables.monster.FeuerMonsterAround",new File("Feuermonster_Around.wav"));
        this.Sounds.put("class grind.movables.monster.FeuerballAround",new File("Feuerball__flyBy.wav"));
        this.Sounds.put("class grind.movables.monster.GeistAround",new File("Gohst_Around.wav"));
        this.Sounds.put("class grind.movables.monster.DornPflanzeAround",new File("Pflanze_Around.wav"));
        this.Sounds.put("class grind.movables.monster.DornPflanzeAttack",new File("Pflanze_Around.wav"));
        this.Sounds.put("openBackPack",new File("backpack.wav"));
        this.Sounds.put("closeBackPack",new File("backpack_reverse.wav"));
        this.Sounds.put("swallow",new File("swallow.wav"));
        this.Sounds.put("Apfel",new File("apple_bite.wav"));
    }

    /**
     * @Autor MEGAtroniker
     * Methode playSound
     * Falls das Dictionary Sounds einen Sound zum übergebenen Key hat, wird dieser abgespielt.
     * @param nameKey String, fungiert als Key für das Dictionary Sounds.
     */
    public void playSound(String nameKey){
        if (Sounds.get(nameKey) != null ){
            setupSound(Sounds.get(nameKey));
        }
    }

    /**
     * Die Methode setupSound dient zum inititalisieren aller benötigten Komponenten zur Tonwiedergabe.
     * Als Parameter wird eine File Variable mit dem pathname zur abzuspielenden Tondatei übergeben.
     * Die Tonwiedergabe wird ebenfalls in dieser Funktion gestartet
     * @param Sound Dateipfad zur .wav Datei
     */
        private void setupSound(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verwendet Gegenstände aus dem Inventar, wenn Koordinaten des Gegenstands mit übergebenen Parametern (+gui Größe) übereinstimmt
     * (z.B. Mausposition)
     *
     * @param x x-K
     * @param y y-Wert der Mausposition
     */

    public void klickItems(int x, int y) {
        int invPos = getInventarPosition(x, y);
        if (invPos >= 0) {
            benutze(invPos);
        }
    }

    /**
     * Ermittelt die Inventarposition, die zur übergebenen Mausposition gehört. Der Rückgabewert ist der Index der
     * zugehörigen Inventarposition, falls der Mauszeiger über dem Inventar steht, andernfalls -1.
     *
     * @param x x-Wert der Mausposition
     * @param y y-Wert der Mausposition
     * @return der Index der zugehörigen Inventarposition; -1 falls nicht der Mauszeiger nicht über dem Inventar steht
     */
    public int getInventarPosition(int x, int y) {
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

    @Override
    public int getGold() {
        return gold;
    }

    /**
     * Zieht übergebenen Betrag vom Kontostand (Spieler Gold) ab
     * @param betrag
     */
    public void verringereGold(int betrag) {
        this.gold = this.gold - betrag;
    }

}
