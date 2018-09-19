/**
 * Die Klasse heißt: PlayerJoinLister.java
 * Die Klasse wurde am: 01.05.2017 | 22:59:16 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.ItemAPI;
import de.bySwordGames.LobbySystem.APIs.LocationAPI;
import de.bySwordGames.LobbySystem.APIs.ScoreboardAPI;
import de.bySwordGames.LobbySystem.MySQL.Spieler;

public class PlayerJoinListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		e.setJoinMessage(null);
		Spieler.createSystemPlayer(player.getUniqueId());
		Spieler.createPlayer(player.getUniqueId());
		Spieler.resetPlayer(player);
		Spieler.setPlayerVisibility(player);
		ScoreboardAPI.setAllPlayers();
		
		player.setMaxHealth(4);
		player.setHealth(4);
		player.setFoodLevel(20);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.setGameMode(GameMode.ADVENTURE);
		player.setLevel(0);
		player.setExp(0);
		player.spigot().setCollidesWithEntities(false);
		
		if(LocationAPI.doesLocationExists("Spawn")) {
			player.teleport(LocationAPI.getLocation("Spawn"));	
		}
		
		player.sendTitle("§8▻ §6§nSwordGamesNET§r §8◅", "§eHerzlich Willkommen!");
		player.playSound(player.getLocation(), Sound.SPLASH, 1F, 2F);
		
		
		if(Spieler.getModuleLongStatus(player.getUniqueId(), "Belohnung") < System.currentTimeMillis() | player.hasPermission("Server.alwaysReward")) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					player.sendTitle("§8▻ §a§nBelohnung§r §8◅", "§7Du kannst deine Belohnung abholen!");
				}
				
			}, 30);
		}
		
		ItemAPI.getLobbyItems(player);
	}

}
