package grind.kacheln;

import processing.core.PApplet;

import java.util.List;
import grind.kacheln.impl.Levelausgang;

public interface ITileMap {
    int getHoehe();
    int getBreite();
    IKachel getKachel(int x, int y);
    List<IKachel> getKachelarten();
    void zeichne(PApplet app);
    Levelausgang getLevelausgang();
}
