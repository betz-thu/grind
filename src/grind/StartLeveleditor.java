package grind;

import grind.core.impl.Leveleditor;
import processing.core.PApplet;

public class StartLeveleditor {
    public static void main(String[] args) {
            String[] params = {"The Grind Leveleditor"};
            PApplet.runSketch(params, new Leveleditor());
    }
}
