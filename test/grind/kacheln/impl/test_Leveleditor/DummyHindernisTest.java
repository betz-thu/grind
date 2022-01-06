package grind.kacheln.impl.test_Leveleditor;

import grind.kacheln.impl.DummyHindernis;
import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class DummyHindernisTest {

    DummyHindernis dummyHindernis = new DummyHindernis();

    @Test
    public void istBetretbar() {
        Assert.assertFalse(dummyHindernis.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertTrue(dummyHindernis.istHindernis());
    }


}