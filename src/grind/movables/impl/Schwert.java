package grind.movables.impl;
import grind.movables.ISpielfigur;
import grind.util.Richtung;
public class Schwert extends Waffe{

    private int stufe;



    public Schwert(int x, int y, int stufe, int wert) {
        super(x, y, 40);
        this.stufe=stufe;
        if (getAusrichtung()==null){
            this.setAusrichtung(Richtung.S);
        }
        this.wert = wert;

    }

    @Override
    public int getSchaden() {
        /**
         * Berechnet den Schaden, welchen ein Schwert anrichtet.
         */
        int schaden = 10* this.stufe;
        return schaden;
    }

    @Override
    public int getStufe() {
        return stufe;
    }

    public int getGroesse(){
        return groesse;
    }

    @Override
    public void beimAnwenden(ISpielfigur figur) {

    }
}
