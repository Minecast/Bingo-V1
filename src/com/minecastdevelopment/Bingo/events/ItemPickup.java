package com.minecastdevelopment.Bingo.events;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.Game.Worlds;
import com.minecastdevelopment.Bingo.utils.Arena;
import com.minecastdevelopment.Bingo.utils.ArenaManager;
import com.minecastdevelopment.Bingo.utils.ConfigFileManager;

public class ItemPickup implements Listener {

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {

		Player player = event.getPlayer();
		Item item = event.getItem();
		Inventory map = Bukkit.createInventory(null, 9);
		int id = 0;
		if (ArenaManager.getManager().isInGame(player)) {
			List<Arena> arenas = ArenaManager.getManager().arenas;
			for (int i = 0; i < arenas.size(); i++) {
				if (arenas.get(i).getPlayers().contains(player.getUniqueId())
						&& arenas.get(i).getStatus() == "STARTED") {
					map = arenas.get(i).getPlayerMap(player.getUniqueId());
					id = arenas.get(i).getId();
				}

			}

			if (map.contains(item.getItemStack().getType())) {
				for (int i = 0; i < map.getSize(); i++) {
					if (map.getItem(i).getType() == item.getItemStack().getType()) {
						player.sendMessage(Bingo.chatPrefix + "Item Collected");
						map.setItem(i, addGlow(map.getItem(i)));
						ArenaManager.getManager().getArena(id).setPlayerMap(player.getUniqueId(), map);

						if (event.getItem().getItemStack().getAmount() > 1) {
							event.getItem().getItemStack().setAmount(event.getItem().getItemStack().getAmount() - 1);
						} else {
							event.getItem().setItemStack(new ItemStack(Material.AIR, 1));
						}
						checkCollumn(id, player, i);
						checkRow(id, player, i);
						checkDiagonal(id, player, i);

					}
				}
			}

		} else {
			return;
		}

	}

	public static ItemStack addGlow(ItemStack item) {
		item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;

	}

	public void checkCollumn(int id, Player player, int slot) {
		int collum = slot % 9;
		Inventory map = ArenaManager.getManager().getArena(id).getPlayerMap(player.getUniqueId());
		for (int i = 0; i < map.getSize(); i++) {
			if (i % 9 == collum) {
				if ((!(map.getItem(i).getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)))
						&& map.getItem(i).getType() != Material.BARRIER) {
					
					return;
				}
			}
		}
		player.sendMessage(Bingo.chatPrefix + "You Have Won!");
		GameWon(id, player);

	}

	public void checkRow(int id, Player player, int slot) {
		int row = slot / 9;
		Inventory map = ArenaManager.getManager().getArena(id).getPlayerMap(player.getUniqueId());
		for (int i = 0; i < map.getSize(); i++) {
			if (i / 9 == row && map.getItem(i).getType() != Material.BARRIER) {
				if ((!(map.getItem(i).getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)))) {
					
					return;
				}
			}

		}
		player.sendMessage(Bingo.chatPrefix + "You Have Won!");
		GameWon(id, player);
	}

	public void checkDiagonal(int id, Player player, int slot) {

		int size = ArenaManager.getManager().getArena(id).getGridSize();
		Inventory map = ArenaManager.getManager().getArena(id).getPlayerMap(player.getUniqueId());

		if (size == 3) {

			if (!(slot == 12 || slot == 22 || slot == 32 || slot == 14 || slot == 30)) {
				return;
			}

			if (map.getItem(12).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(22).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(32).containsEnchantment(Enchantment.SILK_TOUCH)) {
				player.sendMessage(Bingo.chatPrefix + "You Have Won!");
				GameWon(id, player);
				return;
			} else if (map.getItem(14).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(22).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(30).containsEnchantment(Enchantment.SILK_TOUCH)) {
				player.sendMessage(Bingo.chatPrefix + "You Have Won!");
				GameWon(id, player);
				return;
			} else {
				return;

			}

		} else if (size == 5) {

			if (!(slot == 2 || slot == 12 || slot == 22 || slot == 32 || slot == 42 || slot == 6 || slot == 14
					|| slot == 30 || slot == 38)) {
				return;
			}

			if (map.getItem(2).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(12).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(22).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(32).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(42).containsEnchantment(Enchantment.SILK_TOUCH)) {
				player.sendMessage(Bingo.chatPrefix + "You Have Won!");
				GameWon(id, player);
				return;
			} else if (map.getItem(6).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(14).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(22).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(30).containsEnchantment(Enchantment.SILK_TOUCH)
					&& map.getItem(38).containsEnchantment(Enchantment.SILK_TOUCH)) {
				player.sendMessage(Bingo.chatPrefix + "You Have Won!");				
				GameWon(id, player);
				return;
			} else {
				return;

			}

		} else {
			return;
		}

	}
	
	
	
	public void GameWon(int id, Player player){
		ArenaManager.getManager().getArena(id).setStatus("FINSIHED");
		Arena arena =  ArenaManager.getManager().getArena(id);
		for(int i = 0; i < arena.getPlayers().size(); i++ ){			
			Bukkit.getPlayer(arena.getPlayers().get(i)).sendMessage(Bingo.chatPrefix + player.getDisplayName() + " has won the game!");			
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bingo.getInstance(), new Runnable() {
				
				public void run(){
					final List<UUID> players = arena.getPlayers();
					for(int i = players.size() - 1; i >= 0 ; i-- ){			
						Bukkit.getPlayer(players.get(i)).sendMessage(Bingo.chatPrefix + "Arena Restarting");
						ArenaManager.getManager().removePlayer(Bukkit.getPlayer(players.get(i)));
						
						
					}
					if(ConfigFileManager.dataFileCfg.getBoolean("New World")){
						Worlds.deleteWorld(id);
						Worlds.createWorld(id);
					}else{
						arena.rollback();
						Bukkit.getServer().broadcastMessage("Arena Rolled Back");
					}
					
					ArenaManager.getManager().getArena(id).setStatus("WAITING");
			
		}}, 30*20);
		
		
	}
	
	
	
	
}
