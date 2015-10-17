package br.com.rbarrelo.clima.openweathermap;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import br.com.rbarrelo.clima.openweathermap.pojo.OpenWeatherMap;

/**
 * Created by rafaelbarrelo on 10/17/15.
 */
public class OpenWeatherMapDeserializer implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement item = json.getAsJsonObject();

        return (new Gson().fromJson(item, OpenWeatherMap.class));
    }
}
