package grind.util;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author MEGAtroniker
 * Die enum besitzt nun eine Liste mit ihren Elementen
 * Die Enum kann zufällig einen ihrer Werte ausgeben mit der MEthode randomRichtung
 */
public enum Richtung {
    N,  // Norden
    O,  // Osten
    S,  // Süden
    W   // Westen
    ;

    private static final List<Richtung> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Die Methode randomRichtung gibt eine zufällige Richtzung zurück
     * @return zufällige Richtung
     */
    public static Richtung randomRichtung()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }



}

