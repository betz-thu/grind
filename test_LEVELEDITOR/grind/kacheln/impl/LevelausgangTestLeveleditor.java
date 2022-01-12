package grind.kacheln.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LevelausgangTestLeveleditor {

    Levelausgang levelAusgang;

    @Before
    public void setUp() throws Exception {
        this.levelAusgang = new Levelausgang();
    }

    @Test
    public void istBetretbar() {
        Assert.assertTrue(levelAusgang.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertFalse(levelAusgang.istHindernis());
    }

}
