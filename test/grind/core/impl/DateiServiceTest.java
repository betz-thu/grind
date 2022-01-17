package grind.core.impl;

import grind.welt.ILevel;
import grind.welt.ISiedlung;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummySpielwelt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateiServiceTest {

    Spielsteuerung spielsteuerung = new Spielsteuerung();
    DateiService dateiService = new DateiService(this.spielsteuerung);
    ISpielwelt spielwelt;

    @Test
    public void ladeSpielwelt() {
        this.spielwelt = dateiService.ladeSpielwelt("spielweltTEST.json");
        assertNotNull(spielwelt);
        assertEquals(2, spielwelt.getSzenenanzahl());
        if(spielwelt.getSzene(1) instanceof ILevel){
            assertFalse(false);
        }
        if(spielwelt.getSzene(1) instanceof ISiedlung){
            assertTrue(true);
        }
        if (spielwelt.getSzene(0) instanceof ISiedlung){
            assertFalse(false);
        }
    }
}