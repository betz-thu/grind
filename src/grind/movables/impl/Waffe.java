package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.movables.monster.Monster;

public abstract class Waffe extends Gegenstand{

    public Waffe(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
    }
    public abstract int getSchaden();
    public abstract int getStufe();
    public abstract void beimAnwenden(ISpielfigur figur);
//        was passiert beim Anwenden?
            //Position der Waffe ändern
//        if(pruefeKollision){
//
//        }


}
