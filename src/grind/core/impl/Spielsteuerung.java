package grind.core.impl;

import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.kacheln.impl.Levelausgang;
import grind.movables.IMovable;
import grind.movables.ISchatz;
import grind.movables.ISpielfigur;
import grind.movables.impl.Levelende;
import grind.movables.impl.Nahrung;
import grind.movables.impl.Spielfigur;
import grind.movables.impl.*;
import grind.movables.monster.IMonster;
import grind.movables.monster.Monster;
import grind.util.Richtung;
import grind.util.Einstellungen;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

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

    Dictionary<String, PImage> images = new Hashtable<String, PImage>();

    public ISpielmodell getSpielmodell() {
        return spielmodell;
    }

    public void setSpielmodell(ISpielmodell spielmodell) {
        this.spielmodell = spielmodell;
    }

    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;
    boolean klicked;


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
        this.SpielerGeschwindigkeit = (int) Spieler.getGESCHWINDIGKEIT();
        this.klicked = false;
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
        //Spieler.ladeIMGSpielfigur(this);
        ladeBilder(this);
        anzeigeTitelLevel(this.spielmodell.getSzeneNr() + 1);

    }

    public void ladeBilder(Spielsteuerung spielsteuerung) {
        Dictionary images = new Hashtable();
        images.put("class grind.movables.monster.Zombie", (PImage) spielsteuerung.loadImage("Zombie.png"));
        images.put("class grind.movables.impl.Spielfigur", (PImage) spielsteuerung.loadImage("Spielfigur.png"));
        images.put("class grind.movables.impl.Heiltrank", (PImage) spielsteuerung.loadImage("Heiltrank.png"));
        images.put("class grind.movables.impl.Apfel", (PImage) spielsteuerung.loadImage("Apfel.png"));
        images.put("class grind.movables.impl.Mango", (PImage) spielsteuerung.loadImage("Mango.png"));
        images.put("class grind.movables.impl.Gold", (PImage) spielsteuerung.loadImage("Gold.png"));
        images.put("class grind.movables.monster.FeuerMonster", (PImage) spielsteuerung.loadImage("Feuermonster.png"));
        images.put("class grind.movables.monster.DornPflanze", (PImage) spielsteuerung.loadImage("Dornpflanze.png"));
        images.put("Schwert Level 1", (PImage) spielsteuerung.loadImage("newSword1.png"));
        images.put("Schwert Level 2", (PImage) spielsteuerung.loadImage("newSword2.png"));
        images.put("Bogen Level 1", (PImage) spielsteuerung.loadImage("Bogen1.png"));
        images.put("Bogen Level 2", (PImage) spielsteuerung.loadImage("Bogen1.png"));
        images.put("class grind.movables.impl.Pfeil", (PImage) spielsteuerung.loadImage("pfeil.png"));
        images.put("Spezialattacke Level 1", (PImage) spielsteuerung.loadImage("bluefirering.png"));
        images.put("Spezialattacke Level 2", (PImage) spielsteuerung.loadImage("bluefirering.png"));
        spielsteuerung.setImages(images);
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
        int Schulterbreite = 10;
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
                if (key == Einstellungen.TASTE_INVENTAR && Spieler.getInventarGuiGroeße() == 10) {
                    Spieler.setInventarGuiGroeße(30);
                    Spieler.playBackpackOpenSound();
                    keyPressed = false;
                } else if (key == Einstellungen.TASTE_INVENTAR && Spieler.getInventarGuiGroeße() == 30) {
                    Spieler.setInventarGuiGroeße(10);
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
        pruefeUmgebung();
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

        //Items mit klick verwenden
        if (mouseButton == RIGHT) {
            Spieler.klickItems(mouseX, mouseY);
        }

        //Items verschieben
        if (mouseButton == LEFT && klicked == false) {
            int invPos = Spieler.getInvPos(mouseX, mouseY);
            if (invPos >= 0) {
                klicked = true;
                Spieler.auswahl = Spieler.getInventar().get(invPos);
                Spieler.getInventar().remove(invPos);
                Spieler.auswahl.setPosition(mouseX, mouseY);
                Spieler.auswahl.zeichne(this);
            }
        } else if (mouseButton == LEFT && klicked == true) {
            int neuePos = Spieler.getInvPos(mouseX, mouseY);
            if (neuePos >= 0) {
                Spieler.getInventar().add(neuePos, Spieler.auswahl);
            } else {
                Spieler.getInventar().add(Spieler.auswahl);
            }
            Spieler.auswahl = null;
            klicked = false;

        }


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
        if (spielmodell.getSzene() instanceof ILevel) {
            ILevel level = (ILevel) spielmodell.getSzene();
            IKachel spielerKachel = level.getTileMap().getKachel(spielerPosX, spielerPosY);
            if (spielerKachel instanceof Levelausgang) {
                //            System.out.println(spielerKachel);
                levelBeendet = true;
            }
        }
        return levelBeendet;
    }

    /**
     * Kollisionsabfrage: prüft Kollision zwischen Spieler und Movable und löst entsprechende Methode aus.
     * z.B. Spieler hat Kollision mit Gold --> beimSammeln() --> löscht Gold aus dem Level
     */
    public void pruefeKollisionen() {
        ISpielfigur figur = this.spielmodell.getFigur();


        Waffe waffe = figur.getWaffe();
        int WaffeXp = waffe.getPosX() + (waffe.getGroesse() / 2);
        int WaffeXn = waffe.getPosX() - (waffe.getGroesse() / 2);
        int WaffeYp = waffe.getPosY() + (waffe.getGroesse() / 2);
        int WaffeYn = waffe.getPosY() - (waffe.getGroesse() / 2);

        int PfeilXp = figur.getPfeil().getPosX() + (figur.getPfeil().getGroesse() / 2);
        int PfeilXn = figur.getPfeil().getPosX() - (figur.getPfeil().getGroesse() / 2);
        int PfeilYp = figur.getPfeil().getPosY() + (figur.getPfeil().getGroesse() / 2);
        int PfeilYn = figur.getPfeil().getPosY() - (figur.getPfeil().getGroesse() / 2);


        for (IMovable movable : this.spielmodell.getMovables()) {

            int FigurXp = figur.getPosX() + (Einstellungen.GROESSE_SPIELFIGUR / 2);
            int FigurXn = figur.getPosX() - (Einstellungen.GROESSE_SPIELFIGUR / 2);
            int FigurYp = figur.getPosY() + (Einstellungen.GROESSE_SPIELFIGUR / 2);
            int FigurYn = figur.getPosY() - (Einstellungen.GROESSE_SPIELFIGUR / 2);

            int MovableXp = movable.getPosX() + movable.getGroesse() / 2;
            int MovableXn = movable.getPosX() - movable.getGroesse() / 2;
            int MovableYp = movable.getPosY() + movable.getGroesse() / 2;
            int MovableYn = movable.getPosY() - movable.getGroesse() / 2;

            if (pruefeKollision(figur, movable)) {
                if (movable instanceof IMonster) {
                    ((IMonster) movable).beiKollision(figur, movable);
                } else if (movable instanceof ISchatz) {
                    if (!(movable instanceof Waffe)) {
                        ((ISchatz) movable).beimSammeln(figur); // zB. erhöht Gold
                        //}
                        if (!(movable instanceof Waffe)) {
                            spielmodell.removeMovable(movable);
                        } // löscht Schatz aus Level

                        return;
                    }
                    //Wenn Spielfigur auf gleicher Position wie Waffen-Item, soll es aufgesammelt werden
                    if ((movable instanceof Waffe)) {
                        ((ISchatz) movable).beimSammeln(figur);
                        System.out.println("Waffe wurde aufgesammelt!");
                        spielmodell.removeMovable(movable);

                        //Spieler.waffeAusgestattet = true;
                    } else if (movable instanceof Nahrung) {
                        // TODO: Nahrung zu Inventar hinzufügen
                    }
                }
            } else if ((WaffeXp > MovableXn) & (WaffeXn < MovableXp) & (WaffeYp > MovableYn) & (WaffeYn < MovableYp) & waffe instanceof Spezialattacke) {
                if (movable instanceof Monster) {
                    System.out.println(((Monster) movable).getLebensenergie());
                    System.out.println("Kollision!!");

                    ((Monster) movable).reduziereLebensenergie(waffe.getSchaden());


                }
            } else if ((WaffeXp > MovableXn) & (WaffeXn < MovableXp) & (WaffeYp > MovableYn) & (WaffeYn < MovableYp) & (key == ' ') & keyPressed) {
                if (movable instanceof Monster) {
                    System.out.println(((Monster) movable).getLebensenergie());
                    System.out.println("Kollision!!");
                    ((Monster) movable).reduziereLebensenergie(waffe.getSchaden());

                }
            } else if ((PfeilXp > MovableXn) & (PfeilXn < MovableXp) & (PfeilYp > MovableYn) & (PfeilYn < MovableYp)) {
                if (movable instanceof Monster) {
                    System.out.println("Pfeil: " + ((Monster) movable).getLebensenergie());
                    ((Monster) movable).reduziereLebensenergie(figur.getPfeil().getSchaden());
                    spielmodell.removeMovable(figur.getPfeil());
                    figur.setPfeilAbgeschossen(false);
                    figur.getPfeil().setPosition(0, 0);
                }

            }
            if (this.isSpielfeldrand(figur.getPfeil().getPosX(), figur.getPfeil().getPosY())) {
                spielmodell.removeMovable(figur.getPfeil());
                figur.setPfeilAbgeschossen(false);
            }

        }
    }

    private boolean pruefeKollision(IMovable eins, IMovable movable) {
        int WaffeXp = eins.getPosX() + (eins.getGroesse() / 2);
        int WaffeXn = eins.getPosX() - (eins.getGroesse() / 2);
        int WaffeYp = eins.getPosY() + (eins.getGroesse() / 2);
        int WaffeYn = eins.getPosY() - (eins.getGroesse() / 2);


        int MovableXp = movable.getPosX() + movable.getGroesse() / 2;
        int MovableXn = movable.getPosX() - movable.getGroesse() / 2;
        int MovableYp = movable.getPosY() + movable.getGroesse() / 2;
        int MovableYn = movable.getPosY() - movable.getGroesse() / 2;

        return (WaffeXp > MovableXn) & (WaffeXn < MovableXp) & (WaffeYp > MovableYn) & (WaffeYn < MovableYp);
    }

    public void pruefeUmgebung() {
        for (IMovable movable : this.spielmodell.getMovables()) {
            if (movable instanceof Monster) {
                ((IMonster) movable).inDerNaehe(spielmodell.getFigur(), movable);
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
        surface.setTitle(Einstellungen.TITLE + "   Level: " + Integer.toString(LevelNr));
    }

    /**
     * Lädt mithilfe des DateiService eine JSON Datei
     *
     * @return die geladene Spielwelt vom Typ ISpielwelt
     */
    public ISpielwelt ladeSpielwelt() {
        return dateiService.ladeSpielwelt("spielwelt.json");
    }

    public Dictionary getImages() {
        return images;
    }

    public void setImages(Dictionary images) {
        this.images = images;
    }
}