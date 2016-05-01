package de.lonzbonz.supportchat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerListener implements Listener {

	private me.styx.main.Main plugin;
	
	public PlayerListener(me.styx.main.Main main) {
		this.plugin = main;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(plugin.needHelp.contains(p.getName())) {
			plugin.needHelp.remove(p.getName());
		}
		if(plugin.supportChat.containsKey(p.getName())) {
			plugin.supportChat.remove(p.getName());
		}
		if(plugin.supportChat.containsValue(p.getName())) {
			plugin.supportChat.remove(p.getName());
		}
		if(p.hasPermission(plugin.supporterPermission)) {
			if(plugin.onlineSupporters.contains(p.getName())) {
				plugin.onlineSupporters.remove(p.getName());
			}
			e.setQuitMessage("§r[§bSupporter§r] §b" + p.getName() + " §eleft the server.");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission(plugin.supporterPermission)) {
			if(!plugin.onlineSupporters.contains(p.getName())) {
				plugin.onlineSupporters.add(p.getName());
			}
			e.setJoinMessage("§r[§bSupporter§r] §b" + p.getName() + " §ehas joined the server.");
		}
	}
}
