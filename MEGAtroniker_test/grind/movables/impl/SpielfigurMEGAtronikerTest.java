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
        tFigur.playSound("class grind.movables.monster.Zombie"+"Attack");
    }

    @Test
    public void playZombieAroundSound() {
        tFigur.playSound("class grind.movables.monster.Zombie"+"Around");
    }

    @Test
    public void playFeuerMonsterAroundSound() {
        tFigur.playSound("class grind.movables.monster.FeuerMonster"+"Around");
    }

    @Test
    public void playFeuerBallAroundSound() {
        tFigur.playSound("class grind.movables.monster.Feuerball"+"Around");
    }

    @Test
    public void playGeistAroundSound() {
        tFigur.playSound("class grind.movables.monster.Geist"+"Around");
    }

    @Test
    public void playPflanzeAroundSound() {
        tFigur.playSound("class grind.movables.monster.DornPflanze"+"Around");
    }

    @Test
    public void playPflanzeAttacSound() {
        tFigur.playSound("class grind.movables.monster.Dornpflanze"+"Attack");
    }
}