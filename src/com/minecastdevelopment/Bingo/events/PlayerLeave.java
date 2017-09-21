package com.minecastdevelopment.Bingo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class PlayerLeave implements Listener {
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		
		Player player = event.getPlayer();
		
		ArenaManager.getManager().removePlayer(player);
		
	}

}
