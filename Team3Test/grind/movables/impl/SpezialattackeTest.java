package grind.movables.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpezialattackeTest {
    public static final int STUFE = 8;

    private Spezialattacke spezialattacke;

    @Before
    public void setUp() throws Exception {
        spezialattacke = new Spezialattacke(10, 10, STUFE);
    }

    @Test
    public void getSchaden() {
        Assert.assertEquals(spezialattacke.getSchaden(), STUFE*60);
    }
}