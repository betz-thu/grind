package grind.movables.monster;

import grind.core.impl.Spielsteuerung;
import grind.kacheln.impl.TileMap;
import grind.movables.impl.Spielfigur;
import grind.util.FeuerModus;
import grind.util.Richtung;
import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import static org.junit.Assert.*;

public class FeuerMonsterTest {

    TileMap tileMap;
    Spielsteuerung steuerung;
    Spielfigur tFigur;
    FeuerMonster tFeuerMonster;
    FeuerModus feuerModus;
    @Before
    public void setUp() throws Exception {
        tileMap = new TileMap();
        steuerung = new Spielsteuerung();
        tFigur = new Spielfigur(0, 0, Richtung.N);
        tFeuerMonster = new FeuerMonster(200,200,this.tileMap,this.steuerung, Richtung.N,100, FeuerModus.KONSTANT);

    }

    @Test
    public void getter(){
        int xTest = 200;
        int yTest=200;
        tFigur.setPosition(xTest,yTest);
        tFeuerMonster.setPosition(xTest,yTest);
        assertEquals(tFigur.getPosX(),xTest);
        assertEquals(tFigur.getPosY(),yTest);
        assertEquals(tFeuerMonster.getPosX(),xTest);
        assertEquals(tFeuerMonster.getPosY(),yTest);
        assertEquals(tFeuerMonster.getAusrichtung(),Richtung.N);
        assertFalse(tFeuerMonster.isHatKollidiert());
        tFeuerMonster.setHatKollidiert(true);
        assertTrue(tFeuerMonster.isHatKollidiert());
    }

    @Test
    public void beiKollision() {
        int xTest = 100;
        int yTest=xTest;
        this.tFigur.setPosition(xTest,yTest);
        tFeuerMonster.setPosition(xTest,yTest);
        tFeuerMonster.beiKollision(tFigur);
        assertTrue(tFeuerMonster.isHatKollidiert());
    }

    @Test
    public void zeichne() {
    }

    @Test
    public void bewege() {
        System.out.println(tileMap.getBreite()+" " + tileMap.getHoehe());



        boolean wegBlockiert;

        feuerModus= tFeuerMonster.feuerModus;
        assertEquals(feuerModus,FeuerModus.KONSTANT);
        tFeuerMonster.bewege();
        assertFalse(steuerung.isErlaubteKoordinate(tFeuerMonster.getPosX(), tFeuerMonster.getPosY()-10));
        tFeuerMonster.setPosition(tFeuerMonster.getPosX(), tFeuerMonster.getPosY() );

        System.out.println("Monsterpos: "+tFeuerMonster.getPosX()+"  "+ tFeuerMonster.getPosY());

        System.out.println("Spielfeldrand: "+steuerung.isSpielfeldrand(50,50));

        System.out.println("impl. if von is erlaubte koor: "+steuerung.getKachelByCoordinates(tFeuerMonster.getPosX(),tFeuerMonster.getPosY()-10).istBetretbar());

        System.out.println("eigentliche Methode: " +steuerung.isErlaubteKoordinate(tFeuerMonster.getPosX(), tFeuerMonster.getPosY()-10));

    /*
        for(int i=0; i<1200;i+=40){
            for (int j=0; j<800;i+=40){
                System.out.println(steuerung.getKachelByCoordinates(i,j).istBetretbar());
            }


/*
        /*
        do{

            if(steuerung.isErlaubteKoordinate(tFeuerMonster.getPosX(), tFeuerMonster.getPosY()+40)){
                wegBlockiert=false;
            }
            wegBlockiert=true;
        }
        while (!wegBlockiert);
        {
            tFeuerMonster.setPosition(tFeuerMonster.getPosX()+i, tFeuerMonster.posY );
            if(steuerung.isErlaubteKoordinate(tFeuerMonster.getPosX(), tFeuerMonster.getPosY()+40)){
                wegBlockiert=false;
            }
            wegBlockiert=true;
            i+=i;
        }
        assertTrue(steuerung.isErlaubteKoordinate(tFeuerMonster.getPosX(), tFeuerMonster.getPosY()+10));
        tFeuerMonster.bewege();
        System.out.println(tFeuerMonster.getPosX()+""+ tFeuerMonster.getPosY());*/
    }

    @Test
    public void shootFeuerball() {
    }

    @Test
    public void resetTimer() {
    }

    @Test
    public void vorBetreten() {
    }
}