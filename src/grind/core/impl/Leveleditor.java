package grind.core.impl;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.*;
import grind.movables.IMovable;
import grind.movables.impl.*;
import grind.movables.monster.*;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.LaufModus;
import grind.util.Richtung;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.util.Dictionary;
import java.util.Hashtable;


public class Leveleditor extends Spielsteuerung {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;
    private final Spielsteuerung spielsteuerung;

    private final int menuBreite = 1;
    private final int menuHoehe = 8;
    private final int breiteExit;
    private final int breiteSpeichern;
    private final int breiteLaden;
    private final int breiteLeeren;
    private final int breiteLevel;
    private final int breiteSiedlung;
    private final int breiteVor;
    private final int breiteZurueck;
    private int levelCount = 1;
    private int levelNr = 1;
    private int speicherHinweisLevel;
    private int speicherHinweis = 1;
    private DateiService dateiService;
    private ISpielwelt spielwelt;
    private TileMap tileMap;
    private IKachel[][] menuArrayKacheln;
    private IMovable[][] menuArrayMovables;
    private IKachel aktuelleKachel;
    private IMovable aktuellesMovable;
    private final Button exitButton;
    private final Button speichernButton;
    private final Button ladeButton;
    private final Button leerenButton;
    private final Button levelButton;
    private final Button siedlungButton;
    private final Button bildVor;
    private final Button bildZurueck;
    //private PImage spielfigurBild;
    Dictionary images = new Hashtable();


    /**
     * Konstruktor des Leveleditors
     * Erstellt von den benötigten Klassen Instanzen um die Darstellung der einzelnen Spielobjekte zu ermöglichen
     * Befüllt die Menüs mit den entsprechenden Instanzen der Klassen
     * Ändert die Kachelgräße um den Leveleditor auch auf kleineren Bildschirmen vollständig anzuzeigen
     * Legt die Größe der Buttons anhand der Kachelgrößen fest
     */
    public Leveleditor(){
        Einstellungen.LAENGE_KACHELN_Y = 30;
        Einstellungen.LAENGE_KACHELN_X = 30;
        this.menuArrayKacheln = new IKachel[menuHoehe][menuBreite];
        this.menuArrayMovables = new IMovable[12][1];
        this.tileMap = new TileMap();
        this.spielwelt = new DummySpielwelt();
        this.spielsteuerung = new Spielsteuerung();
        this.dateiService = new DateiService(this.spielsteuerung);
        this.aktuelleKachel = null;
        this.aktuellesMovable = null;
        this.exitButton = new Button(0);
        this.speichernButton = new Button(1);
        this.ladeButton = new Button(2);
        this.leerenButton = new Button(3);
        this.levelButton = new Button(4);
        this.siedlungButton = new Button(5);
        this.bildZurueck = new Button(6);
        this.bildVor = new Button(7);
        //this.spielfigurBild = new PImage();

        this.breiteExit = exitButton.getBreite();

        this.breiteSpeichern = exitButton.getBreite()
                + speichernButton.getBreite();

        this.breiteLaden = exitButton.getBreite()
                + speichernButton.getBreite()
                + ladeButton.getBreite();

        this.breiteLeeren = exitButton.getBreite()
                + speichernButton.getBreite()
                + ladeButton.getBreite()
                + leerenButton.getBreite();

        this.breiteLevel = exitButton.getBreite()
                + speichernButton.getBreite()
                + ladeButton.getBreite()
                + leerenButton.getBreite()
                + levelButton.getBreite();

        this.breiteSiedlung = exitButton.getBreite()
                + speichernButton.getBreite()
                + ladeButton.getBreite()
                + leerenButton.getBreite()
                + levelButton.getBreite()
                + siedlungButton.getBreite();

        this.breiteZurueck = exitButton.getBreite()
                + speichernButton.getBreite()
                + ladeButton.getBreite()
                + leerenButton.getBreite()
                + levelButton.getBreite()
                + siedlungButton.getBreite()
                + bildZurueck.getBreite();

        this.breiteVor = exitButton.getBreite()
                + speichernButton.getBreite()
                + ladeButton.getBreite()
                + leerenButton.getBreite()
                + levelButton.getBreite()
                + siedlungButton.getBreite()
                + bildZurueck.getBreite()
                + bildVor.getBreite();


        //Befüllen des Menuarrays mit den Kachelarten
        this.menuArrayKacheln[0][0] = new Baum();
        this.menuArrayKacheln[1][0] = new DummyHindernis();
        this.menuArrayKacheln[2][0] = new Fels();
        this.menuArrayKacheln[3][0] = new Holzbrücke();
        this.menuArrayKacheln[4][0] = new Levelausgang();
        this.menuArrayKacheln[5][0] = new Wasser();
        this.menuArrayKacheln[6][0] = new Weg();
        this.menuArrayKacheln[7][0] = new Wiese();

        //Befüllen des Menuarrays mit den Movables
        this.menuArrayMovables[0][0] = new Spielfigur(0,0, Richtung.N);
        this.menuArrayMovables[1][0] = new Schwert(40,40,1);
        this.menuArrayMovables[2][0] = new Mango(0,0);
        this.menuArrayMovables[3][0] = new Levelende(0,0);
        this.menuArrayMovables[4][0] = new Heiltrank(0,0);
        this.menuArrayMovables[5][0] = new Gold(0,0);
        this.menuArrayMovables[6][0] = new Apfel(0,0);
        this.menuArrayMovables[7][0] = new DornPflanze(0,0,this.tileMap);
        this.menuArrayMovables[8][0] = new FeuerMonster(0,0,this.tileMap,this.spielsteuerung,Richtung.N,1, FeuerModus.RANDOM);
        this.menuArrayMovables[9][0] = new Geist(0,0,this.tileMap);
        this.menuArrayMovables[10][0] = new Zombie(0,0,this.tileMap,Richtung.N,this.spielsteuerung, LaufModus.DEFAULT);
        this.menuArrayMovables[11][0] = new Stern(0,0);

    }

