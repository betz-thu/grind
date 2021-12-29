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
import grind.util.Richtung;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;


public class Leveleditor extends PApplet {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;

    private int menuBreite = 1;
    private int menuHoehe = 8;
    private int breiteExit;
    private int breiteSpeichern;
    private int breiteLaden;
    private int breiteLeeren;
    private int breiteLevel;
    private int breiteSiedlung;
    private int levelCount = 0;
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
    private IKachel aktuelleKachel;
    private Button exitButton;
    private Button speichernButton;
    private Button ladeButton;
    private Button leerenButton;
    private Button levelButton;
    private Button siedlungButton;

    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Leveleditor(){
        this.menuArrayKacheln = new IKachel[menuHoehe][menuBreite];
        this.tileMap = new TileMap();
        this.spielwelt = new DummySpielwelt();
        //this.spielsteuerung = new Spielsteuerung();
        //this.spielmodell = new Spielmodell(this.spielwelt,this.spielsteuerung);
        this.dateiService = new DateiService();
//        this.Spieler = (Spielfigur) spielmodell.getFigur();
//        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
        this.aktuelleKachel = null;
        this.exitButton = new Button(0);
        this.speichernButton = new Button(1);
        this.ladeButton = new Button(2);
        this.leerenButton = new Button(3);
        this.levelButton = new Button(4);
        this.siedlungButton = new Button(5);

        this.breiteExit = exitButton.getBreite();
        this.breiteSpeichern = exitButton.getBreite() + speichernButton.getBreite();
        this.breiteLaden = exitButton.getBreite() + speichernButton.getBreite() + ladeButton.getBreite();
        this.breiteLeeren = exitButton.getBreite() + speichernButton.getBreite() + ladeButton.getBreite() + leerenButton.getBreite();
        this.breiteLevel = exitButton.getBreite() + speichernButton.getBreite() + ladeButton.getBreite() + leerenButton.getBreite() + levelButton.getBreite();
        this.breiteSiedlung = exitButton.getBreite() + speichernButton.getBreite() + ladeButton.getBreite() + leerenButton.getBreite() + levelButton.getBreite() + siedlungButton.getBreite();


        //Befüllen des Menuarrays mit den Kachelarten
        this.menuArrayKacheln[0][0] = new Baum();
        this.menuArrayKacheln[1][0] = new DummyHindernis();
        this.menuArrayKacheln[2][0] = new Fels();
        this.menuArrayKacheln[3][0] = new Holzbrücke();
        this.menuArrayKacheln[4][0] = new Levelausgang();
        this.menuArrayKacheln[5][0] = new Wasser();
        this.menuArrayKacheln[6][0] = new Weg();
        this.menuArrayKacheln[7][0] = new Wiese();




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

    public int getSpielfeldBreite() {
        return SpielfeldBreite;
    }

    public int getSpielfeldHoehe() {
        return SpielfeldHoehe;
    }


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

    /**
     * @MEGAtroniker
     * Die Methode springt zur nächsten Szene durch das Betätigen der Taste "F12"
     */
    private void szeneUeberspringen() {
        abfrageFTasten();
    }

    /**
     * @author LuHe20
     * Cheat für das Überspringen einer Szene mit F12.
     * Speichern der Spielwelt mit F11
     * Laden der Spielwelt mit F10
     */
    private void abfrageFTasten() {
        //F12 neue Szene
        if (keyPressed && !pressed) {
            if (keyCode == 123) {
                pressed = true;
                fTaste = "F12";
            } else if(keyCode == 122){
                pressed = true;
                fTaste = "F11";
            } else if(keyCode == 121){
                pressed = true;
                fTaste = "F10";
            }
        } else if(!keyPressed && pressed){
            switch (fTaste) {
                case "F12":

                    levelBeendet = true;

                    break;
                case "F11":

                    speichereSpielwelt();

                    System.out.println("F11");

                    break;
                case "F10":

                    ISpielwelt welt;
                    welt = ladeSpielwelt();
                    this.spielmodell.setSpielwelt(welt);
                    spielmodell.setSzeneNr(0);
                    spielmodell.betreteSzene(spielmodell.getSzeneNr());

                    System.out.println("F10");

                    break;
            }

            pressed = false;
        }
    }

    private void aktualisiere() {
        spielmodell.entferneToteMonster();
        spielmodell.bewege();
        //levelBeendet = ueberpruefeLevelende();
        starteNeueSzene();
    }

    /**
     * @author LuHe20
     * Startet, wenn levelBeendet Bedingung wahr ist, die nächste Szene.
     */
    private void starteNeueSzene() {
        if(levelBeendet){
            levelBeendet = false;
            spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
            anzeigeTitelLevel(spielmodell.getSzeneNr() + 1);
        }
    }

    private void zeichne() {
        //this.spielmodell.getTileMap().zeichne(this);
        zeichneTileMap(this);
        zeichneAssetMenu(this, this.menuArrayKacheln);
//        zeichneMovableMenu(this, this.menuArrayMovables);
        zeichneMausKachel(this, aktuelleKachel, mouseX, mouseY);
        zeichneButtons(this);
        zeichneCounts(this);
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
                    aktuelleKachel = getMenukachel(mausYmenu, mausXmenu, this.menuArrayKacheln);
                }
            }
            if (mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe && (aktuelleKachel != null)) {
                tileMap.setKachel(aktuelleKachel, mausYkachel, mausXkachel);
            }

