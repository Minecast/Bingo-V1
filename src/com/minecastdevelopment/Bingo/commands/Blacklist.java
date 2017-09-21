package com.minecastdevelopment.Bingo.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.utils.ConfigFileManager;

public class Blacklist extends CommandLayout{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {

		if(!(args.length == 2)){
			sender.sendMessage(Bingo.chatPrefix + "Incorrect Arguments");
			return;			
		}
		
		Material material = Material.getMaterial(args[1]);
		
		if(material == null){
			sender.sendMessage(Bingo.chatPrefix + "Unknown Material");
			return;
		}else{
			List<String> blacklist = ConfigFileManager.dataFileCfg.getStringList("Blacklist");
			blacklist.add(material.toString());
			ConfigFileManager.dataFileCfg.set("Blacklist", blacklist);
			ConfigFileManager.saveDataFile(null);
			sender.sendMessage(Bingo.chatPrefix + "Added to blacklist");
		}
		
		
		
		
	}
	
	

}