    /**
     * Methode settings, setzt Spielfeldgröße auf die in den Einstellungen gesetzten Parameter.
     */
    @Override
    public void settings() {
        SpielfeldBreite = Einstellungen.LAENGE_KACHELN_X * Einstellungen.ANZAHL_KACHELN_X;
        SpielfeldHoehe = Einstellungen.LAENGE_KACHELN_Y * Einstellungen.ANZAHL_KACHELN_Y;
        size(SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X*2,SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y);
    }

    /**
     * Methode setup, lädt Darstellung der Spielfigur und zeigt den Titel des Programms an
     */
    @Override
    public void setup() {
        imageMode(PConstants.CORNER);
        ladeBilder(this);
        //spielfigurBild = loadImage("SpielfigurOhneWaffe.jpg");
        anzeigeTitelLevel(levelNr);
//        Spieler.ladeIMGSpielfigur(this);
//        anzeigeTitelLevel(this.spielmodell.getSzeneNr()+1);
    }

    /**
     * Methode draw, zeichnet alle sichtbare Elemente.
     * (Tilemap, Movables, Buttons, ...)
     */
    @Override
    public void draw() {
        background(255);
    //    eingabe();
    //    aktualisiere();
        zeichne();
    }

//    /**
//     * @MEGAtroniker
//     * Die Methode springt zur nächsten Szene durch das Betätigen der Taste "F12"
//     */
//    private void szeneUeberspringen() {
//        abfrageFTasten();
//    }
//
//    /**
//     * @author LuHe20
//     * Cheat für das Überspringen einer Szene mit F12.
//     * Speichern der Spielwelt mit F11
//     * Laden der Spielwelt mit F10
//     */
//    private void abfrageFTasten() {
//        //F12 neue Szene
//        if (keyPressed && !pressed) {
//            if (keyCode == 123) {
//                pressed = true;
//                fTaste = "F12";
//            } else if(keyCode == 122){
//                pressed = true;
//                fTaste = "F11";
//            } else if(keyCode == 121){
//                pressed = true;
//                fTaste = "F10";
//            }
//        } else if(!keyPressed && pressed){
//            switch (fTaste) {
//                case "F12":
//
//                    levelBeendet = true;
//
//                    break;
//                case "F11":
//
//                    speichereSpielwelt();
//
//                    System.out.println("F11");
//
//                    break;
//                case "F10":
//
//                    ISpielwelt welt;
//                    welt = ladeSpielwelt();
//                    this.spielmodell.setSpielwelt(welt);
//                    spielmodell.setSzeneNr(0);
//                    spielmodell.betreteSzene(spielmodell.getSzeneNr());
//
//                    System.out.println("F10");
//
//                    break;
//            }
//
//            pressed = false;
//        }
//    }
//
//    private void aktualisiere() {
//        spielmodell.entferneToteMonster();
//        spielmodell.bewege();
//        //levelBeendet = ueberpruefeLevelende();
//        starteNeueSzene();
//    }
//
//    /**
//     * @author LuHe20
//     * Startet, wenn levelBeendet Bedingung wahr ist, die nächste Szene.
//     */
//    private void starteNeueSzene() {
//        if(levelBeendet){
//            levelBeendet = false;
//            spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
//            spielmodell.betreteSzene(spielmodell.getSzeneNr());
//            anzeigeTitelLevel(spielmodell.getSzeneNr() + 1);
//        }
//    }

