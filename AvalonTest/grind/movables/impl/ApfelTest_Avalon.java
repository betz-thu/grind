package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApfelTest_Avalon {

    private Apfel apfel;
    private Spielfigur figur;

    @Before
    public void setUp() throws Exception {
        apfel = new Apfel(100, 100, 3, 3);
        figur = new Spielfigur( 200, 200, Richtung.N);
        figur.gold = 5;
    }
    @Test
    public void beimKaufen () {
        apfel.beimKaufen(figur);
        Assert.assertTrue(figur.getInventar().contains(apfel));
        Assert.assertEquals(5-apfel.getWert(), figur.getGold());
    }
}