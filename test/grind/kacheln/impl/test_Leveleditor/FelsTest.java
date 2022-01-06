package grind.kacheln.impl.test_Leveleditor;

import grind.kacheln.impl.Fels;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FelsTest {

    Fels fels = new Fels();

    @Test
    public void istBetretbar() {
        assertFalse(fels.istBetretbar());
    }

    @Test
    public void istHindernis() {
        assertTrue(fels.istHindernis());
    }
}