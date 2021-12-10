package grind.kacheln;

import processing.core.PApplet;

public class Fels implements IKachel{
    @Override
    public boolean istBetretbar() {
        return false;
    }

    @Override
    public boolean istHindernis() {
        return true;
    }

    @Override
    public void zeichne(PApplet app, int x, int y) {

    }
}
