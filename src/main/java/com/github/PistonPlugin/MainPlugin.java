package com.github.PistonPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {
	MainListener listener;
	
	@Override
	public void onEnable() {
		listener=new MainListener(this);
		getLogger().info("Piston Plugin has been enabled");
	}
	
	@Override
	public void onDisable() {
		listener=null;
		getLogger().info("Piston Plugin has been disabled");
	}
}
