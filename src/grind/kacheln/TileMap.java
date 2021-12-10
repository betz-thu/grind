package grind.kacheln;

import processing.core.PApplet;

public class TileMap implements ITileMap {

    @Override
    public int getHoehe() {
        return 0;
    }

    @Override
    public int getBreite() {
        return 0;
    }

    @Override
    public IKachel getKachel(int x, int y) {
        return null;
    }

    @Override
    public void zeichne(PApplet app) {

    }

    private IKachel[][] kacheln;
    void setKachel(int x, int y, IKachel kachel){
        this.kacheln = kacheln;

    }
}
