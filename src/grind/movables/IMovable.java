package grind.movables;

import grind.core.impl.Spielsteuerung;
import grind.util.Richtung;
import processing.core.PApplet;
/**
 * @Autor Megatroniker
 * Ergänzung der Methoden getAusrichtung und setAusrichtung für die Ausrichtung der Spielfigur
 * bzw. allgemein auch anderer Movables.
 **/
public interface IMovable {

    int getPosX();
    int getPosY();
    int getGroesse();
    void setPosition(int x, int y);
    void zeichne(Spielsteuerung spielsteuerung);
    void bewege();
    Richtung getAusrichtung();
    void setAusrichtung(Richtung richtung);
}

