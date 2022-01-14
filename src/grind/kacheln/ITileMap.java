package grind.kacheln;

import processing.core.PApplet;

public interface ITileMap {
    int getHoehe();
    int getBreite();
    IKachel getKachel(int i, int j);
    void zufaelligeTileMap();
    void zeichne(PApplet app);
    void setKachel(IKachel kachel, int i, int j);
}
