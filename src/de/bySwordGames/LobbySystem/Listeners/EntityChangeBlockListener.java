/**
 * Die Klasse heißt: EntityChangeByEntityListener.java
 * Die Klasse wurde am: 02.05.2017 | 14:33:44 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlockListener implements Listener {
	
	@EventHandler
	public void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {
		e.setCancelled(true);
	}

}
