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
import grind.util.Einstellungen;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.internal.builders.JUnit4Builder;
import processing.core.PApplet;
import static org.junit.Assert.*;

/**
 * @author MEGAtroniker
 * als wir diese geöffnet haben, gab es die folgende Fehlermeldung:
 *
 * org.junit.runners.model.InvalidTestClassError: Invalid test class 'grind.core.impl.SpielsteuerungTest':
 *   1. The class grind.core.impl.SpielsteuerungTest is not public.
 *   2. Test class should have exactly one public constructor
 *   3. No runnable methods
 *
 *   laut stackoverflow ein Problem zwischen JUnit 4 vs 5!?
 */

/*class SpielsteuerungTest {
    Spielsteuerung spielsteuerung = new Spielsteuerung();
    private IKachel kachel;
    private Spielfigur figur;
    private Monster monster;
    private TileMap tileMap;
    private PApplet app;
    private Schwert schwert;

    @BeforeEach
    void setUp() {
        kachel = new Levelausgang();
        figur = new Spielfigur((float) 100, (float) 100, Richtung.S);
        monster = new DornPflanze((float) 120, (float) 100, tileMap);
        app = new PApplet();
        schwert = new Schwert(100, 100, 1);
    }

    @Test
    void ueberpruefeLevelende() {
        if (kachel instanceof Levelausgang) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }

    }

    @Test
    void pruefeKollisionen() {
        int anfangsLebensenerige = monster.getLebensenergie();
        if (app.key == ' ') {
            pruefeKollisionen();
            Assert.assertEquals(anfangsLebensenerige - schwert.getSchaden(), monster.getLebensenergie());
        }
    }
}*/

