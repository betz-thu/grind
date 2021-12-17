package grind.welt.impl;

import grind.welt.ISpielwelt;
import grind.welt.ISzene;

import java.util.ArrayList;
import java.util.List;

public class DummySpielwelt implements ISpielwelt {

    private List<ISzene> szenen = new ArrayList<>();

    public DummySpielwelt(){
        this.szenen.add(0,new DummyLevel());
        this.szenen.add(1,new DummyLevel());
        this.szenen.add(2,new DummyLevel());
    }

    @Override
    public ISzene getSzene(int n) {
        if (n > szenen.size()-1){
            throw new UnsupportedOperationException("Keine weiteren Szenen mehr implementiert!");
        }
        return szenen.get(n);
    }
}
