package grind.movables.monster;

import grind.kacheln.ITileMap;
import grind.util.Einstellungen;


/**
 * @author MEGAtroniker
 * Die Klasse DornPflanze ermöglicht es Moster dieser Art zu erstellen.
 * Dieses Monster ist unbeweglich, Kollision und in der Nach ist wie in der abstrakten Monsterklasse implementiert.
 */
public class DornPflanze extends Monster {
    transient private int schaden = 20;
    private final ITileMap tileMap;


    /**
     * @author MEGAtroniker
     * Der Konstruktor die Erstellung der Instanzen der DornPflanze
     * @param posX initiale X Position
     * @param posY initiale Y Position
     * @param tileMap aktuelle TileMap
     */
    public DornPflanze(float posX, float posY, ITileMap tileMap) {
        super(posX, posY,Einstellungen.LAENGE_KACHELN_X);
        this.tileMap = tileMap;
        setSchaden(schaden);
    }


    /**
     * @author MEGAtroniker
     * Getter getGroesse gibt die Größe der DornPflanze zurück, diese entspricht der Kachelgröße.
     * @return Größe der DornPflanze
     */
    @Override
    public int getGroesse() {
        return groesse = Einstellungen.LAENGE_KACHELN_X;
    }


    /**
     * @author MEGAtroniker
     * Getter gerGeschwindigkeit gibt es nur, weil die Methode überschrieben werden muss.
     * @return Gescwindigkeit = 0, da sich die DornPflanze nicht bewegen kann.
     */
    @Override
    public int getGeschwindigkeit() {
        return 0;
    }


}

