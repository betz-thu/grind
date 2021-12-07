public class Gold extends Waehrung {

    public Gold(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    int getWert() {
        return 1;
    }
}
