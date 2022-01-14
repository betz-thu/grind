package grind.movables.monster;

import grind.kacheln.impl.TileMap;
import grind.util.Einstellungen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeistMEGAtronikerTest {

    //die meisten Methoden werden schon in MonsterMEGAtronikerTest getestet.

    Geist geist = new Geist(1,1,new TileMap());

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void zeichne() {
    }

    @Test
    public void bewege() {
    }

    @Test
    public void getGroesse() {
        assertEquals(geist.getGroesse(), Einstellungen.LAENGE_KACHELN_X / 2);
    }

    @Test
    public void vorBetreten() {
    }

    @Test
    public void getSpielmodell() {
    }

    @Test
    public void setSpielmodell() {
    }
}