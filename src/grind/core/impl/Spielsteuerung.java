package grind.core.impl;

import grind.StartLeveleditor;
import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.impl.Levelende;
import grind.movables.impl.Nahrung;
import grind.movables.impl.Spielfigur;
import grind.movables.impl.*;
import grind.movables.monster.DornPflanze;
import grind.movables.monster.IMonster;
import grind.movables.monster.Monster;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Autor Megatronik
 * steuert Spielfigur, zeigt sichtbare Objekte an.
 */
public class Spielsteuerung extends PApplet {
    private int SpielfeldBreite;
    private int SpielfeldHoehe;
    final Spielfigur Spieler;
    final int SpielerGeschwindigkeit;
    int Tastendruck;
    private String fTaste;
    private DateiService dateiService;

    public ISpielmodell getSpielmodell() {
        return spielmodell;
    }

    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }

    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;

    int CountStart;
    int duration = 20;
    int time = 20;

    int Spielerstartx;
    int Spielerstarty;
    int movablestartx;
    int movavlestarty;


    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Spielsteuerung() {
        this.dateiService = new DateiService(this);
        this.spielmodell = new Spielmodell(ladeSpielwelt(), this);
        // this.spielmodell.betreteSzene(1);
        this.spielmodell.betreteSzene(this.spielmodell.getSzeneNr());

        this.Spieler = (Spielfigur) spielmodell.getFigur();
        this.SpielerGeschwindigkeit = (int) Spieler.getGeschwindigkeit();
        // this.tileMap = (ITileMap) spielmodell.getTileMap();
    }

    public int getSpielfeldBreite() {
        return SpielfeldBreite;
    }

    public int getSpielfeldHoehe() {
        return SpielfeldHoehe;
    }


    /**
     * Methode settings, setzt Spielfeldgröße auf die in den Einstellungen gesetzten Parameter.
     */
    @Override
    public void settings() {
        SpielfeldBreite = Einstellungen.LAENGE_KACHELN_X * Einstellungen.ANZAHL_KACHELN_X;
        SpielfeldHoehe = Einstellungen.LAENGE_KACHELN_Y * Einstellungen.ANZAHL_KACHELN_Y;
        size(SpielfeldBreite, SpielfeldHoehe);

    }

    /**
     * Methode setup, lädt Darstellung der Spielfigur.
     */
    @Override
    public void setup() {
        imageMode(PConstants.CORNER);
        Spieler.ladeIMGSpielfigur(this);
        anzeigeTitelLevel(this.spielmodell.getSzeneNr() + 1);
        CountStart = millis();
    }

    /**
     * Methode draw, zeichnet alle sichtbare Elemente.
     * (Spielfigur, Lebensenergie, Kontostand, Tilemap)
     */
    @Override
    public void draw() {
        eingabe();
        aktualisiere();
        zeichne();
        gameover();
        countdown();
    }


    /**
     * Methode eingabe: richtet Figur in Laufrichtung aus, wenn möglich bewegt sie die Figur in Laufrichtung.
     * Beim Prüfen, ob die Figur in Laufrichtung bewegt werden kann, werden zwei punkte auf Schulterbreite überprüft,
     * damit die Figur nicht teilweise in unbetretbare Kacheln läuft.
     */
    private void eingabe() {
        int x = Spieler.getPosX();
        int y = Spieler.getPosY();
        int Schulterbreite = 15;
        if (keyPressed) {
            if (key == 'a' || keyCode == LEFT) {
                Spieler.setAusrichtung(Richtung.W);
                if (isErlaubteKoordinate(x - SpielerGeschwindigkeit - 20, y - Schulterbreite) && isErlaubteKoordinate(x - SpielerGeschwindigkeit - 20, y + Schulterbreite)) {
                    Spieler.bewege(Richtung.W);
                }
            } else if (key == 'w' || keyCode == UP) {
                Spieler.setAusrichtung(Richtung.N);
                if (isErlaubteKoordinate(x - Schulterbreite, y - SpielerGeschwindigkeit - 20) && isErlaubteKoordinate(x + Schulterbreite, y - SpielerGeschwindigkeit - 20)) {
                    Spieler.bewege(Richtung.N);
                }
            } else if (key == 's' || keyCode == DOWN) {
                Spieler.setAusrichtung(Richtung.S);
                if (isErlaubteKoordinate(x - Schulterbreite, y + SpielerGeschwindigkeit + 20) && isErlaubteKoordinate(x + Schulterbreite, y + SpielerGeschwindigkeit + 20)) {
                    Spieler.bewege(Richtung.S);
                }
            } else if (key == 'd' || keyCode == RIGHT) {
                Spieler.setAusrichtung(Richtung.O);
                if (isErlaubteKoordinate(x + SpielerGeschwindigkeit + 20, y - Schulterbreite) && isErlaubteKoordinate(x + SpielerGeschwindigkeit + 20, y + Schulterbreite)) {
                    Spieler.bewege(Richtung.O);
                }
            } else if (key == '1') {
                Tastendruck = 0;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '2') {
                Tastendruck = 1;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '3') {
                Tastendruck = 2;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '4') {
                Tastendruck = 3;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '5') {
                Tastendruck = 4;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '6') {
                Tastendruck = 5;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '7') {
                Tastendruck = 6;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '8') {
                Tastendruck = 7;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '9') {
                Tastendruck = 8;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            } else if (key == '0') {
                Tastendruck = 9;
                Spieler.benutze(Tastendruck);
                keyPressed = false;

            }
            //Inventar öffnen
            if (keyPressed) {
                if (key == Einstellungen.TASTE_INVENTAR && Spieler.getInventarGroeße() == 10) {
                    Spieler.setInventarGroeße(30);
                    Spieler.playBackpackOpenSound();
                    keyPressed = false;
                } else if (key == Einstellungen.TASTE_INVENTAR && Spieler.getInventarGroeße() == 30) {
                    Spieler.setInventarGroeße(10);
                    keyPressed = false;
                    Spieler.playBackpackCloseSound();
                }
            }
        }

        szeneUeberspringen();
    }

    /**
     * @MEGAtroniker Die Methode springt zur nächsten Szene durch das Betätigen der Taste "F12"
     */
    private void szeneUeberspringen() {
        abfrageFTasten();
    }

    /**
     * @author LuHe20
     * Cheat für das Überspringen einer Szene mit F12.
     * Speichern der Spielwelt mit F11
     * Laden der Spielwelt mit F10
     */
    private void abfrageFTasten() {
        //F12 neue Szene
        if (keyPressed && !pressed) {
            if (keyCode == 123) {
                pressed = true;
                fTaste = "F12";
            } else if (keyCode == 122) {
                pressed = true;
                fTaste = "F11";
            } else if (keyCode == 121) {
                pressed = true;
                fTaste = "F10";
            }
        } else if (!keyPressed && pressed) {
            switch (fTaste) {
                case "F12":

                    levelBeendet = true;

                    break;
                case "F11":
                    System.out.println("F11 nicht belegt");

                    break;
                case "F10":

                    ISpielwelt welt;
                    welt = ladeSpielwelt();
                    this.spielmodell.setSpielwelt(welt);
                    spielmodell.setSzeneNr(0);
                    spielmodell.betreteSzene(spielmodell.getSzeneNr());

                    System.out.println("F10 - welt geladen");

                    break;
            }

            pressed = false;
        }
    }

    private void aktualisiere() {
        pruefeKollisionen();
        spielmodell.entferneToteMonster();
        spielmodell.bewege();
        levelBeendet = ueberpruefeLevelende();
        starteNeueSzene();
    }

    /**
     * @author LuHe20
     * Startet, wenn levelBeendet Bedingung wahr ist, die nächste Szene.
     */
    private void starteNeueSzene() {
        if (levelBeendet) {
            levelBeendet = false;
            spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
            anzeigeTitelLevel(spielmodell.getSzeneNr() + 1);
        }
    }

    private void zeichne() {
        spielmodell.zeichne(this);
    }

    @Override
    public void mousePressed() {
        // nur notwendig, falls Maus benötigt wird
    }

    public boolean ueberpruefeLevelende() {
        //Abfrage ob der aktuelle Standpunkt der Spielfigur eine Kachel vom Typ Levelausgang ist.
        pruefeLevelausgang();

        for (int i = 0; i < spielmodell.getFigur().getInventar().size(); i++) {
            if (spielmodell.getFigur().getInventar().size() >= i + 1) {
                if (spielmodell.getFigur().getInventar().get(i) instanceof Levelende) {
                    System.out.println("Levelende Bedingung wurde gefunden");
                    levelBeendet = true;
                    spielmodell.getFigur().getInventar().remove(i);
                    break;
                }
            }
        }
        return levelBeendet;
    }

    /**
     * @return levelBeendet
     * @author LuHe20
     * Prüft, ob die aktuelle Kachel auf der sich der Spieler befindet,
     * eine Kachel des Typs: Levelausgang ist.
     */
    private boolean pruefeLevelausgang() {
        int spielerPosX = spielmodell.getFigur().getPosY() / Einstellungen.LAENGE_KACHELN_Y;
        int spielerPosY = spielmodell.getFigur().getPosX() / Einstellungen.LAENGE_KACHELN_X;
        IKachel spielerKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(spielerPosX, spielerPosY);
        if (spielerKachel instanceof Levelausgang) {
//            System.out.println(spielerKachel);
            levelBeendet = true;
        }
        return levelBeendet;
    }

    /**
     * Kollisionsabfrage: prüft Kollision zwischen Spieler und Movable und löst entsprechende Methode aus.
     * z.B. Spieler hat Kollision mit Gold --> beimSammeln() --> löscht Gold aus dem Level
     */
    public void pruefeKollisionen() {
        int FigurXp = this.spielmodell.getFigur().getPosX() + (Einstellungen.GROESSE_SPIELFIGUR / 2);
        int FigurXn = this.spielmodell.getFigur().getPosX() - (Einstellungen.GROESSE_SPIELFIGUR / 2);
        int FigurYp = this.spielmodell.getFigur().getPosY() + (Einstellungen.GROESSE_SPIELFIGUR / 2);
        int FigurYn = this.spielmodell.getFigur().getPosY() - (Einstellungen.GROESSE_SPIELFIGUR / 2);
        int WaffeXp = this.spielmodell.getFigur().getWaffe().getPosX() + (spielmodell.getFigur().getWaffe().getGroesse() / 2);

        int WaffeXn = this.spielmodell.getFigur().getWaffe().getPosX() - (spielmodell.getFigur().getWaffe().getGroesse() / 2);
        int WaffeYp = this.spielmodell.getFigur().getWaffe().getPosY() + (spielmodell.getFigur().getWaffe().getGroesse() / 2);
        int WaffeYn = this.spielmodell.getFigur().getWaffe().getPosY() - (spielmodell.getFigur().getWaffe().getGroesse() / 2);


        for (IMovable movable : this.spielmodell.getMovables()) {
            int MovableXp = movable.getPosX() + movable.getGroesse() / 2;
            int MovableXn = movable.getPosX() - movable.getGroesse() / 2;
            int MovableYp = movable.getPosY() + movable.getGroesse() / 2;
            int MovableYn = movable.getPosY() - movable.getGroesse() / 2;
            if ((FigurXp > MovableXn) & (FigurXn < MovableXp) & (FigurYp > MovableYn) & (FigurYn < MovableYp)) {

                if (movable instanceof IMonster) {
                    ((IMonster) movable).beiKollision(spielmodell.getFigur());
                } else if (movable instanceof ISchatz) {
                    if (!(movable instanceof Waffe)) {
                        ((ISchatz) movable).beimSammeln(spielmodell.getFigur()); // zB. erhöht Gold
                    }
                    if (!(movable instanceof Waffe)) {
                        spielmodell.removeMovable(movable);
                    } // löscht Schatz aus Level
                    if ((movable instanceof Waffe) & !(Spieler.waffeAusgestattet)) {
                        ((ISchatz) movable).beimSammeln(spielmodell.getFigur());
                    }
                    return;
                } else if (movable instanceof Nahrung) {

                }

            } else if ((WaffeXp > MovableXn) & (WaffeXn < MovableXp) & (WaffeYp > MovableYn) & (WaffeYn < MovableYp) & (key == ' ')) {
                if (movable instanceof Monster) {
                    System.out.println(((Monster) movable).getLebensenergie());
                    ((Monster) movable).reduziereLebensenergie(spielmodell.getFigur().getWaffe().getSchaden());

                }
            }
        }
    }


    /**
     * Methode getKachelByCoordinates, gibt IKachel zurück, auf der die gegebenen Koordinaten liegen.
     *
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return IKachel
     */
    public IKachel getKachelByCoordinates(int x, int y) {
        int j = x / Einstellungen.LAENGE_KACHELN_X;
        int i = y / Einstellungen.LAENGE_KACHELN_Y;
        return spielmodell.getTileMap().getKachel(i, j);
    }

    /**
     * Methode isSpielfeldrand, gibt boolean zurück, der wahr ist, wenn die gegebenen Koordinaten
     * außerhalb des Spielfelds liegen.
     *
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return boolean
     */
    public boolean isSpielfeldrand(int x, int y) {
        return x <= 0 || x >= SpielfeldBreite || y <= 0 || y >= SpielfeldHoehe;
    }

    /**
     * Methode isErlaubteKoordinate, gibt boolean zurück, der wahr ist, wenn die gegebenen Koordinaten weder
     * außerhalb des Spielfelds, noch auf einer unbetretbaren Kachel liegen.
     *
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return boolean
     */
    public boolean isErlaubteKoordinate(int x, int y) {
        if (!isSpielfeldrand(x, y)) {
            return getKachelByCoordinates(x, y).istBetretbar();
        } else return false;
    }

    public void anzeigeTitelLevel(int LevelNr) {
        frame.setTitle(Einstellungen.TITLE + "   Level: " + Integer.toString(LevelNr));
    }

    /**
     * Lädt mithilfe des DateiService eine JSON Datei
     *
     * @return die geladene Spielwelt vom Typ ISpielwelt
     */

    public ISpielwelt ladeSpielwelt() {
        return dateiService.ladeSpielwelt("spielwelt.json");
    }


    public int countdown() {
        if (time > 0) {
            time = duration - (millis() - CountStart) / 1000;
            fill(0);
            text(time, 1150, 20);
        }
        return time;
    }


    public void gameover() {
        if (Spieler.getLebensenergie() <= 0 || time <= 0) {
            pushStyle();
            List<Gegenstand> inhalt = Spieler.getInventar();
            inhalt.clear();
            fill(0, 0, 0);
            rect(200, 120, 800, 600);
            fill(138, 3, 3);
            textSize(80);
            text("Game Over", 410, 350);
            textSize(30);
            text("Goldbetrag: " + Spieler.getGold(), 410,400);
            textSize(45);
            text("Press 'R' to Restart", 410, 500);
            text("Press 'Q' to Exit", 410, 550);


            popStyle();


            if (keyPressed) {
                if (key == 'R' || key == 'r') {
                    restart();
                }
                if (key == 'Q' || key == 'q') {
                    System.exit(0);
                }
            }
        }
    }

    public void restart() {

        clear();
        background(255, 250, 250);
        Spieler.setLebensenergie(10);
        setup();
        time = 20;
        duration = 20;
        Spieler.setGold(5);

        ISpielwelt resetSpielwelt = ladeSpielwelt();
        this.spielmodell.setSpielwelt(resetSpielwelt);
        spielmodell.setSzeneNr(0);
        spielmodell.betreteSzene(spielmodell.getSzeneNr());


/**Wenn GameOver aufgerufen wird,wird das Inventar gelöscht, damit man nicht Nahrung zu sich nehmen kann und dadurch
 * das Leben auf 0+ aufgefüllt wird. Zeit wird bei Restart zurückgesetzt. Gold wird ausgegeben und zurückgestzt.
 *
    Anfängliche Spielwelt wird neu aus json datei geladen und in das Spielmodell gesetzt.
    Erste Szene wird gesetzt und betreten
 *
 */

    }

    public void startx(IMovable movable) {
        if (time >= 20) {
            Spielerstartx = Spieler.getPosX();
            Spielerstarty = Spieler.getPosX();
            movablestartx = movable.getPosX();
            movavlestarty = movable.getPosY();
        }
        Spieler.setPosition(Spielerstartx,Spielerstarty);
        movable.setPosition(movablestartx,movavlestarty);
    }

    /**
     * Versuch die Startpositionen am Anfang aufzugreifen und diese bei Restart wieder in SetPosition aufrufen
     */



}

