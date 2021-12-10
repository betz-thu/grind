package grind.movables;

import grind.kacheln.IKachel;
import grind.util.Richtung;

public interface ISpielfigur extends IMovable {


    void setPosition(int x, int y);
    void erhoeheGold(int betrag);
    void bewege(Richtung richtung);
}

