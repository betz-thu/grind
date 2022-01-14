package grind.welt.impl;

import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DummySpielweltTestLeveleditor {

    ILevel levelTest;

    ISpielwelt spielwelt;

    @Before
    public void setUp() throws Exception {
        this.spielwelt = new DummySpielwelt();
        this.levelTest = new DummyLevel();
    }

    @Test
    public void getSzene() {
        Assert.assertTrue(spielwelt.getSzene(0) instanceof DummyLevel);
    }

    @Test
    public void addSzene(){
        spielwelt.addSzene((ISzene) levelTest, 1);
        Assert.assertEquals(2, spielwelt.getSzenenanzahl());
        Assert.assertEquals(levelTest, spielwelt.getSzene(1));
    }

    @Test
    public void getSzenenanzahl() {
        Assert.assertEquals(1, spielwelt.getSzenenanzahl());
    }

    @Test
    public void removeSzenen() {
        spielwelt.removeSzenen();
        Assert.assertEquals(1, spielwelt.getSzenenanzahl());

    }

}
