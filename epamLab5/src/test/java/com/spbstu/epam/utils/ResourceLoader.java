package com.spbstu.epam.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.spbstu.epam.entities.Data;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class ResourceLoader {

    private static Map<String, Data> DATA;

    static {
        load();
    }

    @SneakyThrows
    private static void load() {
        FileReader fileReader = new FileReader(ResourceLoader.class.getClassLoader().getResource("data/data.json").getFile());
        JsonReader jsonReader = new JsonReader(fileReader);

        Type type = new TypeToken<Map<String, Data>>() {
        }.getType();

        DATA = new Gson().fromJson(jsonReader, type);
    }

    public static Data getData(String dataId) {
        return DATA.get(dataId);
    }
}