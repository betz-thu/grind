package grind.core.impl;

import grind.movables.impl.Heiltrank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SpielsteuerungTest_Avalon {


    private Heiltrank heiltrank;
    private Heiltrank heiltrank2;
    private Spielsteuerung spiel;


    @Before
    public void setUp() throws Exception {

        heiltrank = new Heiltrank( 100, 100, 3, 3);
        heiltrank2 = new Heiltrank( 100, 100, 3,3);
        spiel = new Spielsteuerung();

    }

    @Test
    public void beiKollisionSiedlung() {
        spiel.spielmodell.getMovables().add(heiltrank);
        Assert.assertTrue(spiel.spielmodell.getMovables().contains(heiltrank));
        spiel.beiKollisionSiedlung(heiltrank);
        Assert.assertTrue(spiel.spielmodell.getFigur().getInventar().contains(heiltrank));
        Assert.assertEquals(2, spiel.spielmodell.getFigur().getGold());
        Assert.assertFalse(spiel.spielmodell.getMovables().contains(heiltrank));

        //Spieler versucht erneut zu kaufen ohne genügend Geld
        spiel.spielmodell.getMovables().add(heiltrank2);
        spiel.beiKollisionSiedlung(heiltrank2);
        Assert.assertEquals(2, spiel.spielmodell.getFigur().getGold());
        Assert.assertTrue(spiel.spielmodell.getMovables().contains(heiltrank2));
        Assert.assertFalse(spiel.spielmodell.getFigur().getInventar().contains(heiltrank2));
    }
}