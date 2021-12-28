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
        levelBeendet = ueberpruefeLevelende();
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
//        zeichneMovableMenu(this, this.menuArrayMovables);



    }

    @Override
    public void mousePressed() {

    }

    public boolean ueberpruefeLevelende() {
        //Abfrage ob der aktuelle Standpunkt der Spielfigur eine Kachel vom Typ Levelausgang ist.
        pruefeLevelausgang();

        for (int i=0; i<spielmodell.getFigur().getInventar().size();i++){
            if (spielmodell.getFigur().getInventar().size()>=i+1) {
                if (spielmodell.getFigur().getInventar().get(i) instanceof Levelende) {
                    System.out.println("Levelende Bedingung wurde gefunden");
                    levelBeendet = true;
                    spielmodell.getFigur().getInventar().remove(i);
                    break;
                }
            }
        }
        return levelBeendet;
    }
    /**
     * @author LuHe20
     * Prüft, ob die aktuelle Kachel auf der sich der Spieler befindet,
     * eine Kachel des Typs: Levelausgang ist.
     * @return levelBeendet
     */
    private boolean pruefeLevelausgang() {
        int spielerPosX = spielmodell.getFigur().getPosY()/Einstellungen.LAENGE_KACHELN_Y;
        int spielerPosY = spielmodell.getFigur().getPosX()/Einstellungen.LAENGE_KACHELN_X;
        IKachel spielerKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(spielerPosX,spielerPosY);
        if (spielerKachel instanceof Levelausgang){
//            System.out.println(spielerKachel);
            levelBeendet = true;
        }
        return levelBeendet;
    }


    /**
     * Methode getKachelByCoordinates, gibt IKachel zurück, auf der die gegebenen Koordinaten liegen.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return IKachel
     */
    public IKachel getKachelByCoordinates(int x, int y) {
        int j =  x/Einstellungen.LAENGE_KACHELN_X;
        int i =  y/Einstellungen.LAENGE_KACHELN_Y;
        return spielmodell.getTileMap().getKachel(i,j);
    }

    /**
     * Methode isSpielfeldrand, gibt boolean zurück, der wahr ist, wenn die gegebenen Koordinaten
     * außerhalb des Spielfelds liegen.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return boolean
     */
    public boolean isSpielfeldrand(int x, int y){
        return x <= 0 || x >= SpielfeldBreite || y <= 0 || y >= SpielfeldHoehe;
    }

    /**
     * Methode isErlaubteKoordinate, gibt boolean zurück, der wahr ist, wenn die gegebenen Koordinaten weder
     * außerhalb des Spielfelds, noch auf einer unbetretbaren Kachel liegen.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return boolean
     */
    public boolean isErlaubteKoordinate(int x, int y) {
        if(!isSpielfeldrand(x,y)){
            return getKachelByCoordinates(x,y).istBetretbar();
        } else return false;
    }

    public void anzeigeTitelLevel(int LevelNr){
        frame.setTitle(Einstellungen.TITLE + "   Leveleditor Level: " + Integer.toString(LevelNr));
    }



    private void zeichneTileMap(PApplet app){
        tileMap.zeichne(app);
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