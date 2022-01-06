package grind.movables.monster;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.ISpielfigur;
import grind.util.Einstellungen;
import processing.core.PApplet;

public class Zombie extends Monster{
    transient private int posX;
    transient private int posY;
    transient private final static int GESCHWINDIGKEIT = 2;
    transient private int deltaX;
    transient private int deltaY;
    transient private int schaden = 15;
    transient ITileMap tileMap;

    private boolean hilfsVariable = false;


    public Zombie(float posX, float posY, ITileMap tileMap) {
        super(posX, posY, Einstellungen.LAENGE_KACHELN_X);
        this.tileMap = tileMap;
        this.posX = (int)posX;
        this.posY = (int)posY;
        this.deltaX = -GESCHWINDIGKEIT; // gibt Zombie eine Anfangsrichtung und geschwindigkeit
        this.deltaY = -GESCHWINDIGKEIT;

        setSchaden(schaden);

    }



    @Override
    public void zeichne(PApplet app) {
        app.fill(0, 127, 127);
        app.ellipse(this.getPosX(), this.getPosY(), groesse, groesse);
    }

    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X;
    }

    @Override
    public void bewege() {
        int posX = this.getPosX();
        int posY = this.getPosY();


// Abprallen an Spielfeldrand
        if (posX < 0) {
            deltaX = -deltaX;
        } else if (posX > Einstellungen.ANZAHL_KACHELN_X*Einstellungen.LAENGE_KACHELN_X) {
            deltaX = -deltaX;
        }

        if (posY < 0) {
            deltaY = -deltaY;
        } else if (posY > Einstellungen.ANZAHL_KACHELN_Y*Einstellungen.LAENGE_KACHELN_Y) {
            deltaY = -deltaY;
        }
// Wenn nicht am Spielfeldrand, bewege dich nach vorne
        posX += deltaX;
        posY += deltaY;

// Prüfe ob Zombie eine unbetretbare Fläche mit dem nächsten Positionswechsel betritt
        // Zombie befindet sich nicht genau in einer Kachel und nimmt daher 4 Kacheln ein --> 4 Kacheln müssen auch geprüft werden
        // Die Hilfvariable dient dazu, dass nur eine Kachel eine Kollision mit dem Zombie hat und nicht mehrere gleichzeitig,
        // da es sonst zur mehrmaligen Umkehrung der Bewegungsrichtung kommt und am Ende der Zombie durch die Kacheln geht.

        IKachel kachel = tileMap.getKachel((posY+groesse/2)/Einstellungen.LAENGE_KACHELN_Y, (posX+groesse/2)/Einstellungen.LAENGE_KACHELN_X);
        vorBetreten(kachel);


        if(!hilfsVariable){
            IKachel kachel2 = tileMap.getKachel((posY-groesse/2)/Einstellungen.LAENGE_KACHELN_Y, (posX+groesse/2)/Einstellungen.LAENGE_KACHELN_X);
            vorBetreten(kachel2);
        }
        if(!hilfsVariable){
            IKachel kachel3 = tileMap.getKachel((posY+groesse/2)/Einstellungen.LAENGE_KACHELN_Y, (posX-groesse/2)/Einstellungen.LAENGE_KACHELN_X);
            vorBetreten(kachel3);
        }
        if(!hilfsVariable){
            IKachel kachel4 = tileMap.getKachel((posY-groesse/2)/Einstellungen.LAENGE_KACHELN_Y, (posX-groesse/2)/Einstellungen.LAENGE_KACHELN_X);
            vorBetreten(kachel4);
        }


// setze neue Position
        this.setPosition(posX, posY);
        hilfsVariable = false;
    }

    @Override
    public void vorBetreten(IKachel kachel) {
        if(!kachel.istBetretbar()){

            deltaX = -deltaX;
            deltaY = -deltaY;


            posX += deltaX ;
            posY += deltaY ;

            hilfsVariable = true; // Eine Kachel wurde getroffen und Richtung umgekehrt.

        }
    }

}