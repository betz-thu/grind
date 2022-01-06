package grind.core.impl;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;
import grind.kacheln.IKachel;
import grind.kacheln.ITileMap;
import grind.kacheln.impl.*;
import grind.movables.IMovable;
import grind.movables.impl.*;
import grind.movables.monster.DornPflanze;
import grind.movables.monster.FeuerMonster;
import grind.movables.monster.Geist;
import grind.movables.monster.Zombie;
import grind.util.Einstellungen;
import grind.util.FeuerModus;
import grind.util.Richtung;
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


public class DateiService {
    private GsonBuilder gsonbuilder;
    private Gson gson;

    private ITileMap tilemap;
    private Spielsteuerung spielsteuerung;

    //TODO: Spielsteuerung braucht malerweise nicht übergeben werden.
    // --> Workaround für das FeuerMonster, da dieses die Spielsteuerung braucht.
    public DateiService(Spielsteuerung spielsteuerung) {
        this.spielsteuerung = spielsteuerung;
        this.gsonbuilder = new GsonBuilder();
        this.gsonbuilder.setLenient();
/**
 * Custom Serializer für die Serialisierung von einem IMovable.
 * Vorgehen:
 * 1. Standartmäßige Serialisierung von IMovable mit GSON
 * 2. Namen der Klasse als Property hinzufügen -> wichtig bei Deserialisierung
 */
        JsonSerializer<IMovable> iMovableJsonSerializer = new JsonSerializer<IMovable>() {
            @Override
            public JsonElement serialize(IMovable iMovable, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonElement jsonElement = jsonSerializationContext.serialize(iMovable);

                jsonElement.getAsJsonObject().addProperty("classname", String.valueOf(iMovable.getClass()));

                return jsonElement;
            }
        };
/**
 * Custom DeSerializer für die Deserialisierung von einem IMovable.
 * Anhand des Klassennamens wird eine Instanz der Klasse erzeugt und zurückgegeben
 */
        JsonDeserializer<IMovable> iMovableJsonDeserializer = new JsonDeserializer<IMovable>() {
            @Override
            public IMovable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                String klassenname;
                int posX;
                int posY;
                Richtung richtung = null;

                klassenname = jsonObject.get("classname").getAsString();
                posX = jsonObject.get("posX").getAsInt();
                posY = jsonObject.get("posY").getAsInt();

                if (klassenname.equals("class grind.movables.impl.Spielfigur")) {
                    String richtungsString;
                    richtungsString = jsonObject.get("ausrichtung").getAsString();

                    switch (richtungsString) {

                        case "N":
                            richtung = Richtung.N;
                            break;
                        case "S":
                            richtung = Richtung.S;
                            break;
                        case "W":
                            richtung = Richtung.W;
                            break;
                        case "O":
                            richtung = Richtung.O;
                            break;
                        default:
                            richtung = null;
                            break;
                    }
                }

                IMovable iMovable = null;

                switch (klassenname) {

                    case "class grind.movables.impl.Apfel":
                        iMovable = new Apfel(posX, posY);
                        break;
                    case "class grind.movables.impl.Gold":
                        iMovable = new Gold(posX, posY);
                        break;
                    case "class grind.movables.impl.Spielfigur":
                        iMovable = new Spielfigur(posX, posY, richtung);
                        break;
                    case "class grind.movables.impl.Stern":
                        iMovable = new Stern(posX,posY);
                        break;
                    case "class grind.movables.monster.DornPflanze":
                        iMovable = new DornPflanze(posX, posY, tilemap);
                        break;
                    case "class grind.movables.monster.Geist":
                        iMovable = new Geist(posX, posY, tilemap);
                        break;
                    case "class grind.movables.monster.Zombie":
                        iMovable = new Zombie(posX, posY, tilemap);
                        break;
                    case "class grind.movables.impl.Heiltrank":
                        iMovable = new Heiltrank(posX, posY);
                        break;
                    case "class grind.movables.impl.Mango":
                        iMovable = new Mango(posX, posY);
                        break;
                    case "class grind.movables.impl.Schwert":
                        iMovable = new Schwert(posX, posY, 1);
                        break;
                    case "class grind.movables.impl.Levelende":
                        iMovable = new Levelende(posX, posY);
                        break;
                    case "class grind.movables.monster.FeuerMonster":
                        iMovable = new FeuerMonster(posX, posY, tilemap, spielsteuerung, Richtung.N, 100, FeuerModus.RANDOM);
                    default:
                        break;
                }

                return iMovable;
            }
        };
/**
 * Custom Serializer für die Serialisierung von einer ISzene.
 * Vorgehen:
 * 1. Standartmäßige Serialisierung von ISzene mit GSON
 * 2. Namen der Klasse als Property hinzufügen -> wichtig bei Deserialisierung
 */
        JsonSerializer<ISzene> iSzeneJsonSerializer = new JsonSerializer<ISzene>() {
            @Override
            public JsonElement serialize(ISzene iSzene, Type type, JsonSerializationContext jsonSerializationContext) {

                JsonElement jsonElement = jsonSerializationContext.serialize(iSzene);

                jsonElement.getAsJsonObject().addProperty("classname", String.valueOf(iSzene.getClass()));

                return jsonElement;
            }
        };
/**
 * Custom DeSerializer für die Deserialisierung von einer ISzene.
 * Anhand des Klassennamens wird eine Instanz der Klasse erzeugt und zurückgegeben
 */
        JsonDeserializer<ISzene> iSzeneJsonDeserializer = new JsonDeserializer<ISzene>() {
            @Override
            public ISzene deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                String klassenname;
                klassenname = jsonObject.get("classname").getAsString();
                ISzene iSzene = null;
                switch (klassenname) {

                    case "class grind.welt.impl.DummyLevel":
                        iSzene = jsonDeserializationContext.deserialize(jsonElement,
                                new TypeToken<DummyLevel>(){}.getType());
                        break;
//                    case "class grind.welt.impl.DummySiedlung":
//                        iSzene = new DummySiedlung();
//                        break;
                    default:
                        break;
                }

            return iSzene;
            }
        };
