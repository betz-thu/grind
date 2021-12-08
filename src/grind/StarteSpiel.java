package grind;

import grind.core.impl.Spielsteuerung;
import processing.core.PApplet;

public class StarteSpiel {
    public static void main(String[] args) {
        String[] params = {"The Grind"};
        PApplet.runSketch(params, new Spielsteuerung());
    }
}
