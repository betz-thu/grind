package grind.movables.impl;

import grind.movables.ISchatz;
import grind.movables.ISpielfigur;

public abstract class Schatz extends Movable implements ISchatz {

/*
    @Autor D0KRay 16.12.2021
    Auskommentiert da nicht benötigt und beim Generieren einer JSON Datei eine Exception geworfen wird
        int posX;
        int posY;
 */

    public Schatz(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void bewege() {
        // Schätze bleiben an der Stelle.
    }

    public abstract void beimSammeln(ISpielfigur figur); // nicht im Klassendiagramm...
}