    /**
     * Ruft alle einzelnen zeichne Methoden auf.
     */
    private void zeichne() {
        //this.spielmodell.getTileMap().zeichne(this);
        zeichneTileMap();
        zeichneMovables();
        zeichneAssetMenu(this.menuArrayKacheln);
        zeichneMovableMenu(this.menuArrayMovables);
        zeichneMausKachel(aktuelleKachel, mouseX, mouseY);
        zeichneMausMovable(aktuellesMovable,mouseX,mouseY);
        zeichneButtons();
        zeichneCounts();
        zeichneFehler();
    }

    /**
     * Fragt ab welche Maustaste gedrückt wurde.
     * Bei einem Linksklick wird überprüft, ob man sich auf einem Menupunkt (Kachel/Movable), Button oder auf
     * der Tilemap befindet.
     * Befindet man sich auf der Tilemap, wird das aktuell ausgewählte Objekt platziert.
     * Befindet man sich auf einem Menupunkt, wird dieser als akutelle Mauskachel gewählt.
     * Befindet man sich auf einem Button, wird dieser aufgerufen.
     *
     * Bei einem Rechtsklick wird die aktuelle Mauskachel auf null gesetzt.
     */
    @Override
    public void mousePressed() {
        int mausXmenu = (mouseX - SpielfeldBreite) / Einstellungen.LAENGE_KACHELN_X;
        int mausYmenu = mouseY / Einstellungen.LAENGE_KACHELN_Y;
        int mausXkachel = mouseX / Einstellungen.LAENGE_KACHELN_X;
        int mausYkachel = mouseY / Einstellungen.LAENGE_KACHELN_Y;

        if (mouseButton == LEFT) {
            if (mouseX > SpielfeldBreite && mouseX <= SpielfeldBreite + menuBreite * Einstellungen.LAENGE_KACHELN_X) {
                if (mouseY < menuHoehe * Einstellungen.LAENGE_KACHELN_Y) {
                    aktuellesMovable = null;
                    aktuelleKachel = getMenukacheliKachel(mausYmenu, mausXmenu, this.menuArrayKacheln);
                }
            } else if (mouseX > SpielfeldBreite + menuBreite && mouseX <= SpielfeldBreite + (2 * menuBreite) * Einstellungen.LAENGE_KACHELN_X){
                if (mouseY < 12 * Einstellungen.LAENGE_KACHELN_Y){
                    aktuelleKachel = null;
                    aktuellesMovable = getMenukacheliMovable(mausYmenu, mausXmenu - 1, menuArrayMovables);
                }
            }
            if (mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe && (aktuelleKachel != null || aktuellesMovable != null)) {
                if (aktuelleKachel != null){
                    tileMap.setKachel(aktuelleKachel, mausYkachel, mausXkachel);
                } else if (aktuellesMovable != null){
                    aktuellesMovable.setPosition(mouseX,mouseY);
                    addMovablezuLevel(aktuellesMovable);
                }
            }
            buttonAction(mouseY, mouseX);

        } else if (mouseButton == RIGHT){
            aktuelleKachel = null;
            aktuellesMovable = null;
        }
    }

