public abstract class Schatz implements ISchatz {

    int posX;
    int posY;

    public Schatz(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void bewege() {
        // Schätze bleiben an der Stelle.
    }
}
