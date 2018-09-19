/**
 * Die Klasse heißt: ItemDropListener.java
 * Die Klasse wurde am: 02.05.2017 | 14:53:10 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class PlayerDropItemListener implements Listener {
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		
		if(!(Lobby.getInstance().editMode.contains(player))) {
			e.setCancelled(true);
		}
		
	}

}
