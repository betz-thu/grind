package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.*;

public class SternTest {

    private Spielfigur figur;
    private Stern stern;

    public static final float GESCHWINDIGKEITSBOOST = Stern.DAUERGESCHWINDIGKEITSBOOST; // nicht ändern. +5f ist so festgelegt für die Geschwindigkeitsänderung in der Stern Klasse
    public static final int IMMUNITÄTSDAUER = Stern.DAUERSTERNEVENT; // [ms] nicht ändern, da in Stern Klasse so festgelegt

    @Before
    public void setUp() throws Exception {
        figur = new Spielfigur(300, 300, Richtung.N);
        stern = new Stern(500, 500);


    }

    @Test
    public void beimSammeln() {

        stern.beimSammeln(figur);

        // Inventargröße der Figur wird um 1 erhöht
        Assert.assertEquals(1, figur.getInventar().size());
    }

    @Test
    public void spielerWirdImmunUndSchneller() {
        float geschwindigkeitVorStern = figur.getGeschwindigkeit();
        stern.beimAnwenden(figur); // beimAnwenden führt nur spielerWirdImmunUndSchneller() aus
        float geschwindigkeitNachStern = figur.getGeschwindigkeit();

        // Testet, ob Geschwindigkeit erhöht ist
        Assert.assertEquals(geschwindigkeitVorStern + GESCHWINDIGKEITSBOOST, geschwindigkeitNachStern, 0.1);
        // Testet, ob Figur immun ist
        Assert.assertEquals(true, figur.isImmun());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                // Testet, ob Figur nach Immunitätsdauer wieder Angreifbar ist.
                Assert.assertEquals(false, figur.isImmun());
                // Testet, ob Geschwindigkeit wieder auf Normalzustand ist.
                Assert.assertEquals(geschwindigkeitVorStern, figur.getGeschwindigkeit(), 0.1);
                timer.cancel();

            }

        }, IMMUNITÄTSDAUER + 1);

    }
}