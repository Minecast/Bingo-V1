package com.minecastdevelopment.Bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minecastdevelopment.Bingo.Bingo;

public class BingoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Bingo.chatPrefix + "You must be a player to execute this command");
			return false;
		}

		Player player = (Player) sender;

		if (args.length == 0) {
			new HelpCommand().executeCommand(sender, cmd, args);
			return true;
		} else if (args[0].toLowerCase() == "help") {
			new HelpCommand().executeCommand(sender, cmd, args);
			return true;
		} else if (args[0].toLowerCase().equals("create")) {
			if (player.hasPermission("Bingo.create") || player.hasPermission("Bingo.op")) {
				new Create().executeCommand(sender, cmd, args);
			} else {
				new Blacklist().executeCommand(sender, cmd, args);
			}			
			return true;
		} else if (args[0].toLowerCase().equals("join")) {
			new Join().executeCommand(sender, cmd, args);
			return true;
		} else if (args[0].toLowerCase().equals("leave")) {
			new Leave().executeCommand(sender, cmd, args);
			return true;
		} else if (args[0].toLowerCase().equals("map")) {
			new Map().executeCommand(sender, cmd, args);
			return true;
		} else if (args[0].toLowerCase().equals("blacklist")) {
			if (player.hasPermission("Bingo.blacklist") || player.hasPermission("Bingo.op")) {
				new Blacklist().executeCommand(sender, cmd, args);
			} else {
				player.sendMessage(Bingo.chatPrefix + "You do not have permission to do that!");
			}			
			return true;
		} else if (args[0].toLowerCase().equals("getid")) {
			if (player.hasPermission("Bingo.getid") || player.hasPermission("Bingo.op")) {
				new GetMaterial().executeCommand(sender, cmd, args);
			} else {
				player.sendMessage(Bingo.chatPrefix + "You do not have permission to do that!");
			}			
			return true;
		} else if (args[0].toLowerCase().equals("remove")) {
			if (player.hasPermission("Bingo.remove") || player.hasPermission("Bingo.op")) {
				new Remove().executeCommand(sender, cmd, args);
			} else {
				player.sendMessage(Bingo.chatPrefix + "You do not have permission to do that!");
			}
			return true;
		}else if(args[0].toLowerCase().equals("info")){
			new Info().executeCommand(sender, cmd, args);
			return true;
		}else if(args[0].toLowerCase().equals("author")){
			new Author().executeCommand(sender, cmd, args);
			return true;
		}
		else {
		}
			new HelpCommand().executeCommand(sender, cmd, args);
			return true;
		}

}
