package grind.movables.impl;

import grind.movables.ISpielfigur;
import grind.welt.impl.DummyLevel;

public abstract class Gegenstand extends Schatz{
    public Gegenstand(int posX, int posY, int groesse) {
        super(posX, posY, groesse);
        this.wert = 3;  // jeder Gegenstand startet mit Wert 3 @Team Leveleditor: gerne ändern
    }

    @Override
    public void beimSammeln(ISpielfigur figur){
        figur.getInventar().add(this);
    }


    public abstract void beimAnwenden(ISpielfigur figur);

    public void beimKaufen(ISpielfigur figur){
        figur.getInventar().add(this);
        figur.verringereGold(wert);

    }
}
