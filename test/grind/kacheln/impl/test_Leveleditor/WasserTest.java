package grind.kacheln.impl.test_Leveleditor;

import grind.kacheln.impl.Wasser;
import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class WasserTest {

    Wasser wasser = new Wasser();

    @Test
    public void istBetretbar() {
        Assert.assertTrue(wasser.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertFalse(wasser.istHindernis());
    }

}