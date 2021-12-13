package grind.movables;

import grind.movables.impl.Spielfigur;
import processing.core.PApplet;

public interface IMovable {

    int getPosX();
    int getPosY();
    void setPosition(int x, int y);
    void zeichne(PApplet app);
    void bewege();

}
