package grind.core.impl;

import grind.util.Einstellungen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeveleditorTest {
    Spielsteuerung spielsteuerung = new Spielsteuerung();

    @Before
    public void setUp() throws Exception {
        try {
            spielsteuerung.settings();
        } catch (Exception e) {
            System.out.println("fehlermeldung size");
        }
    }

    @Test
    public void settings() {
        System.out.println("X:Y  " + Einstellungen.ANZAHL_KACHELN_X * Einstellungen.LAENGE_KACHELN_X + ":" + Einstellungen.ANZAHL_KACHELN_Y * Einstellungen.LAENGE_KACHELN_Y);
        assertEquals(Einstellungen.ANZAHL_KACHELN_X * Einstellungen.LAENGE_KACHELN_X, spielsteuerung.getSpielfeldBreite());
        assertEquals(Einstellungen.ANZAHL_KACHELN_Y * Einstellungen.LAENGE_KACHELN_Y, spielsteuerung.getSpielfeldHoehe());
    }


    @Test
    public void setup() {
    }

    @Test
    public void mousePressed() {
    }

    @Test
    public void anzeigeTitelLevel() {
    }

    @Test
    public void getImages() {
    }

    @Test
    public void setImages() {
    }
}