package com.minecastdevelopment.Bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class Leave extends CommandLayout {

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		Player player = (Player) sender;
		
		ArenaManager.getManager().removePlayer(player);
		
	}
	
	

}
