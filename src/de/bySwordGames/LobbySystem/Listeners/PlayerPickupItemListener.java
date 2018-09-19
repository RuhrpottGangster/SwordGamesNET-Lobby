/**
 * Die Klasse heißt: PlayerPickupItemListener.java
 * Die Klasse wurde am: 02.05.2017 | 14:54:31 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class PlayerPickupItemListener implements Listener {
	
	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent e) {
		Player player = e.getPlayer();
		
		if(!(Lobby.getInstance().editMode.contains(player))) {
			e.setCancelled(true);
		}
		
	}

}
