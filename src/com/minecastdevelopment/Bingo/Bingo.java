package com.minecastdevelopment.Bingo;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.minecastdevelopment.Bingo.commands.BingoCommand;
import com.minecastdevelopment.Bingo.events.BingoSigns;
import com.minecastdevelopment.Bingo.events.InventoryClick;
import com.minecastdevelopment.Bingo.events.ItemPickup;
import com.minecastdevelopment.Bingo.events.PlayerLeave;
import com.minecastdevelopment.Bingo.events.PlayerMove;
import com.minecastdevelopment.Bingo.utils.ArenaManager;
import com.minecastdevelopment.Bingo.utils.ConfigFileManager;
import com.minecastdevelopment.Bingo.utils.RollbackManager;

public class Bingo extends JavaPlugin {
	
	public static Bingo instance;
	
	public static String consolePrefix = "[Bingo]";
	public static String chatPrefix = ChatColor.BLUE + "" + ChatColor.BOLD + "" + "[Bingo] ";
	
	public void onEnable(){
		getDataFolder().mkdir();		
		registerCommands();
		registerEvents();
		instance = this;
		ConfigFileManager.loadFiles();
		ArenaManager.getManager().loadGames();
		
	}
	
	public void onDisable(){
		ConfigFileManager.unloadFiles();
		unloadArenas();
	}
	
	public void registerCommands(){
		getCommand("bingo").setExecutor(new BingoCommand());
		
	}
	
	public void registerEvents(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerLeave(), this);
		pm.registerEvents(new BingoSigns(), this);
		pm.registerEvents(new ItemPickup(), this);
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new RollbackManager(), this);
	}
	
	public static Bingo getInstance(){
		return instance;
	}
	
	public void unloadArenas(){
		ArenaManager am = ArenaManager.getManager();
		for(Player all:getServer().getOnlinePlayers()){			
			if(am.isInGame(all)){
				am.removePlayer(all);
			}
			
			
			}
		
	}

}
