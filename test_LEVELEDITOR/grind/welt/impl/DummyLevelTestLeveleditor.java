package grind.welt.impl;

import grind.kacheln.ITileMap;
import grind.kacheln.impl.TileMap;
import grind.movables.IMovable;
import grind.movables.impl.Apfel;
import grind.movables.impl.Gold;
import grind.welt.ILevel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;


public class DummyLevelTestLeveleditor {

    ITileMap tileMap;
    ArrayList<IMovable> positionen = new ArrayList<>();
    IMovable tempApfel;
    IMovable tempGold;
    ILevel level;

    @Before
    public void setUp() throws Exception {
        this.level = new DummyLevel();
        this.tileMap = new TileMap();
        this.tempApfel = new Apfel(0,0,0,0);
        this.tempGold = new Gold(0,0);
        this.level.addPosition(tempApfel);
        this.level.addPosition(tempGold);
    }

    @Test
    public void getPositionen() {
        Assert.assertEquals(2, level.getPositionen().size());
        Assert.assertEquals(tempGold, level.getPositionen().get(1));
    }

    @Test
    public void addPosition() {
        level.addPosition(new Gold(0,0));
        Assert.assertEquals(3, level.getPositionen().size());
        Assert.assertTrue(level.getPositionen().get(2) instanceof Gold);
    }

    @Test
    public void clearPosition() {
        level.clearPosition();
        Assert.assertEquals(0, level.getPositionen().size());
    }
}