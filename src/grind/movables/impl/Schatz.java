package grind.movables.impl;

import grind.movables.ISchatz;

public abstract class Schatz extends Movable implements ISchatz {

    int posX;
    int posY;

    public Schatz(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void bewege() {
        // Schätze bleiben an der Stelle.
    }
}
