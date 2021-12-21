package grind.kacheln.impl;

import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class BaumTest {

    Baum baum = new Baum();

    @Test
    public void istBetretbar() {
        Assert.assertFalse(baum.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertTrue(baum.istHindernis());
    }


}