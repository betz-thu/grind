package grind.core;

import grind.welt.movables.ISpielfigur;
import processing.core.PApplet;

public interface ISpielmodell {

    void betreteSzene(int n);

    void bewege();

    void zeichne(PApplet app);

    ISpielfigur getFigur();
}
