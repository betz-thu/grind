package grind.movables.impl;

import grind.movables.ISpielfigur;

public abstract class Waffe extends Gegenstand{

    public Waffe(int posX, int posY) {
        super(posX, posY);
    }
    public abstract int getSchaden();
    public abstract int getStufe();
    public void beimAnwenden(ISpielfigur figur){
//        was passiert beim Anwenden?
    }
}
