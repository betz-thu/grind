package grind.movables.impl;

import grind.util.Richtung;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpielfigurMEGAtronikerTest {

    private Spielfigur tFigur;
    private int betrag = 3;



    @Before
    public void setUp() throws Exception {
        tFigur = new Spielfigur(0, 0, Richtung.N);
    }

    @Test
    public void bewege() {
        int yPosAlt =tFigur.getPosY();
        int xPosAlt =tFigur.getPosX();
        tFigur.bewege(Richtung.N);
        assertEquals(tFigur.getPosY(),yPosAlt- (int)tFigur.getGESCHWINDIGKEIT());
        assertEquals(tFigur.getPosX(),xPosAlt);
        yPosAlt =tFigur.getPosY();
        xPosAlt =tFigur.getPosX();
        tFigur.bewege(Richtung.S);
        assertEquals(tFigur.getPosY(),yPosAlt+ (int)tFigur.getGESCHWINDIGKEIT());
        assertEquals(tFigur.getPosX(),xPosAlt);
        yPosAlt =tFigur.getPosY();
        xPosAlt =tFigur.getPosX();
        tFigur.bewege(Richtung.W);
        assertEquals(tFigur.getPosX(),xPosAlt- (int)tFigur.getGESCHWINDIGKEIT());
        assertEquals(tFigur.getPosY(),yPosAlt);
        yPosAlt =tFigur.getPosY();
        xPosAlt =tFigur.getPosX();
        tFigur.bewege(Richtung.O);
        assertEquals(tFigur.getPosX(),xPosAlt+ (int)tFigur.getGESCHWINDIGKEIT());
        assertEquals(tFigur.getPosY(),yPosAlt);

    }


    @Test
    public void erhoeheGold(){
        int gold = tFigur.getGold();
        tFigur.erhoeheGold(this.betrag);
        assertEquals(tFigur.getGold(),gold+this.betrag);

    }

    @Test
    public void playZombieAttacSound() {
        tFigur.playZombieAttacSound();
    }

    @Test
    public void playZombieAroundSound() {
        tFigur.playZombieAttacSound();
    }

    @Test
    public void playFeuerMonsterAroundSound() {
        tFigur.playFeuerMonsterAroundSound();
    }

    @Test
    public void playFeuerBallAroundSound() {
        tFigur.playFeuerBallAroundSound();
    }

    @Test
    public void playGeistAroundSound() {
        tFigur.playGeistAroundSound();
    }

    @Test
    public void playPflanzeAroundSound() {
        tFigur.playPflanzeAroundSound();
    }

    @Test
    public void playPflanzeAttacSound() {
        tFigur.playPflanzeAttacSound();
    }
}