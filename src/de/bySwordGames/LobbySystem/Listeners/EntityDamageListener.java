/**
 * Die Klasse heißt: EntityDamageListener.java
 * Die Klasse wurde am: 02.05.2017 | 14:18:12 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e) {
		try {
			if(e.getEntity() instanceof Player) {
				
				e.setCancelled(true);
				
			}
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}

}
