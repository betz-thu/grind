public abstract class Waehrung extends Schatz {

    public Waehrung(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void beimSammeln(ISpielfigur figur) {
        figur.erhoeheGold(this.getWert());
    }

    abstract int getWert();
}
