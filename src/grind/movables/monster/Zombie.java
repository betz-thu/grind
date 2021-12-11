package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Zombie extends Monster{
    private int deltaX = -5;
    private int deltaY = -5;
    ITileMap tileMap;


    public Zombie(float posX, float posY, ITileMap tileMap) {
        super(posX, posY);
        this.tileMap = tileMap;

    }

    @Override
    public void beiKollision(ISpielfigur figur) {

    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(0,127,127);
        app.ellipse(this.getPosX(), this.getPosY(),(float) Einstellungen.LAENGE_KACHELN_X/2 , (float)Einstellungen.LAENGE_KACHELN_Y/2);
    }

    @Override
    public void bewege() {
        int posX = this.getPosX();
        int posY = this.getPosY();

        int altX = posX;
        int altY = posY;

        if (posX < 0) {
            deltaX = 5;
        } else if (posX > Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X) {
            deltaX = -5;
        }

        if (posY < 0) {
            deltaY = 5;
        } else if (posY > Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y) {
            deltaY = -5;
        }


        posX += deltaX;
        posY += deltaY;

        IKachel kachel = tileMap.getKachel(posY/Einstellungen.LAENGE_KACHELN_Y, posX/Einstellungen.LAENGE_KACHELN_X);



        if(!kachel.istBetretbar()){

            posX = altX;
            posY = altY;

            if(deltaX < 0){
                deltaX = Math.abs(deltaX);
            }
            if(deltaX > 0){
                deltaX = -deltaX;
            }
            if(deltaY < 0){
                deltaY = Math.abs(deltaY);
            }
            if(deltaY > 0){
                deltaY = -deltaY;
            }
            posX += deltaX;
            posY += deltaY;

        }

        this.setPosition(posX, posY);
    }

    @Override
    public void vorBetreten(IKachel kachel) {

    }
}
