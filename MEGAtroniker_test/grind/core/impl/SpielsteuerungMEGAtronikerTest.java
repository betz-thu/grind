package grind.core.impl;

import grind.movables.monster.DornPflanze;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import org.junit.Before;
import org.junit.Test;
import processing.core.PImage;

import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class SpielsteuerungMEGAtronikerTest {
    Spielsteuerung spielsteuerung;
    Spielmodell spielmodell;
    DornPflanze dornPflanze;
    int posX = 10;
    int posY = 10;

    @Before
    public void setUp() throws Exception {
        spielsteuerung = new Spielsteuerung();
        spielmodell = new Spielmodell(new ISpielwelt() {
            @Override
            public ISzene getSzene(int n) {
                return null;
            }

            @Override
            public void addSzene(ISzene szene, int szenenNummer) {

            }

            @Override
            public int getSzenenanzahl() {
                return 0;
            }

            @Override
            public void removeSzenen() {

            }
        }, spielsteuerung);
        spielmodell.getFigur().setPosition(posX,posY);
        dornPflanze = new DornPflanze(posX,posY,spielmodell.getTileMap());
        spielmodell.addMonster(dornPflanze);
        spielsteuerung.setSpielmodell(spielmodell);
    }

    @Test
    public void pruefeKollisionen() {
        spielsteuerung.pruefeKollisionen();
        assertEquals(spielsteuerung.getSpielmodell().getFigur().getLebensenergie(),100-dornPflanze.getSchaden());
    }

    @Test
    public void pruefeUmgebung() {
        dornPflanze.setPosition(posX,posY+50);
        spielsteuerung.pruefeUmgebung();
        assertTrue(dornPflanze.isInDerNaehe());

    }
}