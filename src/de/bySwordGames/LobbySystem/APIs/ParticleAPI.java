/**
 * Die Klasse heißt: ParticleAPI.java
 * Die Klasse wurde am: 05.06.2017 | 20:56:43 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */
package de.bySwordGames.LobbySystem.APIs;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

import de.bySwordGames.LobbySystem.Lobby;

public class ParticleAPI {
	
	public static void startParticle() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobby.getInstance(), new Runnable() {

			@Override
			public void run() {
			
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(LocationAPI.doesLocationExists("SkyPvP")) {
						all.spigot().playEffect(LocationAPI.getLocation("SkyPvP"), Effect.INSTANT_SPELL, 1, 1, 0, 0, 0, (float) 0.1, 128, 16);	
					}
					if(LocationAPI.doesLocationExists("SurvivalGames")) {
						all.spigot().playEffect(LocationAPI.getLocation("SurvivalGames"), Effect.INSTANT_SPELL, 1, 1, 0, 0, 0, (float) 0.1, 128, 16);	
					}
					if(LocationAPI.doesLocationExists("Spawn")) {
						all.spigot().playEffect(LocationAPI.getLocation("Spawn"), Effect.FLAME, 1, 1, 0, 0, 0, (float) 0.1, 128, 16);	
					}
					if(LocationAPI.doesLocationExists("CoinShop")) {
						all.spigot().playEffect(LocationAPI.getLocation("CoinShop"), Effect.INSTANT_SPELL, 1, 1, 0, 0, 0, (float) 0.1, 128, 16);	
					}
					if(LocationAPI.doesLocationExists("Belohnung")) {
						all.spigot().playEffect(LocationAPI.getLocation("Belohnung"), Effect.INSTANT_SPELL, 1, 1, 0, 0, 0, (float) 0.1, 128, 16);	
					}
				}
				
			}
			
		}, 0, 40);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Lobby.getInstance(), new Runnable() {

			@Override
			public void run() {
			
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(LocationAPI.doesLocationExists("Belohnung2")) {
						all.spigot().playEffect(LocationAPI.getLocation("Belohnung2"), Effect.LAVA_POP, 1, 1, 0, 1, 0, (float) 0.1, 10, 16);	
					}
				}
				
			}
			
		}, 0, 20 * 1);
		
	}

}
