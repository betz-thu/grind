package grind.kacheln.impl;

public class SaphirTor extends Tor {

    public SaphirTor(){

    }

    @Override
    String getFileNameOpen() {
        return "sapphire_gate_open.png";
    }

    @Override
    String getFileNameClosed() {
        return "sapphire_gate_closed.png";
    }
}
