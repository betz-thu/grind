package grind.core.impl;

import grind.core.ISpielmodell;
import grind.welt.ISpielwelt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SpielsteuerungTestLeveleditor {
    DateiService dateiService;
    Spielsteuerung spielsteuerung;
    ISpielwelt spielwelt;
    ISpielmodell spielmodell;

    @Before
    public void setUp() throws Exception {
        this.spielsteuerung = new Spielsteuerung();
        this.dateiService = new DateiService(this.spielsteuerung);
        this.spielmodell = this.spielsteuerung.getSpielmodell();
    }

    @Test
    public void ladeSpielwelt(){
        spielwelt = null;
        Assert.assertNull(spielwelt);
        spielwelt = dateiService.ladeSpielwelt("spielweltTEST.json");
        Assert.assertTrue(spielwelt instanceof ISpielwelt);
    }
}
