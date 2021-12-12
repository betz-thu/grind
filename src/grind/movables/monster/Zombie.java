package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.IMovable;
import grind.movables.ISpielfigur;
import grind.movables.impl.Movable;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Zombie extends Monster {
    private int posX;
    private int posY;
    private int geschwindigkeit = 1;
    private int deltaX = -geschwindigkeit;
    private int deltaY = -geschwindigkeit;
    ITileMap tileMap;

    int x = this.getPosX() + 19;
    int y = this.getPosY() + 19;

    public Zombie(float posX, float posY, ITileMap tileMap) {
        super(posX, posY);
        this.tileMap = tileMap;
        this.posX = (int) posX;
        this.posY = (int) posY;

    }

    @Override
    public void beiKollision(ISpielfigur figur) {

    }

    @Override
    public void zeichne(PApplet app) {
        app.fill(0, 127, 127);
        app.ellipse(x, y, Einstellungen.LAENGE_KACHELN_X, Einstellungen.LAENGE_KACHELN_Y);
//        app.fill(0,127,127);
//        app.ellipse(this.getPosX(), this.getPosY(),(float) Einstellungen.LAENGE_KACHELN_X/2 , (float)Einstellungen.LAENGE_KACHELN_Y/2);
    }

    @Override
    public void bewege() {
//        int i = 0;
//        int xx = 1;
//        int xx1 = x + 5;
//        int xx2 = x - 5;
//
////        while (i % 2 == 0 ){
//
//            if(this.x < xx1) {
//                x = this.x +xx  ;
//            }
//            else if(this.x > xx2) {
//                x  = this.x -xx;
//            }
//           i += 1;
//        }
//        while (i % 2 != 0){
//
//
//

//            if (x > posX+17 - (3*Einstellungen.LAENGE_KACHELN_X)){
//                deltaX = -geschwindigkeit;
//                posX += deltaX;
//                this.setPosition(posX, posY);
//            }
//
//            else if (x < posX+17 - 3*Einstellungen.LAENGE_KACHELN_X) {
//                deltaX = geschwindigkeit;
//            }
//            int z = this.getPosX() +19 - 1*Einstellungen.LAENGE_KACHELN_X;
//            if ( x>= z ){
//                deltaX = geschwindigkeit;
//                x -= deltaX;
//                this.setPosition(x,y);
//            }
//            else if (this.getPosX()<= x-1*Einstellungen.LAENGE_KACHELN_X){
//                deltaX = -geschwindigkeit;
//                x+= deltaX;
//                this.setPosition(x,y);
//            }
//            if (x < x + Einstellungen.LAENGE_KACHELN_X){
//                deltaX = geschwindigkeit;
//            }
//            else if (x > x + Einstellungen.LAENGE_KACHELN_X){
//                deltaX = -geschwindigkeit;
//            }
//            x += deltaX;
//
//            posX =x;
//            this.setPosition(posX, posY);


//            if (x != x - Einstellungen.LAENGE_KACHELN_X){
//                deltaX = -geschwindigkeit;
//                x+=deltaX;
//                int z1 = x + Einstellungen.LAENGE_KACHELN_X;
//                int z2 = x-Einstellungen.LAENGE_KACHELN_X;
//                if(x> z2){
//                    deltaX = geschwindigkeit;
//                    x = this.getPosX()+ deltaX;
//                } else if (x< z1){
//                    deltaX = - geschwindigkeit;
//                    x = this.getPosX()+ deltaX;
//                }
//            }
        x++;
        if( x > this.getPosX()+19 + 2*Einstellungen.LAENGE_KACHELN_X){
            x = this.getPosX()+19 + 2*Einstellungen.LAENGE_KACHELN_X;
        }
        x--;
        if (x <this.getPosX()+19 + 2*Einstellungen.LAENGE_KACHELN_X){
            x = this.getPosX()+19 + 2*Einstellungen.LAENGE_KACHELN_X;
        }


//        if (posX < 0) {
//            deltaX = geschwindigkeit;
//        } else if (posX > Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X) {
//            deltaX = -geschwindigkeit;
//        }
//
//        if (posY < 0) {
//            deltaY = geschwindigkeit;
//        } else if (posY > Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y) {
//            deltaY = -geschwindigkeit;
//        }
//
//        posX += deltaX;
//        posY += deltaY;
//
//        IKachel kachel = tileMap.getKachel(posY/Einstellungen.LAENGE_KACHELN_Y, posX/Einstellungen.LAENGE_KACHELN_X);
//        vorBetreten(kachel);
//
//
//        this.setPosition(posX, posY);
//    }

//    @Override
//    public void vorBetreten(IKachel kachel) {
//        if(!kachel.istBetretbar()){
//
//            if(deltaX < 0){
//                deltaX = Math.abs(deltaX);
//            }
//            if(deltaX > 0){
//                deltaX = -deltaX;
//            }
//            if(deltaY < 0){
//                deltaY = Math.abs(deltaY);
//            }
//            if(deltaY > 0){
//                deltaY = -deltaY;
//            }
//            posX += deltaX;
//            posY += deltaY;
//
//            this.setPosition(posX, posY);
//        }
//    }

    }


    @Override
    public void vorBetreten(IKachel kachel) {

    }
}
