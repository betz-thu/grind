package grind.movables.monster;

import grind.core.impl.DateiService;
import grind.core.impl.Spielmodell;
import grind.core.impl.Spielsteuerung;
import grind.kacheln.impl.TileMap;
import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.Richtung;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import grind.welt.impl.DummyLevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.RepetitionInfo;

import javax.swing.*;

import static org.junit.Assert.*;

public class MonsterMEGAtronikerTest {

    public static final int SCHADEN = 12;
    public static final int LEBENSENERGIE = 90;

    private Monster monster;
    private TileMap tileMap;
    private Spielfigur spielfigur;
    private DateiService dateiService;
    Spielmodell spielmodell = new Spielmodell(new ISpielwelt() {
        @Override
        public ISzene getSzene(int n) {
            return null;
        }

        @Override
        public void addSzene(ISzene szene, int szenenNummer) {

        }

        @Override
        public int getSzenenanzahl() {
            return 0;
        }

        @Override
        public void removeSzenen() {

        }
    }, new Spielsteuerung());


    @Before
    public void setUp() {
        monster = new Geist(100, 100, tileMap);
        spielfigur = new Spielfigur(100,200, Richtung.N);
        monster.setSchaden(SCHADEN);
        spielfigur.setLebensenergie(LEBENSENERGIE);
    }

    @Test
    public void getGeschwindigkeit() {
        assertEquals(monster.getGeschwindigkeit(),1);
    }

    @Test
    public void setGeschwindigkeit() {
        assertEquals(monster.getGeschwindigkeit(),1);
        int neueGeschwindigkeit = 3;
        monster.setGeschwindigkeit(neueGeschwindigkeit);
        assertEquals(monster.getGeschwindigkeit(),neueGeschwindigkeit);
    }

    @Test
    public void bewege() {
        int posY = monster.getPosX();
        int posX = monster.getPosY();
        monster.bewege();
        assertNotEquals(monster.getPosX(),posX);
        assertNotEquals(monster.getPosY(),posY);
    }

    @Test
    public void getLebensenergie() {
        assertEquals(monster.getLebensenergie(),100);
    }

    @Test
    public void getGroesse() {
        assertEquals(monster.getGroesse(), Einstellungen.LAENGE_KACHELN_X/2);
    }

    @Test
    public void setSchaden() {
        int schaden = 30;
        monster.setSchaden(schaden);
        assertEquals(monster.getSchaden(),schaden);
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
        spielfigur.setPosition(100,170);
        monster.inDerNaehe(spielfigur,monster);
        assertTrue(monster.isInDerNaehe());
    }

    @Test
    public void resetTimerNaehe() {
        if(System.currentTimeMillis()-monster.getStartTimeNaehe()<2000){
            assertTrue(monster.isInDerNaehe());
        } else assertFalse(monster.isInDerNaehe());
    }

    @Test
    public void beiKollision() {
        spielfigur.setPosition(100,100);
        monster.beiKollision(spielfigur,monster);
        assertTrue(monster.isHatKollidiert());
    }

    @Test
    public void setHatKollidiert() {
        monster.setHatKollidiert(true);
        assertTrue(monster.isHatKollidiert());
    }

    @Test
    public void isHatKollidiert() {
        monster.setHatKollidiert(false);
        assertFalse(monster.isHatKollidiert());
    }

    @Test
    public void resetTimerAttac() {
        if(System.currentTimeMillis()-monster.getStartTimeAttac() <2000){
            assertTrue(monster.isHatKollidiert());
        } else assertFalse(monster.isHatKollidiert());
    }

    @Test
    public void getSpielmodell() {
        monster.setSpielmodell(spielmodell);
        assertEquals(monster.getSpielmodell(),spielmodell);
    }

    @Test
    public void setSpielmodell() {
        monster.setSpielmodell(spielmodell);
        assertEquals(monster.getSpielmodell(),spielmodell);
    }

}
