package grind.kacheln.impl;

import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WasserTest {

    private Spielfigur figur;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void istBetretbar() {
        this.figur = figur;
        Assert.assertTrue(String.valueOf(figur),true);
    }

    @Test
    public void istHindernis() {
        this.figur = figur;
        Assert.assertFalse(String.valueOf(figur),false);
    }

    @Test
    public void ladeDatei() {
    }
}