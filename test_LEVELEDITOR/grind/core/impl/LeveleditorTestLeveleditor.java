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
import grind.welt.ISiedlung;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySiedlung;
import grind.welt.impl.DummySpielwelt;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.Assert.*;



public class LeveleditorTestLeveleditor {
    int SpielfeldBreite;
    int SpielfeldHoehe;
    Spielsteuerung spielsteuerung;




    int levelNr = 1;
    int speicherHinweisLevel;
    int speicherHinweis = 1;

    int stringBreite = 90;
    int feuerRate = 1;
    int stufe = 1;
    int wert = 3;
    int punkte = 3;


    DateiService dateiService;
    ISpielwelt spielwelt;
    TileMap tileMap;
    ArrayList<IKachel> menuArrayKacheln;
    ArrayList<IMovable> menuArrayMovables;
    IKachel aktuelleKachel;
    IMovable aktuellesMovable;
    FeuerModus feuerModus = FeuerModus.KONSTANT;
    LaufModus laufModus = LaufModus.DEFAULT;
    Button exitButton;
    Button speichernButton;
    Button ladeButton;
    Button leerenButton;
    Button levelButton;
    Button siedlungButton;
    Button bildVor;
    Button bildZurueck;
    Button einstellungenObenPlus;
    Button einstellungenObenMinus;
    Button einstellungenUntenPlus;
    Button einstellungenUntenMinus;
    int mouseX;
    int mouseY;
    int mouseButton;
    int LEFT;
    int RIGHT;
    IKachel MenukacheliKachel;
    int mausY;
    int mausX;
    int posX;
    int posY;
    int LevelNr;
    ArrayList<IKachel> iKachel;
    ILevel templevel;
    ArrayList<IMovable> movableList;

    @Before
    public void setUp() throws Exception {

        Einstellungen.LAENGE_KACHELN_Y = 30;
        Einstellungen.LAENGE_KACHELN_X = 30;
        this.menuArrayKacheln = new ArrayList<>();
        this.menuArrayMovables = new ArrayList<>();
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
        this.einstellungenObenMinus = new Button(8);
        this.einstellungenUntenMinus = new Button(8);
        this.einstellungenObenPlus = new Button(9);
        this.einstellungenUntenPlus = new Button(9);

        try {
            spielsteuerung.settings();
         } catch (Exception e) {
             System.out.println("fehlermeldung size");
        }
    }

    @Test
    public void settings() {

        System.out.println("X:Y  " + Einstellungen.ANZAHL_KACHELN_X * Einstellungen.LAENGE_KACHELN_X + Einstellungen.LAENGE_KACHELN_X * 2 + ":" + Einstellungen.ANZAHL_KACHELN_Y * Einstellungen.LAENGE_KACHELN_Y + Einstellungen.LAENGE_KACHELN_Y);
        assertEquals(Einstellungen.ANZAHL_KACHELN_X * Einstellungen.LAENGE_KACHELN_X, spielsteuerung.getSpielfeldBreite());
        assertEquals(Einstellungen.ANZAHL_KACHELN_Y * Einstellungen.LAENGE_KACHELN_Y, spielsteuerung.getSpielfeldHoehe());
    }


    @Test
    public void setup() {
    }

    @Test
    public void mousePressed() {
        int mausXmenu = (mouseX - SpielfeldBreite) / Einstellungen.LAENGE_KACHELN_X;
        int mausYmenu = mouseY / Einstellungen.LAENGE_KACHELN_Y;
        int mausXkachel = mouseX / Einstellungen.LAENGE_KACHELN_X;
        int mausYkachel = mouseY / Einstellungen.LAENGE_KACHELN_Y;
        int mausYmovable = mausYkachel * Einstellungen.LAENGE_KACHELN_Y + Einstellungen.LAENGE_KACHELN_Y / 2;
        int mausXmovable = mausXkachel * Einstellungen.LAENGE_KACHELN_X + Einstellungen.LAENGE_KACHELN_X / 2;
        int ausenXKoordMovable = SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X;
        int ausenXKoordKachel = SpielfeldBreite + 2 * Einstellungen.LAENGE_KACHELN_X;

        if (mouseButton == LEFT) {
            if (mouseX > SpielfeldBreite && mouseX <= ausenXKoordMovable) {
                if (mouseY < menuArrayKacheln.size() * Einstellungen.LAENGE_KACHELN_Y) {
                    assertEquals(null, aktuellesMovable);
                    // assertEquals(iKachel.(mausYmenu, mausXmenu, menuArrayKacheln);
                }
            } else if (mouseX > SpielfeldBreite && mouseX <= ausenXKoordKachel) {
                if (mouseY < menuArrayMovables.size() * Einstellungen.LAENGE_KACHELN_Y) {
                    //Die einzelnen Einstellungen der Movables werden hier genullt
                    assertEquals(3, wert);
                    assertEquals(3, punkte);
                    assertEquals(2, feuerRate);
                    assertEquals(1, stufe);
                    assertEquals(FeuerModus.KONSTANT, feuerModus);
                    assertEquals(LaufModus.DEFAULT, laufModus);
                    assertEquals(null, aktuelleKachel);
                    // aktuellesMovable = getMenukacheliMovable(mausYmenu, mausXmenu - 1, menuArrayMovables);
                }
            }
            if (mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe) {
                if (aktuelleKachel != null) {
                    tileMap.setKachel(aktuelleKachel, mausYkachel, mausXkachel);
                } else if (aktuellesMovable != null) {
                    aktuellesMovable.setPosition(mausXmovable, mausYmovable);
                    //addMovablezuLevel(aktuellesMovable);
                } else if (aktuelleKachel == null && aktuellesMovable == null) {

                    assertEquals(spielwelt.getSzene(levelNr-1), templevel);
                    assertEquals(templevel.getPositionen(), movableList);

                    for (int i = 0; i < movableList.size(); i++) {
                        int posX = movableList.get(i).getPosX();
                        int posY = movableList.get(i).getPosY();

                        if (posX == mausXmovable && posY == mausYmovable) {

                            assertEquals(movableList.get(i), aktuellesMovable);
                            movableList.remove(i);
                        }
                    }
                    if (aktuellesMovable == null) {

                        assertEquals(tileMap.getKachel(mausYkachel, mausXkachel), aktuelleKachel);
                        tileMap.setKachel(new Wiese(), mausYkachel, mausXkachel);
                    }
                    //buttonAction(mouseY, mouseX);

                } else if (mouseButton == RIGHT) {
                    if (aktuelleKachel == null && aktuellesMovable == null && mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe) {
                        tileMap.setKachel(new Wiese(), mausYkachel, mausXkachel);
                    }
                    assertEquals(null, aktuelleKachel);
                    assertEquals(null, aktuellesMovable);
                }

            }
        }
    }
        @Test
        public void anzeigeTitelLevel () {

        }