    /**
     * Fügt das übergebene Movable im aktuellen Level in die Instanz von Spielwelt hinzu.
     * Falls ein neues Movable im Spiel implementiert wird, muss dieses hier auch mit implementiert werden.
     * @param movable Das zu speichernde Movable
     */
    private void addMovablezuLevel(IMovable movable){
        Movable tempMovable = null;
        int posX = movable.getPosX();
        int posY = movable.getPosY();
        Richtung richtung = movable.getAusrichtung();
        if (movable instanceof Spielfigur){
            tempMovable = new Spielfigur(posX, posY, richtung);
        } else if (movable instanceof Apfel){
            tempMovable = new Apfel(posX, posY);
        } else if (movable instanceof Gold){
            tempMovable = new Gold(posX, posY);
        } else if (movable instanceof Heiltrank){
            tempMovable = new Heiltrank(posX, posY);
        } else if (movable instanceof Levelende){
            tempMovable = new Levelende(posX, posY);
        } else if (movable instanceof Mango){
            tempMovable = new Mango(posX, posY);
        } else if (movable instanceof Schwert){
            tempMovable = new Schwert(posX, posY, 1);
        } else if (movable instanceof DornPflanze){
            tempMovable = new DornPflanze(posX, posY, this.tileMap);
        } else if (movable instanceof FeuerMonster){
            tempMovable = new FeuerMonster(posX, posY, this.tileMap,this.spielsteuerung,Richtung.N,1,FeuerModus.RANDOM);
            //TODO: Für Feuermonster die Feuerrate und den Feuermodus noch änderbar machen
        } else if (movable instanceof Geist){
            tempMovable = new Geist(posX, posY, this.tileMap);
        } else if (movable instanceof Zombie){
            tempMovable = new Zombie(posX, posY, this.tileMap,Richtung.N,this.spielsteuerung,LaufModus.DEFAULT);
        } else if (movable instanceof Stern){
            tempMovable = new Stern(posX, posY);
        }
        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
        level.addPosition(tempMovable);
    }

    /**
     * Zeigt im Fensterrahmen den Namen des Leveleditors an mit akuteller Levelnummer
     * @param LevelNr Aktuelle Levelnummer
     */
    public void anzeigeTitelLevel(int LevelNr){
        surface.setTitle(Einstellungen.TITLE + "   Leveleditor Level: " + Integer.toString(LevelNr));
    }

    /**
     * Zeichnet das Menü mit den verfügbaren Kacheln am rechten Rand.
     * @param menuArray Das Menuarray mit den verschiedenen Kachelarten
     */
    private void zeichneAssetMenu(IKachel[][] menuArray){
        int mapAussenX = SpielfeldBreite;
        int mapAussenY = 0;
        for (IKachel[] iKachels : menuArray) {
            iKachels[0].zeichne(this, mapAussenX, mapAussenY);
            mapAussenY += Einstellungen.LAENGE_KACHELN_Y;

            if (mapAussenY >= SpielfeldHoehe) {
                mapAussenY = 0;
                mapAussenX += Einstellungen.LAENGE_KACHELN_X;
            }
        }
    }

