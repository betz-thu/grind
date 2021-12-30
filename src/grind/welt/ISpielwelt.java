package grind.welt;

public interface ISpielwelt {
    ISzene getSzene(int n);
    void addSzene(ISzene szene);
    int getSzenenanzahl();
}
