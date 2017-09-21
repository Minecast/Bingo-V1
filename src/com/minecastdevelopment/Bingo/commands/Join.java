package com.minecastdevelopment.Bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class Join extends CommandLayout{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;
		
		if(!(args.length == 2)){
			player.sendMessage(Bingo.chatPrefix + "Please include the game you would like to join");
			return;
			
		}
		
		int id = Integer.parseInt(args[1]);		
		if(ArenaManager.getManager().isInGame(player)){
			player.sendMessage(Bingo.chatPrefix + "Please leave your current game first");
			return;
		}
		ArenaManager.getManager().addPlayer(player, id);
		
	}
	
	

}