    /**
     * Zeichnet das Menü mit den verfügbaren Movables am rechten Rand nach den Kacheln.
     * Die Spielfigur wird als reines Bild dargestellt, da sonst der Lebensbalken mitgezeichnet werden muss.
     * @param /app Applet auf dem gezeichnet werden soll
     * @param menuArray Das Menuarray mit den verschiedenen Movablearten
     */
    private void zeichneMovableMenu(IMovable[][] menuArray){
        int mapAussenX = SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X + Einstellungen.LAENGE_KACHELN_X/2;
        int mapAussenY = Einstellungen.LAENGE_KACHELN_Y/2;
        this.pushStyle();
        this.imageMode(CENTER);
        this.ellipseMode(CENTER);
        this.rectMode(CENTER);
        for (IMovable[] iMovables : menuArray) {
            iMovables[0].setPosition(mapAussenX, mapAussenY);
            if (iMovables[0] instanceof Spielfigur) {
                this.image((PImage) getImages().get(iMovables[0].getClass().toString()), mapAussenX, mapAussenY, Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
            } else {
                iMovables[0].zeichne(this);
            }
            mapAussenY += Einstellungen.LAENGE_KACHELN_Y;

            if (mapAussenY >= SpielfeldHoehe) {
                mapAussenY = 0;
                mapAussenX += Einstellungen.LAENGE_KACHELN_X;
            }
        }
        this.popStyle();
    }

    /**
     * Gibt die Kachel an der übergebenen Position im Array zurück
     * @param mausY Position in Y Richtung
     * @param mausX Position in X Richtung
     * @param iKachel Das Array mit den Kacheln
     * @return Kachel an Stelle [x][y]
     */
    private IKachel getMenukacheliKachel(int mausY, int mausX, IKachel[][] iKachel){

        return iKachel[mausY][mausX];
    }

    /**
     * Gibt das Movable an der übergebenen Position im Array zurück
     * @param mausY Position in Y Richtung
     * @param mausX Position in X Richtung
     * @param iMovable Das Array mit den Movables
     * @return Movable an Stelle [x][y]
     */
    private IMovable getMenukacheliMovable (int mausY, int mausX, IMovable[][] iMovable){

        return iMovable[mausY][mausX];
    }

    /**
     * Falls es sich bei der aktuellen Mausauswahl um eine Kachel handelt, wird diese hier gezeichnet
     * @param /app Applet auf dem gezeichnet werden soll
     * @param aktuelleKachel Die aktuelle Kachel
     * @param mausX Position der Maus in X-Richtung
     * @param mausY Position der Maus in Y-Richtung
     */
    private void zeichneMausKachel(IKachel aktuelleKachel, int mausX, int mausY){
        if (aktuelleKachel != null) {
            pushStyle();
            this.rectMode(PConstants.CENTER);
            this.imageMode(PConstants.CENTER);
            aktuelleKachel.zeichne(this, mausX, mausY);
            popStyle();
        }
    }

    /**
     * Falls es sich bei der aktuellen Mausauswahl um ein Movable handelt, wird dieses hier gezeichnet
     * @param /app Applet auf dem gezeichnet werden soll
     * @param aktuellesMovable  Das aktuelle Movable
     * @param mausX mausX Position der Maus in X-Richtung
     * @param mausY mausY Position der Maus in Y-Richtung
     */
    private void zeichneMausMovable(IMovable aktuellesMovable, int mausX, int mausY){
        if (aktuellesMovable != null) {
            pushStyle();
            this.rectMode(CENTER);
            this.imageMode(CENTER);
            aktuellesMovable.setPosition(mausX,mausY);

            if(aktuellesMovable instanceof Spielfigur){
                this.image((PImage) getImages().get(aktuellesMovable.getClass().toString()), mausX, mausY, Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
            } else {
                aktuellesMovable.zeichne(this);
            }
            popStyle();
        }
    }

    /**
     * Zeichnet die Tilemap des Leveleditors
     */
    private void zeichneTileMap(){
        tileMap.zeichne(this);
    }

    /**
     * Zeichnet die Movalbes, welche auf der Tilemap platziert wurden
     */
    private void zeichneMovables(){
        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
        int anzahlPos = level.getPositionen().size();
        IMovable movable;
        for (int i = 0; i < anzahlPos; i++){
            movable = level.getPositionen().get(i);

            if(movable instanceof Spielfigur){
                this.image((PImage) getImages().get(movable.getClass().toString()), movable.getPosX(), movable.getPosY(), Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
            } else {
                movable.zeichne(this);
            }
        }

    }

    /**
     * Zeichnet die Buttons/Schaltflächen
     */
    private void zeichneButtons(){

        exitButton.zeichne(this,0, SpielfeldHoehe);
        speichernButton.zeichne(this, breiteExit, SpielfeldHoehe);
        ladeButton.zeichne(this, breiteSpeichern, SpielfeldHoehe);
        leerenButton.zeichne(this, breiteLaden, SpielfeldHoehe);
        levelButton.zeichne(this, breiteLeeren, SpielfeldHoehe);
        siedlungButton.zeichne(this, breiteLevel, SpielfeldHoehe);
        bildZurueck.zeichne(this, breiteSiedlung, SpielfeldHoehe);
        bildVor.zeichne(this, breiteZurueck, SpielfeldHoehe);
    }

    /**
     * Zeichnet den Levelcounter und die aktuelle Levelnummer
     */
    private void zeichneCounts() {
        this.pushStyle();
        this.textSize(12);
        this.fill(0, 0, 0);
        this.text("Levelanzahl: " + levelCount, 20, 25);
        this.text("LevelNr: " + levelNr, 20, 40);
        this.popStyle();
    }

    /**
     * Zeichnet die Fehlermeldungen falls beim Speichern die Speicherkriterien nicht erfüllt werden
     * Speicherhinweis  1: Kein Levelausgang
     *                  2: Keine Spielfigur
     *                  3: Mehr als eine Spielfigur
     */
    private void zeichneFehler(){
        this.pushStyle();
        this.textSize(16);
        this.fill(200, 20, 0);
        if (speicherHinweis == 1 && (speicherHinweisLevel != 0)) {
            this.text("Kein Levelende/-ausgang in Level: " + speicherHinweisLevel, 130, 25);
        } else if (speicherHinweis == 2 && (speicherHinweisLevel != 0)) {
            this.text("Keine Spielfigur in Level: " + speicherHinweisLevel, 130, 25);
        } else if (speicherHinweis == 3 && (speicherHinweisLevel != 0)) {
            this.text("Mehr als eine Spielfigur in Level: " + speicherHinweisLevel, 130, 25);
        }
        this.popStyle();
    }

    /**
     * Führt die Aktionen aus, nachdem ein Button gedrückt wurde
     * @param y Position der Maus in y-Richtung
     * @param x Position der Maus in x-Richtung
     */
    private void buttonAction(int y, int x){
        if (y > SpielfeldHoehe && y < exitButton.getHoehe() + SpielfeldHoehe){
            if (x < breiteExit){
                exitLeveleditor();

            } else if (x > breiteExit && x < breiteSpeichern){
                speichereSpielwelt();

            } else if (x > breiteSpeichern && x < breiteLaden){
                ladeSpielweltLeveleditor();

            } else if (x > breiteLaden && x < breiteLeeren){
                leereLevel();

            } else if (x > breiteLeeren && x < breiteLevel){
                neuesLevel();

            } else if (x > breiteLevel && x < breiteSiedlung){
                neueSiedlung();

            } else if (x > breiteSiedlung && x < breiteZurueck){
                springeZurueck();

            } else if (x > breiteZurueck && x < breiteVor){
                springeVor();
            }
        }
    }

    /**
     * Schließt den Leveleditor
     */
    private void exitLeveleditor() {
        System.exit(0);
    }

    /**
     * Speichert die aktulle Spielwelt und resettet die Anzeige
     */
    private void speichereSpielwelt() {
        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
        level.setTilemap(tileMap);
        pruefeSpeicherkriterien();
        if (speicherHinweis == 0){
            levelCount = 1;
            levelNr = 1;
            System.out.println("speichern");
            tileMap = new TileMap();
            dateiService.speichereSpielwelt(spielwelt,"spielwelt.json");
            spielwelt.removeSzenen();
        }
    }

    /**
     * Lädt die Spielwelt aus "spielwelt.json" in die Anzeige
     */
    private void ladeSpielweltLeveleditor() {
        System.out.println("laden");
        speicherHinweis = 0;
        levelNr = 1;
        spielwelt = dateiService.ladeSpielwelt("spielwelt.json");
        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
        tileMap = (TileMap) level.getTileMap();
        levelCount = spielwelt.getSzenenanzahl();
    }

    /**
     * Leert das aktuelle Level
     */
    private void leereLevel() {
        System.out.println("leeren");
        //Java hat ja einen Garbage Collector. Also wäre auch folgendes möglich.
        tileMap = new TileMap();
        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
        level.clearPosition();
        //Anstatt alle Felder mit Wiese zu nullen wie unten
        //leereTilemap();
    }

    /**
     * Erstellt ein neues Level
     */
    private void neuesLevel() {
        System.out.println("level");
        ILevel level = new DummyLevel();
        ILevel iLevel1 = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
        iLevel1.setTilemap(tileMap);
        spielwelt.addSzene(level, levelNr);

        ILevel iLevel2 = (ILevel) this.spielwelt.getSzene(levelNr);
        iLevel2.clearPosition();
        tileMap = new TileMap();
        iLevel2.setTilemap(tileMap);
        levelCount++;
        levelNr++;
        anzeigeTitelLevel(levelNr);
    }

    /**
     * Erstellt eine neue Siedlung
     */
    private void neueSiedlung() {
        //TODO: Siedlungen müssen noch implementiert werden
        System.out.println("siedlung");
    }

    /**
     * Springt in der Spielwelt ein Level zurück
     */
    private void springeZurueck() {
        if (levelCount >= 2 && levelNr > 1){
            ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
            level.setTilemap(tileMap);

            levelNr -= 1;
            level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
            tileMap = (TileMap) level.getTileMap();
        }
        System.out.println("zurück");
    }

    /**
     * Springt in der Spielwelt ein Level vor
     */
    private void springeVor() {
        if (levelCount > levelNr){
            ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
            level.setTilemap(tileMap);

            levelNr++;
            level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
            tileMap = (TileMap) level.getTileMap();
        }
        System.out.println("vor");
    }

    /**
     * Prüft den aktuellen Zustand der Spielwelt, ob jedes Level nur eine Spielfigur und ein/mehrere Levelausgänge besitzt.
     * Falls eine der Bedingungen nicht erfüllt ist, wird der Fehler mithilfe der speicherHinweis Variablen ausgegeben.
     */
    private void pruefeSpeicherkriterien() {
        int szenenAnzahl = spielwelt.getSzenenanzahl();

        for (int i = 0; i < szenenAnzahl; i++){
            speicherHinweisLevel = i + 1;
            ILevel level = (ILevel) this.spielwelt.getSzene(i);
            ITileMap pruefTilemap = level.getTileMap();
            int sizeKachelarten = pruefTilemap.getKachelarten().size();
            int sizeMovables = level.getPositionen().size();

            pruefeLevelausgang(pruefTilemap, sizeKachelarten);

            pruefeLevelende(i, sizeMovables);

            pruefeSpielfigur(i, sizeMovables);

            if (speicherHinweis != 0){
                i = szenenAnzahl;
            }
        }
    }

    private void pruefeSpielfigur(int szenenNr, int sizeMovables) {
        int anzahlSpielfiguren = 0;

        if (speicherHinweis == 0) {
            for (int j = 0; j < sizeMovables; j++) {
                ILevel level = (ILevel) this.spielwelt.getSzene(szenenNr);
                IMovable movable = level.getPositionen().get(j);
                if (movable instanceof Spielfigur) {
                    speicherHinweis = 0;
                    anzahlSpielfiguren++;
                }
            }
            if (anzahlSpielfiguren > 1) {
                speicherHinweis = 3;
            } else if (anzahlSpielfiguren == 0){
                speicherHinweis = 2;
            }
        }
    }

    private void pruefeLevelende(int szenenNr, int sizeMovables) {
        if (speicherHinweis != 0){
            for (int j = 0; j < sizeMovables; j++) {
                ILevel level = (ILevel) this.spielwelt.getSzene(szenenNr);
                IMovable movable = level.getPositionen().get(j);
                if (movable instanceof Levelende) {
                    speicherHinweis = 0;
                    j = sizeMovables;
                } else {
                    speicherHinweis = 1;
                }
            }
        }
    }

    private void pruefeLevelausgang(ITileMap pruefTilemap, int sizeKachelarten) {
        for (int j = 0; j < sizeKachelarten; j++){
            if (pruefTilemap.getKachelarten().get(j) instanceof Levelausgang){
                speicherHinweis = 0;
                j = sizeKachelarten;
            } else {
                speicherHinweis = 1;
            }
        }
    }

    @Override
    public Dictionary getImages() {
        return images;
    }

    @Override
    public void setImages(Dictionary images) {
        this.images = images;
    }
}