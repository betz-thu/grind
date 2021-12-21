package grind.kacheln.impl;

import grind.movables.impl.Spielfigur;
import org.junit.Assert;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import static org.junit.Assert.*;

public class WieseTest {

    private Spielfigur figur;
    private PImage img;
    private PApplet app;
    private String dateiname;

    @Test
    public void istBetretbar() {
        this.figur = figur;
        Assert.assertTrue(String.valueOf(figur),true);
    }

    @Test
    public void istHindernis() {
        this.figur = figur;
        Assert.assertFalse(String.valueOf(figur),false);
    }

    @Test
    public void ladeDatei() {
        this.img = app.loadImage(dateiname);
        Assert.assertEquals(img,dateiname);
    }
}