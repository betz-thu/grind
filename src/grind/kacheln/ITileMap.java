package grind.kacheln;

import processing.core.PApplet;

public interface ITileMap {
    int getHoehe();
    int getBreite();
    IKachel getKachel(int x, int y);

    void zeichne(PApplet app);
}
