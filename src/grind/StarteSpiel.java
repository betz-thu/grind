package grind;


import grind.core.impl.*;
import grind.core.impl.Leveleditor;
import processing.core.PApplet;

public class StarteSpiel {
    public static void main(String[] args) {
        boolean leveledit = false;
        if (leveledit){
            String[] params = {"The Grind Leveleditor"};
            PApplet.runSketch(params, new Leveleditor());
        } else {
            String[] params = {"The Grind"};
            PApplet.runSketch(params, new Spielsteuerung());
        }
    }
}
