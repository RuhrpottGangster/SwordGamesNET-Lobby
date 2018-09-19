/**
 * Die Klasse heißt: ProjectileLaunchListener.java
 * Die Klasse wurde am: 07.06.2017 | 22:00:22 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */
package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileLaunchListener implements Listener {
	
	@EventHandler
	public void onProjectileLaunchEvent(ProjectileLaunchEvent e) {
		LivingEntity entity = (LivingEntity) e.getEntity();
		
		if(entity instanceof Blaze) {
			e.setCancelled(true);
			return;
		}
		
	}

}
