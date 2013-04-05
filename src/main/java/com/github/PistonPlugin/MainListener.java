package com.github.PistonPlugin;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class MainListener implements Listener {
	public MainListener(JavaPlugin plugin) {
		plugin.getLogger().info("Listener initializing...");
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST,ignoreCancelled=true)
	public void onPistonExtend(BlockPistonExtendEvent event) {
		if(event.getLength()==0) return;
		Block topBlock=event.getBlocks().get(event.getLength()-1);
		Vector addit=new Vector(event.getDirection().getModX()*2,event.getDirection().getModY()*2,event.getDirection().getModZ()*2);
		Location refBlockLoc=topBlock.getLocation().add(addit);
		Block refBlock=event.getBlock().getWorld().getBlockAt(refBlockLoc);
		if(refBlock.getTypeId()==Material.SIGN_POST.getId()||refBlock.getTypeId()==Material.WALL_SIGN.getId()) {
			Sign data=(Sign) refBlock.getState();
			if(data.getLine(0).intern()=="[Piston]"&&data.getLine(1).intern()=="break") {
				//FIXME: chunk not refreshed
				topBlock.breakNaturally();
				//topBlock.setTypeId(0);
				topBlock.getWorld().refreshChunk(topBlock.getChunk().getX(), topBlock.getChunk().getZ());
			}
		}

	}
}
