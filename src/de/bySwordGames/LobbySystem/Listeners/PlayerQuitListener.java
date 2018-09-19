/**
 * Die Klasse heißt: PlayerQuitLister.java
 * Die Klasse wurde am: 02.05.2017 | 10:11:50 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.ScoreboardAPI;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		e.setQuitMessage(null);
		ScoreboardAPI.setAllPlayers();
		
		if(Lobby.getInstance().silentLobby.contains(player)) {
			Lobby.getInstance().silentLobby.remove(player);
		}
		if(Lobby.getInstance().editMode.contains(player)) {
			Lobby.getInstance().editMode.remove(player);
		}
		
	}

}
