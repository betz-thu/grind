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

    }
}