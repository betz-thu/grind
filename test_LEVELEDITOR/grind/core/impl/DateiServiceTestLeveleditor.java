package grind.core.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import grind.kacheln.IKachel;
import grind.kacheln.impl.Baum;
import grind.kacheln.impl.DummyHindernis;
import grind.movables.impl.Spielfigur;
import grind.welt.ILevel;
import grind.welt.ISiedlung;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySpielwelt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DateiServiceTestLeveleditor {

    Spielsteuerung spielsteuerung = new Spielsteuerung();
    DateiService dateiService;

    @Before
    public void setUp() throws Exception {
        this.dateiService = new DateiService(this.spielsteuerung);
    }

    /**
     * In der spielweltTEST.json befindet sich eine Tilemap mit allen Kachelarten in einer Reihe
     * In der zweiten Reihe befinden sich alle Movablearten
     */
    @Test
    public void ladeSpielwelt() {
        ISpielwelt spielwelt;
        spielwelt = dateiService.ladeSpielwelt("spielweltTEST.json");
        Assert.assertNotNull(spielwelt);
        Assert.assertEquals(2, spielwelt.getSzenenanzahl());

        Assert.assertTrue(spielwelt.getSzene(0) instanceof ILevel);
        Assert.assertFalse(spielwelt.getSzene(0) instanceof ISiedlung);
        Assert.assertTrue(spielwelt.getSzene(1) instanceof ISiedlung);

        ILevel level = (ILevel) spielwelt.getSzene(0);

        Assert.assertTrue(level.getTileMap().getKachel(0,0) instanceof Baum);
        Assert.assertTrue(level.getTileMap().getKachel(0,1) instanceof DummyHindernis);




    }

    /**
     * In der ladeSpielwelt Methode wird bei leerer oder fehlerhafter JSON Datei eine DummySpielwelt erstellt.
     * Dies wird hier expilizit getestet.
     */
    @Test
    public void ladeLeereSpielwelt() {
        ISpielwelt spielwelt = dateiService.ladeSpielwelt("leerespielweltTEST.json");

        Assert.assertTrue(spielwelt.getSzene(0) instanceof DummyLevel);

        ILevel level = (ILevel) spielwelt.getSzene(0);

        Assert.assertEquals(1, level.getPositionen().size());
        Assert.assertTrue(level.getPositionen().get(0) instanceof Spielfigur);
    }
}