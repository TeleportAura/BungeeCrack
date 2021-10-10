package de.teleportaura.bungeecrack;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCrackCommand extends Command {
	
	public BungeeCrackCommand() {
		super("bungeecrack");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender.hasPermission("bungeecrack")) {
			try {
				sender.sendMessages("Online mode has been set to " + BungeeCrack.toggleOnlineMode() + "!");
			} catch (Throwable t) {
				t.printStackTrace();
				sender.sendMessages("§cAn error occured while trying to execute this command. \n"
						+ "Please check the console/log for details!");
			}
		}
	}

}
