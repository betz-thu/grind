package grind.core.impl;

import grind.kacheln.IKachel;
import grind.kacheln.impl.TileMap;
import grind.kacheln.impl.Wiese;
import grind.movables.IMovable;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.LaufModus;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummySpielwelt;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

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


                }
            }
            /*if (mouseX <= SpielfeldBreite && mouseY <= SpielfeldHoehe) {
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

            }*/
        }
    }


    }
