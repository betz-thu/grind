package grind.movables;

import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class IMovableMEGAtronikerTest {


    Richtung ausrichtung;
    ISpielfigur spielfigur;
    @Before
    public void setUp() throws Exception {
        ausrichtung = Richtung.W;
        spielfigur = new Spielfigur(1,1,ausrichtung);
    }

    @Test
    public void getPosX() {
        int posX = 2;
        spielfigur.setPosition(posX,33);
        assertEquals(posX,spielfigur.getPosX());
    }

    @Test
    public void getPosY() {
        int posY = 3;
        spielfigur.setPosition(34,posY);
        assertEquals(posY,spielfigur.getPosY());
    }

    @Test
    public void getGroesse() {
    }

    @Test
    public void setPosition() {
    }

    @Test
    public void zeichne() {
    }

    @Test
    public void bewege() {
        int posX = spielfigur.getPosX();
        int posY = spielfigur.getPosY();
        spielfigur.bewege(Richtung.N);
        assertEquals(posX,spielfigur.getPosX());
        assertNotEquals(posY,spielfigur.getPosY());
    }

    @Test
    public void getAusrichtung() {

        assertEquals(spielfigur.getAusrichtung(),ausrichtung);
    }

    @Test
    public void setAusrichtung() {
        Richtung neueRichtung = Richtung.N;
        spielfigur.setAusrichtung(neueRichtung);
        assertEquals(neueRichtung,spielfigur.getAusrichtung());
    }
}