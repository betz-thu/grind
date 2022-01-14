package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpielfigurTest2 {
    private Schwert schwert1;
    private Schwert schwert2;
    private Spielfigur figur;

    @Before
    public void setUp() throws Exception {
        schwert1 = new Schwert(100, 100, 1, 3);
        schwert2 = new Schwert(200, 200, 2, 3);
        figur = new Spielfigur(100f, 100f, Richtung.S);

    }

    @Test
    public void aktiviereWaffe(){
        figur.setAktiveWaffe(schwert1);
        figur.inventar.add(schwert2);
        figur.benutze(0);

        boolean waffeImInventar = false;
        for (int i=0; i<figur.getInventar().size(); i++){
            if (figur.getInventar().get(i) instanceof Waffe){
                waffeImInventar = true;
            }
        }
        Assert.assertTrue(waffeImInventar);
        Assert.assertTrue(figur.getInventar().contains(schwert1));
    }
}