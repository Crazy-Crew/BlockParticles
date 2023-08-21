package com.badbones69.blockparticles.plugin.config.json;

import com.badbones69.blockparticles.api.storage.objects.CustomLocation;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class CustomLocationTypeAdapter extends TypeAdapter<CustomLocation> {

    @Override
    public void write(JsonWriter out, CustomLocation location) throws IOException {
        out.beginObject();
        out.name("id").value(location.id());
        out.name("world").value(location.world());
        out.name("x").value(location.x());
        out.name("y").value(location.y());
        out.name("z").value(location.z());
        out.endObject();
    }

    @Override
    public CustomLocation read(JsonReader reader) throws IOException {
        reader.beginObject();

        int id = 0;
        String worldName = null;
        double x = 0, y = 0, z = 0;

        while (reader.hasNext()) {
            String name = reader.nextName();
            
            switch (name) {
                case "id" -> id = reader.nextInt();
                case "world" -> worldName = reader.nextString();
                case "x" -> x = reader.nextDouble();
                case "y" -> y = reader.nextDouble();
                case "z" -> z = reader.nextDouble();
                default -> reader.skipValue();
            }
        }

        reader.endObject();

        assert worldName != null;
        return new CustomLocation(id, worldName, x, y, z);
    }
}