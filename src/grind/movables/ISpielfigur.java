package grind.movables;

import grind.kacheln.IKachel;
import grind.movables.impl.Gegenstand;
import grind.movables.impl.Waffe;
import grind.util.Richtung;
import java.util.List;

public interface ISpielfigur extends IMovable {

    void setPosition(int x, int y);
    void erhoeheGold(int betrag);
    void bewege(Richtung richtung);

    @Override
    int getGroesse();

    List<Gegenstand> getInventar();
    void erhalteSchaden(int schaden);
    Waffe getWaffe();
}
