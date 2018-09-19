/**
 * Die Klasse heißt: HangingPlaceListener.java
 * Die Klasse wurde am: 06.06.2017 | 11:12:57 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */
package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class HangingPlaceListener implements Listener {
	
	@EventHandler
	public void onHangingPlaceEvent(HangingPlaceEvent e) {
		Player player = e.getPlayer();
		
		if(!(Lobby.getInstance().editMode.contains(player) && player.getGameMode() == GameMode.CREATIVE)) {
			e.setCancelled(true);
		}
		
	}

}
