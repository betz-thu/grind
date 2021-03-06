package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeiltrankTest {
    private Spielfigur figur;
    private Heiltrank heiltrank;

    @Before
    public void setUp() throws Exception {
        heiltrank = new Heiltrank(100, 100, 3, 3);
        figur = new Spielfigur(200, 200, Richtung.S);
    }
    @Test
    public void beimAnwenden() {
        figur.setLebensenergie(50);
        heiltrank.beimAnwenden(figur);
        Assert.assertEquals(50 + heiltrank.getPunkte(), figur.getLebensenergie());
        Assert.assertFalse(figur.getInventar().contains(heiltrank));
    }

    @Test
    public void beimSammeln() {
        heiltrank.beimSammeln(figur);
        Assert.assertTrue(figur.getInventar().contains(heiltrank));
    }

    @Test
    public void beimKaufen () {
        heiltrank.beimKaufen(figur);
        Assert.assertTrue(figur.getInventar().contains(heiltrank));
        Assert.assertEquals(5-heiltrank.getWert(), figur.getGold());
    }
}
