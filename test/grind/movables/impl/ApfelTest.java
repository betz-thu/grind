package grind.movables.impl;

import grind.util.Einstellungen;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApfelTest {

    private Spielfigur figur;
    private Apfel apfel;
    private Apfel apfel2;

    @Before
    public void setUp() throws Exception {
        apfel = new Apfel(100, 100);
        apfel2 = new Apfel(100, 120);
        figur = new Spielfigur(200, 200, Richtung.S);
    }

    @Test
    public void beimAnwenden() {
        figur.lebensenergie = 50;
        apfel.beimAnwenden(figur);
        Assert.assertEquals(60, figur.lebensenergie);
    }

    @Test
    public void beimSammeln() {
        apfel.beimSammeln(figur);
        Assert.assertTrue(figur.getInventar().contains(apfel));
    }
}