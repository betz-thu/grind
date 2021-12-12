package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Zombie extends Monster{
    private int posX;
    private int posY;
    private int geschwindigkeit = 1;
    private int deltaX = -geschwindigkeit;
    private int deltaY = -geschwindigkeit;
    ITileMap tileMap;


    public Zombie(float posX, float posY, ITileMap tileMap) {
        super(posX, posY);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;

    }

    @Override
    public void beiKollision(ISpielfigur figur) {

    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(0,127,127);
        app.ellipse(this.getPosX(), this.getPosY(),(float) Einstellungen.LAENGE_KACHELN_X , (float)Einstellungen.LAENGE_KACHELN_Y);
    }

    @Override
    public void bewege() {
        int posX = this.getPosX();
        int posY = this.getPosY();



        if (posX < 0) {
            deltaX = geschwindigkeit;
        } else if (posX > Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X) {
            deltaX = -geschwindigkeit;
        }

        if (posY < 0) {
            deltaY = geschwindigkeit;
        } else if (posY > Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y) {
            deltaY = -geschwindigkeit;
        }

        posX += deltaX;
        posY += deltaY;

        IKachel kachel = tileMap.getKachel(posY/Einstellungen.LAENGE_KACHELN_Y, posX/Einstellungen.LAENGE_KACHELN_X);
        vorBetreten(kachel);


        this.setPosition(posX, posY);
    }

    @Override
    public void vorBetreten(IKachel kachel) {
        if(!kachel.istBetretbar()){

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

            this.setPosition(posX, posY);
        }
    }

}