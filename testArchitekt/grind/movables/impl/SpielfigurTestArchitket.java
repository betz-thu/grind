package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.*;

public class SpielfigurTestArchitket {

    private Spielfigur figur;
    public static final int SCHADEN = 15;
    public static final int IMMUNITÄTSDAUER = 2000; // in ms


    @Before
    public void setUp() throws Exception {

        figur = new Spielfigur(300, 300, Richtung.N);


    }

    @Test
    public void erhalteSchaden() {
        int lebenVorher = figur.getLebensenergie();
        figur.erhalteSchaden(SCHADEN);
        int lebenNacher = figur.getLebensenergie();

        Assert.assertEquals(lebenVorher - SCHADEN ,lebenNacher); // Testet, ob Figur schaden erhält

        figur.erhalteSchaden(SCHADEN);
        Assert.assertEquals(true, figur.isImmun()); // Testet, ob Figur nach Schaden kurzzeitig immun ist.
        Assert.assertEquals(lebenNacher, figur.getLebensenergie()); // Testet, ob Figur während Immunität auch kein Schaden bekommt

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                figur.erhalteSchaden(SCHADEN);
                // Testet, ob nach Figur nach einer gewissen Immunitätsdauer wieder Schaden bekommt
                Assert.assertEquals(lebenNacher -SCHADEN, figur.getLebensenergie());
            }

        }, IMMUNITÄTSDAUER + 1);

    }


}