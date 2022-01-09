package grind.movables.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BogenTest {
    public static final int STUFE = 5;

    private Bogen bogen;

    @Before
    public void setUp() throws Exception {
        bogen = new Bogen(20, 20, STUFE, 3);
    }

    @Test
    public void getSchaden() {
        Assert.assertEquals(bogen.getSchaden(), 0);
    }
}