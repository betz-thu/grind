package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;

public interface IMonster extends IMovable {
    int getLebensenergie();
    void beiKollision(ISpielfigur figur);
    void vorBetreten(IKachel kachel);
    ISpielmodell getSpielmodell();
    void setSpielmodell(ISpielmodell spielmodell);
    void reduziereLebensenergie(int schaden);
    void inDerNaehe(ISpielfigur figur);
}
