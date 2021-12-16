package grind.welt.impl;

import grind.welt.ISpielwelt;
import grind.welt.ISzene;

import java.util.ArrayList;
import java.util.List;

public class DummySpielwelt implements ISpielwelt {

    private List<ISzene> szenen = new ArrayList<>();

    /**
     *
     */
    public DummySpielwelt(){
        this.szenen.add(0,new DummyLevel());
        this.szenen.add(1,new DummyLevel());
        this.szenen.add(2,new DummyLevel());
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
//        ISzene szene = null;
//        if (n == 1){
//            szene = new LevelZwei();
//        } else if (n == 2) {
//            szene =  new LevelZwei();
//        } else {
//            throw new UnsupportedOperationException("Für diese Szene ist nichts erstellt worden!");
//        }
//        return szene;
    }
}
