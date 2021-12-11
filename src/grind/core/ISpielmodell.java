package grind.core;

import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import processing.core.PApplet;

import java.util.List;

public interface ISpielmodell {

    void betreteSzene(int n);

    void bewege();

    void zeichne(PApplet app);

    ISpielfigur getFigur();


    List<ISchatz> getSchaetze();

    List<IMovable> getMovables();

}
