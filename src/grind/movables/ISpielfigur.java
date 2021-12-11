package grind.movables;

import grind.movables.impl.Gegenstand;
import grind.util.Richtung;
import java.util.List;

public interface ISpielfigur extends IMovable {

    void setPosition(int x, int y);
    void erhoeheGold(int betrag);
    void bewege(Richtung richtung);
    List<Gegenstand> getInventar();
}
