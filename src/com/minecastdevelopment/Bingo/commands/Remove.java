package com.minecastdevelopment.Bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minecastdevelopment.Bingo.Bingo;
import com.minecastdevelopment.Bingo.utils.ArenaManager;

public class Remove extends CommandLayout {

	@Override
	public void executeCommand(CommandSender sender, Command cmd, String[] args) {

		Player player = (Player) sender;

		if (!(args.length == 2)) {
			player.sendMessage(Bingo.chatPrefix + "Please include the arena you would like to delete");
			return;

		}

		int id = Integer.parseInt(args[1]);
		ArenaManager.getManager().removeArena(id);
		player.sendMessage(Bingo.chatPrefix + "Arena " + Integer.toString(id) + " has been deleted.");

		return;

	}

}
