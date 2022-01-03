package grind.welt;

public interface ISpielwelt {
    ISzene getSzene(int n);
    void addSzene(ISzene szene, int szenenNummer);
    int getSzenenanzahl();
    void removeSzenen();
}
