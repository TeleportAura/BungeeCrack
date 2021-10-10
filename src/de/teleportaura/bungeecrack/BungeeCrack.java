package de.teleportaura.bungeecrack;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeCrack extends Plugin implements Listener{

	private static boolean onlineMode = false;

	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, this);
	}

	@EventHandler
	public void onPreLogin(PreLoginEvent e){
		e.getConnection().setOnlineMode(onlineMode);
	}

	@EventHandler
	public void onLogin(LoginEvent e){
		if(onlineMode || !BungeeCord.getInstance().config.isOnlineMode()) return;
		e.getConnection().setOnlineMode(true);
	}

	public static void setOnlineMode(boolean onlineMode) {
		BungeeCrack.onlineMode = onlineMode;
	}

	/** @return what online mode has been set to */
	public static boolean toggleOnlineMode(){
		onlineMode=!onlineMode;
		return onlineMode;
	}

}
