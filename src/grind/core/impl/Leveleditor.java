package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.*;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.impl.*;
import grind.movables.monster.*;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.Richtung;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;


public class Leveleditor extends PApplet {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;
    private final Spielsteuerung spielsteuerung;

    private int menuBreite = 1;
    private int menuHoehe = 8;
    private int breiteExit;
    private int breiteSpeichern;
    private int breiteLaden;
    private int breiteLeeren;
    private int breiteLevel;
    private int breiteSiedlung;
    private int breiteVor;
    private int breiteZurueck;
    private int levelCount = 1;
    private int levelNr = 1;
    private int speicherHinweisLevel;
    private int speicherHinweis = 1;

    // private ITileMap tileMap;

    private int Tastendruck;
    private String fTaste;
    private boolean pressed = false;
    private boolean levelBeendet = false;
    private DateiService dateiService;
    private ISpielwelt spielwelt;
    private ISpielmodell spielmodell;
    private TileMap tileMap;
    private IKachel[][] menuArrayKacheln;
    private IMovable[][] menuArrayMovables;
    private IKachel aktuelleKachel;
    private IMovable aktuellesMovable;
    private Button exitButton;
    private Button speichernButton;
    private Button ladeButton;
    private Button leerenButton;
    private Button levelButton;
    private Button siedlungButton;
    private Button bildVor;
    private Button bildZurueck;
    private PImage spielfigurBild;


    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Leveleditor(){
        Einstellungen.LAENGE_KACHELN_Y = 30;
        Einstellungen.LAENGE_KACHELN_X = 30;
        this.menuArrayKacheln = new IKachel[menuHoehe][menuBreite];
        this.menuArrayMovables = new IMovable[11][1];
        this.tileMap = new TileMap();
        this.spielwelt = new DummySpielwelt();
        this.spielsteuerung = new Spielsteuerung();
        //this.spielmodell = new Spielmodell(this.spielwelt,this.spielsteuerung);
        this.dateiService = new DateiService();
//        this.Spieler = (Spielfigur) spielmodell.getFigur();
//        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
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
        this.spielfigurBild = new PImage();

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
        this.menuArrayMovables[1][0] = new Schwert(0,0,1);
        this.menuArrayMovables[2][0] = new Mango(0,0);
        this.menuArrayMovables[3][0] = new Levelende(0,0,40);
        this.menuArrayMovables[4][0] = new Heiltrank(0,0);
        this.menuArrayMovables[5][0] = new Gold(0,0);
        this.menuArrayMovables[6][0] = new Apfel(0,0);
        this.menuArrayMovables[7][0] = new DornPflanze(0,0,this.tileMap);
        this.menuArrayMovables[8][0] = new FeuerMonster(0,0,this.tileMap,this.spielsteuerung,Richtung.N,1, FeuerModus.RANDOM);
        this.menuArrayMovables[9][0] = new Geist(0,0,this.tileMap);
        this.menuArrayMovables[10][0] = new Zombie(0,0,this.tileMap);



/*
// Inhalt des Spielsteuerung Konstruktors
        this.spielmodell = new Spielmodell(new DummySpielwelt(),this);
        // this.spielmodell.betreteSzene(1);
        this.spielmodell.betreteSzene(this.spielmodell.getSzeneNr());

        this.Spieler = (Spielfigur) spielmodell.getFigur();
        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
        // this.tileMap = (ITileMap) spielmodell.getTileMap();
        */

    }




    public ISpielmodell getSpielmodell() {
        return spielmodell;
    }

    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }

