package grind.core.impl;

import grind.util.Einstellungen;
import processing.core.PApplet;

public class Button extends PApplet {

    private final int typ;
    private final int breite;
    private final int hoehe;
    private final int x0;
    private final int y0;

    /**
     * Konstruktor für Klasse Button
     * Erstellt einen Button anhand der eingestellten Parameter
     * @param typ möglich von 0-9 in folgender Reihenfolge (Exit, Speichern, Laden, Leeren, Neues Level, Neue Siedlung, <, >, -, +)
     * @param x X-Koordinate des Startpunkts
     * @param y Y-Koordinate des Startpunkts
     * @param breite Breite des Buttons
     * @param hoehe Hoehe des Buttons
     */
    public Button(int typ, int x, int y, int breite, int hoehe){
        this.typ = typ;
        this.x0 = x;
        this.y0 = y;
        this.breite = breite;
        this.hoehe = hoehe;
    }

    /**
     * Gibt zurück, ob die Maus sich auf dem Button befindet oder nicht.
     * @param mausX Maus Koordinate X
     * @param mausY Maus Koordinate Y
     * @return Boolean, ob die Maus auf dem Button liegt oder nicht
     */
    public boolean mausklick(int mausX, int mausY){
        boolean mausKlicked = false;
        if (mausX >= x0 && mausX <= x0 + breite && mausY >= y0 && mausY <= y0 + hoehe){
            mausKlicked = true;
        }
        return mausKlicked;
    }

    /**
     * Zeichnet den Button abhängig vom Typ an der Stelle x0, y0
     * @param app Applet auf das gezeichnet werden soll
     */
    public void zeichne(PApplet app){
        app.pushStyle();
        float textverhaeltnis = ((Einstellungen.LAENGE_KACHELN_X * Einstellungen.LAENGE_KACHELN_Y) / 1600f);
        app.textSize((int)(16 * textverhaeltnis));
        app.stroke(0);
        app.strokeWeight(2f);

        switch (this.typ){
            case 0:
                app.fill(220, 75, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Exit", x0 + 20, y0 +25 * textverhaeltnis);
                break;
            case 1:
                app.fill(100, 200, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Speichern", x0 + 20, y0 +25 * textverhaeltnis);
                break;
            case 2:
                app.fill(10, 75, 200);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Laden", x0 + 20, y0 +25 * textverhaeltnis);
                break;
            case 3:
                app.fill(255, 10, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Leeren", x0 + 20, y0 + 25 * textverhaeltnis);
                break;
            case 4:
                app.fill(255, 200, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Neues Level", x0 + 10, y0 + 25 * textverhaeltnis);
                break;
            case 5:
                app.fill(180, 100, 80);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Neue Siedlung", x0 + 3, y0 + 25 * textverhaeltnis);
                break;
            case 6:
                app.fill(100, 100, 100);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("<", x0 + 15, y0 + 25 * textverhaeltnis);
                break;
            case 7:
                app.fill(100, 100, 100);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text(">", x0 + 15, y0 + 25 * textverhaeltnis);
                break;
            case 8:
                app.fill(200, 200, 200);
                app.rect(x0, y0, breite,hoehe);
                app.fill(255, 0, 0);
                app.textSize(12);
                app.text("-", x0 + 5, y0 + 20 * textverhaeltnis);
                break;
            case 9:
                app.fill(200, 200, 200);
                app.rect(x0, y0, breite,hoehe);
                app.fill(255, 0, 0);
                app.textSize(12);
                app.text("+", x0 + 5, y0 + 20 * textverhaeltnis);
                break;
            default:
                System.out.println("Falscher Buttontyp!");
                break;
        }
        app.popStyle();
    }

    /**
     * Gibt die Breite des Buttons zurück
     * @return Breite des Buttons
     */
    public int getBreite(){
        return breite;
    }

    /**
     * Gibt die Höhe des Buttons zurück
     * @return Höhe des Buttons
     */
    public int getHoehe(){
        return hoehe;
    }

    /**
     * Gibt die X-Koordinate des Buttonspunkts oben links zurück
     * @return X-Koordinate
     */
    public int getX0() {
        return x0;
    }

    /**
     * Gibt die Y-Koordinate des Buttonspunkts oben links zurück
     * @return Y-Koordinate
     */
    public int getY0() {
        return y0;
    }
}
