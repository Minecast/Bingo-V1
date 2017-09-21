package com.minecastdevelopment.Bingo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		
		if(event.getInventory().getName().equals("Bingo Map")){
			event.setCancelled(true);
		}else{
			return;
		}
	}

}
