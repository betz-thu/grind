package grind.movables.monster;

import grind.kacheln.impl.TileMap;
import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterMEGAtronikerTest {

    public static final int SCHADEN = 12;
    public static final int LEBENSENERGIE = 90;

    private Monster monster;
    private TileMap tileMap;
    private Spielfigur spielfigur;


    @Before
    public void setUp() {
        monster = new Geist(100, 100, tileMap);
        spielfigur = new Spielfigur(200,300, Richtung.N);
        monster.setSchaden(SCHADEN);
        spielfigur.setLebensenergie(LEBENSENERGIE);
    }

    @Test
    public void getGeschwindigkeit() {
    }

    @Test
    public void setGeschwindigkeit() {
    }

    @Test
    public void bewege() {
    }

    @Test
    public void getLebensenergie() {
    }

    @Test
    public void getGroesse() {
    }

    @Test
    public void setSchaden() {
    }

    @Test
    public void isInDerNaehe() {
    }

    @Test
    public void reduziereLebensenergie() {
        int anfangswert = monster.getLebensenergie();
        monster.reduziereLebensenergie(SCHADEN);
        Assert.assertEquals(anfangswert-SCHADEN, monster.getLebensenergie());
    }

    @Test
    public void inDerNaehe() {
    }

    @Test
    public void resetTimerNaehe() {
    }

    @Test
    public void beiKollision() {
    }

    @Test
    public void setHatKollidiert() {
    }

    @Test
    public void isHatKollidiert() {
    }

    @Test
    public void resetTimerAttac() {
    }

    @Test
    public void getSpielmodell() {
    }

    @Test
    public void setSpielmodell() {
    }

}
