package com.minecastdevelopment.Bingo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Info extends CommandLayout{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		Player player = (Player) sender;
		
		player.sendMessage(ChatColor.GOLD + "Once you join a game, you will be teleported into a new world and once two players are in the same game a countdown will start. Once this countdown is a finished, each player will be randomly generated a set of items in a grid which is accessible with the /bingo map command. The winner is the first person to collect a line of their items on their map (Crafted items need to be dropped and picked up to register). When a player completes a line of their card, it will be announced to all players in the game and then game will reset ready for a new game.");
		
		return;
		
		
		
		
	}

}
