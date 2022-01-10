package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.impl.Levelende;
import grind.movables.impl.Nahrung;
import grind.movables.impl.Spielfigur;
import grind.movables.impl.*;
import grind.movables.monster.IMonster;
import grind.movables.monster.Monster;
import grind.util.Einstellungen;
import grind.util.Richtung;
import grind.welt.ISpielwelt;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @Autor Megatronik
 * steuert Spielfigur, zeigt sichtbare Objekte an.
 */
public class Spielsteuerung extends PApplet {

    public enum LevelProgress {
        GETRARNT,
        SICHTBAR,
        AKTIV
    }


    LevelProgress levelprogress = LevelProgress.GETRARNT;


    private int SpielfeldBreite;
    private int SpielfeldHoehe;
    final Spielfigur Spieler;
    final int SpielerGeschwindigkeit;
    int Tastendruck;
    private String fTaste;
    private int levelTimeOffset;

    public ISpielmodell getSpielmodell() {
        return spielmodell;
    }

    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }

    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;



    /**
     * Konstruktor Spielsteuerung, instanziierung des Spielmodells, enthält Szene, Spielfigur, SpielerGeschwindigkeit
     * und Tilemap.
     */
    public Spielsteuerung() {
        this.spielmodell = new Spielmodell(new DummySpielwelt(),this);
        // this.spielmodell.betreteSzene(1);
        this.spielmodell.betreteSzene(this.spielmodell.getSzeneNr());

        this.Spieler = (Spielfigur) spielmodell.getFigur();
        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
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
        anzeigeTitelLevel(this.spielmodell.getSzeneNr()+1);
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
            else if (key == '1') {
                Tastendruck = 0;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='2') {
                Tastendruck = 1;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='3') {
                Tastendruck = 2;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='4') {
                Tastendruck = 3;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='5') {
                Tastendruck = 4;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='6') {
                Tastendruck = 5;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='7') {
                Tastendruck = 6;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='8') {
                Tastendruck = 7;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='9') {
                Tastendruck = 8;
                Spieler.benutze(Tastendruck);
                keyPressed = false;
            }
            else if (key =='0') {
                Tastendruck = 9;
                Spieler.benutze(Tastendruck);
                keyPressed = false;

            }
            //Inventar öffnen
            if(keyPressed) {
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
     * @MEGAtroniker
     * Die Methode springt zur nächsten Szene durch das Betätigen der Taste "F12"
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
            } else if(keyCode == 122){
                pressed = true;
                fTaste = "F11";
            } else if(keyCode == 121){
                pressed = true;
                fTaste = "F10";
            }
        } else if(!keyPressed && pressed){
            switch (fTaste) {
                case "F12":

                    levelBeendet = true;

                    break;
                case "F11":

                    this.spielmodell.speichereSpielwelt();

                    System.out.println("F11");

                    break;
                case "F10":

                    ISpielwelt welt;
                    welt = this.spielmodell.ladeSpielwelt();
                    this.spielmodell.setSpielwelt(welt);
                    spielmodell.setSzeneNr(0);
                    spielmodell.betreteSzene(spielmodell.getSzeneNr());

                    System.out.println("F10");

                    break;
            }

            pressed = false;
        }
    }

    private void aktualisiere() {
        pruefeKollisionen();
        spielmodell.entferneToteMonster();
        spielmodell.bewege();
        pruefeLevelzeit();
        levelBeendet = ueberpruefeLevelende();
        starteNeueSzene();
    }

    private void pruefeLevelzeit(){
        if (levelprogress == LevelProgress.GETRARNT &&  (millis() - levelTimeOffset) > 5000) {
            levelprogress = LevelProgress.SICHTBAR;
            Levelausgang la = spielmodell.getTileMap().getLevelausgang();  // Levelende-Kachel
            la.setLevelprogress(levelprogress);
        }
        else if (levelprogress == LevelProgress.SICHTBAR &&  (millis() - levelTimeOffset) > 15000) {
            levelprogress = LevelProgress.AKTIV;
            Levelausgang la = spielmodell.getTileMap().getLevelausgang();  // Levelende-Kachel
            la.setLevelprogress(levelprogress);
        }
    }

    /**
     * @author LuHe20
     * Startet, wenn levelBeendet Bedingung wahr ist, die nächste Szene.
     */
    private void starteNeueSzene() {
        if(levelBeendet){
            levelBeendet = false;
            spielmodell.setSzeneNr(spielmodell.getSzeneNr() + 1);
            spielmodell.betreteSzene(spielmodell.getSzeneNr());
            anzeigeTitelLevel(spielmodell.getSzeneNr() + 1);
            levelTimeOffset = millis();
            levelprogress=LevelProgress.GETRARNT;
            spielmodell.getTileMap().getLevelausgang().setLevelprogress(levelprogress);
        }
    }

    private void zeichne() {
        spielmodell.zeichne(this);
        anzeigeZeit(millis() - levelTimeOffset);
    }

    @Override
    public void mousePressed() {
        // nur notwendig, falls Maus benötigt wird
    }

    public boolean ueberpruefeLevelende() {
        //Abfrage ob der aktuelle Standpunkt der Spielfigur eine Kachel vom Typ Levelausgang ist.
        pruefeLevelausgang();

        for (int i=0; i<spielmodell.getFigur().getInventar().size();i++){
            if (spielmodell.getFigur().getInventar().size()>=i+1) {
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
     * @author LuHe20
     * Prüft, ob die aktuelle Kachel auf der sich der Spieler befindet,
     * eine Kachel des Typs: Levelausgang ist.
     * @return levelBeendet
     */
    private boolean pruefeLevelausgang() {

        if (levelprogress != LevelProgress.AKTIV)
            return false;
        int spielerPosX = spielmodell.getFigur().getPosY()/Einstellungen.LAENGE_KACHELN_Y;
        int spielerPosY = spielmodell.getFigur().getPosX()/Einstellungen.LAENGE_KACHELN_X;
        IKachel spielerKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(spielerPosX,spielerPosY);
        if (spielerKachel instanceof Levelausgang){
            levelBeendet = true;
        }
        return levelBeendet;
    }
/**
 * Kollisionsabfrage: prüft Kollision zwischen Spieler und Movable und löst entsprechende Methode aus.
 * z.B. Spieler hat Kollision mit Gold --> beimSammeln() --> löscht Gold aus dem Level
 */
    public void pruefeKollisionen() {
        int FigurXp = this.spielmodell.getFigur().getPosX()+(Einstellungen.GROESSE_SPIELFIGUR/2);
        int FigurXn = this.spielmodell.getFigur().getPosX()-(Einstellungen.GROESSE_SPIELFIGUR/2);
        int FigurYp = this.spielmodell.getFigur().getPosY()+(Einstellungen.GROESSE_SPIELFIGUR/2);
        int FigurYn = this.spielmodell.getFigur().getPosY()-(Einstellungen.GROESSE_SPIELFIGUR/2);
        int WaffeXp = this.spielmodell.getFigur().getWaffe().getPosX()+(spielmodell.getFigur().getWaffe().getGroesse()/2);

        int WaffeXn = this.spielmodell.getFigur().getWaffe().getPosX()-(spielmodell.getFigur().getWaffe().getGroesse()/2);
        int WaffeYp = this.spielmodell.getFigur().getWaffe().getPosY()+(spielmodell.getFigur().getWaffe().getGroesse()/2);
        int WaffeYn = this.spielmodell.getFigur().getWaffe().getPosY()-(spielmodell.getFigur().getWaffe().getGroesse()/2);





        for (IMovable movable : this.spielmodell.getMovables()) {
            int MovableXp = movable.getPosX()+movable.getGroesse()/2;
            int MovableXn = movable.getPosX()-movable.getGroesse()/2;
            int MovableYp = movable.getPosY()+movable.getGroesse()/2;
            int MovableYn = movable.getPosY()-movable.getGroesse()/2;
            if ((FigurXp > MovableXn) & (FigurXn< MovableXp) & (FigurYp > MovableYn)  & (FigurYn < MovableYp)) {

                if(movable instanceof IMonster) {

                    ((IMonster) movable).beiKollision(spielmodell.getFigur());
                }
                else if(movable instanceof ISchatz){
                    if (!(movable instanceof Waffe)) {
                        ((ISchatz) movable).beimSammeln(spielmodell.getFigur()); // zB. erhöht Gold
                    }
                    if(!(movable instanceof Waffe)){
                        spielmodell.removeMovable(movable);
                    } // löscht Schatz aus Level
                    if((movable instanceof Waffe) & !(Spieler.waffeAusgestattet)) {
                        ((ISchatz) movable).beimSammeln(spielmodell.getFigur());
                    }
                    return;
                }
                else if(movable instanceof Nahrung){
                    // TODO: Nahrung zu Inventar hinzufügen
                }

            }
            else if((WaffeXp > MovableXn) & (WaffeXn < MovableXp) & (WaffeYp > MovableYn) & (WaffeYn < MovableYp) & (key==' ')){
                if (movable instanceof Monster){
                    System.out.println(((Monster) movable).getLebensenergie());
                    ((Monster) movable).reduziereLebensenergie(spielmodell.getFigur().getWaffe().getSchaden());
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
        int j =  x/Einstellungen.LAENGE_KACHELN_X;
        int i =  y/Einstellungen.LAENGE_KACHELN_Y;
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

    /**
     * @Autor MurderDeathKill
     * Anzeige von Titel und Level im Window-Titel
     * @param LevelNr Levelindex + 1
     */
    public void anzeigeTitelLevel(int LevelNr){
        frame.setTitle(Einstellungen.TITLE + "   Level: " + Integer.toString(LevelNr));
    }
    /**
     * @Autor MurderDeathKill
     * Anzeige der Levelzeit rechts/oben im Spielfenster
     * @param mSek Levelzeit in ms
     */
    private void anzeigeZeit(int mSek){
        fill(30,30,180);  // Blau
        textSize(30);
        text(String.format("%.2f [sec]",mSek/1000.0), SpielfeldBreite-80,40);
    }
}