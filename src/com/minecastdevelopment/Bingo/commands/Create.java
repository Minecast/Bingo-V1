package com.minecastdevelopment.Bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class Create extends CommandLayout {

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		
		Player player = (Player) sender;
		
		if(!(args.length == 2)){
			player.sendMessage(Bingo.chatPrefix + "Please include a gridsize (3 or 5)");
			return;
		}
		int gridSize = Integer.parseInt(args[1]);
		if(!(gridSize == 3 || gridSize == 5)){
			player.sendMessage(Bingo.chatPrefix + "Invalid Grid Size - Must be either 3 or 5");
			return;
		}
		int id = ArenaManager.getManager().createArena(player.getLocation(), gridSize).getId();
		player.sendMessage(Bingo.chatPrefix + "Arena " + Integer.toString(id) + " has been created.");
		
		return;
		
	}

}
