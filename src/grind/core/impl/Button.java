package grind.core.impl;

import grind.util.Einstellungen;
import processing.core.PApplet;

public class Button extends PApplet {

    private int typ;
    private final static int breite = Einstellungen.LAENGE_KACHELN_X * 3;
    private final static int hoehe = Einstellungen.LAENGE_KACHELN_Y;

    public Button(int typ){
        this.typ = typ;
    }

    public void zeichne(PApplet app, int x0, int y0){
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
                app.text("Exit", x0 + 20, y0 +25*textverhaeltnis);
                break;
            case 1:
                app.fill(100, 200, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Speichern", x0 + 20, y0 +25*textverhaeltnis);
                break;
            case 2:
                app.fill(10, 75, 200);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Laden", x0 + 20, y0 +25*textverhaeltnis);
                break;
            case 3:
                app.fill(255, 10, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Leeren", x0 + 20, y0 +25*textverhaeltnis);
                break;
            case 4:
                app.fill(255, 10, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Neues Level", x0 + 10, y0 +25*textverhaeltnis);
                break;
            case 5:
                app.fill(255, 10, 25);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("Neue Siedlung", x0 + 3, y0 +25*textverhaeltnis);
                break;
            case 6:
                app.fill(100, 100, 100);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text("<", x0 + 40, y0 +25*textverhaeltnis);
                break;
            case 7:
                app.fill(100, 100, 100);
                app.rect(x0, y0, breite,hoehe);
                app.fill(0, 0, 0);
                app.text(">", x0 + 40, y0 +25*textverhaeltnis);
                break;
            default:
                System.out.println("Falscher Buttontyp!");
                break;
        }
        app.popStyle();
    }

    public int getBreite(){
        return breite;
    }

    public int getHoehe(){
        return hoehe;
    }
}
