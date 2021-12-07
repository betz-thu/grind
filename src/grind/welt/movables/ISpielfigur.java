package grind.welt.movables;

import grind.allgemein.Richtung;

public interface ISpielfigur extends IMovable {

    void setPosition(int x, int y);
    void erhoeheGold(int betrag);
    void bewege(Richtung richtung);
}
