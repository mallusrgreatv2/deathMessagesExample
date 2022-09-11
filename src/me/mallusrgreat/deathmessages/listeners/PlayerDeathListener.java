package me.mallusrgreat.deathmessages.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.mallusrgreat.deathmessages.Main;
import me.mallusrgreat.deathmessages.utils.Utils;

public class PlayerDeathListener implements Listener{
	private Main plugin;
	public PlayerDeathListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			Player k = e.getEntity().getKiller();
			Player p = e.getEntity();
			String killerhealth = String.valueOf(k.getHealth() / 2);
			String playersend = Utils.chat(plugin.getConfig().getString("killed_message").replace("<killer>", k.getName()).replace("<hearts>", killerhealth));
			String killersend = Utils.chat(plugin.getConfig().getString("killer_message").replace("<player>", p.getName()));
			Bukkit.broadcastMessage(Utils.chat(plugin.getConfig().getString("player_death_message").replace("<player>", p.getName()).replace("<killer>", k.getName())));
			k.sendMessage(killersend);
			p.sendMessage(playersend);
		}
		return;
	}
	
}
