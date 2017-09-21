package com.minecastdevelopment.Bingo.events;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.minecastdevelopment.Bingo.utils.Arena;
import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {

		Player player = event.getPlayer();
		List<Arena> arenas = ArenaManager.getManager().arenas;
		for (int i = 0; i < arenas.size(); i++) {
			if (arenas.get(i).getPlayers().contains(player.getUniqueId()) && (arenas.get(i).getStatus() == "WAITING")
					|| arenas.get(i).getStatus() == "COUNTDOWN") {
				Location from = event.getFrom();

				if (from.getZ() != event.getTo().getZ() && from.getX() != event.getTo().getX()) {
					event.setCancelled(true);

				}

			}

		}

	}

}
