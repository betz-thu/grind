package grind.movables.monster;

import grind.kacheln.impl.TileMap;
import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest2 {
    public static final int SCHADEN = 12;
    public static final int LEBENSENERGIE = 90;

    private Monster monster;
    private TileMap tileMap;
    private Spielfigur spielfigur;

    @Before
    public void setUp() throws Exception {
        monster = new Geist(200, 300, tileMap);
        spielfigur = new Spielfigur(200, 300, Richtung.N);
        monster.setSchaden(SCHADEN);
        spielfigur.setLebensenergie(LEBENSENERGIE);
    }

    @Test
    public void beiKollision() {
        int spielfigurLebenVorher = spielfigur.getLebensenergie();
        monster.setHatKollidiert(false);
        monster.beiKollision(spielfigur,monster);
        Assert.assertEquals(spielfigurLebenVorher - SCHADEN, spielfigur.getLebensenergie());
    }

    @Test
    public void reduziereLebensenergie() {
        int anfangswert = monster.getLebensenergie();
        monster.reduziereLebensenergie(SCHADEN);
        Assert.assertEquals(anfangswert - SCHADEN, monster.getLebensenergie());
    }
}