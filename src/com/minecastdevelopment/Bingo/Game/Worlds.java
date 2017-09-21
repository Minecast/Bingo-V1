package com.minecastdevelopment.Bingo.Game;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;

public class Worlds {

	public static void deleteWorld(int id) {
		
		World delete = Bukkit.getWorld(Integer.toString(id));
		File deleteFolder = delete.getWorldFolder();
		
		
	    if(!delete.equals(null)) {
	        Bukkit.getServer().unloadWorld(delete, true);
	    }
	    
		deleteWorld(deleteFolder);
	}

	public static boolean deleteWorld(File path) {
		if (path.exists()) {
			File files[] = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteWorld(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}
	
	
	public static void createWorld(int id){
		
		WorldCreator creator = new WorldCreator(Integer.toString(id));
	    creator.environment(Environment.NORMAL);	    
		
		Bukkit.getServer().createWorld(creator);
		
	
	}

}
