/**
 * Die Klasse heißt: PlayerInteractEntityListener.java
 * Die Klasse wurde am: 02.05.2017 | 14:31:40 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.LocationAPI;
import de.bySwordGames.LobbySystem.APIs.ScoreboardAPI;
import de.bySwordGames.LobbySystem.MySQL.Spieler;

public class PlayerInteractEntityListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		
		if(e.getRightClicked().getType() == EntityType.ITEM_FRAME | e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
			if(!(Lobby.getInstance().editMode.contains(player))) {
				e.setCancelled(true);
			}
		}
		
		if (player.getItemInHand().getType() == Material.NAME_TAG | player.getItemInHand().getType() == Material.SADDLE) {
			e.setCancelled(true);
			player.updateInventory();
		}
		
		if(e.getRightClicked().getType() == EntityType.VILLAGER) {
			if(e.getRightClicked().getCustomName().equalsIgnoreCase("§6§lShop")) {
				player.openInventory(Lobby.getInstance().shop);
				player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1F, 2F);
				e.setCancelled(true);
			}
		}
		
		if(e.getRightClicked().getType() == EntityType.IRON_GOLEM) {
			e.setCancelled(true);
			if(Spieler.getModuleLongStatus(player.getUniqueId(), "Belohnung") < System.currentTimeMillis() | player.hasPermission("Server.alwaysReward")) {
				Spieler.setModuleStatus(player.getUniqueId(), "Belohnung", System.currentTimeMillis() + Long.valueOf(1) * 1000 * 60 * 60 * 24); 
				
				int coins = (int) (Math.random()*491+10);
				
				Spieler.setCoins(player.getUniqueId(), Spieler.getCoins(player.getUniqueId()) + coins);
				player.sendMessage("§8§l┃ §aBelohnung §8► §7Du hast deine tägliche Belohnung abgeholt!");
				
				if(coins == 500) {
					player.sendMessage("§8  ➟ §a+ §4" + coins + "§r§6✪");
					player.sendTitle("§8▻ §a§nBelohnung§r §8◅", "§7Du hast §4" + coins + " §7Coins erhalten.");
				}
				else {
					player.sendMessage("§8  ➟ §a+ §e" + coins + "§6✪");
					player.sendTitle("§8▻ §a§nBelohnung§r §8◅", "§7Du hast §e" + coins + " §7Coins erhalten.");
				}
				ScoreboardAPI.setScorebaord(player);
				
				if(coins == 500) {
					int bonus = 100;
					
					Bukkit.broadcastMessage("§8§l┃ §aBelohnung §8► " + player.getDisplayName() + "§7 hat §e" + coins + "§6✪ §7gewonnen!");
					Bukkit.broadcastMessage("§8§l┃ §aBelohnung §8► " + "§aAlle bekommen §e" + bonus + "§6✪ §aals Bonus!");
					
					for(Player all : Bukkit.getOnlinePlayers()) {
						if(all != player) {
							Spieler.setCoins(all.getUniqueId(), Spieler.getCoins(all.getUniqueId()) + bonus);
						}
						all.playSound(all.getLocation(), Sound.ENDERDRAGON_DEATH, 1F, 1F);
					}
					
//					Bukkit.getWorld(player.getWorld().getName()).strikeLightningEffect(player.getLocation());
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							for(Player all : Bukkit.getOnlinePlayers()) all.playSound(e.getRightClicked().getLocation(), Sound.LEVEL_UP, 1F, 0F);
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(LocationAPI.doesLocationExists("Belohnung2")) {
									all.spigot().playEffect(LocationAPI.getLocation("Belohnung2"), Effect.LAVA_POP, 1, 1, 0, 2, 0, (float) 0.5, 128, 24);	
								}
							}
						}
						
					}, 2);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							for(Player all : Bukkit.getOnlinePlayers()) all.playSound(e.getRightClicked().getLocation(), Sound.LEVEL_UP, 1F, 0F);
						}
						
					}, 4);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							for(Player all : Bukkit.getOnlinePlayers()) all.playSound(e.getRightClicked().getLocation(), Sound.LEVEL_UP, 1F, 0F);
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(LocationAPI.doesLocationExists("Belohnung2")) {
									all.spigot().playEffect(LocationAPI.getLocation("Belohnung2"), Effect.LAVA_POP, 1, 1, 0, 2, 0, (float) 0.5, 128, 24);	
								}
							}
						}
						
					}, 6);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							for(Player all : Bukkit.getOnlinePlayers()) all.playSound(e.getRightClicked().getLocation(), Sound.LEVEL_UP, 1F, 0F);
						}
						
					}, 8);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(LocationAPI.doesLocationExists("Belohnung2")) {
									all.spigot().playEffect(LocationAPI.getLocation("Belohnung2"), Effect.LAVA_POP, 1, 1, 0, 2, 0, (float) 0.5, 128, 24);	
								}
							}
							
					        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
					        FireworkMeta fwmeta = fw.getFireworkMeta();
					        FireworkEffect.Builder fwbuilder = FireworkEffect.builder();
					        fwbuilder.withTrail();
					        fwbuilder.withFlicker();
					        fwbuilder.withFade(Color.ORANGE);
					        fwbuilder.withColor(Color.YELLOW);
					        fwbuilder.withColor(Color.RED);
					        fwbuilder.with(FireworkEffect.Type.BALL_LARGE);
					        fwmeta.addEffects(fwbuilder.build());
					        fwmeta.setPower(1);
					        fw.setFireworkMeta(fwmeta);
						}
						
					}, 20);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
						
						@Override
						public void run() {
					        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
					        FireworkMeta fwmeta = fw.getFireworkMeta();
					        FireworkEffect.Builder fwbuilder = FireworkEffect.builder();
					        fwbuilder.withTrail();
					        fwbuilder.withFlicker();
					        fwbuilder.withFade(Color.ORANGE);
					        fwbuilder.withColor(Color.YELLOW);
					        fwbuilder.withColor(Color.RED);
					        fwbuilder.with(FireworkEffect.Type.STAR);
					        fwmeta.addEffects(fwbuilder.build());
					        fwmeta.setPower(1);
					        fw.setFireworkMeta(fwmeta);
						}
						
					}, 40);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
						
						@Override
						public void run() {
					        Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
					        FireworkMeta fwmeta = fw.getFireworkMeta();
					        FireworkEffect.Builder fwbuilder = FireworkEffect.builder();
					        fwbuilder.withTrail();
					        fwbuilder.withFlicker();
					        fwbuilder.withFade(Color.ORANGE);
					        fwbuilder.withColor(Color.YELLOW);
					        fwbuilder.withColor(Color.RED);
					        fwbuilder.with(FireworkEffect.Type.CREEPER);
					        fwmeta.addEffects(fwbuilder.build());
					        fwmeta.setPower(1);
					        fw.setFireworkMeta(fwmeta);
						}
						
					}, 60);
					
				}
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

					@Override
					public void run() {
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 5F);
					}
					
				}, 5);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

					@Override
					public void run() {
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 4F);
					}
					
				}, 10);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

					@Override
					public void run() {
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 3F);
					}
					
				}, 20);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

					@Override
					public void run() {
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 2F);
					}
					
				}, 30);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

					@Override
					public void run() {
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
					}
					
				}, 40);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

					@Override
					public void run() {
						player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 0F);
					}
					
				}, 50);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {

					@Override
					public void run() {
						player.playSound(player.getLocation(), Sound.EXPLODE, 0.5F, 2F);
					}
					
				}, 60);
				
			} else {
				
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(Spieler.getModuleLongStatus(player.getUniqueId(), "Belohnung"));
				Date date = cal.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				String dateString = sdf.format(date);
				
//				String[] split = dateString.split(" ");
//				String day = split[0];
//				String time = split[1];
//				player.sendMessage("§8§l┃ §aBelohnung §8► §cDu kannst deine Belohnung erst am §e" + day + "§c um §e" + time + "§c abholen!");
				
				player.sendMessage("§8§l┃ §aBelohnung §8► §cDeine nächste Belohnung §8» §e" + dateString);
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5F, 1F);
				
			}
		}
		
	}

}