    private void addMovablezuLevel(IMovable movable) {

    }

    private void zeichneAssetMenu(ArrayList<IKachel> menuArray){

    }

    private void zeichneMovableMenu(ArrayList<IMovable> menuArray){

    }



    }
//
//    package grind.core.impl;
//
//            import grind.kacheln.IKachel;
//            import grind.kacheln.ITileMap;
//            import grind.kacheln.impl.*;
//            import grind.movables.IMovable;
//            import grind.movables.impl.*;
//            import grind.movables.monster.*;
//            import grind.util.Einstellungen;
//            import grind.util.FeuerModus;
//            import grind.util.LaufModus;
//            import grind.util.Richtung;
//            import grind.welt.ILevel;
//            import grind.welt.ISiedlung;
//            import grind.welt.ISpielwelt;
//            import grind.welt.impl.DummyLevel;
//            import grind.welt.impl.DummySiedlung;
//            import grind.welt.impl.DummySpielwelt;
//            import processing.core.PConstants;
//            import processing.core.PImage;
//
//            import java.util.ArrayList;
//            import java.util.Dictionary;
//            import java.util.Hashtable;
//
//
//public class Leveleditor extends Spielsteuerung {
//    private static int SpielfeldBreite;
//    private static int SpielfeldHoehe;
//    private final Spielsteuerung spielsteuerung;
//
//    private final int breiteExit;
//    private final int breiteSpeichern;
//    private final int breiteLaden;
//    private final int breiteLeeren;
//    private final int breiteLevel;
//    private final int breiteSiedlung;
//    private final int breiteVor;
//    private final int breiteZurueck;
//    private final int breiteEinstObenPlus;
//    private final int breiteEinstUntenPlus;
//    private final int breiteEinstObenMinus;
//    private final int breiteEinstUntenMinus;
//
//    private int levelCount = 1;
//    private int levelNr = 1;
//    private int speicherHinweisLevel;
//    private int speicherHinweis = 1;
//    //Die Stringbreite legt den Abstand zwischen den beiden Auswahlpfeilen (Einstellungen der Movables) fest
//    //Falls die ENUM Namen zu groß werden, muss das hier angepasst werden.
//    private int stringBreite = 90;
//    private int feuerRate = 1;
//    private int stufe = 1;
//    private int wert = 3;
//    private int punkte = 3;
//
//    private DateiService dateiService;
//    private ISpielwelt spielwelt;
//    private TileMap tileMap;
//    private ArrayList<IKachel> menuArrayKacheln;
//    private ArrayList<IMovable> menuArrayMovables;
//    private IKachel aktuelleKachel;
//    private IMovable aktuellesMovable;
//    private FeuerModus feuerModus = FeuerModus.KONSTANT;
//    private LaufModus laufModus = LaufModus.DEFAULT;
//    private final Button exitButton;
//    private final Button speichernButton;
//    private final Button ladeButton;
//    private final Button leerenButton;
//    private final Button levelButton;
//    private final Button siedlungButton;
//    private final Button bildVor;
//    private final Button bildZurueck;
//    private final Button einstellungenObenPlus;
//    private final Button einstellungenObenMinus;
//    private final Button einstellungenUntenPlus;
//    private final Button einstellungenUntenMinus;
//    Dictionary images = new Hashtable();
//
//
//    /**
//     * Konstruktor des Leveleditors
//     * Erstellt von den benötigten Klassen Instanzen um die Darstellung der einzelnen Spielobjekte zu ermöglichen.
//     * Befüllt die Menüs mit den entsprechenden Instanzen der Klassen.
//     * Ändert die Kachelgräße um den Leveleditor auch auf kleineren Bildschirmen vollständig anzuzeigen.
//     * Gibt den einzelnen Buttons ihre Funktionsbeschriftung.
//     * Legt die Größe der Buttons anhand der Kachelgrößen fest.
//     */
//    public Leveleditor(){
//        Einstellungen.LAENGE_KACHELN_Y = 30;
//        Einstellungen.LAENGE_KACHELN_X = 30;
//        this.menuArrayKacheln = new ArrayList<>();
//        this.menuArrayMovables = new ArrayList<>();
//        this.tileMap = new TileMap();
//        this.spielwelt = new DummySpielwelt();
//        this.spielsteuerung = new Spielsteuerung();
//        this.dateiService = new DateiService(this.spielsteuerung);
//        this.aktuelleKachel = null;
//        this.aktuellesMovable = null;
//        this.exitButton = new Button(0);
//        this.speichernButton = new Button(1);
//        this.ladeButton = new Button(2);
//        this.leerenButton = new Button(3);
//        this.levelButton = new Button(4);
//        this.siedlungButton = new Button(5);
//        this.bildZurueck = new Button(6);
//        this.bildVor = new Button(7);
//        this.einstellungenObenMinus = new Button(8);
//        this.einstellungenUntenMinus = new Button(8);
//        this.einstellungenObenPlus = new Button(9);
//        this.einstellungenUntenPlus = new Button(9);
//
//        //this.spielfigurBild = new PImage();
//
//        this.breiteExit = exitButton.getBreite();
//
//        this.breiteSpeichern = exitButton.getBreite()
//                + speichernButton.getBreite();
//
//        this.breiteLaden = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite();
//
//        this.breiteLeeren = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite();
//
//        this.breiteLevel = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite();
//
//        this.breiteSiedlung = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite()
//                + siedlungButton.getBreite();
//
//        this.breiteZurueck = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite()
//                + siedlungButton.getBreite()
//                + bildZurueck.getBreite();
//
//        this.breiteVor = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite()
//                + siedlungButton.getBreite()
//                + bildZurueck.getBreite()
//                + bildVor.getBreite();
//
//        this.breiteEinstObenMinus = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite()
//                + siedlungButton.getBreite()
//                + bildZurueck.getBreite()
//                + bildVor.getBreite()
//                + einstellungenObenMinus.getBreite();
//
//        this.breiteEinstUntenMinus = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite()
//                + siedlungButton.getBreite()
//                + bildZurueck.getBreite()
//                + bildVor.getBreite()
//                + einstellungenUntenMinus.getBreite();
//
//        this.breiteEinstObenPlus = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite()
//                + siedlungButton.getBreite()
//                + bildZurueck.getBreite()
//                + bildVor.getBreite()
//                + einstellungenObenMinus.getBreite()
//                + stringBreite
//                + einstellungenObenPlus.getBreite();
//
//        this.breiteEinstUntenPlus = exitButton.getBreite()
//                + speichernButton.getBreite()
//                + ladeButton.getBreite()
//                + leerenButton.getBreite()
//                + levelButton.getBreite()
//                + siedlungButton.getBreite()
//                + bildZurueck.getBreite()
//                + bildVor.getBreite()
//                + einstellungenUntenMinus.getBreite()
//                + stringBreite
//                + einstellungenUntenPlus.getBreite();
//
//        //Befüllen des Menuarrays mit den Kachelarten
//        this.menuArrayKacheln.add( new Baum());
//        this.menuArrayKacheln.add( new DummyHindernis());
//        this.menuArrayKacheln.add( new Fels());
//        this.menuArrayKacheln.add( new Holzbrücke());
//        this.menuArrayKacheln.add( new Levelausgang());
//        this.menuArrayKacheln.add( new Wasser());
//        this.menuArrayKacheln.add( new Weg());
//        this.menuArrayKacheln.add( new Wiese());
//
//        //Befüllen des Menuarrays mit den Movables
//        //WICHTIG: Wenn neue Movables hinzugefügt werden, muss das und auch im DateiService geschehen.
//        // @see Leveleditor --> Methode addMovablezuLevel
//        // @see Leveleditor --> Methode zeichneEinstellungsmenu
//        // @see DateiService --> Methode IMovable deserialize
//        this.menuArrayMovables.add( new Schwert(40,40,1,this.wert));
//        this.menuArrayMovables.add( new Mango(0,0,this.punkte,this.wert));
//        this.menuArrayMovables.add( new Heiltrank(0,0,this.punkte,this.wert));
//        this.menuArrayMovables.add( new Gold(0,0));
//        this.menuArrayMovables.add( new Apfel(0,0,this.punkte,this.wert));
//        this.menuArrayMovables.add( new Bogen(40,40,1,this.wert));
//        this.menuArrayMovables.add( new Stern(0,0));
//        this.menuArrayMovables.add( new Spezialattacke(40,40,1));
//        this.menuArrayMovables.add( new Spielfigur(0,0, Richtung.N));
//        this.menuArrayMovables.add( new Levelende(0,0));
//        this.menuArrayMovables.add( new DornPflanze(0,0,this.tileMap));
//        this.menuArrayMovables.add( new FeuerMonster(0,0,this.tileMap,this.spielsteuerung,Richtung.N,feuerRate, FeuerModus.RANDOM));
//        this.menuArrayMovables.add( new Geist(0,0,this.tileMap));
//        this.menuArrayMovables.add( new Zombie(0,0,this.tileMap,Richtung.N,this.spielsteuerung, LaufModus.DEFAULT));
//    }
//
//    /**
//     * Methode settings, setzt Spielfeldgröße auf die in den Einstellungen gesetzten Parameter.
//     */
//    @Override
//    public void settings() {
//        SpielfeldBreite = Einstellungen.LAENGE_KACHELN_X * Einstellungen.ANZAHL_KACHELN_X;
//        SpielfeldHoehe = Einstellungen.LAENGE_KACHELN_Y * Einstellungen.ANZAHL_KACHELN_Y;
//        size(SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X*2,SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y);
//    }
//
//    /**
//     * Methode setup, lädt Darstellungen  und zeigt den Titel des Programms an
//     */
//    @Override
//    public void setup() {
//        imageMode(PConstants.CORNER);
//        ladeBilder(this);
//        anzeigeTitelLevel(levelNr);
//    }
//
//    /**
//     * Methode draw, zeichnet alle sichtbare Elemente.
//     * (Tilemap, Movables, Buttons, ...)
//     */
//    @Override
//    public void draw() {
//        background(255);
//        zeichne();
//    }
//
//    /**
//     * Ruft alle einzelnen zeichne Methoden auf.
//     */
//    private void zeichne() {
//        zeichneTileMap();
//        zeichneMovables();
//        zeichneAssetMenu(this.menuArrayKacheln);
//        zeichneMovableMenu(this.menuArrayMovables);
//        zeichneEinstellungsmenu();
//        zeichneMausKachel(aktuelleKachel, mouseX, mouseY);
//        zeichneMausMovable(aktuellesMovable,mouseX,mouseY);
//        zeichneButtons();
//        zeichneCounts();
//        zeichneFehler();
//    }
//
//
//    /**
//     * Fragt ab welche Maustaste gedrückt wurde.
//     * Bei einem Linksklick wird überprüft, ob man sich auf einem Menupunkt (Kachel/Movable), Button oder auf
//     * der Tilemap befindet.
//     * Befindet man sich auf der Tilemap, wird das aktuell ausgewählte Objekt platziert.
//     * Die Platzierung erfolgt nur in Kachelfeldern und ist nicht frei auf der Map platzierfähig.
//     *
//     * Befindet man sich auf einem Menupunkt, wird dieser als akutelle Mauskachel gewählt.
//     * Befindet man sich auf einem Button, wird dieser aufgerufen.
//     * Ist aktuell keine Kachel oder Movable ausgewählt, kann auf dem Spielfeld ein Movable oder eine Kachel
//     * aufgenommen und verschoben/gelöscht werden.
//     *
//     * Die Einstellungen der Movables werden hier beim auswählen auf ihre Standartwerte gesetzt.
//     *
//     * Bei einem Rechtsklick wird die aktuelle Mauskachel auf null gesetzt.
//     */
//    @Override
//    public void mousePressed() {
//        int mausXmenu = (mouseX - SpielfeldBreite) / Einstellungen.LAENGE_KACHELN_X;
//        int mausYmenu = mouseY / Einstellungen.LAENGE_KACHELN_Y;
//        int mausXkachel = mouseX / Einstellungen.LAENGE_KACHELN_X;
//        int mausYkachel = mouseY / Einstellungen.LAENGE_KACHELN_Y;
//        int mausYmovable = mausYkachel * Einstellungen.LAENGE_KACHELN_Y + Einstellungen.LAENGE_KACHELN_Y/2;
//        int mausXmovable = mausXkachel * Einstellungen.LAENGE_KACHELN_X + Einstellungen.LAENGE_KACHELN_X/2;
//        int ausenXKoordMovable = SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X;
//        int ausenXKoordKachel = SpielfeldBreite + 2 * Einstellungen.LAENGE_KACHELN_X;
//
//        if (mouseButton == LEFT) {
//            if (mouseX > SpielfeldBreite && mouseX <= ausenXKoordMovable) {
//                if (mouseY < menuArrayKacheln.size() * Einstellungen.LAENGE_KACHELN_Y) {
//                    aktuellesMovable = null;
//                    aktuelleKachel = getMenukacheliKachel(mausYmenu, mausXmenu, menuArrayKacheln);
//                }
//            } else if (mouseX > SpielfeldBreite && mouseX <= ausenXKoordKachel){
//                if (mouseY < menuArrayMovables.size() * Einstellungen.LAENGE_KACHELN_Y){
//                    //Die einzelnen Einstellungen der Movables werden hier genullt
//                    wert = 3;
//                    punkte = 3;
//                    feuerRate = 2;
//                    stufe = 1;
//                    feuerModus = FeuerModus.KONSTANT;
//                    laufModus = LaufModus.DEFAULT;
//                    aktuelleKachel = null;
//                    aktuellesMovable = getMenukacheliMovable(mausYmenu, mausXmenu - 1, menuArrayMovables);
//                }
//            }
//            if (mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe) {
//                if (aktuelleKachel != null){
//                    tileMap.setKachel(aktuelleKachel, mausYkachel, mausXkachel);
//                } else if (aktuellesMovable != null){
//                    aktuellesMovable.setPosition(mausXmovable,mausYmovable);
//                    addMovablezuLevel(aktuellesMovable);
//                } else if (aktuelleKachel == null && aktuellesMovable == null){
//                    ILevel templevel = (ILevel) spielwelt.getSzene(levelNr-1);
//                    ArrayList<IMovable> movableList = (ArrayList<IMovable>) templevel.getPositionen();
//
//                    for (int i = 0; i < movableList.size(); i++){
//                        int posX = movableList.get(i).getPosX();
//                        int posY = movableList.get(i).getPosY();
//
//                        if (posX == mausXmovable && posY == mausYmovable){
//                            aktuellesMovable = movableList.get(i);
//                            movableList.remove(i);
//                        }
//                    }
//                    if (aktuellesMovable == null) {
//                        aktuelleKachel = tileMap.getKachel(mausYkachel, mausXkachel);
//                        tileMap.setKachel(new Wiese(), mausYkachel, mausXkachel);
//                    }
//                }
//            }
//            buttonAction(mouseY, mouseX);
//
//        } else if (mouseButton == RIGHT){
//            if (aktuelleKachel == null && aktuellesMovable == null && mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe ){
//                tileMap.setKachel(new Wiese(), mausYkachel, mausXkachel);
//            }
//            aktuelleKachel = null;
//            aktuellesMovable = null;
//        }
//    }
//
//    /**
//     * Fügt das übergebene Movable im aktuellen Level in die Instanz von Spielwelt hinzu.
//     * Falls ein neues Movable im Spiel implementiert wird, muss dieses hier auch mit implementiert werden.
//     * WICHTIG: Wenn neue Movables hinzugefügt werden, muss das auch im DateiService geschehen.
//     * @see DateiService --> Methode IMovable deserialize
//     * @param movable Das zu speichernde Movable
//     */
//    private void addMovablezuLevel(IMovable movable) {
//        Movable tempMovable = null;
//        int posX = movable.getPosX();
//        int posY = movable.getPosY();
//        Richtung richtung = movable.getAusrichtung();
//        if (movable instanceof Spielfigur) {
//            tempMovable = new Spielfigur(posX, posY, richtung);
//        } else if (movable instanceof Apfel) {
//            tempMovable = new Apfel(posX, posY, punkte, wert);
//        } else if (movable instanceof Gold) {
//            tempMovable = new Gold(posX, posY);
//        } else if (movable instanceof Heiltrank) {
//            tempMovable = new Heiltrank(posX, posY, punkte, wert);
//        } else if (movable instanceof Levelende) {
//            tempMovable = new Levelende(posX, posY);
//        } else if (movable instanceof Mango) {
//            tempMovable = new Mango(posX, posY, punkte, wert);
//        } else if (movable instanceof Schwert) {
//            tempMovable = new Schwert(posX, posY, stufe, wert);
//        } else if (movable instanceof DornPflanze) {
//            tempMovable = new DornPflanze(posX, posY, this.tileMap);
//        } else if (movable instanceof FeuerMonster) {
//            tempMovable = new FeuerMonster(posX, posY, this.tileMap,this.spielsteuerung,Richtung.N,feuerRate,feuerModus);
//        } else if (movable instanceof Geist) {
//            tempMovable = new Geist(posX, posY, this.tileMap);
//        } else if (movable instanceof Zombie){
//            tempMovable = new Zombie(posX, posY, this.tileMap,Richtung.N,this.spielsteuerung,laufModus);
//        } else if (movable instanceof Bogen){
//            tempMovable = new Bogen(posX, posY, stufe, wert);
//        } else if (movable instanceof Spezialattacke){
//            tempMovable = new Spezialattacke(posX, posY, stufe);
//        } else if (movable instanceof Stern) {
//            tempMovable = new Stern(posX, posY);
//        }
//        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//        level.addPosition(tempMovable);
//    }
//
//    /**
//     * Zeigt im Fensterrahmen den Namen des Leveleditors an mit akuteller Levelnummer
//     * @param LevelNr Aktuelle Levelnummer
//     */
//    public void anzeigeTitelLevel(int LevelNr){
//        surface.setTitle(Einstellungen.TITLE + "   Leveleditor Level: " + Integer.toString(LevelNr));
//    }
//
//    /**
//     * Zeichnet das Menü mit den verfügbaren Kacheln am rechten Rand.
//     * @param menuArray Die Menuarraylist mit den verschiedenen Kachelarten
//     */
//    private void zeichneAssetMenu(ArrayList<IKachel> menuArray){
//        int mapAussenX = SpielfeldBreite;
//        int mapAussenY = 0;
//        for (IKachel iKachels : menuArray) {
//            iKachels.zeichne(this, mapAussenX, mapAussenY);
//            mapAussenY += Einstellungen.LAENGE_KACHELN_Y;
//
//            if (mapAussenY >= SpielfeldHoehe) {
//                mapAussenY = 0;
//                mapAussenX += Einstellungen.LAENGE_KACHELN_X;
//            }
//        }
//    }
//
//    /**
//     * Zeichnet das Menü mit den verfügbaren Movables am rechten Rand nach den Kacheln.
//     * Die Spielfigur wird als reines Bild dargestellt, da sonst der Lebensbalken mitgezeichnet werden muss.
//     * @param menuArray Die Menuarraylist mit den verschiedenen Movablearten
//     */
//    private void zeichneMovableMenu(ArrayList<IMovable> menuArray){
//        int mapAussenX = SpielfeldBreite + Einstellungen.LAENGE_KACHELN_X + Einstellungen.LAENGE_KACHELN_X/2;
//        int mapAussenY = Einstellungen.LAENGE_KACHELN_Y/2;
//        this.pushStyle();
//        this.imageMode(CENTER);
//        this.ellipseMode(CENTER);
//        this.rectMode(CENTER);
//        for (IMovable iMovables : menuArray) {
//            iMovables.setPosition(mapAussenX, mapAussenY);
//            if (iMovables instanceof Spielfigur) {
//                this.image((PImage) getImages().get(iMovables.getClass().toString()), mapAussenX, mapAussenY, Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
//            } else {
//                iMovables.zeichne(this);
//            }
//            mapAussenY += Einstellungen.LAENGE_KACHELN_Y;
//        }
//        this.popStyle();
//    }
//
//    /**
//     * Gibt die Kachel an der übergebenen Position im Array zurück
//     * @param mausY Position in Y Richtung
//     * @param mausX Position in X Richtung
//     * @param iKachel Das Array mit den Kacheln
//     * @return Kachel an Stelle [x][y]
//     */
//    private IKachel getMenukacheliKachel(int mausY, int mausX, ArrayList<IKachel> iKachel){
//
//        return iKachel.get(mausY);
//    }
//
//    /**
//     * Gibt das Movable an der übergebenen Position im Array zurück
//     * Wenn das aktuelle Level eine Siedlung ist, können keine Monster platziert und aufgenommen werden.
//     * @param mausY Position in Y Richtung
//     * @param mausX Position in X Richtung
//     * @param iMovable Das Array mit den Movables
//     * @return Movable an Stelle [x][y]
//     */
//    private IMovable getMenukacheliMovable (int mausY, int mausX, ArrayList<IMovable> iMovable){
//        IMovable tempMovable = iMovable.get(mausY);
//        if (spielwelt.getSzene(levelNr-1) instanceof ISiedlung) {
//            if (tempMovable instanceof IMonster) {
//                tempMovable = null;
//            }
//        }
//        return tempMovable;
//    }
//
//    /**
//     * Falls es sich bei der aktuellen Mausauswahl um eine Kachel handelt, wird diese hier gezeichnet
//     * @param aktuelleKachel Die aktuelle Kachel
//     * @param mausX Position der Maus in X-Richtung
//     * @param mausY Position der Maus in Y-Richtung
//     */
//    private void zeichneMausKachel(IKachel aktuelleKachel, int mausX, int mausY){
//        if (aktuelleKachel != null) {
//            pushStyle();
//            this.rectMode(PConstants.CENTER);
//            this.imageMode(PConstants.CENTER);
//            aktuelleKachel.zeichne(this, mausX, mausY);
//            popStyle();
//        }
//    }
//
//    /**
//     * Falls es sich bei der aktuellen Mausauswahl um ein Movable handelt, wird dieses hier gezeichnet
//     * @param aktuellesMovable  Das aktuelle Movable
//     * @param mausX mausX Position der Maus in X-Richtung
//     * @param mausY mausY Position der Maus in Y-Richtung
//     */
//    private void zeichneMausMovable(IMovable aktuellesMovable, int mausX, int mausY){
//        if (aktuellesMovable != null) {
//            pushStyle();
//            this.rectMode(CENTER);
//            this.imageMode(CENTER);
//            aktuellesMovable.setPosition(mausX,mausY);
//
//            if(aktuellesMovable instanceof Spielfigur){
//                this.image((PImage) getImages().get(aktuellesMovable.getClass().toString()), mausX, mausY, Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
//            } else {
//                aktuellesMovable.zeichne(this);
//            }
//            popStyle();
//        }
//    }
//
//    /**
//     * Zeichnet die Tilemap des Leveleditors
//     */
//    private void zeichneTileMap(){
//        tileMap.zeichne(this);
//    }
//
//    /**
//     * Zeichnet die Movalbes, welche auf der Tilemap platziert wurden
//     */
//    private void zeichneMovables(){
//        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//        int anzahlPos = level.getPositionen().size();
//        IMovable movable;
//        for (int i = 0; i < anzahlPos; i++){
//            movable = level.getPositionen().get(i);
//
//            if(movable instanceof Spielfigur){
//                this.image((PImage) getImages().get(movable.getClass().toString()), movable.getPosX(), movable.getPosY(), Einstellungen.GROESSE_SPIELFIGUR, Einstellungen.GROESSE_SPIELFIGUR);
//            } else {
//                movable.zeichne(this);
//            }
//        }
//
//    }
//
//    /**
//     * Zeichnet neben den Buttons die aktuellen Einstellungen der Mauskachel/movable nieder.
//     * Jedes Objekt hat neben der Position noch weitere Eigenschaften wie den Wert, Punkte und die Stufe.
//     */
//    private void zeichneEinstellungsmenu() {
//        this.pushStyle();
//        float textverhaeltnis = ((Einstellungen.LAENGE_KACHELN_X * Einstellungen.LAENGE_KACHELN_Y) / 1600f);
//        this.textSize((int)(16 * textverhaeltnis));
//        this.fill(0, 0, 0);
//        if (aktuellesMovable instanceof FeuerMonster){
//            this.text(feuerRate, breiteEinstObenMinus + 15, SpielfeldHoehe + 20 * textverhaeltnis);
//            this.text(feuerModus.name(), breiteEinstUntenMinus + 15, SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y/2 + 20 * textverhaeltnis);
//        } else if (aktuellesMovable instanceof Zombie){
//            this.text(laufModus.name(), breiteEinstUntenMinus + 15, SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y/2 + 20 * textverhaeltnis);
//        } else if (aktuellesMovable instanceof Schwert){
//            this.text(stufe, breiteEinstObenMinus + 15, SpielfeldHoehe + 20 * textverhaeltnis);
//        } else if (aktuellesMovable instanceof Bogen){
//            this.text(stufe, breiteEinstObenMinus + 15, SpielfeldHoehe + 20 * textverhaeltnis);
//        } else if (aktuellesMovable instanceof Spezialattacke){
//            this.text(stufe, breiteEinstObenMinus + 15, SpielfeldHoehe + 20 * textverhaeltnis);
//        } else if (aktuellesMovable instanceof Apfel || aktuellesMovable instanceof Heiltrank || aktuellesMovable instanceof Mango){
//            this.text(wert, breiteEinstObenMinus + 15, SpielfeldHoehe + 20 * textverhaeltnis);
//            this.text(punkte, breiteEinstUntenMinus + 15, SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y/2 + 20 * textverhaeltnis);
//        }
//        this.popStyle();
//    }
//
//    /**
//     * Zeichnet die Buttons/Schaltflächen
//     */
//    private void zeichneButtons(){
//
//        exitButton.zeichne(this,0, SpielfeldHoehe);
//        speichernButton.zeichne(this, breiteExit, SpielfeldHoehe);
//        ladeButton.zeichne(this, breiteSpeichern, SpielfeldHoehe);
//        leerenButton.zeichne(this, breiteLaden, SpielfeldHoehe);
//        levelButton.zeichne(this, breiteLeeren, SpielfeldHoehe);
//        siedlungButton.zeichne(this, breiteLevel, SpielfeldHoehe);
//        bildZurueck.zeichne(this, breiteSiedlung, SpielfeldHoehe);
//        bildVor.zeichne(this, breiteZurueck, SpielfeldHoehe);
//        einstellungenObenMinus.zeichne(this, breiteVor, SpielfeldHoehe);
//        einstellungenUntenMinus.zeichne(this, breiteVor, SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y/2);
//        einstellungenObenPlus.zeichne(this, breiteEinstObenMinus + stringBreite, SpielfeldHoehe);
//        einstellungenUntenPlus.zeichne(this, breiteEinstUntenMinus + stringBreite, SpielfeldHoehe + Einstellungen.LAENGE_KACHELN_Y/2);
//    }
//
//    /**
//     * Zeichnet den Levelcounter und die aktuelle Levelnummer
//     */
//    private void zeichneCounts() {
//        this.pushStyle();
//        this.textSize(12);
//        this.fill(0, 0, 0);
//        this.text("Levelanzahl: " + levelCount, 20, 25);
//        this.text("LevelNr: " + levelNr, 20, 40);
//        this.popStyle();
//    }
//
//    /**
//     * Zeichnet die Fehlermeldungen falls beim Speichern die Speicherkriterien nicht erfüllt werden
//     * Speicherhinweis  1: Kein Levelausgang
//     *                  2: Keine Spielfigur
//     *                  3: Mehr als eine Spielfigur
//     */
//    private void zeichneFehler(){
//        this.pushStyle();
//        this.textSize(16);
//        this.fill(200, 20, 0);
//        if (speicherHinweis == 1 && (speicherHinweisLevel != 0)) {
//            this.text("Kein Levelende/-ausgang in Level: " + speicherHinweisLevel, 130, 25);
//        } else if (speicherHinweis == 2 && (speicherHinweisLevel != 0)) {
//            this.text("Keine Spielfigur in Level: " + speicherHinweisLevel, 130, 25);
//        } else if (speicherHinweis == 3 && (speicherHinweisLevel != 0)) {
//            this.text("Mehr als eine Spielfigur in Level: " + speicherHinweisLevel, 130, 25);
//        }
//        this.popStyle();
//    }
//
//    /**
//     * Führt die Aktionen aus, nachdem ein Button gedrückt wurde
//     * @param y Position der Maus in y-Richtung
//     * @param x Position der Maus in x-Richtung
//     */
//    private void buttonAction(int y, int x){
//        if (y > SpielfeldHoehe && y < exitButton.getHoehe() + SpielfeldHoehe){
//            if (x < breiteExit){
//                exitLeveleditor();
//
//            } else if (x > breiteExit && x < breiteSpeichern){
//                speichereSpielwelt();
//
//            } else if (x > breiteSpeichern && x < breiteLaden){
//                ladeSpielweltLeveleditor();
//
//            } else if (x > breiteLaden && x < breiteLeeren){
//                leereLevel();
//
//            } else if (x > breiteLeeren && x < breiteLevel){
//                neuesLevel();
//
//            } else if (x > breiteLevel && x < breiteSiedlung){
//                neueSiedlung();
//
//            } else if (x > breiteSiedlung && x < breiteZurueck){
//                springeZurueck();
//
//            } else if (x > breiteZurueck && x < breiteVor){
//                springeVor();
//            }
//        }
//        if (y > SpielfeldHoehe && y < einstellungenObenMinus.getHoehe() + SpielfeldHoehe){
//            if (x > breiteVor && x < breiteEinstObenMinus){
//                minusobereEinstellung();
//            } else if (x > breiteEinstObenMinus + stringBreite && x < breiteEinstObenPlus){
//                plusobereEinstellung();
//            }
//        }
//        if (y > SpielfeldHoehe + einstellungenObenMinus.getHoehe() && y < einstellungenUntenMinus.getHoehe() + einstellungenObenMinus.getHoehe() + SpielfeldHoehe){
//            if (x > breiteVor && x < breiteEinstUntenMinus){
//                minusuntereEinstellung();
//            } else if (x > breiteEinstUntenMinus + stringBreite && x < breiteEinstUntenPlus){
//                plusuntereEinstellung();
//            }
//        }
//    }
//
//    /**
//     * Verringert die Punkte, den FeuerModus und den LaufModus.
//     */
//    private void minusuntereEinstellung() {
//        if (punkte > 1){
//            punkte--;
//        }
//        iteriereFeuerModus();
//        iteriereLaufModus();
//        System.out.println("minusunten");
//    }
//
//    /**
//     * Erhöht die Punkte, den FeuerModus und den LaufModus.
//     */
//    private void plusuntereEinstellung() {
//        punkte++;
//        iteriereFeuerModus();
//        iteriereLaufModus();
//        System.out.println("plusunten");
//    }
//
//    /**
//     * Verringert die FeuerRate, die Stufe und den Wert.
//     */
//    private void minusobereEinstellung() {
//        if (feuerRate > 0){
//            feuerRate--;
//        }
//        if (stufe > 1){
//            stufe--;
//        }
//        if (wert > 2){
//            wert--;
//        }
//        System.out.println("minusoben");
//    }
//
//    /**
//     * Erhöht die FeuerRate, die Stufe und den Wert.
//     */
//    private void plusobereEinstellung() {
//        feuerRate++;
//        stufe++;
//        wert++;
//        System.out.println("plusoben");
//    }
//
//    /**
//     * Schließt den Leveleditor
//     */
//    private void exitLeveleditor() {
//        System.exit(0);
//    }
//
//    /**
//     * Schaltet bei jedem Aufruf den FeuerModus in den nächsten Modus.
//     * Bei neuen Modis müssen diese hier als neue case hinzugefügt werden
//     */
//    private void iteriereFeuerModus() {
//        switch (feuerModus){
//            case RANDOM:
//                feuerModus = FeuerModus.SEMIRANDOM;
//                break;
//            case SEMIRANDOM:
//                feuerModus = FeuerModus.KONSTANT;
//                break;
//            case KONSTANT:
//                feuerModus = FeuerModus.RANDOM;
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * Schaltet bei jedem Aufruf den LaufModus in den nächsten Modus.
//     * Bei neuen Modis müssen diese hier als neue case hinzugefügt werden
//     */
//    private void iteriereLaufModus(){
//        switch (laufModus){
//            case DEFAULT:
//                laufModus = LaufModus.JAGDT;
//                break;
//            case JAGDT:
//                laufModus = LaufModus.RANDOM;
//                break;
//            case RANDOM:
//                laufModus = LaufModus.DEFAULT;
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * Speichert die aktulle Spielwelt und resettet die Anzeige
//     */
//    private void speichereSpielwelt() {
//        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//        level.setTilemap(tileMap);
//        pruefeSpeicherkriterien();
//        if (speicherHinweis == 0){
//            levelCount = 1;
//            levelNr = 1;
//            System.out.println("speichern");
//            tileMap = new TileMap();
//            dateiService.speichereSpielwelt(spielwelt,"spielwelt.json");
//            spielwelt.removeSzenen();
//        }
//    }
//
//    /**
//     * Lädt die Spielwelt aus "spielwelt.json" in die Anzeige
//     */
//    private void ladeSpielweltLeveleditor() {
//        System.out.println("laden");
//        speicherHinweis = 0;
//        levelNr = 1;
//        spielwelt = dateiService.ladeSpielwelt("spielwelt.json");
//        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//        tileMap = (TileMap) level.getTileMap();
//        levelCount = spielwelt.getSzenenanzahl();
//    }
//
//    /**
//     * Leert das aktuelle Level
//     */
//    private void leereLevel() {
//        System.out.println("leeren");
//        //Java hat ja einen Garbage Collector. Also wäre auch folgendes möglich.
//        tileMap = new TileMap();
//        ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//        level.clearPosition();
//        //Anstatt alle Felder mit Wiese zu nullen wie unten
//        //leereTilemap();
//    }
//
//    /**
//     * Erstellt ein neues Level
//     */
//    private void neuesLevel() {
//        System.out.println("level");
//        ILevel level = new DummyLevel();
//        ILevel iLevel1 = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//        iLevel1.setTilemap(tileMap);
//        spielwelt.addSzene(level, levelNr);
//
//        ILevel iLevel2 = (ILevel) this.spielwelt.getSzene(levelNr);
//        iLevel2.clearPosition();
//        tileMap = new TileMap();
//        iLevel2.setTilemap(tileMap);
//        levelCount++;
//        levelNr++;
//        anzeigeTitelLevel(levelNr);
//    }
//
//    /**
//     * Erstellt eine neue Siedlung
//     */
//    private void neueSiedlung() {
//        System.out.println("Siedlung");
//        ISiedlung siedlung = new DummySiedlung();
//        ILevel iLevel1 = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//        iLevel1.setTilemap(tileMap);
//        spielwelt.addSzene(siedlung, levelNr);
//
//        ISiedlung iSiedlung2 = (ISiedlung) this.spielwelt.getSzene(levelNr);
//        iSiedlung2.clearPosition();
//        tileMap = new TileMap();
//        iSiedlung2.setTilemap(tileMap);
//        levelCount++;
//        levelNr++;
//        anzeigeTitelLevel(levelNr);
//    }
//
//    /**
//     * Springt in der Spielwelt ein Level zurück
//     */
//    private void springeZurueck() {
//        if (levelCount >= 2 && levelNr > 1){
//            ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//            level.setTilemap(tileMap);
//
//            levelNr -= 1;
//            level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//            tileMap = (TileMap) level.getTileMap();
//        }
//        System.out.println("zurück");
//    }
//
//    /**
//     * Springt in der Spielwelt ein Level vor
//     */
//    private void springeVor() {
//        if (levelCount > levelNr){
//            ILevel level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//            level.setTilemap(tileMap);
//
//            levelNr++;
//            level = (ILevel) this.spielwelt.getSzene(this.levelNr-1);
//            tileMap = (TileMap) level.getTileMap();
//        }
//        System.out.println("vor");
//    }
//
//    /**
//     * Prüft den aktuellen Zustand der Spielwelt, ob jedes Level nur eine Spielfigur und ein/mehrere Levelausgänge besitzt.
//     * Falls eine der Bedingungen nicht erfüllt ist, wird der Fehler mithilfe der speicherHinweis Variablen ausgegeben.
//     */
//    private void pruefeSpeicherkriterien() {
//        int szenenAnzahl = spielwelt.getSzenenanzahl();
//
//        for (int i = 0; i < szenenAnzahl; i++){
//            speicherHinweisLevel = i + 1;
//            ILevel level = (ILevel) this.spielwelt.getSzene(i);
//            ITileMap pruefTilemap = level.getTileMap();
//            int sizeKachelarten = pruefTilemap.getKachelarten().size();
//            int sizeMovables = level.getPositionen().size();
//
//            pruefeLevelausgang(pruefTilemap, sizeKachelarten);
//
//            pruefeLevelende(i, sizeMovables);
//
//            pruefeSpielfigur(i, sizeMovables);
//
//            if (speicherHinweis != 0){
//                i = szenenAnzahl;
//            }
//        }
//    }
//
//    /**
//     * Prüft, ob sich mehr als eine Spielfigur in der Szene befindet
//     * @param szenenNr Die aktuelle Szenennummer
//     * @param sizeMovables Die Größe der Movableliste
//     */
//    private void pruefeSpielfigur(int szenenNr, int sizeMovables) {
//        int anzahlSpielfiguren = 0;
//
//        if (speicherHinweis == 0) {
//            for (int j = 0; j < sizeMovables; j++) {
//                ILevel level = (ILevel) this.spielwelt.getSzene(szenenNr);
//                IMovable movable = level.getPositionen().get(j);
//                if (movable instanceof Spielfigur) {
//                    speicherHinweis = 0;
//                    anzahlSpielfiguren++;
//                }
//            }
//            if (anzahlSpielfiguren > 1) {
//                speicherHinweis = 3;
//            } else if (anzahlSpielfiguren == 0){
//                speicherHinweis = 2;
//            }
//        }
//    }
//
//    /**
//     * Prüft, ob sich in der Szene ein Levelende befindet
//     * @param szenenNr Die aktuelle Szenennummer
//     * @param sizeMovables Die Größe der Movableliste
//     */
//    private void pruefeLevelende(int szenenNr, int sizeMovables) {
//        if (speicherHinweis != 0){
//            for (int j = 0; j < sizeMovables; j++) {
//                ILevel level = (ILevel) this.spielwelt.getSzene(szenenNr);
//                IMovable movable = level.getPositionen().get(j);
//                if (movable instanceof Levelende) {
//                    speicherHinweis = 0;
//                    j = sizeMovables;
//                } else {
//                    speicherHinweis = 1;
//                }
//            }
//        }
//    }
//
//    /**
//     * Prüft, ob sich in der Szene ein Levelausgang befindet
//     * @param pruefTilemap Die zu prüfende Tilemap.
//     * @param sizeKachelarten Die Anzahl der Verschiedenen Kachelarten.
//     */
//    private void pruefeLevelausgang(ITileMap pruefTilemap, int sizeKachelarten) {
//        for (int j = 0; j < sizeKachelarten; j++){
//            if (pruefTilemap.getKachelarten().get(j) instanceof Levelausgang){
//                speicherHinweis = 0;
//                j = sizeKachelarten;
//            } else {
//                speicherHinweis = 1;
//            }
//        }
//    }
//
//    /**
//     * Gibt das Verzeichnis mit den Bildern zurück
//     * @return Dictionary mit allen Movable Bildern
//     */
//    @Override
//    public Dictionary getImages() {
//        return images;
//    }
//
//    /**
//     * Setzt ein Verzeichnis in das aktuelle image Verzeichnis
//     * @param images Dictionary welches gesettet werden soll
//     */
//    @Override
//    public void setImages(Dictionary images) {
//        this.images = images;
//    }
//}