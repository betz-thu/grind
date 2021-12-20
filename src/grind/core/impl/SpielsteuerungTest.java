package grind.core.impl;

import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpielsteuerungTest {

    private Spielfigur figur;
    private Spielmodell spielmodell;
    private IKachel kachel;


    @Before
    public void setUp() {
        kachel = new Levelausgang();
    }
    @Test
    public void ueberpruefeLevelende() {
        if (kachel instanceof Levelausgang){
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

}