package grind.kacheln.impl;

import grind.kacheln.impl.LeereKachel;
import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class LeereKachelTestLeveleditor {

    LeereKachel leereKachel = new LeereKachel();

    @Test
    public void istBetretbar() {
        Assert.assertTrue(leereKachel.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertFalse(leereKachel.istHindernis());
    }

}