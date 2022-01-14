package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchwertTest_Avalon {

    private Spielfigur figur;
    private Schwert schwert;

    @Before
    public void setUp() throws Exception {
        figur = new Spielfigur(200, 200, Richtung.randomRichtung());
        schwert = new Schwert(100, 100, 2, 3);
        figur.gold = 5;
    }
    @Test
    public void beimKaufen () {
        schwert.beimKaufen(figur);
        Assert.assertTrue(figur.getInventar().contains(schwert));
        Assert.assertEquals(5-schwert.getWert(), figur.getGold());
    }
}