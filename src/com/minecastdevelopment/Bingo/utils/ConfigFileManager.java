package com.minecastdevelopment.Bingo.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.minecastdevelopment.Bingo.Bingo;



public class ConfigFileManager {

	public static File dataFile = new File(Bingo.getInstance().getDataFolder() + File.separator + "config.yml");
	public static FileConfiguration dataFileCfg = YamlConfiguration.loadConfiguration(dataFile);
	
	public static void loadFiles() {			
		
		if(!dataFile.exists()) {
			try {
				System.out.print(Bingo.consolePrefix + "Creating data file...");				
				dataFile.createNewFile();			
				dataFileCfg.addDefault("New World", false);		
				dataFileCfg.options().copyDefaults(true);				
				dataFileCfg.save(dataFile);
			} catch (IOException e) {
				System.out.print(Bingo.consolePrefix + "Error creating data file!");
				e.printStackTrace();
			}
		}	
	}
	
	public static void unloadFiles() {
		
		File dataFile = new File(Bingo.getInstance().getDataFolder() + File.separator + "config.yml");
		FileConfiguration dataFileCfg = YamlConfiguration.loadConfiguration(dataFile);
		try {
			dataFileCfg.save(dataFile);
		} catch (IOException e) {
			System.out.print(Bingo.consolePrefix + "Could not save data file!");
			e.printStackTrace();
		}
	}
	
	public static void saveDataFile(Player player) {
		try {
			dataFileCfg.save(dataFile);
		} catch (IOException e) {
			System.out.print(Bingo.consolePrefix + "Error creating data file!");
			player.sendMessage(Bingo.consolePrefix + ChatColor.RED + "Error creating data file!");
			e.printStackTrace();
		}		
	}
}
