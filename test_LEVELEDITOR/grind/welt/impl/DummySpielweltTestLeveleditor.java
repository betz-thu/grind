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

    List<ISzene> szenen = new ArrayList<>();
    ILevel levelTest;
    int anzahlSzenen;

    @Before
    public void setUp() throws Exception {
        this.levelTest = new DummyLevel();
        this.szenen.add(0, this.levelTest);
        this.anzahlSzenen = szenen.size();
    }

    @Test
    public void getSzene() {
        Assert.assertEquals(levelTest, szenen.get(0));
    }

    @Test
    public void addSzene(){
        szenen.add(new DummyLevel());
        Assert.assertEquals(2, szenen.size());
    }

    @Test
    public void getSzenenanzahl() {
        Assert.assertEquals(1, szenen.size());
    }

    @Test
    public void removeSzenen() {
        szenen.clear();
        Assert.assertEquals(0,szenen.size());
        szenen.add(new DummyLevel());
        Assert.assertEquals(1, szenen.size());

    }

}
