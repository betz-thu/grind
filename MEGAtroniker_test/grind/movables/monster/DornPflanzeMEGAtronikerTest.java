package grind.movables.monster;

import grind.kacheln.impl.TileMap;
import grind.util.Einstellungen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DornPflanzeMEGAtronikerTest {

    //die meisten Methoden werden schon in MonsterMEGAtronikerTest getestet.


    DornPflanze dornPflanze = new DornPflanze(1,1,new TileMap());

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void bewege() {
        int posX = dornPflanze.getPosX();
        int posY = dornPflanze.getPosY();
        dornPflanze.bewege();
        assertEquals(dornPflanze.getPosX(),posX);
        assertEquals(dornPflanze.getPosY(),posY);
    }

    @Test
    public void getGroesse() {
        assertEquals(dornPflanze.getGroesse(), Einstellungen.LAENGE_KACHELN_X);
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

    @Test
    public void setGeschwindigkeit() {
        assertEquals(dornPflanze.getGeschwindigkeit(),0);
    }

    @Test
    public void getGeschwindigkeit() {
    }
}