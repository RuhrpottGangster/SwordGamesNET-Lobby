/**
 * Die Klasse heißt: HangingBreakListener.java
 * Die Klasse wurde am: 06.06.2017 | 11:13:45 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */
package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class HangingBreakListener implements Listener {
	
	@EventHandler
	public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent e) {
		
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			
			if(!(Lobby.getInstance().editMode.contains(player) && player.getGameMode() == GameMode.CREATIVE)) {
				e.setCancelled(true);
			}
			
		}
		
	}

}
