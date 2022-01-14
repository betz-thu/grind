package grind.core.impl;

import grind.kacheln.IKachel;
import grind.kacheln.impl.Wiese;
import grind.movables.IMovable;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.welt.ILevel;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummyLevelTestLeveleditor;
import grind.welt.impl.DummySiedlung;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeveleditorTestLeveleditor {

    Leveleditor leveleditor;
    int mausLinksTasteGedrueckt = 37;
    int mausRechtsTasteGedrueckt = 39;
    int mouseXKachelPlaceTest = 15;
    int mouseYKachelPlaceTest = 11;
    int mouseXMovablePlaceTest = 45;
    int mouseYMovablePlaceTest = 11;
    int mouseXMenuKachelTest = 916;
    int mouseYMenuKachelTest = 15;
    int mouseXMenuMovableTest = 943;
    int mouseYMenuMovableTest = 15;
    int mouseXButtonExitTest = 45;
    int mouseYButtonExitTest = 619;
    int mouseXButtonSpeichernTest = 125;
    int mouseYButtonSpeichernTest = 619;
    int mouseXButtonLadenTest = 217;
    int mouseYButtonLadenTest = 619;
    int mouseXButtonLeerenTest = 314;
    int mouseYButtonLeerenTest = 619;
    int mouseXButtonLevelTest = 399;
    int mouseYButtonLevelTest = 619;
    int mouseXButtonSiedlungTest = 485;
    int mouseYButtonSiedlungTest = 619;
    int mouseXButtonLevelVorTest = 587;
    int mouseYButtonLevelVorTest = 619;
    int mouseXButtonLevelZurueckTest = 550;
    int mouseYButtonLevelZureuckTest = 619;
    int mouseXButtonEinstellungenObenPlusTest = 715;
    int mouseYButtonEinstellungenObenPlusTest = 607;
    int mouseXButtonEinstellungenUntenPlusTest = 715;
    int mouseYButtonEinstellungenUntenPlusTest = 621;
    int mouseXButtonEinstellungenUntenMinusTest = 607;
    int mouseYButtonEinstellungenUntenMinusTest = 621;
    int mouseXButtonEinstellungenObenMinusTest = 607;
    int mouseYButtonEinstellungenObenMinusTest = 607;

    @Before
    public void setUp() throws Exception {
        this.leveleditor = new Leveleditor();
        this.leveleditor.setSpielfeldBreite(Einstellungen.LAENGE_KACHELN_X * Einstellungen.ANZAHL_KACHELN_X);
        this.leveleditor.setSpielfeldHoehe(Einstellungen.LAENGE_KACHELN_Y * Einstellungen.ANZAHL_KACHELN_Y);
    }

    @Test
    public void mousePressed() {
        //TODO: für jeden Testfall eine neue Mauskoordinate eintragen --> So kommt man dann in alle Methoden und deren Untermethoden.
        IKachel testKachel;
        IMovable testMovable;
        int punkteZuBeginn;


        //Test 1
        //Kachel auswählen

        Assert.assertEquals(null, leveleditor.getAktuelleKachel());
        leveleditor.mouseX = mouseXMenuKachelTest;
        leveleditor.mouseY = mouseYMenuKachelTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        testKachel = leveleditor.getMenuArrayKacheln().get(0);
        Assert.assertEquals(testKachel, leveleditor.getAktuelleKachel());


        //Test 2
        //Kachel platzieren

        leveleditor.mouseX = mouseXKachelPlaceTest;
        leveleditor.mouseY = mouseYKachelPlaceTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(testKachel, leveleditor.getTilemap().getKachel(0,0));


        //Test 3
        //Mauskachel/movable nullen

        Assert.assertNotNull(leveleditor.getAktuelleKachel());
        leveleditor.mouseButton = mausRechtsTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertNull(leveleditor.getAktuelleKachel());
        Assert.assertNull(leveleditor.getAktuellesMovable());


        //Test 4
        //Movable auswählen

        leveleditor.mouseX = mouseXMenuMovableTest;
        leveleditor.mouseY = mouseYMenuMovableTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        testMovable = leveleditor.getMenuArrayMovables().get(0);
        Assert.assertEquals(testMovable, leveleditor.getAktuellesMovable());


        //Test 5
        //Movableeigenschaften mit Buttons testen

        Assert.assertEquals(1, leveleditor.getStufe());
        leveleditor.mouseX = mouseXButtonEinstellungenObenPlusTest;
        leveleditor.mouseY = mouseYButtonEinstellungenObenPlusTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(2, leveleditor.getStufe());
        leveleditor.mouseX = mouseXButtonEinstellungenObenMinusTest;
        leveleditor.mouseY = mouseYButtonEinstellungenObenMinusTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(1, leveleditor.getStufe());
        Assert.assertEquals(FeuerModus.KONSTANT, leveleditor.getFeuerModus());
        punkteZuBeginn = leveleditor.getPunkte();
        Assert.assertEquals(punkteZuBeginn, leveleditor.getPunkte());
        leveleditor.mouseX = mouseXButtonEinstellungenUntenPlusTest;
        leveleditor.mouseY = mouseYButtonEinstellungenUntenPlusTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(FeuerModus.RANDOM, leveleditor.getFeuerModus());
        Assert.assertEquals((punkteZuBeginn+1), leveleditor.getPunkte());
        leveleditor.mouseX = mouseXButtonEinstellungenUntenMinusTest;
        leveleditor.mouseY = mouseYButtonEinstellungenUntenMinusTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(FeuerModus.SEMIRANDOM, leveleditor.getFeuerModus());
        Assert.assertEquals(punkteZuBeginn, leveleditor.getPunkte());


        //Test 6
        //Movable platzieren
        ILevel preuflevel = (ILevel) leveleditor.getSpielwelt().getSzene(0);
        Assert.assertEquals(0, preuflevel.getPositionen().size());
        leveleditor.mouseX = mouseXMovablePlaceTest;
        leveleditor.mouseY = mouseYMovablePlaceTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        preuflevel = (ILevel) leveleditor.getSpielwelt().getSzene(0);
        Assert.assertEquals(testMovable.getPosX(), preuflevel.getPositionen().get(0).getPosX());
        Assert.assertEquals(testMovable.getPosY(), preuflevel.getPositionen().get(0).getPosY());
        Assert.assertEquals(1, preuflevel.getPositionen().size());


        //Test 7
        //Neues Level erstellen

        leveleditor.mouseX = mouseXButtonLevelTest;
        leveleditor.mouseY = mouseYButtonLevelTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(2, leveleditor.getSpielwelt().getSzenenanzahl());
        Assert.assertTrue(leveleditor.getSpielwelt().getSzene(1) instanceof DummyLevel);
        Assert.assertEquals(2, leveleditor.getLevelCount());


        //Test 8
        //neue Siedlung erstellen

        leveleditor.mouseX = mouseXButtonSiedlungTest;
        leveleditor.mouseY = mouseYButtonSiedlungTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(3, leveleditor.getSpielwelt().getSzenenanzahl());
        Assert.assertTrue(leveleditor.getSpielwelt().getSzene(2) instanceof DummySiedlung);
        Assert.assertEquals(3, leveleditor.getLevelCount());


        //Test 9
        //Level zurückspringen

        leveleditor.mouseX = mouseXButtonLevelZurueckTest;
        leveleditor.mouseY = mouseYButtonLevelZureuckTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(2, leveleditor.getLevelNr());


        //Test 10
        //Level vor springen

        leveleditor.mouseX = mouseXButtonLevelVorTest;
        leveleditor.mouseY = mouseYButtonLevelVorTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(3, leveleditor.getLevelNr());


        //Test 11
        //Speicherhinweis prüfen

        leveleditor.mouseX = mouseXButtonSpeichernTest;
        leveleditor.mouseY = mouseYButtonSpeichernTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(1, leveleditor.getSpeicherHinweis());


        //Test 12
        //Spielwelt laden

        leveleditor.setSpielweltJson("spielweltTEST.json");
        leveleditor.mouseX = mouseXButtonLadenTest;
        leveleditor.mouseY = mouseYButtonLadenTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(2, leveleditor.getLevelCount());
        Assert.assertTrue((leveleditor.getSpielwelt().getSzene(1)) instanceof DummySiedlung);


        //Test 13
        //Level leeren

        Assert.assertNotNull(((ILevel) leveleditor.getSpielwelt().getSzene(0)).getTileMap());
        leveleditor.mouseX = mouseXButtonLeerenTest;
        leveleditor.mouseY = mouseYButtonLeerenTest;
        leveleditor.mouseButton = mausLinksTasteGedrueckt;
        leveleditor.mousePressed();
        Assert.assertEquals(0, ((ILevel) leveleditor.getSpielwelt().getSzene(0)).getPositionen().size());
        for (int i = 0; i < Einstellungen.ANZAHL_KACHELN_Y; i++) {
            for (int j = 0; j < Einstellungen.ANZAHL_KACHELN_X; j++) {
                Assert.assertTrue(leveleditor.getTilemap().getKachel(i,j) instanceof Wiese);
            }
        }
    }
}