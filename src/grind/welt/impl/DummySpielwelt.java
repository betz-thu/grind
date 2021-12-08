package grind.welt.impl;

import grind.welt.ISpielwelt;
import grind.welt.ISzene;

public class DummySpielwelt implements ISpielwelt {

    @Override
    public ISzene getSzene(int n) {
        return new DummyLevel();
    }
}
