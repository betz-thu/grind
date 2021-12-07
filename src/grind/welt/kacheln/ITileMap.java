package grind.welt.kacheln;

public interface ITileMap {
    int getHoehe();
    int getBreite();
    IKachel getKachel(int x, int y);
}
