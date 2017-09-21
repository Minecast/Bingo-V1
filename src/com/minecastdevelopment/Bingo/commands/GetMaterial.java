package com.minecastdevelopment.Bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minecastdevelopment.Bingo.Bingo;

public class GetMaterial extends CommandLayout{

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {

		Player player = (Player) sender;
		
		ItemStack item = player.getItemInHand();
		player.sendMessage(Bingo.chatPrefix + item.getType().toString());
		
		
	}

}
