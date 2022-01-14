package grind.kacheln.impl;


import grind.kacheln.ITileMap;
import grind.util.Einstellungen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TilemapTestLeveleditor {

    ITileMap tilemap;
    @Before
    public void setUp() throws Exception {
        this.tilemap = new TileMap();
    }

    @Test
    public void zufaelligeTileMap() {
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                tilemap.setKachel(null, i,j);
                Assert.assertNull(tilemap.getKachel(i,j));
            }
        }
        tilemap.zufaelligeTileMap();
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                Assert.assertTrue(tilemap.getKachel(i,j) instanceof Wiese);
            }
        }
    }

    @Test
    public void getKachel() {
        Assert.assertTrue(tilemap.getKachel(100,100) instanceof DummyHindernis);
        Assert.assertTrue(tilemap.getKachel(1,1) instanceof Wiese);
        Assert.assertTrue(tilemap.getKachel(-1,-1) instanceof DummyHindernis);
    }
}
