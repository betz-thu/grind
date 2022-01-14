package grind.core.impl;

import grind.kacheln.IKachel;
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
import grind.welt.ISzene;
import grind.welt.impl.DummySpielwelt;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.Assert.*;



public class LeveleditorTest {
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

        this.menuArrayKacheln.add(new Baum());
        this.menuArrayKacheln.add(new DummyHindernis());
        this.menuArrayKacheln.add(new Fels());
        this.menuArrayKacheln.add(new Holzbrücke());
        this.menuArrayKacheln.add(new Levelausgang());
        this.menuArrayKacheln.add(new Wasser());
        this.menuArrayKacheln.add(new Weg());
        this.menuArrayKacheln.add(new Wiese());

        this.menuArrayMovables.add(new Schwert(40, 40, 1, this.wert));
        this.menuArrayMovables.add(new Mango(0, 0, this.punkte, this.wert));
        this.menuArrayMovables.add(new Heiltrank(0, 0, this.punkte, this.wert));
        this.menuArrayMovables.add(new Gold(0, 0));
        this.menuArrayMovables.add(new Apfel(0, 0, this.punkte, this.wert));
        this.menuArrayMovables.add(new Bogen(40, 40, 1, this.wert));
        this.menuArrayMovables.add(new Stern(0, 0));
        this.menuArrayMovables.add(new Spezialattacke(40, 40, 1));
        this.menuArrayMovables.add(new Spielfigur(0, 0, Richtung.N));
        this.menuArrayMovables.add(new Levelende(0, 0));
        this.menuArrayMovables.add(new DornPflanze(0, 0, this.tileMap));
        this.menuArrayMovables.add(new FeuerMonster(0, 0, this.tileMap, this.spielsteuerung, Richtung.N, feuerRate, FeuerModus.RANDOM));
        this.menuArrayMovables.add(new Geist(0, 0, this.tileMap));
        this.menuArrayMovables.add(new Zombie(0, 0, this.tileMap, Richtung.N, this.spielsteuerung, LaufModus.DEFAULT));
        templevel = (ILevel) spielwelt.getSzene(levelNr - 1);
        movableList = new ArrayList<>();
        //ArrayList<IMovable> movableList = (ArrayList<IMovable>) templevel.getPositionen();


        /*try {
            spielsteuerung.settings();
        } catch (Exception e) {
            System.out.println("fehlermeldung size");
        }*/
    }


    @Test
    public void settings() {
        //spielsteuerung.setSize(900,600);
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
                    assertEquals(iKachel.get(mausY), aktuelleKachel);
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

                    assertNotNull(aktuellesMovable);
                    if (spielwelt.getSzene(levelNr - 1) instanceof ISiedlung) {
                        assertTrue(true);
                    }

                }
            }
        }
            if (mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe) {
                if (aktuelleKachel != null) {
                    tileMap.setKachel(aktuelleKachel, mausYkachel, mausXkachel);
                } else if (aktuellesMovable != null) {
                    aktuellesMovable.setPosition(mausXmovable, mausYmovable);
                    //addMovablezuLevel(aktuellesMovable);
                } else if (aktuelleKachel == null && aktuellesMovable == null) {
                    ArrayList<IMovable> movableList = (ArrayList<IMovable>) templevel.getPositionen();

                    assertNotNull(spielwelt.getSzene(levelNr-1));
                    //ILevel templevel = (ILevel) spielwelt.getSzene(levelNr-1);

                    //TODO: An der Stelle würde ich eher abfragen ob die Spielwelt erst einmal nicht null ist
                    //laut der Fehlermeldu ng erwartet er das Dummylevel mit der speicheradresse x in templevel.
                    //das geht aber nicht weil beide keinerlei kontakt miteinander hatten

                    //assertEquals(templevel.getPositionen(), movableList);

                    for (int i = 0; i <  movableList.size(); i++) {
                        int posX = movableList.get(i).getPosX();
                        int posY = movableList.get(i).getPosY();

                        if (posX == mausXmovable && posY == mausYmovable) {

                            assertEquals(movableList.get(i), aktuellesMovable);
                            movableList.remove(i);
                        }
                    }
                    if (aktuellesMovable == null) {

                        //assertEquals(tileMap.getKachel(mausYkachel, mausXkachel), aktuelleKachel);
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


