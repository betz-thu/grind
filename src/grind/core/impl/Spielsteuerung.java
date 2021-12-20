package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.impl.Apfel;
import grind.movables.impl.Nahrung;
import grind.movables.impl.Spielfigur;
import grind.movables.monster.IMonster;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author Megatronik
 * steuert Spielfigur, zeigt sichtbare Objekte an.
 */
public class Spielsteuerung extends PApplet {
    private int SpielfeldBreite;
    private int SpielfeldHoehe;
    final Spielfigur Spieler;
    final int SpielerGeschwindigkeit;

    public ISpielmodell getSpielmodell() {
        return spielmodell;
    }

    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }

    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;

    public int getSpielfeldBreite() {
        return SpielfeldBreite;
    }

    public void setSpielfeldBreite(int spielfeldBreite) {
        SpielfeldBreite = spielfeldBreite;
    }

    public int getSpielfeldHoehe() {
        return SpielfeldHoehe;
    }

    public void setSpielfeldHoehe(int spielfeldHoehe) {
        SpielfeldHoehe = spielfeldHoehe;
    }

    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt(), this);
        this.spielmodell.betreteSzene(this.spielmodell.getSzeneNr());
        this.Spieler = (Spielfigur) spielmodell.getFigur();
        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
    }

    /**
     * @MEGAtroniker
     * Methode settings, setzt Spielfeldgröße auf die in den Einstellungen gesetzten Parameter.
     */
    @Override
    public void settings() {
        SpielfeldBreite = Einstellungen.LAENGE_KACHELN_X * Einstellungen.ANZAHL_KACHELN_X;
        SpielfeldHoehe = Einstellungen.LAENGE_KACHELN_Y * Einstellungen.ANZAHL_KACHELN_Y;
        size(SpielfeldBreite, SpielfeldHoehe);
    }

    /**
     * @MEGAtroniker
     * Methode setup, lädt Darstellung der Spielfigur.
     */
    @Override
    public void setup() {
        imageMode(PConstants.CORNER);
        Spieler.ladeIMGSpielfigur(this);

    }

    /**
     * @MEGAtroniker
     * Methode draw, zeichnet alle sichtbare Elemente.
     * (Spielfigur, Lebensenergie, Kontostand, Tilemap)
     */
    @Override
    public void draw() {
        eingabe();
        aktualisiere();
        zeichne();
        pruefeKollisionen();
    }

    /**
     * @MEGAtroniker
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
            }
        }

        szeneUeberspringen();
    }

    /**
     * @MEGAtroniker
     * Die Methode springt zur nächsten Szene durch das Betätigen der Taste "F12"
     */
    private void szeneUeberspringen() {
        //F12 neue Szene
        if (keyPressed && !pressed) {
            if (keyCode == 123) {
                pressed = true;
            }
        } else if (!keyPressed && pressed) {
            pressed = false;
            System.out.println("F12");
            spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
        }
    }


    private void aktualisiere() {
        pruefeKollisionen();
        spielmodell.bewege();
        levelBeendet = ueberpruefeLevelende();
        //Nachdem das Levelende erfolgreich beendet wurde, wird in die nächste Szene gesprungen
        if (levelBeendet) {
            levelBeendet = false;
            spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
        }

    }

    public void pruefeKollisionen() {
        int FigurXp = this.spielmodell.getFigur().getPosX() + (Einstellungen.GROESSE_SPIELFIGUR / 2);
        int FigurXn = this.spielmodell.getFigur().getPosX() - (Einstellungen.GROESSE_SPIELFIGUR / 2);
        int FigurYp = this.spielmodell.getFigur().getPosY() + (Einstellungen.GROESSE_SPIELFIGUR / 2);
        int FigurYn = this.spielmodell.getFigur().getPosY() - (Einstellungen.GROESSE_SPIELFIGUR / 2);
        for (IMovable movable : this.spielmodell.getMovables()) {
            int MovableXp = movable.getPosX() + movable.getGroesse() / 2;
            int MovableXn = movable.getPosX() - movable.getGroesse() / 2;
            int MovableYp = movable.getPosY() + movable.getGroesse() / 2;
            int MovableYn = movable.getPosY() - movable.getGroesse() / 2;
            if ((FigurXp > MovableXn) & (FigurXn < MovableXp) & (FigurYp > MovableYn) & (FigurYn < MovableYp)) {
                if (movable instanceof IMonster) {
                    ((IMonster) movable).beiKollision(spielmodell.getFigur());
                    // TODO: prüfe ob Monster ein Feuerball ist. Wenn ja, bekommt es Schaden und wird dann aus Spielwelt gelöscht --> spielmodell.removeMovable(movable)
                } else if (movable instanceof ISchatz) {
                    ((ISchatz) movable).beimSammeln(spielmodell.getFigur()); // Erhöht Gold
                    spielmodell.removeMovable(movable); // löscht Schatz aus Level
                    //return;
                } else if (movable instanceof Nahrung) {
                    // TODO: Nahrung zu Inventar hinzufügen und aus Spielwelt löschen --> spielmodell.removeMovable(movable)
                    System.out.println("Nahrung augenommen!");
                }
            }
        }
    }

    private void zeichne() {
        spielmodell.zeichne(this);
    }

    @Override
    public void mousePressed() {
        // nur notwendig, falls Maus benötigt wird
    }

    //TODO getkachelByCoordinate... des hier ist ja schlimm
    public boolean ueberpruefeLevelende() {
        //Abfrage ob der aktuelle Standpunkt der Spielfigur eine Kachel vom Typ Levelausgang ist.
        int posY = spielmodell.getFigur().getPosY();
        int posX = spielmodell.getFigur().getPosX();
        int kachelX = posY / Einstellungen.LAENGE_KACHELN_Y;
        int kachelY = posX / Einstellungen.LAENGE_KACHELN_X;
        IKachel aktuelleKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(kachelX, kachelY);
        if (aktuelleKachel instanceof Levelausgang) {
            System.out.println(aktuelleKachel);
            if (spielmodell.getSzene().getLevel().getTileMap().getKachel(spielmodell.getFigur().getPosY() / Einstellungen.LAENGE_KACHELN_Y, spielmodell.getFigur().getPosX() / Einstellungen.LAENGE_KACHELN_X) instanceof Levelausgang) {
                System.out.println(spielmodell.getSzene().getLevel().getTileMap().getKachel(spielmodell.getFigur().getPosY() / 39, spielmodell.getFigur().getPosX() / 39));
                levelBeendet = true;
            }
            for (int i = 0; i < 5; i++) {
                if (spielmodell.getFigur().getInventar().size() >= i + 1) {
                    if (spielmodell.getFigur().getInventar().get(i) instanceof Apfel) {
                        System.out.println("Levelende Bedingung wurde gefunden");
                        levelBeendet = true;
                    }
                }
            }
        }
        return levelBeendet;
    }


    /**
     * @MEGAtroniker
     * Methode getKachelByCoordinates, gibt IKachel zurück, auf der die gegebenen Koordinaten liegen.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return IKachel
     */

    public IKachel getKachelByCoordinates(int x, int y) {
        int j =  x/Einstellungen.LAENGE_KACHELN_X;
        int i =  y/Einstellungen.LAENGE_KACHELN_Y;
        return spielmodell.getTileMap().getKachel(i,j);
    }

    /**
     * @MEGAtroniker
     * Methode isSpielfeldrand, gibt boolean zurück, der wahr ist, wenn die gegebenen Koordinaten
     * außerhalb des Spielfelds liegen.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return boolean
     */
    public boolean isSpielfeldrand(int x, int y){
        return x <= 0 || x >= SpielfeldBreite || y <= 0 || y >= SpielfeldHoehe;
    }

    /**
     * @MEGAtroniker
     * Methode isErlaubteKoordinate, gibt boolean zurück, der wahr ist, wenn die gegebenen Koordinaten weder
     * außerhalb des Spielfelds, noch auf einer unbetretbaren Kachel liegen.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return boolean
     */
    public boolean isErlaubteKoordinate(int x, int y) {
        if(!isSpielfeldrand(x,y)){
            return getKachelByCoordinates(x,y).istBetretbar();
        } else return false;
    }
}
