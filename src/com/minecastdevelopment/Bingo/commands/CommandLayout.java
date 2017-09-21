package com.minecastdevelopment.Bingo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CommandLayout {

	public abstract void executeCommand(CommandSender sender, Command cmd, String[] args);
}
