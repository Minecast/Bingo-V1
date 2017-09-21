package com.minecastdevelopment.Bingo.utils;

import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class RollbackManager implements Listener{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		Arena arena = ArenaManager.getManager().getPlayersArena(event.getPlayer());

		if (arena == null)
			return;
		else if (arena.getStatus() == "COUNTDOWN" || arena.getStatus() == "WAITING")
			event.setCancelled(true);

		handle(event.getBlock().getState(), event.getPlayer());

	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Arena arena = ArenaManager.getManager().getPlayersArena(e.getPlayer());

		if (arena == null)
			return;
		else if (arena.getStatus() == "COUNTDOWN" || arena.getStatus() == "WAITING")
			e.setCancelled(true);

		handle(e.getBlockReplacedState(), e.getPlayer());
	}

	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		handle(e.getBlock().getState());
	}

	@EventHandler
	public void onBlockGrow(org.bukkit.event.block.BlockGrowEvent e) {
		handle(e.getNewState());
	}

	@EventHandler
	public void onBlockMultiPlace(org.bukkit.event.block.BlockMultiPlaceEvent e) {
		for (BlockState b : e.getReplacedBlockStates()) {
			handle(b, e.getPlayer());
		}
	}

	@EventHandler
	public void onLeavesDecay(org.bukkit.event.block.LeavesDecayEvent e) {
		handle(e.getBlock().getState());
	}

	/*
	 * If you don't have a player.
	 */
	private void handle(BlockState state) {
		for (Arena arena : ArenaManager.getManager().arenas) {
			handle(state, arena);
			break;

		}
	}

	private void handle(BlockState state, Player player) {
		Arena arena = ArenaManager.getManager().getPlayersArena(player);

		if (arena == null) {
			return;
		}
		handle(state, arena);
	}

	private void handle(BlockState state, Arena arena) {
		arena.addBlockState(state);
	}
}
