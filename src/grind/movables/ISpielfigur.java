package grind.movables;

import grind.movables.impl.Gegenstand;
import grind.movables.impl.Waffe;
import grind.util.Richtung;
import java.util.List;

public interface ISpielfigur extends IMovable {

    void setPosition(int x, int y);
    int getGold();
    void erhoeheGold(int betrag);
    void verringereGold(int betrag);
    int getLebensenergie();
    void setLebensenergie(int neueLebensenergie);
    void bewege(Richtung richtung);

    @Override
    int getGroesse();

    List<Gegenstand> getInventar();
    void erhalteSchaden(int schaden);
    Waffe getWaffe();
    Waffe getPfeil();
    void setPfeilAbgeschossen(boolean setzteAuf);
    void playApfelSound();
    void playSwallowSound();
    void playZombieAttacSound();
    void playZombieAroundSound();
    void playFeuerMonsterAroundSound();
    void playFeuerBallAroundSound();
    void playGeistAroundSound();
    void playPflanzeAroundSound();
    void playPflanzeAttacSound();

}
