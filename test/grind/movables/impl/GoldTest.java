package grind.movables.impl;

import grind.util.Einstellungen;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoldTest {

    Gold gold;
    Spielfigur figur;

    @Before
    public void setUp() throws Exception {
        gold = new Gold(100, 200, Einstellungen.GROESSE_GOLD);
        figur = new Spielfigur(150, 150, Richtung.S, Einstellungen.GROESSE_SPIELFIGUR);
    }

    @Test
    public void beimSammeln() {
        int goldVorher = figur.gold;
        this.gold.beimSammeln(figur);
        Assert.assertEquals(goldVorher + 1, figur.gold);
    }

    /**
     * Stelle sicher, dass das Goldstück sich nicht bewegt.
     */
    @Test
    public void bewege() {
        int posX = gold.getPosX();
        int posY = gold.getPosY();
        gold.bewege();
        Assert.assertEquals(posX, gold.getPosX());
        Assert.assertEquals(posY, gold.getPosY());
    }

    @Test
    public void setPosition() {
    }

    @Test
    public void getWert() {
    }
}