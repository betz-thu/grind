package grind.kacheln.impl;

import grind.kacheln.impl.Wasser;
import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class WasserTestLeveleditor {

    Wasser wasser = new Wasser();

    @Test
    public void istBetretbar() {
        Assert.assertFalse(wasser.istBetretbar());
    }

    @Test
    public void istHindernis() {
        Assert.assertFalse(wasser.istHindernis());
    }

}