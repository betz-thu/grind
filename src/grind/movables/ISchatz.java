package grind.movables;

public interface ISchatz extends IMovable {
    void beimSammeln(ISpielfigur figur);
    int getWert();
    void setWert(int wert);
}
