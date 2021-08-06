package de.teleportaura.bungeecrack;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.conf.Configuration;

public class BungeeCrack extends Plugin {

	public void onEnable() {
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new BungeeCrackCommand());

		BungeeCord.getInstance().getScheduler().schedule(this, new Runnable() {

			@Override
			public void run() {
				BungeeCrack.setOnlineMode(false);
			}
		}, 1, TimeUnit.SECONDS);

	}

	public static void setOnlineMode(boolean onlineMode) {
		try {
			if (BungeeCord.getInstance().config.isOnlineMode() == onlineMode)
				return;
			toggleOnlineMode();
		} catch (Throwable t) {
		}
	}

	/** @return what online mode has been set to */
	@SuppressWarnings("unchecked")
	public static boolean toggleOnlineMode() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Configuration cfg = BungeeCord.getInstance().config;

		Class<Configuration> cfgClazz = (Class<Configuration>) cfg.getClass();
		while (cfgClazz == null || !cfgClazz.getName().equals(Configuration.class.getName())) {
			if (cfgClazz.getSuperclass() != null) {
				cfgClazz = (Class<Configuration>) cfgClazz.getSuperclass();
			} else {
				System.out.println("Where tf are you trying to run me???");
				System.exit(1337);
			}
		}

		Field f = cfgClazz.getDeclaredField("onlineMode");
		f.setAccessible(true);
		boolean newVal = !f.getBoolean(cfg);
		f.setBoolean(cfg, newVal);
		return newVal;
	}

}
