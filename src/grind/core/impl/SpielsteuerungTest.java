package grind.core.impl;

import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class SpielsteuerungTest {

    private int posX;
    private int posY;
    private Spielfigur figur;
    private Spielmodell spielmodell;
    private IKachel Levelausgang;
    private String levelbeendet;

    @Test
    public void ueberpruefeLevelende() {
        int kachelX = posY / Einstellungen.LAENGE_KACHELN_Y;
        int kachelY = posX / Einstellungen.LAENGE_KACHELN_X;
        IKachel aktuelleKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(kachelX, kachelY);

        Assert.assertTrue(String.valueOf(levelbeendet), true);
    }

}