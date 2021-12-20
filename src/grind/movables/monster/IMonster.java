package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;

public interface IMonster extends IMovable {
    int getLebensenergie();
    void beiKollision(ISpielfigur figur);
    void vorBetreten(IKachel kachel);
    void reduziereLebensenergie(int schaden);
}