//    public int getSpielfeldBreite() {
//        return SpielfeldBreite;
//    }
//
//    public int getSpielfeldHoehe() {
//        return SpielfeldHoehe;
//    }


    /**
     * Methode settings, setzt Spielfeldgröße auf die in den Einstellungen gesetzten Parameter.
     */
    @Override
    public void settings() {
        SpielfeldBreite = Einstellungen.LAENGE_KACHELN_X * Einstellungen.ANZAHL_KACHELN_X;
        SpielfeldHoehe = Einstellungen.LAENGE_KACHELN_Y * Einstellungen.ANZAHL_KACHELN_Y;
        //fullScreen();
        //size(displayWidth,displayHeight);
        size(SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X*2,SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y);
    }

    /**
     * Methode setup, lädt Darstellung der Spielfigur.
     */
    @Override
    public void setup() {
        imageMode(PConstants.CORNER);
        spielfigurBild = loadImage("SpielfigurOhneWaffe.jpg");
        anzeigeTitelLevel(levelNr);
//        Spieler.ladeIMGSpielfigur(this);
//        anzeigeTitelLevel(this.spielmodell.getSzeneNr()+1);
    }

    /**
     * Methode draw, zeichnet alle sichtbare Elemente.
     * (Spielfigur, Lebensenergie, Kontostand, Tilemap)
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

    private void zeichne() {
        //this.spielmodell.getTileMap().zeichne(this);
        zeichneTileMap(this);
        zeichneMovables(this);
        zeichneAssetMenu(this, this.menuArrayKacheln);
        zeichneMovableMenu(this, this.menuArrayMovables);
        zeichneMausKachel(this, aktuelleKachel, mouseX, mouseY);
        zeichneMausMovable(this,aktuellesMovable,mouseX,mouseY);
        zeichneButtons(this);
        zeichneCounts(this);
        zeichneFehler(this);
    }

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
                if (mouseY < 11 * Einstellungen.LAENGE_KACHELN_Y){
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

//    public boolean ueberpruefeLevelende() {
//        //Abfrage ob der aktuelle Standpunkt der Spielfigur eine Kachel vom Typ Levelausgang ist.
//        pruefeLevelausgang();
//
//        for (int i=0; i<spielmodell.getFigur().getInventar().size();i++){
//            if (spielmodell.getFigur().getInventar().size()>=i+1) {
//                if (spielmodell.getFigur().getInventar().get(i) instanceof Levelende) {
//                    System.out.println("Levelende Bedingung wurde gefunden");
//                    levelBeendet = true;
//                    spielmodell.getFigur().getInventar().remove(i);
//                    break;
//                }
//            }
//        }
//        return levelBeendet;
//    }
//    /**
//     * @author LuHe20
//     * Prüft, ob die aktuelle Kachel auf der sich der Spieler befindet,
//     * eine Kachel des Typs: Levelausgang ist.
//     * @return levelBeendet
//     */
//    private boolean pruefeLevelausgang() {
//        int spielerPosX = spielmodell.getFigur().getPosY()/Einstellungen.LAENGE_KACHELN_Y;
//        int spielerPosY = spielmodell.getFigur().getPosX()/Einstellungen.LAENGE_KACHELN_X;
//        IKachel spielerKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(spielerPosX,spielerPosY);
//        if (spielerKachel instanceof Levelausgang){
////            System.out.println(spielerKachel);
//            levelBeendet = true;
//        }
//        return levelBeendet;
//    }

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
            tempMovable = new Levelende(posX, posY, Einstellungen.GROESSE_LEVELENDE);
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
            tempMovable = new Zombie(posX, posY, this.tileMap);
        }
        this.spielwelt.getSzene(this.levelNr-1).getLevel().addPosition(tempMovable);
    }

    public void anzeigeTitelLevel(int LevelNr){
        frame.setTitle(Einstellungen.TITLE + "   Leveleditor Level: " + Integer.toString(LevelNr));
    }

    private void zeichneAssetMenu(PApplet app, IKachel[][] menuArray){
        int mapAussenX = SpielfeldBreite;
        int mapAussenY = 0;
        for (int i = 0; i < menuArray.length; i++){
            menuArray[i][0].zeichne(app, mapAussenX,mapAussenY);
            mapAussenY += Einstellungen.LAENGE_KACHELN_Y;

            if (mapAussenY >= SpielfeldHoehe){
                mapAussenY = 0;
                mapAussenX += Einstellungen.LAENGE_KACHELN_X;
            }
        }
    }

    private void zeichneMovableMenu(PApplet app, IMovable[][] menuArray){
        int mapAussenX = SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X + Einstellungen.LAENGE_KACHELN_X/2;
        int mapAussenY = Einstellungen.LAENGE_KACHELN_Y/2;
        app.pushStyle();
        app.imageMode(CENTER);
        app.ellipseMode(CENTER);
        app.rectMode(CENTER);
        for (int i = 0; i < menuArray.length; i++){
            menuArray[i][0].setPosition(mapAussenX,mapAussenY);
            if(menuArray[i][0] instanceof Spielfigur){
                app.image(spielfigurBild, mapAussenX, mapAussenY, Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
            } else {
                menuArray[i][0].zeichne(app);
            }
            mapAussenY += Einstellungen.LAENGE_KACHELN_Y;

            if (mapAussenY >= SpielfeldHoehe){
                mapAussenY = 0;
                mapAussenX += Einstellungen.LAENGE_KACHELN_X;
            }
        }
        app.popStyle();
    }

    private IKachel getMenukacheliKachel(int mausY, int mausX, IKachel[][] iKachel){

        return iKachel[mausY][mausX];
    }

    private IMovable getMenukacheliMovable (int mausY, int mausX, IMovable[][] iMovable){

        return iMovable[mausY][mausX];
    }

    private void zeichneMausKachel(PApplet app, IKachel aktuelleKachel, int mausX, int mausY){
        if (aktuelleKachel != null) {
            pushStyle();
            app.rectMode(PConstants.CENTER);
            app.imageMode(PConstants.CENTER);
            aktuelleKachel.zeichne(app, mausX, mausY);
            popStyle();
        }
    }

    private void zeichneMausMovable(PApplet app, IMovable aktuellesMovable, int mausX, int mausY){
        if (aktuellesMovable != null) {
            pushStyle();
            app.rectMode(CENTER);
            app.imageMode(CENTER);
            aktuellesMovable.setPosition(mausX,mausY);

            if(aktuellesMovable instanceof Spielfigur){
                app.image(spielfigurBild, mausX, mausY, Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
            } else {
                aktuellesMovable.zeichne(app);
            }
            popStyle();
        }
    }


    private void zeichneTileMap(PApplet app){
        tileMap.zeichne(app);
    }

    private void zeichneMovables(PApplet app){
        int anzahlPos = spielwelt.getSzene(levelNr-1).getLevel().getPositionen().size();
        IMovable movable;
        for (int i = 0; i < anzahlPos; i++){
            movable = spielwelt.getSzene(levelNr-1).getLevel().getPositionen().get(i);

            if(movable instanceof Spielfigur){
                app.image(spielfigurBild, movable.getPosX(), movable.getPosY(), Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
            } else {
                movable.zeichne(app);
            }
        }

    }

    private void zeichneButtons(PApplet app){

        exitButton.zeichne(app,0, SpielfeldHoehe);
        speichernButton.zeichne(app, breiteExit, SpielfeldHoehe);
        ladeButton.zeichne(app, breiteSpeichern, SpielfeldHoehe);
        leerenButton.zeichne(app, breiteLaden, SpielfeldHoehe);
        levelButton.zeichne(app, breiteLeeren, SpielfeldHoehe);
        siedlungButton.zeichne(app, breiteLevel, SpielfeldHoehe);
        bildZurueck.zeichne(app, breiteSiedlung, SpielfeldHoehe);
        bildVor.zeichne(app, breiteZurueck, SpielfeldHoehe);
    }

    private void zeichneCounts(PApplet app) {
        app.pushStyle();
        app.textSize(12);
        app.fill(0, 0, 0);
        app.text("Levelanzahl: " + levelCount, 20, 25);
        app.text("LevelNr: " + levelNr, 20, 40);
        app.popStyle();
    }

    private void zeichneFehler(PApplet app){
        app.pushStyle();
        app.textSize(16);
        app.fill(200, 20, 0);
        if (speicherHinweis == 1 && (speicherHinweisLevel != 0)) {
            app.text("Kein Levelende/-ausgang in Level: " + speicherHinweisLevel, 130, 25);
        } else if (speicherHinweis == 2 && (speicherHinweisLevel != 0)) {
            app.text("Keine Spielfigur in Level: " + speicherHinweisLevel, 130, 25);
        } else if (speicherHinweis == 3 && (speicherHinweisLevel != 0)) {
            app.text("Mehr als eine Spielfigur in Level: " + speicherHinweisLevel, 130, 25);
        }
        app.popStyle();
    }

    private void buttonAction(int y, int x){
        if (y > SpielfeldHoehe && y < exitButton.getHoehe() + SpielfeldHoehe){
            if (x < breiteExit){
                System.exit(0);
            } else if (x > breiteExit && x < breiteSpeichern){
                spielwelt.getSzene(levelNr-1).getLevel().setTilemap(tileMap);
                pruefeSpeicherkriterien();
                if (speicherHinweis == 0){
                    levelCount = 1;
                    levelNr = 1;
                    System.out.println("speichern");
                    tileMap = new TileMap();
                    dateiService.speichereSpielwelt(spielwelt,"spielwelt.json");
                    spielwelt.removeSzenen();
                }
            } else if (x > breiteSpeichern && x < breiteLaden){
                System.out.println("laden");
                levelNr = 1;
                spielwelt = dateiService.ladeSpielwelt("spielwelt.json");
                tileMap = (TileMap) spielwelt.getSzene(levelNr - 1).getLevel().getTileMap();
                levelCount = spielwelt.getSzenenanzahl();
            } else if (x > breiteLaden && x < breiteLeeren){
                System.out.println("leeren");
                //Java hat ja einen Garbage Collector. Also wäre auch folgendes möglich.
                tileMap = new TileMap();
                spielwelt.getSzene(levelNr-1).getLevel().clearPosition();
                //Anstatt alle Felder mit Wiese zu nullen wie unten
                //leereTilemap();
            } else if (x > breiteLeeren && x < breiteLevel){
                System.out.println("level");
                ILevel level = new DummyLevel();
                spielwelt.getSzene(levelNr-1).getLevel().setTilemap(tileMap);
                spielwelt.addSzene(level, levelNr);
                spielwelt.getSzene(levelNr).getLevel().clearPosition();
                tileMap = new TileMap();
                levelCount++;
                levelNr++;
                anzeigeTitelLevel(levelNr);
            } else if (x > breiteLevel && x < breiteSiedlung){
                System.out.println("siedlung");
            } else if (x > breiteSiedlung && x < breiteZurueck){
                if (levelCount > 2 && levelNr > 1){
                    levelNr -= 1;
                    tileMap = (TileMap) spielwelt.getSzene(levelNr-1).getLevel().getTileMap();
                }
                System.out.println("zurück");
            } else if (x > breiteZurueck && x < breiteVor){
                if (levelCount > levelNr){
                    levelNr++;
                    tileMap = (TileMap) spielwelt.getSzene(levelNr-1).getLevel().getTileMap();
                }
                System.out.println("vor");
            }
        }
    }

    private void pruefeSpeicherkriterien() {
        int szenenAnzahl = spielwelt.getSzenenanzahl();
        for (int i = 0; i < szenenAnzahl; i++){
            speicherHinweisLevel = i + 1;
            ITileMap pruefTilemap = spielwelt.getSzene(i).getLevel().getTileMap();
            int sizeKachelarten = pruefTilemap.getKachelarten().size();
            int sizeMovables = spielwelt.getSzene(i).getLevel().getPositionen().size();
            int anzahlSpielfiguren = 0;
            for (int j = 0; j < sizeKachelarten; j++){
                if (pruefTilemap.getKachelarten().get(j) instanceof Levelausgang){
                    speicherHinweis = 0;
                    j = sizeKachelarten;
                } else {
                    speicherHinweis = 1;
                }
            }
            if (speicherHinweis != 0){
                for (int j = 0; j < sizeMovables; j++) {
                    IMovable movable = spielwelt.getSzene(i).getLevel().getPositionen().get(j);
                    if (movable instanceof Levelende) {
                        speicherHinweis = 0;
                        j = sizeMovables;
                    } else {
                        speicherHinweis = 1;
                    }
                }
            }
            if (speicherHinweis == 0) {
                for (int j = 0; j < sizeMovables; j++) {
                    IMovable movable = spielwelt.getSzene(i).getLevel().getPositionen().get(j);
                    if (movable instanceof Spielfigur) {
                        speicherHinweis = 0;
                        anzahlSpielfiguren++;
                    } else {
                        speicherHinweis = 2;
                    }
                }
                if (anzahlSpielfiguren > 1) {
                    speicherHinweis = 3;
                } else if (anzahlSpielfiguren == 0){
                    speicherHinweis = 2;
                }
            }
            if (speicherHinweis != 0){
                i = szenenAnzahl;
            }
        }
    }
}