package grind.core.impl;

import com.google.gson.*;

import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.TileMap;
import grind.welt.ILevel;
import grind.welt.ISpielwelt;
import grind.welt.ISzene;
import grind.welt.impl.DummyLevel;
import grind.welt.impl.DummySpielwelt;

import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DateiService {
    GsonBuilder gsonbuilder;
    Gson gson;
    String json;

    private Object ISpielwelt;
    private Object ISzene;


    public DateiService() {
        this.gsonbuilder = new GsonBuilder();
        //        gsonbuilder.registerTypeAdapter(Spielmodell.class, new SpielmodellInstanceCreator());
//        JsonDeserializer<ISpielwelt> deserializer = new JsonDeserializer<ISpielwelt>() {
//            @Override
//            public ISpielwelt deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                JsonObject jsonObject = json.getAsJsonObject();
//
//                List<ISzene> szenen = new ArrayList<>();
//                ISpielwelt test = new DummySpielwelt();
//
//                return test;
//            }
//        };
        gsonbuilder.registerTypeAdapter(ISzene.class, new ISzeneInstanceCreator());
        gsonbuilder.registerTypeAdapter(ISpielwelt.class, new ISpielweltInstanceCreator());
//        gsonbuilder.registerTypeAdapter(ISpielwelt.class, deserializer);
        this.gson = gsonbuilder.setPrettyPrinting().create();
    }

    /**
     * Lädt eine Spielwelt aus einer JSON Datei und gibt diese zurück
     * @param dateiname Dateiname mit .json Endung
     * @return Eine Instanz von ISpielwelt erstellt mit den Parametern der JSON Datei
     */
    protected ISpielwelt ladeSpielmodell(String dateiname) {
        DummySpielwelt spielwelt = new DummySpielwelt();
  //      ISzene szene = new DummyLevel();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("spielwelt.json"));

                spielwelt = gson.fromJson(reader, DummySpielwelt.class);

//            Object object = gson.fromJson(reader,Object.class);
//            object.toString();
//            spielwelt = (ISpielwelt) object;
//            System.out.println(spielwelt);

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return spielwelt;
    }

    /**
     * Speichert eine Spielwelt vom Typ ISpielwelt in einer JSON Datei ab
     * @param spielwelt Die zu speichernde Spielwelt
     * @param dateiname Dateiname der JSON Datei
     */
    protected void speicheSpielmodell(ISpielwelt spielwelt, String dateiname) {
        json = gson.toJson(spielwelt);

        try {
            Writer writer = Files.newBufferedWriter(Paths.get(dateiname));
            gson.toJson(spielwelt, writer);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//    private class SpielmodellInstanceCreator implements InstanceCreator<DummySpielwelt> {
//
//        @Override
//        public DummySpielwelt createInstance(Type type) {
//            // create new object with our additional property
//            DummySpielwelt spielwelt = new DummySpielwelt();
//
//            // return it to gson for further usage
//            return spielwelt;
//        }
//    }
//

    /**
     * Interne Verarbeitung der JSON Datei beim Laden,
     * da ISzene nicht von GSON implementiert werden kann
     * Funktioniert aber noch NICHT !
     */
    private class ISzeneInstanceCreator implements InstanceCreator<ISzene> {

        public ISzeneInstanceCreator() {
            DummySpielwelt spielwelt = new DummySpielwelt();
            this.szenen.add(0,new DummyLevel());
            this.szenen.add(1,new DummyLevel());
            this.szenen.add(2,new DummyLevel());
            this.anzahlSzenen = szenen.size();
        }
        @Override
        public ISzene createInstance(Type type) {
            DummySpielwelt spielwelt = new DummySpielwelt();
           // create new object with our additional property
            System.out.println("Will eine Instanz erstellen");
//           ISzene szene = new DummyLevel();
//            ISzene szene = new DummyLevel();
            //szene = (ISzene) type;
            // return it to gson for further usage
            return szene;
        }
    }
    /**
     * Interne Verarbeitung der JSON Datei beim Laden,
     * da ISpielwelt nicht von GSON implementiert werden kann
     * Funktioniert aber noch NICHT !
     */
//    private class ISpielweltInstanceCreator implements InstanceCreator<ISpielwelt> {
//
//        @Override
//        public ISpielwelt createInstance(Type type) {
//            // create new object with our additional property
//            DummySpielwelt spielwelt = new DummySpielwelt();
//
//            // return it to gson for further usage
//            return spielwelt;
//        }
//    }
}
