package grind.movables.impl;

import grind.movables.ISchatz;
import grind.movables.ISpielfigur;

public abstract class Schatz extends Movable implements ISchatz {

    int posX;
    int posY;
    int groesse;

    public Schatz(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
    }

    @Override
    public void bewege() {
        // Schätze bleiben an der Stelle.
    }

    public abstract void beimSammeln(ISpielfigur figur); // nicht im Klassendiagramm...
}
