package grind.kacheln.impl;

import grind.kacheln.impl.Weg;
import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class WegTestLeveleditor {

    Weg weg = new Weg();

    @Test
    public void istBetretbar() {
        Assert.assertTrue(weg.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertFalse(weg.istHindernis());
    }

}