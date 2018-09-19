/**
 * Die Klasse heißt: PlayerAchievementAwardedListener.java
 * Die Klasse wurde am: 07.05.2017 | 19:10:57 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class PlayerAchievementAwardedListener implements Listener {
	
	@EventHandler
	public void onPlayerAchievmentAdwardedEvent(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}

}
