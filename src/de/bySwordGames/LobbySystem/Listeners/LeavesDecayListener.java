/**
 * Die Klasse heißt: LeavesDecayListener.java
 * Die Klasse wurde am: 02.05.2017 | 10:24:28 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeavesDecayListener implements Listener {
	
	@EventHandler
	public void onLeavesDecayEvent(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

}
