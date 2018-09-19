/**
 * Die Klasse heißt: PlayerMoveListener.java
 * Die Klasse wurde am: 06.06.2017 | 11:52:32 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */
package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.LocationAPI;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		
		if(player.getLocation().distance(LocationAPI.getLocation("Belohnung2")) < 2) {
			if(!(Lobby.getInstance().editMode.contains(player))) {
				Vector v = new Vector(LocationAPI.getLocation("Spawn").getX() - player.getLocation().getX(), LocationAPI.getLocation("Spawn").getY() - player.getLocation().getY(), LocationAPI.getLocation("Spawn").getZ() - player.getLocation().getZ()).normalize().multiply(1.25).setY(0.5);
				player.setVelocity(v);
				player.playSound(player.getLocation(), Sound.WITHER_SHOOT, 0.5F, 0F);
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0));
			}
		}
		
		if(player.getLocation().distance(LocationAPI.getLocation("Shop")) < 2) {
			if(!(Lobby.getInstance().editMode.contains(player))) {
				Vector v = new Vector(LocationAPI.getLocation("Spawn").getX() - player.getLocation().getX(), LocationAPI.getLocation("Spawn").getY() - player.getLocation().getY(), LocationAPI.getLocation("Spawn").getZ() - player.getLocation().getZ()).normalize().multiply(1.25).setY(0.5);
				player.setVelocity(v);
				player.playSound(player.getLocation(), Sound.WITHER_SHOOT, 0.5F, 0F);
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0));
			}
		}
		
		if(player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.WATER | player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.STATIONARY_WATER | player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.WATER_LILY) {
			Vector v = player.getLocation().getDirection().setY(+ 2.5);
			player.setVelocity(v);
			player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1F, 1F);
		}
		
		if(player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.REDSTONE_BLOCK && player.getLocation().subtract(0, 0, 0).getBlock().getType() == Material.STONE_PLATE) {
			Vector v = player.getLocation().getDirection().setY(+ 1).multiply(1.5);
			player.setVelocity(v);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					Vector v = player.getLocation().getDirection().setY(+ 0).multiply(2);
					player.setVelocity(v);
				}
				
			}, 15);
			
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!(Lobby.getInstance().silentLobby.contains(all))) {
					if(all != player) {
						all.spigot().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1, 1, 0, 0, 0, (float) 0.1, 128, 16);
					}
				}
			}
			player.spigot().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1, 1, 0, 0, 0, (float) 0.1, 128, 16);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

				@Override
				public void run() {
					player.playSound(player.getLocation(), Sound.FIREWORK_LAUNCH, 1F, 0F);
				}
				
			}, 5);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

				@Override
				public void run() {
					player.playSound(player.getLocation(), Sound.IRONGOLEM_HIT, 2F, 0F);
				}
				
			}, 15);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

				@Override
				public void run() {
					player.playSound(player.getLocation(), Sound.SHEEP_SHEAR, 2F, 0F);
				}
				
			}, 20);
			
		}
		
	}

}
