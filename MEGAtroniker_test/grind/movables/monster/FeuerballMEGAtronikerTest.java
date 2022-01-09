package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.junit.Assert.*;

public class FeuerballMEGAtronikerTest {
    Spielsteuerung steuerung = new Spielsteuerung();
    Feuerball ball = new Feuerball(100,100,1,1,steuerung);


    @Before
    public void setUp() throws Exception {

        steuerung.getSpielmodell().addMonster(ball);
    }

    @Test
    public void zeichne() {
    }

    @Test
    public void bewege() {
        try {
            steuerung.settings();
        } catch (Exception e) {
            System.out.println("fehlermeldung size");
        }
        System.out.println("SpielfeldBreite: "+steuerung.getSpielfeldBreite());
        int posXdavor = ball.getPosX();
        int posYdavor = ball.getPosY();
        ball.bewege();
        assertEquals((int) (posXdavor+ball.deltaX), ball.getPosX());
        assertEquals((int) (posYdavor+ball.deltaY), ball.getPosY());
    }

    @Test
    public void beiKollision() {
        steuerung.getSpielmodell().getFigur().setPosition(100,100);
        ball.beiKollision(steuerung.getSpielmodell().getFigur(),ball);
        assertFalse(steuerung.getSpielmodell().getMovables().contains(ball));



    }

    @Test
    public void vorBetreten() {
    }
}