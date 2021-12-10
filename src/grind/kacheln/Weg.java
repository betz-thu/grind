package grind.kacheln;

import processing.core.PApplet;

public class Weg implements IKachel{
    @Override
    public boolean istBetretbar(){
        return true;
    }

    @Override
    public boolean istHindernis(){
        return false;
    }

    @Override
    public void zeichne (PApplet app, int x, int y){

    }
}
