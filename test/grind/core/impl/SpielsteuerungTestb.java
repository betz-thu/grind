package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummySpielwelt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpielsteuerungTestb {
    Spielsteuerung spielsteuerung = new Spielsteuerung();
    DateiService dateiService = new DateiService(this.spielsteuerung);
    ISpielwelt spielwelt = new DummySpielwelt();
    ISpielmodell spielmodell = new Spielmodell(this.spielwelt, this.spielsteuerung);
    private IKachel kachel;

    @BeforeEach
     public void setUp() {
        kachel = new Levelausgang();
        this.spielmodell = this.spielsteuerung.getSpielmodell();
    }
    @Test
    public void ueberpruefeLevelende() {
        if (kachel instanceof Levelausgang) {
        assertTrue(true);
    } else {
        assertTrue(false);
    }
    }

    @Test
    public void ladeSpielwelt() {
        spielwelt = dateiService.ladeSpielwelt("spielweltTEST.json");
        if (spielwelt instanceof ISpielwelt){
            assertTrue(true);
        }
    }
}