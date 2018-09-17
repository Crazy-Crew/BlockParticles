package me.badbones69.blockparticles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {
	
	static SettingsManager instance = new SettingsManager();
	
	public static SettingsManager getInstance() {
		return instance;
	}
	
	Plugin p;
	
	FileConfiguration config;
	File cfile;
	
	FileConfiguration data;
	File dfile;
	
	public void setup(Plugin p) {
		cfile = new File(p.getDataFolder(), "config.yml");
		config = p.getConfig();
		// config.options().copyDefaults(true);
		// saveConfig();
		
		if(!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		dfile = new File(p.getDataFolder(), "data.yml");
		if(!dfile.exists()) {
			try {
				File en = new File(p.getDataFolder(), "/data.yml");
				InputStream E = getClass().getResourceAsStream("/data.yml");
				copyFile(E, en);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		data = YamlConfiguration.loadConfiguration(dfile);
	}
	
	public static void copyFile(InputStream in, File out) throws Exception { // https://bukkit.org/threads/extracting-file-from-jar.16962/
		InputStream fis = in;
		FileOutputStream fos = new FileOutputStream(out);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			if(fis != null) {
				fis.close();
			}
			if(fos != null) {
				fos.close();
			}
		}
	}
	
	public FileConfiguration getData() {
		return data;
	}
	
	public void saveData() {
		try {
			data.save(dfile);
		}catch(IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
		}
	}
	
	public void reloadData() {
		data = YamlConfiguration.loadConfiguration(dfile);
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public void saveConfig() {
		try {
			config.save(cfile);
		}catch(IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
		}
	}
	
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(cfile);
	}
	
	public PluginDescriptionFile getDesc() {
		return p.getDescription();
	}
}