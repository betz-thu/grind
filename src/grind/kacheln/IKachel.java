package grind.kacheln;

import processing.core.PApplet;

public interface IKachel {
    boolean istBetretbar();
    boolean istHindernis();
    void zeichne(PApplet app, int x, int y);
    void ladeDatei(String dateiname, PApplet app);
}
