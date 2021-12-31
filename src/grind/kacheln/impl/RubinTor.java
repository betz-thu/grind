package grind.kacheln.impl;

import grind.kacheln.IKachel;
import grind.util.Einstellungen;
import processing.core.PApplet;
import processing.core.PImage;

public class RubinTor extends Tor {

    public RubinTor(){

    }

    @Override
    String getFileNameOpen() {
        return "ruby_gate_open.png";
    }

    @Override
    String getFileNameClosed() {
        return "ruby_gate_closed.png";
    }
}
