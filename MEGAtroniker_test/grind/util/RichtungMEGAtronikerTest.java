package grind.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RichtungMEGAtronikerTest {


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void randomRichtung() {
        Richtung randomRichtung= Richtung.randomRichtung();
        System.out.println(randomRichtung);
        assertTrue(randomRichtung == Richtung.N || randomRichtung ==  Richtung.O || randomRichtung ==  Richtung.S ||randomRichtung ==  Richtung.W );
    }

    @Test
    public void values() {
        List<Richtung> testRichtung = new ArrayList<>();
        testRichtung.add(Richtung.N);
        testRichtung.add(Richtung.O);
        testRichtung.add(Richtung.S);
        testRichtung.add(Richtung.W);
        int i=0;
    for(Richtung value:Richtung.values()){
        assertEquals(value,testRichtung.get(i));
        i+=1;
        }

    }

}