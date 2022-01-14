package grind.core.impl;

import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import grind.movables.impl.Spielfigur;
import grind.movables.monster.Feuerball;
import grind.movables.monster.IMonster;
import grind.util.Richtung;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummySpielwelt;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class SpielmodellMEGAtronikerTest {

    ISpielwelt spielwelt = new DummySpielwelt();
    Spielsteuerung steuerung = new Spielsteuerung();
    Spielmodell spielmodell;
    Feuerball feuerBall =new Feuerball(100,100,10,10,steuerung);


    @Before
    public void setUp() throws Exception {
        spielmodell = new Spielmodell(spielwelt,steuerung);
    }

    @Test
    public void addMonster() {
        int oldSize=spielmodell.getMovables().size();
        spielmodell.addMonster(new Feuerball(100,100,10,10,steuerung));
        assertEquals(spielmodell.getMovables().size(),oldSize+1);

    }

    @Test
    public void removeMovable() {
        int oldSize=spielmodell.getMovables().size();
        spielmodell.addMonster(feuerBall);
        spielmodell.removeMovable(feuerBall);
        assertEquals(spielmodell.getMovables().size(),oldSize);
    }
}