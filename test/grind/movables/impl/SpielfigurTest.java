package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

class SpielfigurTest {
    private Schwert schwert1;
    private Schwert schwert2;
    private Spielfigur figur;

    @Before
    void setUp() {
        schwert1 = new Schwert(100, 100, 1, 3);
        schwert2 = new Schwert(200, 200, 2, 3);
        figur = new Spielfigur((float)100, (float)100, Richtung.S);
    }

    @Test
    void aktiviereWaffe() {
        figur.setAktiveWaffe(schwert2);
        figur.setAktiveWaffe(schwert1);
        boolean waffeImInventar = false;
        for (int i=0; i<figur.getInventar().size(); i++){
            if (figur.getInventar().get(i) instanceof Waffe){
                waffeImInventar = true;
            }
        }
        Assert.assertTrue(waffeImInventar);
        Assert.assertTrue(figur.getInventar().contains(schwert2));
//        Assert.assertEquals(figur.getWaffe(), schwert1);
    }


}