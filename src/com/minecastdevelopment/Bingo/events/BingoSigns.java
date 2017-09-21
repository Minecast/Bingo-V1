package com.minecastdevelopment.Bingo.events;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class BingoSigns implements Listener {

	@EventHandler
	public void onSignChange(SignChangeEvent event) {

		if (event.getLine(0).equalsIgnoreCase("[Bingo]") && event.getLine(1) != null) {
			event.setLine(0, ChatColor.BOLD + "" + ChatColor.BLUE + "" + "[Bingo]");
			int id = Integer.parseInt(event.getLine(1));
			int size = ArenaManager.getManager().getArena(id).getGridSize();
			event.setLine(3, size + " x " + size);
		}

	}

	@EventHandler
     public void onPlayerInteract(PlayerInteractEvent event) {
             if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
             if (event.getClickedBlock().getState() instanceof Sign) {
                     Sign s = (Sign) event.getClickedBlock().getState();
                     if (s.getLine(0).contains("[Bingo]")) {
                            ArenaManager.getManager().addPlayer(event.getPlayer(), Integer.parseInt(s.getLine(1)));
                            
                     }
             }
     }
}
