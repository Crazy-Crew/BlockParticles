package com.badbones69.blockparticles.api.storage.types.file.json;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.storage.objects.ParticleData;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.ryderbelserion.ruby.other.config.FileEngine;
import com.ryderbelserion.ruby.other.config.types.FileType;
import com.ryderbelserion.ruby.paper.plugin.config.json.LocationTypeAdapter;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public sealed class ParticleJson extends FileEngine permits DataManager {

    final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);

    public ParticleJson(Path path) {
        super("locations.json", path, FileType.JSON);

        GsonBuilder builder = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(Location.class, new LocationTypeAdapter());

        setGsonBuilder(builder);
    }

    @Expose
    public static ConcurrentHashMap<String, ParticleData> particles = new ConcurrentHashMap<>();
}