/**
 * Die Klasse heißt: PlayerCommandPreprocessListener.java
 * Die Klasse wurde am: 05.06.2017 | 17:23:20 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */
package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

import de.bySwordGames.LobbySystem.Lobby;

public class PlayerCommandPreprocessListener implements Listener {
	
	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		String message = e.getMessage().split(" ")[0];
		HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(message);
		
		if(!(player.hasPermission("Server.Administrator"))) {
			if(message.startsWith("/minecraft") && message.contains(":")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.noPermissions);
			}
			if(message.startsWith("/bukkit") && message.contains(":")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.noPermissions);
			}
			if(message.equalsIgnoreCase("/version")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.noPermissions);
			}
			if(message.equalsIgnoreCase("/help")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.noPermissions);
			}
			if(message.equalsIgnoreCase("/pl")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.noPermissions);
			}
			if(message.equalsIgnoreCase("/plugins")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.noPermissions);
			}
			if(message.equalsIgnoreCase("/pex")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
			if(message.equalsIgnoreCase("/promote")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
			if(message.equalsIgnoreCase("/demote")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
			if(message.equalsIgnoreCase("/nocheatplus")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
			if(message.equalsIgnoreCase("/ncp")) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
		}
		if(!(e.isCancelled())) {
			if(topic == null) {
				e.setCancelled(true);
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
		}
		
	}

}
