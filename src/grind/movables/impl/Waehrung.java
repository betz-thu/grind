package grind.movables.impl;

import grind.core.impl.Spielmodell;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import grind.welt.impl.DummyLevel;


public abstract class Waehrung extends Schatz {

    public Waehrung(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
    }

    @Override
    public void beimSammeln(ISpielfigur figur) {
        figur.erhoeheGold(this.getWert());

    }


    abstract int getWert();
}
