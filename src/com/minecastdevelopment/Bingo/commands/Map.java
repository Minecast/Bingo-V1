package com.minecastdevelopment.Bingo.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.utils.Arena;
import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class Map extends CommandLayout {

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;
		List<Arena> arenas = ArenaManager.getManager().arenas;
		for(int i =0; i < arenas.size(); i++){
			
			if(arenas.get(i).getPlayers().contains(player.getUniqueId())){
				if(arenas.get(i).getStatus() == "STARTED"){
					player.openInventory(arenas.get(i).getPlayerMap(player.getUniqueId()));
					return;
				}else{
					player.sendMessage(Bingo.chatPrefix + "Please Wait for the game to start before opening your map");
					return;
				}
				
			}
			
			
		}
		
		player.sendMessage(Bingo.chatPrefix + "You arent in a game");
		
	}
	

}
