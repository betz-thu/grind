package grind.movables.monster;

import grind.kacheln.impl.TileMap;
import grind.movables.ISpielfigur;
import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import grind.util.Richtung;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    private Monster monster;
    private TileMap tileMap;
    private Spielfigur spielfigur;
    int schaden = 10;

    @BeforeEach
    void setUp() {
        monster = new Geist((float)100, (float)100, tileMap);
        spielfigur = new Spielfigur(200,300, Richtung.N);
        monster.setSchaden(schaden);
        spielfigur.setLebensenergie(90);
    }

    @Test
    void reduziereLebensenergie() {
        int anfangswert = monster.getLebensenergie();
        monster.reduziereLebensenergie(10);
        Assert.assertEquals(anfangswert-10, monster.getLebensenergie());
    }

    @Test
    void beiKollision() {
        int spielfigurLebenVorher = spielfigur.getLebensenergie();
        monster.beiKollision(spielfigur);
        Assert.assertEquals(spielfigurLebenVorher-schaden,  spielfigur.getLebensenergie());
    }
}