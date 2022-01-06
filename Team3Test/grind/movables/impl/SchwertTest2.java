package grind.movables.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchwertTest2 {
    public static final int STUFE = 2;

    private Schwert schwert;

    @Before
    public void setUp() throws Exception {
        schwert = new Schwert(10, 10, STUFE);
    }

    @Test
    public void getSchaden() {
        Assert.assertEquals(schwert.getSchaden(), 10*STUFE);
    }
}