package com.badbones69.blockparticles;

import com.badbones69.blockparticles.api.FileManager.Files;
import com.badbones69.blockparticles.api.CrazyManager;
import com.badbones69.blockparticles.api.enums.BPFountains;
import com.badbones69.blockparticles.api.enums.BPParticles;
import com.badbones69.blockparticles.api.enums.types.Particles;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.controllers.Fountains;
import com.badbones69.blockparticles.plugin.items.ItemBuilder;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import java.util.*;

public class Methods {

    private final BlockParticles plugin = JavaPlugin.getPlugin(BlockParticles.class);
    
    private final CrazyManager crazyManager = this.plugin.getCrazyManager();

    private final Fountains fountains = this.plugin.getFountains();
    
    public void reset() {
        kill();
        startParticles();
    }
    
    public ArrayList<String> getLocations() {
        return (ArrayList<String>) Files.DATA.getFile().getConfigurationSection("locations").getKeys(false);
    }
    
    public void kill() {
        this.crazyManager.getParticleControl().getLocations().clear();

        for (World world : this.plugin.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item item) {
                    if (this.crazyManager.getFountainItem().contains(item)) item.remove();
                }
            }
        }
        
        this.crazyManager.getFountainItem().clear();
        this.plugin.getServer().getScheduler().cancelTasks(this.plugin);
    }

    private BPFountains getFountainFromName(String name) {
        for (BPFountains fountain : BPFountains.values()) {
            if (fountain == BPFountains.CUSTOM) {
                for (CustomFountain customFountain : this.crazyManager.getCustomFountains()) {
                    if (customFountain.getName().equalsIgnoreCase(name.replace(" ", ""))) return BPFountains.CUSTOM;
                }
            } else if (fountain.name().equalsIgnoreCase(name)) {
                return fountain;
            }
        }

        return null;
    }

    public BPParticles getParticleFromName(String name) {
        for (BPParticles fountain : BPParticles.values()) {
            if (fountain.name().equalsIgnoreCase(name.replace(" ", ""))) {
                return fountain;
            }
        }
        return null;
    }
    
    public void startParticles() {
        FileConfiguration data = Files.DATA.getFile();

        if (data.contains("locations")) return;

        for (final String id : data.getConfigurationSection("locations").getKeys(false)) {
            World world = this.plugin.getServer().getWorld(data.getString("locations." + id + ".world"));
            String particle = data.getString("locations." + id + ".particle");
            final Location loc = new Location(world, data.getInt("locations." + id + ".x"), data.getInt("locations." + id + ".y"), data.getInt("locations." + id + ".z"));
            if (getFountainFromName(particle) != null) {
                switch (Objects.requireNonNull(getFountainFromName(particle))) {
                    case MARIO -> this.fountains.startMario(loc, id);
                    case POKEMON -> this.fountains.startPokemon(loc, id);
                    case FOOD -> this.fountains.startFood(loc, id);
                    case MOBS -> this.fountains.startMobs(loc, id);
                    case HALLOWEEN -> this.fountains.startHalloween(loc, id);
                    case GEMS -> this.fountains.startGems(loc, id);
                    case HEADS -> this.fountains.startHeads(loc, id);
                    case PRESENTS -> this.fountains.startPresents(loc, id);
                    case CUSTOM -> this.fountains.startCustomFountain(loc, id, particle);
                }
            }

            if (getParticleFromName(particle) != null) {
                switch (Objects.requireNonNull(getParticleFromName(particle))) {
                    case LOVE_WELL -> this.crazyManager.getParticleControl().playLoveWell(loc, id);
                    case BIG_LOVE_WELL -> this.crazyManager.getParticleControl().playBigLoveWell(loc, id);
                    case LOVE_TORNADO -> this.crazyManager.getParticleControl().playLoveTornado(loc, id);
                    case WITCH_TORNADO -> this.crazyManager.getParticleControl().playWitchTornado(loc, id);
                    case FLAME_WHEEL -> this.crazyManager.getParticleControl().playFlameWheel(loc, id);
                    case SOUL_WELL -> this.crazyManager.getParticleControl().playSoulWell(loc, id);
                    case BIG_SOUL_WELL -> this.crazyManager.getParticleControl().playBigSoulWell(loc, id);
                    case SANTA_HAT -> this.crazyManager.getParticleControl().playSantaHat(loc, id);
                    case SNOW_BLAST -> this.crazyManager.getParticleControl().playSnowBlast(loc, id);
                    case RAINBOW -> this.crazyManager.getParticleControl().playRainbow(loc, id);
                    case ENDER_SIGNAL -> this.crazyManager.getParticleControl().playEnderSignal(loc, id);
                    case MOB_SPAWNER -> this.crazyManager.getParticleControl().playMobSpawner(loc, id);
                    case ANGRY_VILLAGER -> this.crazyManager.getParticleControl().playAngryVillager(loc, id);
                    case HAPPY_VILLAGER -> this.crazyManager.getParticleControl().playHappyVillager(loc, id);
                    case FOOTPRINT -> this.crazyManager.getParticleControl().playFootPrint(loc, id);
                    case FIRE_SPEW -> this.crazyManager.getParticleControl().playFireSpew(loc, id);
                    case SNOWSTORM -> this.crazyManager.getParticleControl().playSnowStorm(loc, id);
                    case DOUBLE_WITCH -> this.crazyManager.getParticleControl().playDoubleSpiral(loc, id, Particles.DOUBLE_WITCH, 5);
                    case WITCH -> this.crazyManager.getParticleControl().playSpiral(loc, id, Particles.WITCH, 5);
                    case MAGIC -> this.crazyManager.getParticleControl().playMagic(loc, id);
                    case SPEW -> this.crazyManager.getParticleControl().playSpew(loc, id);
                    case HALO -> this.crazyManager.getParticleControl().playHalo(loc, id);
                    case MUSIC -> this.crazyManager.getParticleControl().playMusic(loc, id);
                    case POTION -> this.crazyManager.getParticleControl().playPotion(loc, id);
                    case SNOW -> this.crazyManager.getParticleControl().playSnow(loc, id);
                    case FIRESTORM -> this.crazyManager.getParticleControl().playFireStorm(loc, id);
                    case WATER -> this.crazyManager.getParticleControl().startWater(loc, id);
                    case CHAINS -> this.crazyManager.getParticleControl().playChains(loc, id);
                    case ENCHANT -> this.crazyManager.getParticleControl().playEnchant(loc, id);
                    case FOG -> this.crazyManager.getParticleControl().playFog(loc, id);
                    case STORM -> this.crazyManager.getParticleControl().playStorm(loc, id);
                    case BIG_FLAME -> this.crazyManager.getParticleControl().playBigFlame(loc, id);
                    case FLAME -> this.crazyManager.getParticleControl().playFlame(loc, id);
                    case VOLCANO -> this.crazyManager.getParticleControl().playVolcano(loc, id);
                    case SPIRAL -> this.crazyManager.getParticleControl().playSpiral(loc, id, Particles.SPIRAL, 1);
                    case DOUBLE_SPIRAL -> this.crazyManager.getParticleControl().playDoubleSpiral(loc, id, Particles.DOUBLE_SPIRAL, 5);
                    case CRIT -> this.crazyManager.getParticleControl().playCrit(loc, id);
                    case BIG_CRIT -> this.crazyManager.getParticleControl().playBigCrit(loc, id);
                }
            }
        }
    }
    
    public void addLoc(Player player, String name) {
        /*String Prefix = Files.CONFIG.getFile().getString("settings.prefix");

        if (Files.DATA.getFile().contains("locations")) {
            for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    player.sendMessage(color(Prefix + "&3That location name is taken please remove it and replace it here."));
                    return;
                }
            }
        }

        Block block = player.getTargetBlock(null, 5);

        if (block.isEmpty()) {
            player.sendMessage(Methods.color(Prefix + "&cYou are not looking at a block."));
            return;
        }

        Location l = block.getLocation();
        String w = l.getWorld().getName();
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();
        Files.DATA.getFile().set("locations." + name + ".world", w);
        Files.DATA.getFile().set("locations." + name + ".x", x);
        Files.DATA.getFile().set("locations." + name + ".y", y);
        Files.DATA.getFile().set("locations." + name + ".z", z);
        Files.DATA.getFile().set("locations." + name + ".particle", "Spiral");
        Files.DATA.saveFile();
        kill();
        startParticles();
        //player.sendMessage(color(Prefix + "&3You have added &6" + name + " &3to the block."));*/
    }
    
    public void delLoc(CommandSender player, String name) {
        String Prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (Files.DATA.getFile().contains("locations")) {
            for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    Files.DATA.getFile().set("locations." + loc, null);
                    Files.DATA.saveFile();
                    Bukkit.getScheduler().cancelTask(this.crazyManager.getParticleControl().getLocations().get(loc));
                    //player.sendMessage(color(Prefix + "&3You have just deleted &6" + name + "&3."));
                    return;
                }
            }
        }

        //player.sendMessage(color(Prefix + "&3There are no locations called &6" + name + "&3."));
    }
    
    public void listLoc(Player player) {
        /*String Prefix = Methods.color(Files.CONFIG.getFile().getString("settings.prefix"));
        if (!Files.DATA.getFile().contains("locations")) {
            //player.sendMessage(Prefix + Methods.color("&cThere are no locations set!"));
            return;
        }
        if (Files.DATA.getFile().getConfigurationSection("locations").getKeys(false).isEmpty()) {
            player.sendMessage(Prefix + Methods.color("&cThere are no locations set!"));
            return;
        }
        String msg;
        String part;
        StringBuilder l = new StringBuilder();
        int line = 1;
        for (String L : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
            if (Files.DATA.getFile().getConfigurationSection("locations." + L).getKeys(false).isEmpty()) {
                Files.DATA.getFile().set("locations." + L, null);
                Files.DATA.saveFile();
                continue;
            }
            String W = Files.DATA.getFile().getString("locations." + L + ".world");
            String X = Files.DATA.getFile().getString("locations." + L + ".x");
            String Y = Files.DATA.getFile().getString("locations." + L + ".y");
            String Z = Files.DATA.getFile().getString("locations." + L + ".z");
            
            part = Methods.color("&8[&6" + line + "&8]: " + "&c" + L + "&8, &c" + W + "&8, &c" + X + "&8, &c" + Y + "&8, &c" + Z);
            l.append(part);
            l.append("\n");
            line++;
            
        }
        msg = l.toString();
        line = line - 1;
        player.sendMessage(Prefix + Methods.color("&6A list of all the locations."));
        player.sendMessage(Methods.color("&c[Locations Name]&8, &c[World]&8, &c[X]&8, &c[Y]&8, &c[Z]"));
        player.sendMessage(msg);
        player.sendMessage(Methods.color("&3Number of locations: &6" + line));*/
    }
    
    public int randomColor() {
        Random r = new Random();
        return r.nextInt(255);
    }
    
    public ItemStack makeItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta m = item.getItemMeta();
        //m.setDisplayName(color(name));
        item.setItemMeta(m);
        return item;
    }
    
    public void setLoc(CommandSender player, String name, String particle) {
        /*String prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (BPFountains.getFromName(particle) == null && BPParticles.getFromName(particle) == null && this.crazyManager.getCustomFountain(particle) == null) {
            player.sendMessage(color(prefix + "&6" + particle + " &cis not a particle. Please do /bp help for more information."));
            return;
        }
        if (Files.DATA.getFile().contains("locations")) {
            for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
                if (loc.equalsIgnoreCase(name)) {
                    Files.DATA.getFile().set("locations." + loc + ".particle", particle);
                    Files.DATA.saveFile();
                    kill();
                    startParticles();
                    player.sendMessage(color(prefix + "&3You have just set &6" + name + "'s &3particle to &6" + particle + "&3."));
                    return;
                }
            }
        }
        player.sendMessage(color(prefix + "&3There are no locations called &6" + name + "&3."));
         */
    }

    public void spawnHeads(Location loc, String id, List<String> marioHeads) {
        this.crazyManager.getParticleControl().getLocations().put(id, this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
            for (String head : getRandomHeads(marioHeads)) {
                final Item headItem = Objects.requireNonNull(this.plugin.getServer().getWorld(loc.getWorld().getName())).dropItem(loc.clone().add(.5, .8, .5), getPlayerHead("http://textures.minecraft.net/texture/" + head));
                headItem.setVelocity(new Vector(randomVector(), .01, randomVector()));
                this.crazyManager.addFountainItem(headItem);
                this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
                    this.crazyManager.removeFountainItem(headItem);
                    headItem.remove();
                }, 2 * 20);
            }
        }, 0, 3));
    }

    private List<String> getRandomHeads(List<String> headList) {
        List<String> pickedHeads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int p = random.nextInt(headList.size());
            pickedHeads.add(headList.get(p));
        }
        return pickedHeads;
    }

    public List<ItemStack> getRandomCustomHead(List<ItemStack> headList) {
        List<ItemStack> pickedHeads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int p = random.nextInt(headList.size());
            pickedHeads.add(headList.get(p));
        }
        return pickedHeads;
    }

    public float randomVector() {
        return (float) -.1 + (float) (Math.random() * ((.1 - -.1)));
    }
    
    public ItemStack getPlayerHead(String name) {
        return getPlayerHead(name, null);
    }
    
    public ItemStack getPlayerHead(String playerName, String displayName) {
        return ItemBuilder.setMaterial(Material.PLAYER_HEAD).setPlayerName(playerName)
                .setDisplayName(displayName).build();
    }
}