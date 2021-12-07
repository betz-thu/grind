package grind.welt.movables;

import processing.core.PApplet;

public interface IMovable {

    int getPosX();
    int getPosY();
    void zeichne(PApplet app);
    void bewege();
}