/**
 * Custom Serializer für die Serialisierung von eine ITilemap.
 * Vorgehen:
 * 1. Standartmäßige Serialisierung von ITilemap mit GSON
 * 2. Namen der Klasse als Property hinzufügen -> wichtig bei Deserialisierung
 */
        JsonSerializer<ITileMap> iTileMapJsonSerializer = new JsonSerializer<ITileMap>() {
            @Override
            public JsonElement serialize(ITileMap iTileMap, Type type, JsonSerializationContext jsonSerializationContext) {

                JsonElement jsonElement = jsonSerializationContext.serialize(iTileMap);

                jsonElement.getAsJsonObject().addProperty("classname", String.valueOf(iTileMap.getClass()));

                return jsonElement;
            }
        };
/**
 * Custom DeSerializer für die Deserialisierung von einer ITilemap.
 * Anhand des Klassennamens wird eine Instanz der Klasse erzeugt und zurückgegeben
 */
        JsonDeserializer<ITileMap> iTileMapJsonDeserializer = new JsonDeserializer<ITileMap>() {
            @Override
            public ITileMap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                String klassenname;
                klassenname = jsonObject.get("classname").getAsString();
                ITileMap iTileMap = null;
                switch (klassenname) {

                    case "class grind.kacheln.impl.TileMap":
                        iTileMap = jsonDeserializationContext.deserialize(jsonElement,
                                new TypeToken<TileMap>(){}.getType());
                        break;
//                    case "class grind.welt.impl.DummySiedlung":
//                        iSzene = new DummySiedlung();
//                        break;
                    default:
                        break;
                }
                tilemap = iTileMap;
                return iTileMap;
            }
        };
