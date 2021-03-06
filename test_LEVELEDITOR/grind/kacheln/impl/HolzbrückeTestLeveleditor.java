package grind.kacheln.impl;

import grind.kacheln.impl.Holzbrücke;
import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class HolzbrückeTestLeveleditor {

    Holzbrücke holzbrücke = new Holzbrücke();

    @Test
    public void istBetretbar() {
        Assert.assertTrue(holzbrücke.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertFalse(holzbrücke.istHindernis());
    }

}