package grind.movables.impl;

import grind.movables.IMovable;
import grind.movables.ISpielfigur;

public abstract class Movable implements IMovable {

    float posX;
    float posY;

    public Movable(float posX, float posY) {
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


}