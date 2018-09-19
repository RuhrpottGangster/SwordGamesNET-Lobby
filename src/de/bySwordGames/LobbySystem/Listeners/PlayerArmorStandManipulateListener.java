/**
 * Die Klasse heißt: ArmorStandManipulateListener.java
 * Die Klasse wurde am: 02.05.2017 | 10:25:26 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

import de.bySwordGames.LobbySystem.Lobby;

public class PlayerArmorStandManipulateListener implements Listener {
	
	@EventHandler
	public void onPlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent e) {
		Player player = e.getPlayer();
		
		if(!Lobby.getInstance().editMode.contains(player) && (!(player.getGameMode() == GameMode.CREATIVE))) {
			e.setCancelled(true);
		}
		
	}

}
