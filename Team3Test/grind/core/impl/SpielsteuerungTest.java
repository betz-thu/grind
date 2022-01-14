package grind.core.impl;

import org.junit.Before;
import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.kacheln.impl.TileMap;
import grind.movables.impl.Schwert;
import grind.movables.impl.Spielfigur;
import grind.movables.monster.DornPflanze;
import grind.movables.monster.Monster;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Test;
import processing.core.PApplet;
import static org.junit.Assert.*;

public class SpielsteuerungTest {
    Spielsteuerung spielsteuerung = new Spielsteuerung();
    private IKachel kachel;
    private Spielfigur figur;
    private Monster monster;
    private TileMap tileMap;
    private PApplet app;
    private Schwert schwert;

    @Before
    public void setUp() throws Exception {
        kachel = new Levelausgang();
        figur = new Spielfigur(100f, 100f, Richtung.S);
        monster = new DornPflanze(120f, 100f, tileMap);
        app = new PApplet();
        schwert = new Schwert(100, 100, 1, 3);
    }

    @Test
    public void ueberpruefeLevelende(){
        if(kachel instanceof Levelausgang) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void pruefeKollisionen() {
        int anfangsLebensenergie = monster.getLebensenergie();
        if (app.key == ' '){
            pruefeKollisionen();
            Assert.assertEquals(anfangsLebensenergie - schwert.getSchaden(), monster.getLebensenergie());
        }
    }
}