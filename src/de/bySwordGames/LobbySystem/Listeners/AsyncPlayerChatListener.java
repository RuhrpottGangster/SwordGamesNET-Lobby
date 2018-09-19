/**
 * Die Klasse heißt: AsyncPlayerChatListener.java
 * Die Klasse wurde am: 02.05.2017 | 12:33:30 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class AsyncPlayerChatListener implements Listener {
	
	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String message = e.getMessage().replaceAll("%", "%%");
		
		if(player.hasPermission("Server.Administrator") | player.hasPermission("Server.Head-Developer") | player.hasPermission("Server.Developer") | 
				player.hasPermission("Server.Content") | player.hasPermission("Server.Head-Moderator") | player.hasPermission("Server.Moderator") | 
				player.hasPermission("Server.Supporter") | player.hasPermission("Server.Builder")) {
			message = ChatColor.translateAlternateColorCodes('&', message);
		}
		
		if(Lobby.getInstance().silentLobby.contains(player)) {
			player.sendMessage(Lobby.prefix + "§cDu darfst in der SilentLobby nichts schreiben.");
			e.setCancelled(true);
		}
		
		for (Player all : Lobby.getInstance().silentLobby) {
			e.getRecipients().remove(all);
		}
		
		if(player.hasPermission("Server.Administrator")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §4Administrator §8► §4" + player.getName() + "§8 » §6" + message);
		} else if(player.hasPermission("Server.Head-Developer")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §bHead-Developer §8► §b" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Developer")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §bDeveloper §8► §b" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Content")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §bContent §8► §b" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Head-Moderator")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §cHead-Moderator §8► §c" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Moderator")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §cModerator §8► §c" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Supporter")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §eSupporter §8► §e" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Builder")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §2Builder §8► §2" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Youtuber")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §5Youtuber §8► §5" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.PremiumPlus")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §6Premium §8► §6" + player.getName() + "§8 » §7" + message);
		} else if(player.hasPermission("Server.Premium")) {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §6Premium §8► §6" + player.getName() + "§8 » §7" + message);
		} else {
			player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1F, 1F);
			e.setFormat("§8§l┃ §9Spieler §8► §9" + player.getName() + "§8 » §7" + message);
		}
		
	}

}
