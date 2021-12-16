package grind.core.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import grind.core.ISpielmodell;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DateiService {
    GsonBuilder gsonbuilder;
    Gson gson;



    public DateiService(){
        this.gsonbuilder = new GsonBuilder();
        this.gson = gsonbuilder.create();

//        gsonbuilder.registerTypeAdapter(DummySpielwelt.class, new spielweltAdapter());
//        gsonbuilder.registerTypeAdapter(DummyLevel.class, new levelAdapter());
//        gsonbuilder.registerTypeAdapter(Movable.class, new movableAdapter());
//        gsonbuilder.registerTypeAdapter(Einstellungen.class, new einstellungenAdapter());
//        gsonbuilder.setPrettyPrinting();

    }

    /**
     *
     * @param dateiname
     * @return
     */
    protected ISpielmodell ladeSpielmodell(String dateiname){
        ISpielmodell spielmodell = gson.fromJson(dateiname, ISpielmodell.class);
        return spielmodell;
    }

    /**
     *
     * @param spielmodell
     */
    protected void speicheSpielmodell(ISpielmodell spielmodell){
        String jsonString;
        jsonString = gson.toJson(spielmodell);
        System.out.println(jsonString);
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("spielmodell.json"));
            gson.toJson(spielmodell, writer);
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
//
////    private boolean schreibeInDatei(String string){
////        boolean writeSuccess = false;
////        try {
////            FileWriter Writer = new FileWriter("spielwelt.txt");
////            Writer.write(string);
////            Writer.close();
////            System.out.println("Erfolgreich in Datei geschrieben!");
////            writeSuccess = true;
////        } catch (IOException e) {
////            System.out.println("Schreibfehler");
////            e.printStackTrace();
////        }
////        return writeSuccess;
////    }
////
//    static class spielweltAdapter extends TypeAdapter<DummySpielwelt> {
//            @Override
//            public void write(JsonWriter jsonWriter, DummySpielwelt spielwelt) throws IOException {
//                jsonWriter.beginObject();
//                jsonWriter.name("spielwelt");
//                jsonWriter.value(String.valueOf(spielwelt.getSzene(0).getLevel().getTileMap().getKachel(0,0)));
//                jsonWriter.endObject();
//            }
//
//            @Override
//            public DummySpielwelt read(JsonReader jsonReader) throws IOException {
//                return null;
//            }
//        }
//
//    static class levelAdapter extends TypeAdapter<DummyLevel> {
//        @Override
//        public void write(JsonWriter jsonWriter, DummyLevel level) throws IOException {
//            jsonWriter.beginObject();
//            jsonWriter.name("spielwelt");
//            jsonWriter.value();
//            jsonWriter.endObject();
//        }
//
//        @Override
//        public DummyLevel read(JsonReader jsonReader) throws IOException {
//            return null;
//        }
//    }
//    static class movableAdapter extends TypeAdapter<DummyLevel> {
//        @Override
//        public void write(JsonWriter jsonWriter, DummyLevel level) throws IOException {
//            jsonWriter.beginObject();
//            jsonWriter.name("spielwelt");
//            jsonWriter.value(String.valueOf(level.);
//            jsonWriter.endObject();
//        }
//
//        @Override
//        public DummyLevel read(JsonReader jsonReader) throws IOException {
//            return null;
//        }
//    }
//    static class einstellungenAdapter extends TypeAdapter<DummyLevel> {
//        @Override
//        public void write(JsonWriter jsonWriter, DummyLevel level) throws IOException {
//            jsonWriter.beginObject();
//            jsonWriter.name("spielwelt");
//            jsonWriter.value(String.valueOf(level.);
//            jsonWriter.endObject();
//        }
//
//        @Override
//        public DummyLevel read(JsonReader jsonReader) throws IOException {
//            return null;
//        }
//    }
    }
