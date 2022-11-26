package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MangoTest {
    
    // minor change for testing purposes

    // test

    private Spielfigur figur;
    private Mango mango;

    @Before
    public void setUp() throws Exception {
        mango = new Mango(100, 100, 3, 3);
        figur = new Spielfigur(200, 200, Richtung.N);
        figur.gold = 5;
    }

    @Test
    public void beimAnwenden() {
        figur.setLebensenergie(50);
        mango.beimAnwenden(figur);
        Assert.assertEquals(50 + mango.getPunkte(), figur.getLebensenergie());
    }

    @Test
    public void beimSammeln() {
        mango.beimSammeln(figur);
        Assert.assertTrue(figur.getInventar().contains(mango));
    }

    @Test
    public void beimKaufen () {
        mango.beimKaufen(figur);
        Assert.assertTrue(figur.getInventar().contains(mango));
        Assert.assertEquals(5-mango.getWert(), figur.getGold());
    }
}
