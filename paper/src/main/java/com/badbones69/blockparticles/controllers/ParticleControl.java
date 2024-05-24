package com.badbones69.blockparticles.controllers;

import com.badbones69.blockparticles.api.enums.particles.Particles;
import org.bukkit.Location;
import java.util.Map;

public interface ParticleControl {
    
    Map<String, Integer> getLocations();
    
    void playVolcano(final Location location, final String id);
    
    void playBigFlame(final Location location, final String id);
    
    void playFlame(final Location location, final String id);
    
    void playDoubleSpiral(final Location location, final String id, Particles particle, int amount);
    
    void playSpiral(final Location location, final String id, Particles particle, int amount);
    
    void playCrit(final Location location, final String id);
    
    void playBigCrit(final Location location, final String id);
    
    void playStorm(final Location location, final String id);
    
    void playFog(final Location location, final String id);
    
    void playEnchant(final Location location, final String id);
    
    void playChains(final Location location, final String id);
    
    void playFireStorm(final Location location, final String id);
    
    void playSnow(final Location location, final String id);
    
    void playSpew(final Location location, final String id);
    
    void playPotion(final Location location, final String id);
    
    void playMusic(final Location location, final String id);
    
    void playMagic(final Location location, final String id);
    
    void playSnowStorm(final Location location, final String id);
    
    void playFireSpew(final Location location, final String id);
    
    void playFootPrint(final Location location, final String id);
    
    void playHappyVillager(final Location location, final String id);
    
    void playAngryVillager(final Location location, final String id);
    
    void playMobSpawner(final Location location, final String id);
    
    void startWater(final Location location, final String id);
    
    void playEnderSignal(final Location location, final String id);
    
    void playRainbow(final Location location, final String id);
    
    void playSnowBlast(final Location location, final String id);
    
    void playHalo(final Location location, final String id);
    
    void playSantaHat(final Location location, final String id);
    
    void playSoulWell(final Location location, final String id);
    
    void playBigSoulWell(final Location location, final String id);
    
    void playFlameWheel(final Location location, final String id);
    
    void playWitchTornado(final Location location, final String id);
    
    void playLoveTornado(final Location location, final String id);
    
    void playBigLoveWell(final Location location, final String id);
    
    void playLoveWell(final Location location, final String id);
    
}