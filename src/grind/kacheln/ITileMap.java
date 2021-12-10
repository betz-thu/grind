package grind.kacheln;

import processing.core.PApplet;

public interface ITileMap {
    int getHoehe();
    int getBreite();
    IKachel getKachel(int x, int y);
    IKachel getCurrentKachel(float x, float y);

    void zeichne(PApplet app);
}
