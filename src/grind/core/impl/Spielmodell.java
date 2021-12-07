package grind.core.impl;

import grind.core.ISpielmodell;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import grind.welt.movables.ISpielfigur;
import grind.welt.movables.impl.Spielfigur;
import processing.core.PApplet;

public class Spielmodell implements ISpielmodell {

    ISpielwelt spielwelt;
    ISpielfigur figur;
    ILevel level;

    public Spielmodell(ISpielwelt spielwelt) {
        this.spielwelt = spielwelt;
        this.figur = new Spielfigur();
        this.figur.setPosition(600, 400);
    }

    @Override
    public void betreteSzene(int n) {
        ISzene szene = this.spielwelt.getSzene(n);
        if (szene instanceof ILevel) {
            ILevel level = (ILevel) szene;
            betreteLevel(level);
        } else {
            throw new UnsupportedOperationException("Siedlungen sind noch nicht implementiert.");
        }
    }

    private void betreteLevel(ILevel level) {
        this.level = level;
    }

    @Override
    public void bewege() {
        // die Spielfigur bewegt sich nicht von selbst
    }

    @Override
    public void zeichne(PApplet app) {

        if (this.level != null) {
            this.level.zeichne(app);
        }

        this.figur.zeichne(app);


    }

    @Override
    public ISpielfigur getFigur() {
        return this.figur;
    }
}
