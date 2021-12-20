package grind.core.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpielsteuerungTest {
    Spielsteuerung spielsteuerung = new Spielsteuerung();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void settings() {
        try {
            spielsteuerung.settings();
        } catch (Exception e) {
            System.out.println("fehlermeldung size");
        }
        assertEquals(1200,spielsteuerung.getSpielfeldBreite());
        assertEquals(600,spielsteuerung.getSpielfeldHoehe());
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
        assertTrue(spielsteuerung.isSpielfeldrand(100,100));
        assertFalse(spielsteuerung.isSpielfeldrand(100,2000));
        assertFalse(spielsteuerung.isSpielfeldrand(2000,100));
        assertFalse(spielsteuerung.isSpielfeldrand(2000,2000));
    }

    @Test
    public void isErlaubteKoordinate() {

    }
}