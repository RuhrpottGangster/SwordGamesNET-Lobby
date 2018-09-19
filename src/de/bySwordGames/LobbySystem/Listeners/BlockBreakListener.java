/**
 * Die Klasse heißt: BlockBreakListener.java
 * Die Klasse wurde am: 02.05.2017 | 10:22:06 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class BlockBreakListener implements Listener {
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		Player player = e.getPlayer();
		
		if(!(Lobby.getInstance().editMode.contains(player) && player.getGameMode() == GameMode.CREATIVE)) {
			e.setCancelled(true);
		}
		
	}

}
