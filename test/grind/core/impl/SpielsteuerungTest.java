package grind.core.impl;

import grind.kacheln.IKachel;
import grind.kacheln.impl.LeereKachel;
import grind.kacheln.impl.Levelausgang;
import grind.kacheln.impl.TileMap;
import grind.movables.IMovable;
import grind.movables.impl.Schwert;
import grind.movables.impl.Spielfigur;
import grind.movables.monster.DornPflanze;
import grind.movables.monster.Monster;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import static org.junit.jupiter.api.Assertions.*;

class SpielsteuerungTest {
    private IKachel kachel;
    private Spielfigur figur;
    private Monster monster;
    private TileMap tileMap;
    private PApplet app;
    private Schwert schwert;

    @BeforeEach
    void setUp() {
        kachel = new Levelausgang();
        figur = new Spielfigur((float)100, (float)100, Richtung.S,50);
        monster = new DornPflanze((float)120, (float)100,tileMap, 30);
        app = new PApplet();
        schwert = new Schwert(100, 100, 1);
    }

    @Test
    void ueberpruefeLevelende() {
        if(kachel instanceof Levelausgang){
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
        }

    }

    @Test
    void pruefeKollisionen() {
        int anfangsLebensenerige = monster.getLebensenergie();
        if (app.key==' '){
            pruefeKollisionen();
            Assert.assertEquals(anfangsLebensenerige-schwert.getSchaden(), monster.getLebensenergie());
        }


        /**
         * @author MEGAtroniker
         * ab hier ist unsere Test.
         */
import grind.util.Einstellungen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpielsteuerungTest {
    Spielsteuerung spielsteuerung = new Spielsteuerung();

    @Before
    public void setUp() throws Exception {
        try {
            spielsteuerung.settings();
        } catch (Exception e) {
            System.out.println("fehlermeldung size");
        }
    }

    @Test
    public void settings() {
        System.out.println("X:Y  "+Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X+":"+Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y);
        assertEquals(Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X,spielsteuerung.getSpielfeldBreite());
        assertEquals(Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y,spielsteuerung.getSpielfeldHoehe());
    }

    @Test
    public void setup() {
    }

    @Test
    public void draw() {
    }

    @Test
    public void pruefeKollisionen() {
    }

    @Test
    public void mousePressed() {
    }

    @Test
    public void ueberpruefeLevelende() {
    }

    @Test
    public void getKachelByCoordinates() {
    }

    @Test
    public void isSpielfeldrand() {
        assertFalse(spielsteuerung.isSpielfeldrand(100,100));
        assertTrue(spielsteuerung.isSpielfeldrand(100,2000));
        assertTrue(spielsteuerung.isSpielfeldrand(2000,100));
        assertTrue(spielsteuerung.isSpielfeldrand(2000,2000));
    }

    @Test
    public void isErlaubteKoordinate() {

    }
}