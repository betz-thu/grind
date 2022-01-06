package grind.movables.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PfeilTest {
    public static final int STUFE = 1;

    private Pfeil pfeil;

    @Before
    public void setUp() throws Exception {
        pfeil = new Pfeil(5, 5, STUFE);
    }

    @Test
    public void getSchaden() {
        Assert.assertEquals(pfeil.getSchaden(), 15*STUFE);
    }


}