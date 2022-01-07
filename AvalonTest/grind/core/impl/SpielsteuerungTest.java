package grind.core.impl;

import grind.movables.impl.Spielfigur;
import grind.util.Richtung;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpielsteuerungTest {

    private Spielfigur figur;

    @Before
    public void setUp() throws Exception {
        figur = new Spielfigur(200, 200, Richtung.N);

    }

    @Test
    public void siedlung() {
    }
}