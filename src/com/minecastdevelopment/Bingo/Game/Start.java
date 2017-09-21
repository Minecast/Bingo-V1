package com.minecastdevelopment.Bingo.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.utils.ArenaManager;
import com.minecastdevelopment.Bingo.utils.ConfigFileManager;

import net.minecraft.server.v1_11_R1.Item;
import net.minecraft.server.v1_11_R1.MinecraftKey;

public class Start {
	static int taskId = 0;

	public static boolean Countdown(int id) {

		ArenaManager.getManager().getArena(id)
				.setCountdownID(Bukkit.getScheduler().scheduleSyncRepeatingTask(Bingo.getInstance(), new Runnable() {

					int i = 60;

					public void run() {

						List<UUID> players = ArenaManager.getManager().getArena(id).getPlayers();
						Material[] materials = Material.values();
						if (i % 5 == 0 || i <= 10) {
							for (int j = 0; j < players.size(); j++) {
								Bukkit.getServer().getPlayer(players.get(j))
										.sendMessage(Bingo.chatPrefix + "Game Starting in " + Integer.toString(i));

							}
						}

						i = i - 1;
						if (i == 0) {
							finishCountdown(id);
						}
					}
				}, 0, 20));

		return true;

	}

	private static void finishCountdown(int id) {

		Bukkit.getServer().getScheduler().cancelTask(ArenaManager.getManager().getArena(id).getCountdownID());
		ArenaManager.getManager().getArena(id).setStatus("STARTED");
		generateGrids(id);
	}

	public static void cancelCountdown(int id) {

		Bukkit.getServer().getScheduler().cancelTask(ArenaManager.getManager().getArena(id).getCountdownID());
		ArenaManager.getManager().getArena(id).setStatus("WAITING");
	}

	public static void generateGrids(int id) {
		int gridSize = ArenaManager.getManager().getArena(id).getGridSize();
		Inventory inv;
		inv = Bukkit.getServer().createInventory(null, 45);
		
		List<UUID> players = ArenaManager.getManager().getArena(id).getPlayers();
		ArrayList<Item> items = new ArrayList<Item>();
		
		for (int k = 1; k < 451; k++) {
			Item item = Item.getById(k);
			items.add(item);

		}
		for (int i = 0; i < players.size(); i++) {
			Player player = Bukkit.getPlayer(players.get(i));

			for (int j = 0; j < inv.getSize(); j++) {
				int size = ArenaManager.getManager().getArena(id).getGridSize();
				int indent = (9 - size) / 2;
				if (j < 9 && size != 5) {
					inv.setItem(j, new ItemStack(Material.BARRIER, 1));
				} else if (j % 9 < indent || j % 9 > indent - 1 + size) {
					inv.setItem(j, new ItemStack(Material.BARRIER, 1));
				} else if (inv.getSize() - j < 9 && size != 5) {

					inv.setItem(j, new ItemStack(Material.BARRIER, 1));
				} else {					
					boolean h = false;
					ItemStack item = new ItemStack(Material.AIR, 1);
					List<String> blacklist = ConfigFileManager.dataFileCfg.getStringList("Blacklist");
							
					
					
					while (item.getType() == Material.AIR || h == false) {
						Random ran = new Random();
						int r = ran.nextInt(items.size());						
						Item temp = items.get(r);
						
						item = new ItemStack(Material.getMaterial(Item.getId(temp)), 1);					
						if(!(inv.contains(item))){
							if(!(blacklist.contains(Material.getMaterial(Item.getId(temp)).toString()))){
								h = true;
							}else{
								
							}
												
							
						}else{
													}
					}

					inv.setItem(j, item);
					
				}

			}

			ArenaManager.getManager().getArena(id).setPlayerMap(player.getUniqueId(), inv);
		}

	}

}
