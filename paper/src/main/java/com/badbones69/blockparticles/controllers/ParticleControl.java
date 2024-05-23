package com.badbones69.blockparticles.controllers;

import com.badbones69.blockparticles.api.enums.Particles;
import org.bukkit.Location;

import java.util.HashMap;

public interface ParticleControl {
    
    HashMap<String, Integer> getLocations();
    
    void playVolcano(Location location, String id);
    
    void playBigFlame(Location location, String id);
    
    void playFlame(Location location, String id);
    
    void playDoubleSpiral(Location location, String id, Particles particle, int amount);
    
    void playSpiral(Location location, String id, Particles particle, int amount);
    
    void playCrit(Location location, String id);
    
    void playBigCrit(Location location, String id);
    
    void playStorm(Location location, String id);
    
    void playFog(Location location, String id);
    
    void playEnchant(Location location, String id);
    
    void playChains(Location location, String id);
    
    void playFireStorm(Location location, String id);
    
    void playSnow(Location location, String id);
    
    void playSpew(Location location, String id);
    
    void playPotion(Location location, String id);
    
    void playMusic(Location location, String id);
    
    void playMagic(Location location, String id);
    
    void playSnowStorm(Location location, String id);
    
    void playFireSpew(Location location, String id);
    
    void playFootPrint(Location location, String id);
    
    void playHappyVillager(Location location, String id);
    
    void playAngryVillager(Location location, String id);
    
    void playMobSpawner(Location location, String id);
    
    void startWater(Location location, String id);
    
    void playEnderSignal(Location location, String id);
    
    void playRainbow(Location location, String id);
    
    void playSnowBlast(Location location, String id);
    
    void playHalo(Location location, String id);
    
    void playSantaHat(Location location, String id);
    
    void playSoulWell(Location location, String id);
    
    void playBigSoulWell(Location location, String id);
    
    void playFlameWheel(Location location, String id);
    
    void playWitchTornado(Location location, String id);
    
    void playLoveTornado(Location location, String id);
    
    void playBigLoveWell(Location location, String id);
    
    void playLoveWell(Location location, String id);
    
}