package grind.movables.monster;

import grind.kacheln.impl.TileMap;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    private Monster monster;
    private TileMap tileMap;

    @BeforeEach
    void setUp() {
        monster = new Geist((float)100, (float)100, tileMap,20);
    }

    @Test
    void reduziereLebensenergie() {
        int anfangswert = monster.getLebensenergie();
        monster.reduziereLebensenergie(10);
        Assert.assertEquals(anfangswert-10, monster.getLebensenergie());
    }
}