import java.util.ArrayList;
import java.util.List;

public class Spielwelt implements ISpielwelt{
    private List<ISzene> szenen = new ArrayList<>();

    @Override
    public int getAnzahlSzenen() {
        return this.szenen.size();
    }
}
