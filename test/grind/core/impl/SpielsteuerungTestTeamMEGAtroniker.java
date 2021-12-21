package grind.core.impl;

import grind.util.Einstellungen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


class SpielsteuerungTestTeamMEGAtroniker {
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
    public void draw() {
    }

    @Test
    public void pruefeKollisionen() {
    }

    @Test
    public void mousePressed() {
    }

    @Test
    public void ueberpruefeLevelende() {
    }

    @Test
    public void getKachelByCoordinates() {
    }

    @Test
    public void isSpielfeldrand() {
        assertFalse(spielsteuerung.isSpielfeldrand(100, 100));
        assertTrue(spielsteuerung.isSpielfeldrand(100, 2000));
        assertTrue(spielsteuerung.isSpielfeldrand(2000, 100));
        assertTrue(spielsteuerung.isSpielfeldrand(2000, 2000));
    }

    @Test
    public void isErlaubteKoordinate() {

    }
}