package me.badbones69.blockparticles;

import me.badbones69.blockparticles.api.FileManager;
import me.badbones69.blockparticles.api.FileManager.Files;
import me.badbones69.blockparticles.api.ParticleManager;
import me.badbones69.blockparticles.api.enums.Particles;
import me.badbones69.blockparticles.api.objects.ItemBuilder;
import me.badbones69.blockparticles.controllers.Fountains;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Methods implements Listener {

    public static HashMap<Location, Location> Locations = new HashMap<>();
    private static ParticleManager bp = ParticleManager.getInstance();

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String removeColor(String msg) {
        return ChatColor.stripColor(msg);
    }

    public static void reset() {
        kill();
        startParticles();
    }

    private static Collection<Entity> getNearbyEntities(Location loc, double x, double y, double z) {
        try {
            return loc.getWorld().getNearbyEntities(loc, x, y, z);
        } catch (Exception ignored) {
        }
        return new ArrayList<>();
    }

    public static boolean noPlayers(Location loc, int range) {
        try {
            Collection<Entity> out = getNearbyEntities(loc, range, range, range);
            if (!out.isEmpty()) {
                for (Entity e : out) {
                    if (e instanceof LivingEntity) {
                        LivingEntity en = (LivingEntity) e;
                        if (en instanceof Player) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return true;
    }

    public static ArrayList<String> getLocations() {
        return new ArrayList<>(Files.DATA.getFile().getConfigurationSection("locations").getKeys(false));
    }

    public static void kill() {
        bp.getParticleControl().getLocations().clear();
        for (World w : Bukkit.getServer().getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (e instanceof Item) {
                    Item item = (Item) e;
                    if (bp.getFountainItem().contains(item)) {
                        item.remove();
                    }
                }
            }
        }
        bp.getFountainItem().clear();
        Bukkit.getScheduler().cancelTasks(ParticleManager.getInstance().getPlugin());
    }

    public static void startParticles() {
        if (Files.DATA.getFile().getConfigurationSection("locations") == null) {
            Files.DATA.getFile().set("locations.clear", null);
            Files.DATA.saveFile();
        }
        for (final String id : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
            World world = Bukkit.getServer().getWorld(Files.DATA.getFile().getString("locations." + id + ".world"));
            String particle = Files.DATA.getFile().getString("locations." + id + ".particle");
            int x = Files.DATA.getFile().getInt("locations." + id + ".x");
            int y = Files.DATA.getFile().getInt("locations." + id + ".y");
            int z = Files.DATA.getFile().getInt("locations." + id + ".z");
            final Location loc = new Location(world, x, y, z);

            if (FileManager.Files.CONFIG.getFile().get("settings.heads." + particle) != null)
                Fountains.startCustomFountain(loc, id, particle);

            if (particle.equalsIgnoreCase("LoveWell")) bp.getParticleControl().playLoveWell(loc, id);
            if (particle.equalsIgnoreCase("BigLoveWell")) bp.getParticleControl().playBigLoveWell(loc, id);
            if (particle.equalsIgnoreCase("LoveTornado")) bp.getParticleControl().playLoveTornado(loc, id);
            if (particle.equalsIgnoreCase("WitchTornado")) bp.getParticleControl().playWitchTornado(loc, id);
            if (particle.equalsIgnoreCase("FlameWheel")) bp.getParticleControl().playFlameWheel(loc, id);
            if (particle.equalsIgnoreCase("SoulWell")) bp.getParticleControl().playSoulWell(loc, id);
            if (particle.equalsIgnoreCase("BigSoulWell")) bp.getParticleControl().playBigSoulWell(loc, id);
            if (particle.equalsIgnoreCase("SantaHat")) bp.getParticleControl().playSantaHat(loc, id);

            // TODO: Convert this from hard coded types.
            if (particle.equalsIgnoreCase("Mario")) Fountains.startMario(loc, id);
            if (particle.equalsIgnoreCase("Pokemon")) Fountains.startPokemon(loc, id);
            if (particle.equalsIgnoreCase("Food")) Fountains.startFood(loc, id);
            if (particle.equalsIgnoreCase("Mobs")) Fountains.startMobs(loc, id);

            if (particle.equalsIgnoreCase("Halo")) bp.getParticleControl().playHalo(loc, id);
            if (particle.equalsIgnoreCase("Snow Blast") || particle.equalsIgnoreCase("SnowBlast"))
                bp.getParticleControl().playSnowBlast(loc, id);
            if (particle.equalsIgnoreCase("Rainbow")) bp.getParticleControl().playRainbow(loc, id);
            if (particle.equalsIgnoreCase("Ender Signal") || particle.equalsIgnoreCase("EnderSignal"))
                bp.getParticleControl().playEnderSignal(loc, id);
            if (particle.equalsIgnoreCase("Mob Spawner") || particle.equalsIgnoreCase("MobSpawner"))
                bp.getParticleControl().playMobSpawner(loc, id);
            if (particle.equalsIgnoreCase("Angry Villager") || particle.equalsIgnoreCase("AngryVillager"))
                bp.getParticleControl().playAngryVillager(loc, id);
            if (particle.equalsIgnoreCase("Happy Villager") || particle.equalsIgnoreCase("HappyVillager"))
                bp.getParticleControl().playHappyVillager(loc, id);
            if (particle.equalsIgnoreCase("Foot Print") || particle.equalsIgnoreCase("FootPrint"))
                bp.getParticleControl().playFootPrint(loc, id);
            if (particle.equalsIgnoreCase("Fire Spew") || particle.equalsIgnoreCase("FireSpew"))
                bp.getParticleControl().playFireSpew(loc, id);
            if (particle.equalsIgnoreCase("Snow Storm") || particle.equalsIgnoreCase("SnowStorm"))
                bp.getParticleControl().playSnowStorm(loc, id);
            if (particle.equalsIgnoreCase("Double Witch") || particle.equalsIgnoreCase("DoubleWitch"))
                bp.getParticleControl().playDoubleSpiral(loc, id, Particles.DOUBLEWITCH, 5);
            if (particle.equalsIgnoreCase("Witch")) bp.getParticleControl().playSpiral(loc, id, Particles.WITCH, 5);
            if (particle.equalsIgnoreCase("Magic")) bp.getParticleControl().playMagic(loc, id);

            // TODO: Convert this from hard coded types.
            if (particle.equalsIgnoreCase("Presents")) Fountains.startPresents(loc, id);
            if (particle.equalsIgnoreCase("Spew")) bp.getParticleControl().playSpew(loc, id);
            if (particle.equalsIgnoreCase("Music")) bp.getParticleControl().playMusic(loc, id);
            if (particle.equalsIgnoreCase("Potion")) bp.getParticleControl().playPotion(loc, id);
            if (particle.equalsIgnoreCase("Snow")) bp.getParticleControl().playSnow(loc, id);
            if (particle.equalsIgnoreCase("Fire Storm") || particle.equalsIgnoreCase("FireStorm"))
                bp.getParticleControl().playFireStorm(loc, id);
            if (particle.equalsIgnoreCase("Water")) bp.getParticleControl().startWater(loc, id);
            if (particle.equalsIgnoreCase("Chains")) bp.getParticleControl().playChains(loc, id);
            if (particle.equalsIgnoreCase("Enchant")) bp.getParticleControl().playEnchant(loc, id);
            if (particle.equalsIgnoreCase("Fog")) bp.getParticleControl().playFog(loc, id);
            if (particle.equalsIgnoreCase("Storm")) bp.getParticleControl().playStorm(loc, id);

            // TODO: Convert this from hard coded types.
            if (particle.equalsIgnoreCase("Heads")) Fountains.startHeads(loc, id);
            if (particle.equalsIgnoreCase("Big Flame") || particle.equalsIgnoreCase("BigFlame"))
                bp.getParticleControl().playBigFlame(loc, id);
            if (particle.equalsIgnoreCase("Flame")) bp.getParticleControl().playFlame(loc, id);

            // TODO: Convert this from hard coded types.
            if (particle.equalsIgnoreCase("Halloween")) Fountains.startHalloween(loc, id);
            if (particle.equalsIgnoreCase("Gems")) Fountains.startGems(loc, id);

            if (particle.equalsIgnoreCase("Valcano") || particle.equalsIgnoreCase("Volcano"))
                bp.getParticleControl().playVolcano(loc, id);
            if (particle.equalsIgnoreCase("Spiral")) bp.getParticleControl().playSpiral(loc, id, Particles.SPIRAL, 1);
            if (particle.equalsIgnoreCase("Double Spiral") || particle.equalsIgnoreCase("DoubleSpiral"))
                bp.getParticleControl().playDoubleSpiral(loc, id, Particles.DOUBLESPIRAL, 5);
            if (particle.equalsIgnoreCase("Crit")) bp.getParticleControl().playCrit(loc, id);
            if (particle.equalsIgnoreCase("Big Crit") || particle.equalsIgnoreCase("BigCrit"))
                bp.getParticleControl().playBigCrit(loc, id);
        }
    }

    public static void addLoc(Player player, String name) {
        String Prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (!Files.DATA.getFile().contains("locations")) {
            Files.DATA.getFile().set("locations.clear", null);
            Files.DATA.saveFile();
        }
        for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
            if (loc.equalsIgnoreCase(name)) {
                player.sendMessage(color(Prefix + "&3That location name is taken please remove it and replace it here."));
                return;
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
        player.sendMessage(color(Prefix + "&3You have added &6" + name + " &3to the BlockParticlesAPI."));
    }

    public static void delLoc(CommandSender player, String name) {
        String Prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (Files.DATA.getFile().getConfigurationSection("locations") == null) {
            Files.DATA.getFile().set("locations.clear", null);
            Files.DATA.saveFile();
        }
        for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
            if (loc.equalsIgnoreCase(name)) {
                Files.DATA.getFile().set("locations." + loc, null);
                Files.DATA.saveFile();
                Bukkit.getScheduler().cancelTask(bp.getParticleControl().getLocations().get(loc));
                player.sendMessage(color(Prefix + "&3You have just deleted &6" + name + "&3."));
                return;
            }
        }
        player.sendMessage(color(Prefix + "&3There are no locations called &6" + name + "&3."));
    }

    public static void listLoc(Player player) {
        String Prefix = Methods.color(Files.CONFIG.getFile().getString("settings.prefix"));
        if (Files.DATA.getFile().getConfigurationSection("locations") == null) {
            player.sendMessage(Prefix + Methods.color("&cThere are no locations set!"));
            Files.DATA.getFile().set("locations.clear", null);
            Files.DATA.saveFile();
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
        player.sendMessage(Methods.color("&3Number of locations: &6" + line));
    }

    public static int randomColor() {
        Random r = new Random();
        return r.nextInt(255);
    }

    public static ItemStack makeItem(Material material, int amount, String name) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(color(name));
        item.setItemMeta(m);
        return item;
    }

    public static void setLoc(CommandSender player, String name, String particle) {
        String Prefix = Files.CONFIG.getFile().getString("settings.prefix");
        if (Files.DATA.getFile().getConfigurationSection("locations") == null) {
            Files.DATA.getFile().set("locations.clear", null);
            Files.DATA.saveFile();
        }
        List<String> list = new ArrayList<>();
        list.add("LoveWell");
        list.add("BigLoveWell");
        list.add("LoveTornado");
        list.add("WitchTornado");
        list.add("FlameWheel");
        list.add("SoulWell");
        list.add("BigSoulWell");
        list.add("SantaHat");
        list.add("Mario");
        list.add("Pokemon");
        list.add("Food");
        list.add("Mobs");
        list.add("Halo");
        list.add("SnowBlast");
        list.add("Rainbow");
        list.add("EnderSignal");
        list.add("MobSpawner");
        list.add("AngryVillager");
        list.add("HappyVillager");
        list.add("FootPrint");
        list.add("FireSpew");
        list.add("SnowStorm");
        list.add("DoubleWitch");
        list.add("Witch");
        list.add("Magic");
        list.add("Presents");
        list.add("Spew");
        list.add("Music");
        list.add("Potion");
        list.add("Snow");
        list.add("FireStorm");
        list.add("Water");
        list.add("Chains");
        list.add("Enchant");
        list.add("Fog");
        list.add("Storm");
        list.add("Heads");
        list.add("BigFlame");
        list.add("Flame");
        list.add("Halloween");
        list.add("Volcano");
        list.add("Gems");
        list.add("Spiral");
        list.add("DoubleSpiral");
        list.add("Crit");
        list.add("BigCrit");
        boolean c = false;
        for (String l : list) {
            if (particle.equalsIgnoreCase(l)) {
                c = true;
                break;
            }
        }

        if (FileManager.Files.CONFIG.getFile().get("settings.heads." + particle) != null) {
            c = true;
        }

        if (!c) {
            player.sendMessage(color(Prefix + "&6" + particle + " &cis not a particle. Please do /bp help for more information."));
            return;
        }

        for (String loc : Files.DATA.getFile().getConfigurationSection("locations").getKeys(false)) {
            if (loc.equalsIgnoreCase(name)) {
                Files.DATA.getFile().set("locations." + loc + ".particle", particle);
                Files.DATA.saveFile();
                kill();
                startParticles();
                player.sendMessage(color(Prefix + "&3You have just set &6" + name + "'s &3particle to &6" + particle + "&3."));
                return;
            }
        }
        player.sendMessage(color(Prefix + "&3There are no locations called &6" + name + "&3."));
    }

    public static boolean permCheck(Player player, String perm) {
        if (!player.hasPermission("bparticles." + perm.toLowerCase())) {
            player.sendMessage(color("&cYou do not have permission to use that command!"));
            return true;
        }
        return false;
    }

    public static ItemStack getPlayerHead(String name) {
        return getPlayerHead(name, null);
    }

    public static ItemStack getPlayerHead(String playerName, String displayName) {
        return new ItemBuilder()
        .setMaterial("PLAYER_HEAD", "SKULL_ITEM:3")
        .setPlayer(playerName)
        .setName(displayName != null ? color(displayName) : new Random().nextInt(Integer.MAX_VALUE) + "")
        .build();
    }

}