/**
 * Custom Serializer für die Serialisierung von einer IKachel.
 * Vorgehen:
 * 1. Standartmäßige Serialisierung von IKachel mit GSON
 * 2. Namen der Klasse als Property hinzufügen -> wichtig bei Deserialisierung
 */
        JsonSerializer<IKachel> iKachelJsonSerializer = new JsonSerializer<IKachel>() {
            @Override
            public JsonElement serialize(IKachel iKachel, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("classname", String.valueOf(iKachel.getClass()));
                return jsonObject;
            }
        };
/**
 * Custom DeSerializer für die Deserialisierung von einer IKachel.
 * Anhand des Klassennamens wird eine Instanz der Klasse erzeugt und zurückgegeben
 */
        JsonDeserializer<IKachel> iKachelJsonDeserializer = new JsonDeserializer<IKachel>() {
            @Override
            public IKachel deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                String klassenname;
                klassenname = jsonObject.get("classname").getAsString();
                IKachel iKachel = null;
                switch (klassenname) {

                    case "class grind.kacheln.impl.Wiese":
                        iKachel = new Wiese();
                        break;
                    case "class grind.kacheln.impl.Weg":
                        iKachel = new Weg();
                        break;
                    case "class grind.kacheln.impl.Holzbrücke":
                        iKachel = new Holzbrücke();
                        break;
                    case "class grind.kacheln.impl.Wasser":
                        iKachel = new Wasser();
                        break;
                    case "class grind.kacheln.impl.Fels":
                        iKachel = new Fels();
                        break;
                    case "class grind.kacheln.impl.Baum":
                        iKachel = new Baum();
                        break;
                    case "class grind.kacheln.impl.Levelausgang":
                        iKachel = new Levelausgang();
                        break;
                    case "class grind.kacheln.impl.DummyHindernis":
                        iKachel = new DummyHindernis();
                        break;
                    case "class grind.kacheln.impl.LeereKachel":
                        iKachel = new LeereKachel();
                        break;
                    default:
                        break;
                }

                return iKachel;
            }
        };
/**
 * Registieren der einzelnen Serializer/DeSerializer im Gsonbuilder
 */
        gsonbuilder.registerTypeAdapter(IMovable.class, iMovableJsonSerializer);
        gsonbuilder.registerTypeAdapter(IMovable.class, iMovableJsonDeserializer);

        gsonbuilder.registerTypeAdapter(ISzene.class, iSzeneJsonSerializer);
        gsonbuilder.registerTypeAdapter(ISzene.class, iSzeneJsonDeserializer);

        gsonbuilder.registerTypeAdapter(ITileMap.class, iTileMapJsonSerializer);
        gsonbuilder.registerTypeAdapter(ITileMap.class, iTileMapJsonDeserializer);

        gsonbuilder.registerTypeAdapter(IKachel.class, iKachelJsonSerializer);
        gsonbuilder.registerTypeAdapter(IKachel.class, iKachelJsonDeserializer);

        this.gson = gsonbuilder.setPrettyPrinting().create();
    }

    /**
     * Lädt eine Spielwelt aus einer JSON Datei und gibt diese zurück
     * @param dateiname Dateiname mit .json Endung
     * @return Eine Instanz von ISpielwelt erstellt mit den Parametern der JSON Datei
     */
    protected ISpielwelt ladeSpielwelt(String dateiname) {
        DummySpielwelt spielwelt = new DummySpielwelt();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(dateiname));

                spielwelt = gson.fromJson(reader, DummySpielwelt.class);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return spielwelt;
    }

    /**
     * Speichert eine Spielwelt vom Typ ISpielwelt in einer JSON Datei ab
     * @param spielwelt Die zu speichernde Spielwelt
     * @param dateiname Dateiname der JSON Datei
     */
    protected void speichereSpielwelt(ISpielwelt spielwelt, String dateiname) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(dateiname));
            gson.toJson(spielwelt, writer);
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
