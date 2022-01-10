package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import grind.util.LaufModus;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ZombieMEGAtronikerTest {

    private Monster zombie;
    private ITileMap tileMap;
    private Spielsteuerung steuerung;
    private Spielfigur spielfigur;
    public static final int SCHADEN = 10;
    public static final int LEBENSENERGIE = 90;

    @Before
    public void setUp() throws Exception {
        Monster zombie = new Zombie(100,100,tileMap, Richtung.N,steuerung, LaufModus.DEFAULT);
        spielfigur = new Spielfigur(200, 300, Richtung.N);
        zombie.setSchaden(SCHADEN);
        spielfigur.setLebensenergie(LEBENSENERGIE);
    }


    @Test
    public void getGroesse() {
        assertEquals(Einstellungen.LAENGE_KACHELN_X,zombie.getGroesse());
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
    }

    @Test
    public void getGeschwindigkeit() {
    }
}