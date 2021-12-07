package grind.welt.impl;

import grind.welt.ISpielwelt;
import grind.welt.ISzene;

public class LeereSpielwelt implements ISpielwelt {

    @Override
    public ISzene getSzene(int n) {
        return new LeererLevel();
    }
}
