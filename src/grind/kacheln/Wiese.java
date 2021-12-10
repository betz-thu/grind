package grind.kacheln;

import processing.core.PApplet;

public class Wiese implements IKachel{
    @Override
    public boolean istBetretbar() {
    }

    @Override
    public boolean istHindernis() {
        return false;
    }

    @Override
    public void zeichne(PApplet app, int x, int y) {

    }
}
