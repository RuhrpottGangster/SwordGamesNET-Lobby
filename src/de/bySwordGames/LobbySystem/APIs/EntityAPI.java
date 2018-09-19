/**
 * Die Klasse heißt: EntityAPI.java
 * Die Klasse wurde am: 02.05.2017 | 23:24:34 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.APIs;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.bySwordGames.LobbySystem.Files;

public class EntityAPI {
	
	public static void removeShop() {
		if(LocationAPI.doesLocationExists("Shop")) {
			for(World world : Bukkit.getWorlds()) {
				for(Entity entitys : world.getEntities()) {
					if(entitys instanceof Villager) {
						if(entitys.getCustomName().equalsIgnoreCase("§6§lShop")) {
							entitys.remove();
						}
					}
				}
			}
		}
	}
	
	public static void spawnShop() {
		if(LocationAPI.doesLocationExists("Shop")) {
			Villager v = (Villager) Bukkit.getWorld(Files.cfg.getString("Position.Shop.Welt")).spawnEntity(LocationAPI.getLocation("Shop"), EntityType.VILLAGER);
			v.setCustomName("§6§lShop");
			v.setCustomNameVisible(true);
			v.setRemoveWhenFarAway(false);
			v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));
			v.setNoDamageTicks(Integer.MAX_VALUE);	
		}
	}
	
	public static void removeReward() {
		if(LocationAPI.doesLocationExists("Belohnung2")) {
			for(World world : Bukkit.getWorlds()) {
				for(Entity entitys : world.getEntities()) {
					if(entitys instanceof IronGolem) {
						if(entitys.getCustomName().equalsIgnoreCase("§a§lBelohnung")) {
							entitys.remove();
						}
					}
				}
			}
		}
	}
	
	public static void spawnReward() {
		if(LocationAPI.doesLocationExists("Belohnung2")) {
			IronGolem golem = (IronGolem) Bukkit.getWorld(Files.cfg.getString("Position.Belohnung2.Welt")).spawnEntity(LocationAPI.getLocation("Belohnung2"), EntityType.IRON_GOLEM);
			golem.setCustomName("§a§lBelohnung");
			golem.setCustomNameVisible(true);
			golem.setRemoveWhenFarAway(false);
			golem.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));
			golem.setNoDamageTicks(Integer.MAX_VALUE);	
		}
	}

}
