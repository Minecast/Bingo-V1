package com.minecastdevelopment.Bingo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand extends CommandLayout {

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {

		Player player = (Player) sender;
		
		player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "" +  "Bingo Commands Help");
		player.sendMessage("");
		player.sendMessage(ChatColor.GOLD + "/bingo join <Game_Number>" + ChatColor.LIGHT_PURPLE + " - Joins the requested game!");
		player.sendMessage(ChatColor.GOLD + "/bingo leave" + ChatColor.LIGHT_PURPLE + " - Leave your current game!");
		player.sendMessage(ChatColor.GOLD + "/bingo map" + ChatColor.LIGHT_PURPLE + " - Opens up your map so you can see the items you need to collect");
		player.sendMessage(ChatColor.GOLD + "/bingo info" + ChatColor.LIGHT_PURPLE + " - Provides an explanation of the game");
		player.sendMessage(ChatColor.GOLD + "/bingo author" + ChatColor.LIGHT_PURPLE + " - See information about the developer.");
		
		if(player.hasPermission("Bingo.create") || player.hasPermission("Bingo.op")){
			player.sendMessage(ChatColor.GOLD + "/bingo create" + ChatColor.LIGHT_PURPLE + " - Creates a New Game");			
		}
		
		if(player.hasPermission("Bingo.remove") || player.hasPermission("Bingo.op")){
			player.sendMessage(ChatColor.GOLD + "/bingo remove <Game_Number>" + ChatColor.LIGHT_PURPLE + " - Deletes the requested game");			
		}
		
		if(player.hasPermission("Bingo.blacklist") || player.hasPermission("Bingo.op")){
			player.sendMessage(ChatColor.GOLD + "/bingo blacklist <Material_Name>" + ChatColor.LIGHT_PURPLE + " - Prevent an item appearing in Bingo Maps.");			
		}
		
		if(player.hasPermission("Bingo.getid") || player.hasPermission("Bingo.op")){
			player.sendMessage(ChatColor.GOLD + "/bingo getid" + ChatColor.LIGHT_PURPLE + " - Get the Material Name for the item you are holding to use it in the blacklist");			
		}
		
		return;
		
	}
	
	
}
