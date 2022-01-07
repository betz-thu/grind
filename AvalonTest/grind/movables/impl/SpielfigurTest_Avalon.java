package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpielfigurTest_Avalon {
    private Spielfigur figur;
    private Apfel apfel;
    private Schwert schwert;


    @Before
    public void setUp() throws Exception {
        figur = new Spielfigur(200,200, Richtung.N);
        apfel = new Apfel(200,200);
        schwert = new Schwert(10, 50, 50);
        figur.getInventar().add(apfel);
        figur.getInventar().add(schwert);
    }

    @Test
    public void klickItems() {
        figur.klickItems(200, 200);
        figur.klickItems(10, 50); //Schwert bleibt im Inventar
        Assert.assertEquals(1, figur.getInventar().size());

    }

    @Test
    public void getInvPos() {
        Assert.assertEquals(0, figur.getInvPos(200,200));
        Assert.assertEquals(1, figur.getInvPos(10,50));
        Assert.assertEquals(-1, figur.getInvPos(50,50));

    }

    @Test
    public void verringereGold() {
        figur.gold = 10;
        figur.verringereGold(5);
        Assert.assertEquals(5,figur.getGold());
    }
}