            buttonAction(mouseY, mouseX);

        } else if (mouseButton == RIGHT){
            aktuelleKachel = null;
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

    private IKachel getMenukachel (int mausY, int mausX, IKachel[][] iKachel){

        return iKachel[mausY][mausX];
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

    private void zeichneTileMap(PApplet app){
        tileMap.zeichne(app);
    }

    private void zeichneCounts(PApplet app) {
        app.pushStyle();
        app.textSize(12);
        app.fill(0, 0, 0);
        app.text("Level: " + levelCount, 20, 25);
        app.popStyle();
    }

    private void buttonAction(int y, int x){
        if (y > SpielfeldHoehe && y < exitButton.getHoehe() + SpielfeldHoehe){
            if (x < breiteExit){
                exit();
            } else if (x > breiteExit && x < breiteSpeichern){
                levelCount = 0;
                System.out.println("speichern");
                ILevel level = new DummyLevel();
                level.setTilemap(tileMap);
                spielwelt.addSzene(level);
                tileMap = new TileMap();
                dateiService.speichereSpielwelt(spielwelt,"spielwelt.json");
            } else if (x > breiteSpeichern && x < breiteLaden){
                System.out.println("laden");
                spielwelt = dateiService.ladeSpielwelt("spielwelt.json");
                tileMap = (TileMap) spielwelt.getSzene(0).getLevel().getTileMap();
                levelCount = 0;
            } else if (x > breiteLaden && x < breiteLeeren){
                System.out.println("leeren");
                //Java hat ja einen Garbage Collector. Also wäre auch folgendes möglich.
                tileMap = new TileMap();
                //Anstatt alle Felder mit Wiese zu nullen wie unten
                //leereTilemap();
            } else if (x > breiteLeeren && x < breiteLevel){
                System.out.println("level");
                ILevel level = new DummyLevel();
                level.setTilemap(tileMap);
                spielwelt.addSzene(level);
                tileMap = new TileMap();
                levelCount++;
            } else if (x > breiteLevel && x < breiteSiedlung){
                System.out.println("siedlung");
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
    }

    public void speichereSpielwelt(){
        //TODO: TileMap und Movalbes in die Spielwelt kopieren und in JSON speichern

        dateiService.speichereSpielwelt(spielwelt, "spielwelt.json");
    }

    /**
     * Lädt mithilfe des DateiService eine JSON Datei
     * @return die geladene Spielwelt vom Typ ISpielwelt
     */
    public ISpielwelt ladeSpielwelt(){
        return dateiService.ladeSpielwelt("spielwelt.json");
    }

//    /**
//     * Speichert eine Spielwelt in einer JSON Datei ab mithilfe des DateiService
//     */
//    public void speichereSpielwelt(){
//        this.dateiService.speichereSpielwelt(this.spielwelt,"spielwelt.json");
//    }

}