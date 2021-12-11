package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.welt.impl.DummyLevel;

public abstract class Gegenstand extends Schatz{
    public Gegenstand(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void beimSammeln(ISpielfigur figur){
        figur.getInventar().add(this);
    }

    public abstract void beimAnwenden(ISpielfigur figur);
}