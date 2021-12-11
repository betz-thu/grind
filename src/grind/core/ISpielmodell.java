package grind.core;

import grind.movables.ISpielfigur;
import grind.welt.ISzene;
import processing.core.PApplet;

public interface ISpielmodell {

    void betreteSzene(int n);

    void bewege();

    void zeichne(PApplet app);

    ISpielfigur getFigur();

    int getSzeneNr();

    void setSzeneNr(int szeneNR);

    ISzene getSzene();
}
