package grind.core.impl;

import grind.kacheln.impl.Levelausgang;
import grind.movables.ISchatz;
import grind.movables.impl.Apfel;
import grind.util.Richtung;
import grind.core.ISpielmodell;
import grind.kacheln.IKachel;
import grind.movables.impl.Spielfigur;
import grind.util.Einstellungen;
import grind.welt.impl.DummySpielwelt;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @Autor Megatronik
 * steuert Spielfigur, zeigt sichtbare Objekte an.
 */

public class Spielsteuerung extends PApplet {
    private static int SpielfeldBreite;
    private static int SpielfeldHoehe;
    private Spielfigur Spieler;
    private int SpielerGeschwindigkeit;
    private String fTaste;
    // private ITileMap tileMap;
    ISpielmodell spielmodell;
    boolean pressed = false;
    boolean levelBeendet = false;
    DateiService dateiService;


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
        this.dateiService = new DateiService();
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

        pruefeKollisionen();

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

        abfrageFTasten();
    }

    /**
     * @Autor LuHe20
     * Cheat für das Überspringen einer Szene mit F12.
     */
    private void abfrageFTasten() {
        //F12 neue Szene
        if (keyPressed && !pressed){
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
            if(fTaste.equals("F12")){
                levelBeendet = true;
            } else if(fTaste.equals("F11")){
                speichereSpielwelt(this.spielmodell);
                System.out.println("F11");
            } else if(fTaste.equals("F10")){
                levelBeendet = true;
                this.spielmodell = ladeSpielwelt();
                System.out.println("F10");
            }

            pressed = false;

        }
    }

    private void aktualisiere() {
        spielmodell.bewege();
        levelBeendet = ueberpruefeLevelende();
        starteNeueSzene();

    }

    /**
     * @Autor LuHe20
     * Startet, wenn levelBeendet Bedingung wahr ist, die nächste Szene.
     */
    private void starteNeueSzene() {
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
        pruefeLevelausgang();

        for (int i=0; i<5;i++){
            if (spielmodell.getFigur().getInventar().size()>=i+1) {
                if (spielmodell.getFigur().getInventar().get(i) instanceof Apfel) {
                    System.out.println("Levelende Bedingung wurde gefunden");
                    levelBeendet = true;

                }
            }
        }

        return levelBeendet;
    }

    /**
     * @Autor LuHe20
     * Prüft, ob die aktuelle Kachel auf der sich der Spieler befindet,
     * eine Kachel des Typs: Levelausgang ist.
     * @return levelBeendet
     */
    private boolean pruefeLevelausgang() {
        int spielerPosX = spielmodell.getFigur().getPosY()/Einstellungen.LAENGE_KACHELN_Y;
        int spielerPosY = spielmodell.getFigur().getPosX()/Einstellungen.LAENGE_KACHELN_X;
        IKachel spielerKachel = spielmodell.getSzene().getLevel().getTileMap().getKachel(spielerPosX,spielerPosY);
        if (spielerKachel instanceof Levelausgang){
//            System.out.println(spielerKachel);
            levelBeendet = true;
        }
        return levelBeendet;
    }

    public void pruefeKollisionen(){ // als extra Methode oder zu aktualisiere() dazu?
        int FigurX = this.spielmodell.getFigur().getPosX();
        int FigurY = this.spielmodell.getFigur().getPosY();
        int i = 0;
        int toRemove = -1;
        for(ISchatz schatz: this.spielmodell.getSchaetze()){
            if(((FigurX > schatz.getPosX()-30) & (FigurX<schatz.getPosX()+30)) & ((FigurY > schatz.getPosY()-30)) & (FigurY<schatz.getPosY()+30)) {
                schatz.beimSammeln(this.spielmodell.getFigur());
                this.spielmodell.getMovables().remove(schatz);
                toRemove = i;
            }
            i += 1;
        }

        if(toRemove != -1){
            this.spielmodell.getSchaetze().remove(toRemove);
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

    private ISpielmodell ladeSpielwelt(){
        return this.dateiService.ladeSpielmodell("spielwelt.json");
    }

    private void speichereSpielwelt(ISpielmodell spielmodell){
        this.dateiService.speicheSpielmodell(spielmodell);
    }
}
