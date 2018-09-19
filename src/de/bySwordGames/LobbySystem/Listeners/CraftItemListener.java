/**
 * Die Klasse heißt: CraftItemListener.java
 * Die Klasse wurde am: 07.05.2017 | 19:11:55 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class CraftItemListener implements Listener {
	
	@EventHandler
	public void onCraftItemEvent(CraftItemEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			Player player = (Player) e.getWhoClicked();
			
			if(!(Lobby.getInstance().editMode.contains(player))) {
				e.setCancelled(true);
			}
			
		}
	}

}
