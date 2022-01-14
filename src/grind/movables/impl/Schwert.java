package grind.movables.impl;
import grind.movables.ISpielfigur;
import grind.util.Richtung;
public class Schwert extends Waffe{

    private int stufe;


    /**
     * Konstruktor des Schwerts. Legt die Anfangsbedingungen fest.
     * @param x Gibt an an welcher x-Position sich das Schwert befindet.
     * @param y Gibt an an welcher y-Position sich das Schwert befindet.
     * @param stufe Gibt an welche Stufe das Schwert hat. (Daraus wird später der Schaden berechnet.
     */
    public Schwert(int x, int y, int stufe) {
        super(x, y, 40);
        this.stufe=stufe;
        if (getAusrichtung()==null){
            this.setAusrichtung(Richtung.S);
        }

    }

    /**
     * Getter für den Schaden welchen das Schwert anrichtet. Der Schaden wird über die Stufe berechnet.
     * @return Gibt den berechneten Schaden als int Wert zurück.
     */
    @Override
    public int getSchaden() {
        /**
         * Berechnet den Schaden, welchen ein Schwert anrichtet.
         */
        int schaden = 10* this.stufe;
        return schaden;
    }

    /**
     * Getter für die Stufe des Schwerts.
     * @return Gibt die Stufe des Schwerts zurück.
     */
    @Override
    public int getStufe() {
        return stufe;
    }

    /**
     * Getter für die Größe des Schwerts.
     * @return Gibt die Schwertgröße zurück.
     */
    public int getGroesse(){
        return groesse;
    }

    /**
     * Methode beim Anwenden wird in der Klasse Schwert nicht verwendet.
     * @param figur
     */
    @Override
    public void beimAnwenden(ISpielfigur figur) {

    }
}
