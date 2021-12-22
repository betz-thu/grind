package grind.kacheln.impl.test_Leveleditor;

import grind.kacheln.impl.Wiese;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WieseTest {



        Wiese wiese = new Wiese();

        @Test
        public void istBetretbar() {
            Assert.assertTrue(wiese.istBetretbar());
        }

        @Test
        public void istHindernis() {
            Assert.assertFalse(wiese.istHindernis());
        }
}