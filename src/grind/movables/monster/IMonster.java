package grind.movables.monster;

import grind.core.ISpielmodell;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;


/**
 * @author MEGAtroniker
 * Das Interface IMonster enthält zusatliche füt Monster nötigr Methoden
 */
public interface IMonster extends IMovable {
    int getLebensenergie();
    void beiKollision(ISpielfigur figur, IMovable monster);
    ISpielmodell getSpielmodell();
    void setSpielmodell(ISpielmodell spielmodell);
    void reduziereLebensenergie(int schaden);
    void inDerNaehe(ISpielfigur figur, IMovable monster);
    void setGeschwindigkeit(int xGeschwindigkeit);
    int getGeschwindigkeit();

}
