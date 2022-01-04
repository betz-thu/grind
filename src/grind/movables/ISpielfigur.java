package grind.movables;

import grind.movables.impl.Gegenstand;
import grind.movables.impl.Waffe;
import grind.util.Richtung;
import java.util.List;

public interface ISpielfigur extends IMovable {

    void setPosition(int x, int y);
    void erhoeheGold(int betrag);
    int getLebensenergie();
    void setLebensenergie(int neueLebensenergie);
    void bewege(Richtung richtung);
    void setGeschwindigkeit(float immunGeschwindigkeit);
    float getGeschwindigkeit();
    void setImmun(boolean isImmun);
    boolean isImmun();

    @Override
    int getGroesse();

    List<Gegenstand> getInventar();
    void erhalteSchaden(int schaden);
    Waffe getWaffe();
    void playApfelSound();
    void playSwallowSound();
}
