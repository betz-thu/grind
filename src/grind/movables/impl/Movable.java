package grind.movables.impl;

import grind.movables.IMovable;
import grind.util.Richtung;

/**
 * @Autor Megatroniker
 * Überladener Konstruktor, um Spielfigur und anderer Movables eine Ausrichtung zu geben.
 * getAusrichtung und setAusrichtung, um Ausrichtung zu setzen oder zu übergeben.
 */
import grind.movables.ISpielfigur;

public abstract class Movable implements IMovable {

    float posX;
    float posY;
    Richtung ausrichtung;

    public Movable(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Methode Movable
     * Überladener Konstruktor, um Spielfigur und anderer Movables eine Ausrichtung zu geben.
     * @param posX gibt X-Position des Movables an.
     * @param posY gibt Y-Position des Movables an.
     * @param ausrichtung gibt Ausrichtung des Movables mithilfe der enmus an.
     */
    public Movable(float posX, float posY, Richtung ausrichtung) {
        this.ausrichtung = ausrichtung;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public int getPosX() {
        return (int) posX;
    }

    @Override
    public int getPosY() {
        return (int) posY;
    }

    @Override
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    /**
     * Methode getAusrichtung gibt die aktuelle Ausrichtung des Movables zurück.
     * @return Richtung in Form der enum
     */
    @Override
    public Richtung getAusrichtung() {
        return ausrichtung;
    }

    /**
     * Methode setAusrichtung setzt die aktuelle Ausrichtung auf den Übergabewert.
     * @param ausrichtung ist die neue Ausrichtung.
     */
    @Override
    public void setAusrichtung(Richtung ausrichtung) {
        this.ausrichtung = ausrichtung;
    }
}
