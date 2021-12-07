package grind.welt.kacheln;

public class TileMap implements ITileMap {

    int hoehe;
    int breite;
    IKachel[][] kacheln;

    public TileMap(int hoehe, int breite) {
        this.hoehe = hoehe;
        this.breite = breite;
        this.kacheln = new IKachel[this.hoehe][this.breite];
    }

    @Override
    public int getHoehe() {
        return hoehe;
    }

    @Override
    public int getBreite() {
        return breite;
    }

    @Override
    public IKachel getKachel(int i, int j) {
        return kacheln[i][j];
    }
}
