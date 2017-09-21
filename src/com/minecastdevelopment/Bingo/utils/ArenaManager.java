package com.minecastdevelopment.Bingo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.Game.Start;
import com.minecastdevelopment.Bingo.Game.Worlds;

public class ArenaManager {

	// save where the player teleported
	public Map<UUID, Location> locs = new HashMap<UUID, Location>();
	// make a new instance of the class
	public static ArenaManager am = new ArenaManager();
	// a few other fields
	Map<UUID, ItemStack[]> inv = new HashMap<UUID, ItemStack[]>();
	Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();
	// list of arenas
	public List<Arena> arenas = new ArrayList<Arena>();
	int arenaSize = 0;

	static Bingo plugin;

	public ArenaManager(Bingo bingo) {
		plugin = bingo;
	}

	public ArenaManager() {

	}

	// we want to get an instance of the manager to work with it statically
	public static ArenaManager getManager() {
		return am;
	}

	// get an Arena object from the list
	public Arena getArena(int i) {
		for (Arena a : arenas) {
			if (a.getId() == i) {
				return a;
			}
		}
		return null;
	}

	// add players to the arena, save their inventory
	public void addPlayer(Player p, int i) {
		Arena a = getArena(i);// get the arena you want to join
		if (a == null) {// make sure it is not null
			p.sendMessage(Bingo.chatPrefix + "Arena Not Found!");
			return;
		}

		if (a.getStatus() == "STARTED") {
			p.sendMessage(Bingo.chatPrefix + "Arena Already Started");
			return;
		}

		a.getPlayers().add(p.getUniqueId());// add them to the arena list of
											// players
		inv.put(p.getUniqueId(), p.getInventory().getContents());// save
																	// inventory
		armor.put(p.getUniqueId(), p.getInventory().getArmorContents());

		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		p.setHealth(20);
		p.setSaturation(20);
		locs.put(p.getUniqueId(), p.getLocation());
		Random random = new Random();
		if(Bukkit.getWorld(Integer.toString(a.getId())) == null){
			Worlds.createWorld(a.getId());
		}
		Location l = new Location(Bukkit.getWorld(Integer.toString(a.getId())), random.nextInt(1000), 60,
				random.nextInt(1000));

		p.teleport(l);// teleport to the arena spawn
		p.setBedSpawnLocation(l);
		p.sendMessage(Bingo.chatPrefix + "Arena " + Integer.toString(i) + " joined!");
		if (a.getPlayers().size() >= 2 && a.getStatus() == "WAITING") {
			a.setStatus("COUNTDOWN");
			Start.Countdown(i);

		}

	}

	// remove players
	public void removePlayer(Player p) {
		Arena a = null;// make an arena
		for (Arena arena : arenas) {
			if (arena.getPlayers().contains(p.getUniqueId())) {
				a = arena;// if the arena has the player, the arena field would
							// be the arena containing the player
			}
			// if none is found, the arena will be null
		}
		if (a == null || !a.getPlayers().contains(p.getUniqueId())) {// make
																		// sure
																		// it is
																		// not
																		// null
			p.sendMessage(Bingo.chatPrefix + "You aren't in a game!");
			return;
		}

		a.getPlayers().remove(p.getUniqueId());// remove from arena

		p.getInventory().clear();
		p.getInventory().setArmorContents(null);

		p.getInventory().setContents(inv.get(p.getUniqueId()));// restore
																// inventory
		p.getInventory().setArmorContents(armor.get(p.getUniqueId()));

		inv.remove(p.getUniqueId());// remove entries from hashmaps
		armor.remove(p.getUniqueId());
		p.teleport(locs.get(p.getUniqueId()));
		locs.remove(p.getUniqueId());

		p.setFireTicks(0);
		p.sendMessage(Bingo.chatPrefix + "Arena " + Integer.toString(a.getId()) + " left!");

		if (a.getPlayers().size() < 2 && a.getStatus() == "COUNTDOWN") {
			Start.cancelCountdown(a.getId());
		}
	}

	// create arena
	public Arena createArena(Location l, int gridSize) {
		int num = arenaSize + 1;
		arenaSize++;

		Arena a = new Arena(l, num, gridSize);
		arenas.add(a);
		Worlds.createWorld(num);
		ConfigFileManager.dataFileCfg.set("Arenas." + num, serializeLoc(l));
		List<Integer> list = ConfigFileManager.dataFileCfg.getIntegerList("Arenas.Arenas");
		list.add(num);
		ConfigFileManager.dataFileCfg.set("Arenas.Arenas", list);
		ConfigFileManager.dataFileCfg.set("Arenas." + Integer.toString(num) + " size", gridSize);
		ConfigFileManager.saveDataFile(null);

		return a;
	}

	public Arena reloadArena(Location l, int gridSize) {
		int num = arenaSize + 1;
		arenaSize++;

		Arena a = new Arena(l, num, gridSize);
		arenas.add(a);

		return a;
	}

	public void removeArena(int i) {
		Arena a = getArena(i);
		if (a == null) {

			return;
		}
		Worlds.deleteWorld(i);
		arenas.remove(a);

		ConfigFileManager.dataFileCfg.set("Arenas." + i, null);
		List<Integer> list = ConfigFileManager.dataFileCfg.getIntegerList("Arenas.Arenas");
		list.remove(i);
		ConfigFileManager.dataFileCfg.set("Arenas.Arenas", list);
		ConfigFileManager.saveDataFile(null);

	}

	public boolean isInGame(Player p) {
		for (Arena a : arenas) {
			if (a.getPlayers().contains(p.getUniqueId()))
				return true;
		}
		return false;
	}

	public void loadGames() {
		arenaSize = 0;

		if (ConfigFileManager.dataFileCfg.getIntegerList("Arenas.Arenas").isEmpty()) {
			return;
		}

		for (int i : ConfigFileManager.dataFileCfg.getIntegerList("Arenas.Arenas")) {
			Arena a = reloadArena(deserializeLoc(ConfigFileManager.dataFileCfg.getString("Arenas." + i)),
					ConfigFileManager.dataFileCfg.getInt("Arenas." + i + " size"));
			a.id = i;
			
		}
	}

	public String serializeLoc(Location l) {
		return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
	}

	public Location deserializeLoc(String s) {
		String[] st = s.split(",");
		return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]),
				Integer.parseInt(st[3]));
	}

	public Arena getPlayersArena(Player player) {
		for (int i = 1; i <= arenas.size(); i++) {
			if (am.getArena(i) == null) {

			} else {
				if (am.getArena(i).getPlayers().contains(player.getUniqueId())) {
					return am.getArena(i);
				}
			}

		}

		return null;
	}
}