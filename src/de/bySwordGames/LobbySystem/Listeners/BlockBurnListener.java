/**
 * Die Klasse heißt: BlockBurnListener.java
 * Die Klasse wurde am: 02.05.2017 | 10:23:47 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

public class BlockBurnListener implements Listener {
	
	@EventHandler
	public void onBlockBurnEvent(BlockBurnEvent e) {
		e.setCancelled(true);
	}

}
