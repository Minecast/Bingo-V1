package com.minecastdevelopment.Bingo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.minecastdevelopment.Bingo.Bingo;

public class Arena {
	// Individual arena info here

	public int id;
	public String status;
	public int countdownID;
	public int gridSize = 3;
	private final Location spawn;
	private Map<UUID, ItemStack[]> maps = new HashMap<UUID, ItemStack[]>();
	private final List<UUID> players = new ArrayList<UUID>();
	private ArrayList<BlockState> changedBlocks = new ArrayList<BlockState>();

	public Arena(Location spawn, int id, int gridSize) {
		this.spawn = spawn;
		this.id = id;
		this.status = "WAITING";
		this.gridSize = gridSize;

	}

	// Getters

	public int getId() {
		return this.id;
	}

	public List<UUID> getPlayers() {
		return this.players;
	}

	public Location getSpawn() {
		return this.spawn;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String newStatus) {
		status = newStatus;
	}

	public void setCountdownID(int id) {
		countdownID = id;
	}

	public int getCountdownID() {
		return countdownID;
	}

	public void setGridSize(int size) {
		gridSize = size;
	}

	public int getGridSize() {
		return gridSize;
	}

	public Inventory getPlayerMap(UUID uuid) {

		Inventory inv = Bukkit.createInventory(null, 5 * 9, "Bingo Map");
		inv.setContents(maps.get(uuid));
		return inv;
	}

	public void setPlayerMap(UUID uuid, Inventory inv) {

		maps.put(uuid, inv.getContents());

	}

	public void addBlockState(BlockState state) {

		if (!(changedBlocks.contains(state))) {
			changedBlocks.add(state);

		}

	}
	
	public void regen(final List<BlockState> blocks, final boolean effect, final long speed) {

        new BukkitRunnable() {
            int i = -1;
            @SuppressWarnings("deprecation")
            public void run() {
                if (i != blocks.size() - 1) {
                    i++;
                    BlockState bs = changedBlocks.get(i);
                    bs.update(true, false);
                    if (effect)
                        bs.getBlock().getWorld().playEffect(bs.getLocation(), Effect.STEP_SOUND, bs.getBlock().getType());
                }else {

                    blocks.clear();
                    this.cancel();
                }
            }
        }.runTaskTimer(Bingo.getInstance(), speed, speed);
    }

	public void rollback() {

		regen(changedBlocks, true, (long) 1);


 	}
}
