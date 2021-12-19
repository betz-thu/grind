package grind.core.impl;

import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.impl.Apfel;
import grind.movables.impl.Movable;
import grind.movables.impl.Nahrung;
import grind.movables.monster.IMonster;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @Autor Megatronik
 * steuert Spielfigur, zeigt sichtbare Objekte an.
 */
import java.util.ArrayList;
import java.util.List;

public class Spielsteuerung extends PApplet {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;
    private Spielfigur Spieler;
    private int SpielerGeschwindigkeit;
    // private ITileMap tileMap;
    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;



    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt());
        // this.spielmodell.betreteSzene(1);
        this.spielmodell.betreteSzene(this.spielmodell.getSzeneNr());
        this.Spieler = (Spielfigur) spielmodell.getFigur();
        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
        // this.tileMap = (ITileMap) spielmodell.getTileMap();
    }

    /**
     * Methode settings, setzt Spielfeldgröße auf die in den Einstellungen gesetzten Parameter.
     */
    @Override
    public void settings() {
        SpielfeldBreite = Einstellungen.LAENGE_KACHELN_X*Einstellungen.ANZAHL_KACHELN_X;
        SpielfeldHoehe = Einstellungen.LAENGE_KACHELN_Y*Einstellungen.ANZAHL_KACHELN_Y;
        size(SpielfeldBreite,SpielfeldHoehe);
    }

    /**
     * Methode setup, lädt Darstellung der Spielfigur.
     */
    @Override
    public void setup() {
        imageMode(PConstants.CORNER);
        Spieler.ladeIMGSpielfigur(this);

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

    }

    /**
     * Methode eingabe: richtet Figur in Laufrichtung aus, wenn möglich bewegt sie die Figur in Laufrichtung.
     * Beim Prüfen, ob die Figur in Laufrichtung bewegt werden kann, werden zwei punkte auf Schulterbreite überprüft,
     * damit die Figur nicht teilweise in unbetretbare Kacheln läuft.
     */
    private void eingabe() {
        int x = Spieler.getPosX();
        int y = Spieler.getPosY();
        if (keyPressed) {
            if (key == 'a' || keyCode == LEFT) {
                Spieler.setAusrichtung(Richtung.W);
                if(isErlaubteKoordinate(x-SpielerGeschwindigkeit-20,y-20) && isErlaubteKoordinate(x-SpielerGeschwindigkeit-20,y+20)){
                    Spieler.bewege(Richtung.W);
                }
            } else if (key == 'w' || keyCode == UP) {
                Spieler.setAusrichtung(Richtung.N);
                if(isErlaubteKoordinate(x-20,y-SpielerGeschwindigkeit-20) && isErlaubteKoordinate(x+20,y-SpielerGeschwindigkeit-20)){
                    Spieler.bewege(Richtung.N);
                }
            } else if (key == 's' || keyCode == DOWN) {
                Spieler.setAusrichtung(Richtung.S);
                if(isErlaubteKoordinate(x-20,y+SpielerGeschwindigkeit+20) && isErlaubteKoordinate(x+20,y+SpielerGeschwindigkeit+20)){
                    Spieler.bewege(Richtung.S);
                }
            } else if (key == 'd' || keyCode == RIGHT) {
                Spieler.setAusrichtung(Richtung.O);
                if(isErlaubteKoordinate(x+SpielerGeschwindigkeit+20,y-20) && isErlaubteKoordinate(x+SpielerGeschwindigkeit+20,y+20)){
                    Spieler.bewege(Richtung.O);
                }
            }
        }

        //F12 neue Szene
        if (keyPressed && !pressed){
            if (keyCode == 123) {
                pressed = true;
            }
        } else if(!keyPressed && pressed){
            pressed = false;
            System.out.println("F12");
            spielmodell.setSzeneNr(spielmodell.getSzeneNr()+1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
        }
    }

    private void aktualisiere() {
        pruefeKollisionen();
        spielmodell.bewege();
        levelBeendet = ueberpruefeLevelende();
        //Nachdem das Levelende erfolgreich beendet wurde, wird in die nächste Szene gesprungen
        if(levelBeendet){
            levelBeendet = false;
            spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
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
        int posY = spielmodell.getFigur().getPosY();
        int posX = spielmodell.getFigur().getPosX();
        int kachelX = posY / Einstellungen.LAENGE_KACHELN_Y;
        int kachelY = posX / Einstellungen.LAENGE_KACHELN_X;
        IKachel aktuelleKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(kachelX, kachelY);
        if (aktuelleKachel instanceof Levelausgang){
            System.out.println(aktuelleKachel);
            levelBeendet = true;
        }

        for (int i=0; i<5;i++){
            if (spielmodell.getFigur().getInventar().size()>=i+1) {
                if (spielmodell.getFigur().getInventar().get(i) instanceof Apfel) {
                    System.out.println("Levelende Bedingung wurde gefunden");
                    levelBeendet = true;
                    spielmodell.getFigur().getInventar().remove(i);
                    break;

//                    spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
//                    spielmodell.betreteSzene(spielmodell.getSzeneNr());
                }
            }
        }

        return levelBeendet;
    }
/**
 * Kollisionsabfrage: prüft Kollision von Spieler mit Movable und führt dann dementsprechende Methode aus.
 * z.B. Wenn Kollision von Spieler mit Monster --> Spieler bekommt schaden
 *  Wenn Kollision von Spieler mit Gold --> Erhöhe Kontostand und lösche Gold aus Spielwelt
 *
 *  wird in aktualisiere aufgerufen, um dauerhaft nach Kollisionen zu prüfen
 */
    public void pruefeKollisionen() {
        int FigurXp = this.spielmodell.getFigur().getPosX()+(Einstellungen.GROESSE_SPIELFIGUR/2);
        int FigurXn = this.spielmodell.getFigur().getPosX()-(Einstellungen.GROESSE_SPIELFIGUR/2);
        int FigurYp = this.spielmodell.getFigur().getPosY()+(Einstellungen.GROESSE_SPIELFIGUR/2);
        int FigurYn = this.spielmodell.getFigur().getPosY()-(Einstellungen.GROESSE_SPIELFIGUR/2);

        for (IMovable movable : this.spielmodell.getMovables()) {
            int MovableXp = movable.getPosX()+movable.getGroesse()/2;
            int MovableXn = movable.getPosX()-movable.getGroesse()/2;
            int MovableYp = movable.getPosY()+movable.getGroesse()/2;
            int MovableYn = movable.getPosY()-movable.getGroesse()/2;
            if ((FigurXp > MovableXn) & (FigurXn< MovableXp) & (FigurYp > MovableYn)  & (FigurYn < MovableYp)) {

                if(movable instanceof IMonster) {

                    ((IMonster) movable).beiKollision(spielmodell.getFigur());

                    // TODO: prüfe ob Monster ein Feuerball ist. Wenn ja, bekommt es Schaden und wird dann aus Spielwelt gelöscht --> spielmodell.removeMovable(movable)
                }
                else if(movable instanceof ISchatz){
                    ((ISchatz) movable).beimSammeln(spielmodell.getFigur()); // Erhöht Gold
                    spielmodell.removeMovable(movable); // löscht Schatz aus Level
                    return;
                }
                else if(movable instanceof Nahrung){
                    // TODO: Nahrung zu Inventar hinzufügen und aus Spielwelt löschen --> spielmodell.removeMovable(movable)
                }
            }
        }
    }


    /**
     * Methode getKachelByCoordinates, gibt IKachel zurück, auf der die gegebenen Koordinaten liegen.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     * @return IKachel
     */
    public IKachel getKachelByCoordinates(int x, int y) {
        int j = (int) x/Einstellungen.LAENGE_KACHELN_X;
        int i = (int) y/Einstellungen.LAENGE_KACHELN_Y;
        return spielmodell.getTileMap().getKachel(i,j);
    }

    /**
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