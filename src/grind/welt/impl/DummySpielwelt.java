package grind.welt.impl;

import grind.kacheln.IKachel;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;

import java.util.ArrayList;
import java.util.List;

public class DummySpielwelt implements ISpielwelt {

    private List<ISzene> szenen = new ArrayList<>();
    private int anzahlSzenen;
    /**
     *
     */
    public DummySpielwelt(){
       this.szenen.add(0,new DummyLevel());
//        this.szenen.add(1,new DummyLevel());
//        this.szenen.add(2,new DummyLevel());
        this.anzahlSzenen = szenen.size();
    }

    /**
     * Gibt die gewünschte Szene aus der List<ISzene> zurück
     * @param n Szenennummer in der Szenenliste
     * @return Szene an Listennummer n
     */
    @Override
    public ISzene getSzene(int n) {
        if (n > szenen.size()-1){
            throw new UnsupportedOperationException("Keine weiteren Szenen mehr implementiert!");
        }
        return szenen.get(n);
    }

    @Override
    public void addSzene(ISzene szene, int szenenNummer){
        szenen.add(szenenNummer, szene);
        anzahlSzenen = szenen.size();
    }

    @Override
    public int getSzenenanzahl() {
        return szenen.size();
    }

    @Override
    public void removeSzenen() {
        this.szenen.clear();
        this.szenen.add(0, new DummyLevel());
    }
}
