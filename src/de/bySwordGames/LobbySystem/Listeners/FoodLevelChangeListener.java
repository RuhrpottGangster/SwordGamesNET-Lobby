/**
 * Die Klasse heißt: FoodLevelChangeListener.java
 * Die Klasse wurde am: 02.05.2017 | 14:23:22 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {
	
	@EventHandler
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

}
