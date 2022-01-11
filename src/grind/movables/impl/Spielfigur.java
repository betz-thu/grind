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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author MEGAtronik
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


    private int inventarGuiGroeße;
    private int guiGroeße;
    //public boolean waffeAusgestattet=false;
    transient Waffe aktiveWaffe = testwaffe;
    private boolean spezialAktiviert = false;
    private int countSpezialDauer = 0;
    //Waffe aktiveWaffe = testbogen;


    public Gegenstand auswahl;

    /**
     * @return GESCHWINDIGKEIT
     * @MEGAtroniker Methode getGeschwindigkeit, Getter für die Geschwindigkeit.
     */
    public float getGESCHWINDIGKEIT() {
        return GESCHWINDIGKEIT;
    }

    /**
     * Konstruktor Spielfigur
     *
     * @param posX gibt X-Position der Spielfigur an.
     * @param posY gibt Y-Position der Spielfigur an.
     */
    public Spielfigur(float posX, float posY, Richtung richtung) {
        super(posX, posY, richtung, Einstellungen.GROESSE_SPIELFIGUR);
        inventar = new ArrayList<>();
        setAktiveWaffe(testwaffe);
        //setAktiveWaffe(testbogen);
        inventarGuiGroeße = 10;
        guiGroeße = 50;
    }

    /**
     * Methode zeichne: zeichnet Bild der Spielfigur, abhängig von Ausrichtung und Position.
     * Dadurch schaut die Spielfigur immer in Laufrichtung.
     * Bild: "SpielfigurOhneWaffe.jpg"
     *
     * @param spielsteuerung Spielsteuerung, für Darstellung in Processing.
     */
    @Override
    public void zeichne(Spielsteuerung spielsteuerung) {
        zeichneSpielfigur(spielsteuerung);
        zeichneLebensbalken(spielsteuerung);
        zeichneKontostand(spielsteuerung);

        //Zeichne kleines Inventar
        zeichneInventar(spielsteuerung, inventarGuiGroeße, 850, 720, guiGroeße);
        zeichneInventarInhalt(spielsteuerung, inventarGuiGroeße, 550, 720, guiGroeße);
        if (auswahl != null) {
            auswahl.setPosition(spielsteuerung.mouseX, spielsteuerung.mouseY);
            auswahl.zeichne(spielsteuerung);
        }
        spielsteuerung.gameover();
    }

    @Override
    public int getGroesse() {
        return this.groesse = Einstellungen.GROESSE_SPIELFIGUR;
    }

    /**
     * Methode zeichneSpielfigur, stellt SpielfigurOhneWaffe dar.
     * (zukünftig: stellt SpielfigurOhneWaffe, SpielfigurMitSchwert, SpielfigurMitBogen usw dar.)
     *
     * @param app
     */
    public void zeichneSpielfigur(Spielsteuerung app) {
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
                schwertPositionX = 0;
                schwertPositionY = -1;
                break;
            case O:
                n = 1;
                schwertPositionX = 1;
                schwertPositionY = 0;
                break;
            case S:
                n = 2;
                schwertPositionX = 0;
                schwertPositionY = 1;
                break;
            case W:
                n = 3;
                schwertPositionX = -1;
                schwertPositionY = 0;
        }
        app.rotate(PConstants.HALF_PI * n);
        app.image((PImage) app.getImages().get(this.getClass().toString()), 0, 0, groesse, groesse);
        app.popMatrix();
        app.popStyle();


        //testattacke.zeichne(app);
        if (app.key == ' ' & app.keyPressed) { //Schwert nur anzeigen, wenn Leertaste gedrückt wurde
            if (!abgeschossen) {
                /**
                 * Wenn der Pfeil noch nicht abgeschossen wurde wird die Pfeilrichtung und die Abschussposition festgelegt.
                 */
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

            if (!(aktiveWaffe instanceof Spezialattacke)) {
                //aktiveWaffe.setGroesse(40);
                aktiveWaffe.setPosition(this.getPosX() + aktiveWaffe.getGroesse() * schwertPositionX, this.getPosY() + aktiveWaffe.getGroesse() * schwertPositionY);
                aktiveWaffe.setAusrichtung(this.getAusrichtung());
                aktiveWaffe.zeichne(app);
                //countSpezialDauer +=1;
                //if (countSpezialDauer == 70){
                //   aktiveWaffe.setGroesse(1);
                // app.
                //countSpezialDauer=0;
                //}

            }
//            testpfeil.zeichne(app);
//            testpfeil.setPosition(testpfeil.getPosX()+1, testpfeil.getPosY() + 1);

        }
        if (abgeschossen && aktiveWaffe instanceof Bogen) {
            /**
             * Der Pfeil fliegt in Blichrichtung der Spielfigur mit in der Klasse Pfeil definierter Geschwindigkeit los.
             */
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
        if (spezialAktiviert) {
            testattacke.setPosition(this.getPosX(), this.getPosY());
            testattacke.setGroesse(150);
            testattacke.zeichne(app);
            setAktiveWaffe(testattacke);
            countSpezialDauer += 1;
            if (countSpezialDauer == 30) {
                spezialAktiviert = false;
                setAktiveWaffe(testwaffe);
                countSpezialDauer = 0;
            }
        }
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
        app.fill(255, 255, 255);
        app.stroke(255, 255, 255);
        app.strokeWeight(2f);
        app.textSize(24);
        //app.fill(204, 102, 0);
        for (int i = 1; i <= groeße; i++) {
            app.fill(211, 211, 211, 200);
            app.rect(startkoordinateX - i * guiGroeße, startkoordinateY, guiGroeße, guiGroeße);


            if (i <= 10) {
                app.fill(0, 0, 0);
                app.text(zaehler, startkoordinateX - i * guiGroeße, startkoordinateY + 20);
                if (zaehler == 0) {
                    zaehler = 10;
                }
                zaehler--;

            }
            if (i % 10 == 0 && i > 0) {
                startkoordinateY -= guiGroeße;
                startkoordinateX += guiGroeße * 10;
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

    //Methode zum benutzen oder ausrüsten von Gegenständen
    public void benutze(int position) {
        if (inventar.size() > position) {
            if (inventar.get(position) instanceof Nahrung || inventar.get(position) instanceof Stern) {
                inventar.get(position).beimAnwenden(this);
                inventar.remove(position);
            } else if (inventar.get(position) instanceof Spezialattacke) {
                //erst groß zeichnen
                //inventar.get(position).beimAnwenden(this);

                spezialAktiviert = true;
                inventar.remove(position);
            } else if (inventar.get(position) instanceof Waffe) {
                Waffe waffe = (Waffe) inventar.get(position);

                inventar.add(aktiveWaffe);
                this.setAktiveWaffe(waffe);
                inventar.remove(position);
                System.out.println("Neue Waffe ausgerüstet");

            }
        }
    }

    /**
     * Methode zeichneKontostand, stellt Kontostand als Balken oben links an.
     *
     * @param app Spielsteuerung, als Instanz von PApplet.
     */
    private void zeichneKontostand(Spielsteuerung app) {
        app.fill(255, 215, 0);
        app.rect(10, 5, gold * 5, 10);
        app.text(Integer.toString(gold), 20 + gold * 5, 15);
    }


    /**
     * Methode zeichneLebensbalken, stellt Lebensbalken links oben dar.
     *
     * @param app Spielsteuerung, als Instanz von PApplet.
     */
    private void zeichneLebensbalken(PApplet app) {
        app.fill(150);
        app.rect(10, 20, 100, 10);
        app.fill(0, 150, 0);
        app.rect(10, 20, lebensenergie, 10);
    }

    @Override
    public void erhalteSchaden(int schaden) {

        if (!isImmun && !isSternAngewandt) {
            this.lebensenergie -= schaden;
            setImmun(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Dient dazu, dass Spieler Immunität beibehält, wenn er in der Immunität ein Stern benutzt
                    if (isSternAngewandt) {
                        setImmun(true);
                        Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                setImmun(false);
                                timer2.cancel();
                            }
                        }, Stern.DAUERSTERNEVENT);
                    }

                    setImmun(false);
                    timer.cancel();
                }

            }, IMMUNITÄTSDAUERNACHSCHADEN); // nach 2 Sekunden setzt er Immunität wieder auf falsch --> Spielfigur ist nicht mehr immun
        }
    }

    @Override
    public void setSternAngewandt(boolean angewandt) {
        this.isSternAngewandt = angewandt;
    }

    @Override
    public boolean isSternAngewandt() {
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
     * Methode bewege, setzt neue Koordinaten der Figur.
     *
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
     * @param betrag erhöhung des Goldwerts
     * @MEGAtroniker Die Methode erhoeheGold erhöht den wert der Variable gold um den betrag
     */
    @Override
    public void erhoeheGold(int betrag) {
        this.gold += betrag;
        //System.out.printf("TODO: Erhöhe Gold um %d.", betrag);
    }

    @Override
    public List<Gegenstand> getInventar() {
        return this.inventar;
    }

    @Override
    public int getLebensenergie() {
        return this.lebensenergie;
    }

    public int setLebensenergie(int neueLebensenergie) {
        this.lebensenergie = neueLebensenergie;
        return neueLebensenergie;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }


    public void setAktiveWaffe(Waffe waffe) {
        //erst aktuelle Waffe dem Inventar hinzufügen,  damit sie nicht verloren geht.
        //inventar.add(aktiveWaffe);
        //neue Waffe ausrüsten
        aktiveWaffe = waffe;
    }

    public Waffe getWaffe() {
        return aktiveWaffe;
    }

    public Waffe getPfeil() {
        return this.testpfeil;
    }

    public void setPfeilAbgeschossen(boolean setzteAuf) {
        this.abgeschossen = setzteAuf;
    }

    public void setInventarGuiGroeße(int inventarGuiGroeße) {
        this.inventarGuiGroeße = inventarGuiGroeße;
    }

    public int getInventarGuiGroeße() {
        return inventarGuiGroeße;
    }

    public void playApfelSound() {
        File apfelSound = new File("apple_bite.wav");
        setupSound(apfelSound);
    }

    public void playSwallowSound() {
        File swallowSound = new File("swallow.wav");
        setupSound(swallowSound);
    }

    public void playBackpackOpenSound() {
        File backpackSound = new File("backpack.wav");
        setupSound(backpackSound);
    }

    public void playBackpackCloseSound() {
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


    private void setupSound(File Sound) {
        try {
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
        for (int i = 0; i < inventar.size(); i++) {
            xBereich = (x <= inventar.get(i).getPosX() + guiGroeße / 2 && x > inventar.get(i).getPosX() - guiGroeße / 2);
            yBereich = (y <= inventar.get(i).getPosY() + guiGroeße / 2 && y > inventar.get(i).getPosY() - guiGroeße / 2);
            if (xBereich && yBereich) {
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
