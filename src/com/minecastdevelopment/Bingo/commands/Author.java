package com.minecastdevelopment.Bingo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Author extends CommandLayout {

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;
		
		player.sendMessage(ChatColor.BLUE + "Bingo created by Reilly910, Owner of Minecast Development");
		player.sendMessage(ChatColor.BLUE +"Website : "  + ChatColor.GOLD + "" + ChatColor.UNDERLINE + "" + "http://www.minecastdevelopment.com");
		player.sendMessage(ChatColor.BLUE +"Plugin available for purchase at "  + ChatColor.GOLD + "" + ChatColor.UNDERLINE + "" + "http://shop.minecastdevelopment.com");
		
		
		
	}

}
