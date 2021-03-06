package grind.welt.impl;

import grind.welt.ISpielwelt;
import grind.welt.ISzene;

import java.util.ArrayList;
import java.util.List;

public class DummySpielwelt implements ISpielwelt {

    private List<ISzene> szenen = new ArrayList<>();

    public DummySpielwelt(){
        this.szenen.add(0,new DummyLevel());
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

    /**
     * Fügt die übergebene Szene der Spielwelt an der gewünschten Stelle hinzu
     * @param szene Szene welche hinzugefügt werden soll
     * @param szenenNummer Position der Szene in der Liste
     */
    @Override
    public void addSzene(ISzene szene, int szenenNummer){
        this.szenen.add(szenenNummer, szene);
    }

    /**
     * Gibt die Anzahl der Szenen zurück
     * @return Szenenanzahl
     */
    @Override
    public int getSzenenanzahl() {
        return szenen.size();
    }

    /**
     * Löscht alle Szenen aus der Spielwelt
     */
    @Override
    public void removeSzenen() {
        this.szenen.clear();
        this.szenen.add(0, new DummyLevel());
    }
}
