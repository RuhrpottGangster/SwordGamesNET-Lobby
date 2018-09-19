/**
 * Die Klasse heißt: PlayerInteractListener.java
 * Die Klasse wurde am: 02.05.2017 | 10:28:53 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.ItemAPI;
import de.bySwordGames.LobbySystem.MySQL.Spieler;

public class PlayerInteractListener implements Listener { 
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		try {
			Player player = e.getPlayer();
			if(!(Lobby.getInstance().editMode.contains(player))) {
				if(e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					
					if(player.getItemInHand().getType() == Material.COMPASS) {
						player.playSound(player.getLocation(), Sound.CLICK, 2F, 3F);
						
						Inventory tp = Bukkit.createInventory(null, 9*6, "§8➥ §aWähle einen Spielmodus");
						
						for(int i = 0; i < tp.getSize(); i++) {
							tp.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
							
						}
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								tp.setItem(9*2+4, ItemAPI.createItemWithLore(Material.MAGMA_CREAM, 0, 1, "§8» §6Spawn §8┃ §7Unser Lobbyspawn", "§8➥ §7Klicke um zum §eSpawn §7zu teleportieren."));
								player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1F, 1F);
							}
							
						}, 5);
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								tp.setItem(9*4+3, ItemAPI.createItemWithLore(Material.GOLD_INGOT, 0, 1, "§8» §bCoin Shop §8┃ §7Lobby Shop", "§8➥ §7Klicke um zum §eCoinShop §7zu teleportieren."));
								player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1F, 1F);
							}
							
						}, 7);
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								tp.setItem(9*4+5, ItemAPI.createItemWithLore(Material.BOOK, 0, 1, "§8» §aTägliche Belohnung §8┃ §7Lobby Belohnung", "§8➥ §7Klicke um zur §eBelohnung §7zu teleportieren."));
								player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1F, 1F);
							}
							
						}, 9);
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								tp.setItem(9*1+2, ItemAPI.createItemWithLore(Material.BOW, 0, 1, "§8» §9SkyPvP §8┃ §7PvP",  "§8➥ §7Klicke um zu §eSkyPvP §7zu teleportieren."));
								player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1F, 1F);
							}
							
						}, 11);
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(Lobby.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								tp.setItem(9*1+6, ItemAPI.createItemWithLore(Material.IRON_SWORD, 0, 1, "§8» §cSurvival Games §8┃ §7PvP",  "§8➥ §7Klicke um zu §eSurvivalGames §7zu teleportieren."));
								player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1F, 1F);
							}
							
						}, 13);
						
						player.openInventory(tp);
						e.setCancelled(true);
					}
					if(player.getItemInHand().getType() == Material.INK_SACK) {
						if(!(Lobby.getInstance().visibilityChangeCooldown.contains(player))) {
							Lobby.getInstance().visibilityChangeCooldown.add(player);
							if(Spieler.getModuleStatus(player.getUniqueId(), "Spieler") == 2) {
								player.playSound(player.getLocation(), Sound.LEVEL_UP, 2F, 3F);
								Spieler.setModuleStatus(player.getUniqueId(), "Spieler", 1);
								player.getInventory().setItem(1, ItemAPI.createItem(Material.INK_SACK, 5, 1, "§9Sichtbarkeit §8» §5Nur VIPs"));
							} else if(Spieler.getModuleStatus(player.getUniqueId(), "Spieler") == 1) {
								player.playSound(player.getLocation(), Sound.LEVEL_UP, 2F, 3F);
								Spieler.setModuleStatus(player.getUniqueId(), "Spieler", 0);
								player.getInventory().setItem(1, ItemAPI.createItem(Material.INK_SACK, 1, 1, "§9Sichtbarkeit §8» §cDeaktiviert"));
							} else if(Spieler.getModuleStatus(player.getUniqueId(), "Spieler") == 0) {
								player.playSound(player.getLocation(), Sound.LEVEL_UP, 2F, 3F);
								Spieler.setModuleStatus(player.getUniqueId(), "Spieler", 2);
								player.getInventory().setItem(1, ItemAPI.createItem(Material.INK_SACK, 10, 1, "§9Sichtbarkeit §8» §aAktiviert"));
							}
							Spieler.setPlayerVisibility(player);
							Bukkit.getScheduler().scheduleAsyncDelayedTask(Lobby.getInstance(), new Runnable() {
								@Override
								public void run() {
									Lobby.getInstance().visibilityChangeCooldown.remove(player);
								}
							}, 20 * 1);
							e.setCancelled(true);
						}
						e.setCancelled(true);
					}
					if(player.getItemInHand().getType() == Material.NAME_TAG && player.hasPermission("Server.PremiumPlus")) {
						if(!(Lobby.getInstance().nickChangeCooldown.contains(player))) {
							Lobby.getInstance().nickChangeCooldown.add(player);
							if(Spieler.getModuleStatus(player.getUniqueId(), "AutoNick") == 1) {
								player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2F, 3F);
								Spieler.setModuleStatus(player.getUniqueId(), "AutoNick", 0);
								
								if(player.hasPermission("Server.Youtuber")) {
									player.getInventory().setItem(2, ItemAPI.createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §cDeaktiviert"));
								} else {
									player.getInventory().setItem(4, ItemAPI.createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §cDeaktiviert"));
								}
								
							} else if(Spieler.getModuleStatus(player.getUniqueId(), "AutoNick") == 0) {
								player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2F, 3F);
								Spieler.setModuleStatus(player.getUniqueId(), "AutoNick", 1);
								
								if(player.hasPermission("Server.Youtuber")) {
									player.getInventory().setItem(2, ItemAPI.createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §aAktiviert"));
								} else {
									player.getInventory().setItem(4, ItemAPI.createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §aAktiviert"));
								}
								
							}
							Bukkit.getScheduler().scheduleAsyncDelayedTask(Lobby.getInstance(), new Runnable() {
								@Override
								public void run() {
									Lobby.getInstance().nickChangeCooldown.remove(player);
								}
							}, 20 * 1);
							e.setCancelled(true);
						}
						e.setCancelled(true);
					}
					if(player.getItemInHand().getType() == Material.TNT && player.hasPermission("Server.Youtuber")) {
						if(!(Lobby.getInstance().silentlobbyChangeCooldown.contains(player))) {
							Lobby.getInstance().silentlobbyChangeCooldown.add(player);
							if(Spieler.getModuleStatus(player.getUniqueId(), "SilentLobby") == 1) {
								player.playSound(player.getLocation(), Sound.EXPLODE, 0.5F, 5F);
								Spieler.setModuleStatus(player.getUniqueId(), "SilentLobby", 0);
								Lobby.getInstance().silentLobby.remove(player);
								player.getInventory().setItem(6, ItemAPI.createItem(Material.TNT, 0, 1, "§cSilentLobby §8» §cDeaktiviert"));
								for(Player all : Bukkit.getOnlinePlayers()) {
									if(Spieler.getModuleStatus(all.getUniqueId(), "SilentLobby") == 0) {
										if(Spieler.getModuleStatus(all.getUniqueId(), "Spieler") == 2 | Spieler.getModuleStatus(all.getUniqueId(), "Spieler") == 1) {
											all.showPlayer(player);
										}
										player.showPlayer(all);
									}
								}
							} else if(Spieler.getModuleStatus(player.getUniqueId(), "SilentLobby") == 0) {
								player.playSound(player.getLocation(), Sound.EXPLODE, 0.5F, 5F);
								Spieler.setModuleStatus(player.getUniqueId(), "SilentLobby", 1);
								Lobby.getInstance().silentLobby.add(player);
								player.getInventory().setItem(6, ItemAPI.createItem(Material.TNT, 0, 1, "§cSilentLobby §8» §aAktiviert"));
								for(Player all : Bukkit.getOnlinePlayers()) {
									all.hidePlayer(player);
									player.hidePlayer(all);
								}
							}
						}
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Lobby.getInstance(), new Runnable() {
							@Override
							public void run() {
								Lobby.getInstance().silentlobbyChangeCooldown.remove(player);
							}
						}, 20 * 1);
						e.setCancelled(true);
					}
					if(player.getItemInHand().getType() == Material.SKULL_ITEM) {
						player.playSound(player.getLocation(), Sound.VILLAGER_HAGGLE, 1F, 3F);
						player.sendMessage(Lobby.prefix + "§cDas Freunde-System ist derzeit noch in programmierung.");
						e.setCancelled(true);
					}
					if(player.getItemInHand().getType() == Material.NETHER_STAR) {
						player.playSound(player.getLocation(), Sound.LAVA_POP, 1F, 3F);
						player.openInventory(Lobby.getInstance().menu);
						e.setCancelled(true);
					}
					
				}
				
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					
					if(e.getClickedBlock().getType() == Material.WOODEN_DOOR | e.getClickedBlock().getType() == Material.ACACIA_DOOR | e.getClickedBlock().getType() == Material.BIRCH_DOOR | e.getClickedBlock().getType() == Material.DARK_OAK_DOOR | e.getClickedBlock().getType() == Material.JUNGLE_DOOR | e.getClickedBlock().getType() == Material.SPRUCE_DOOR | e.getClickedBlock().getType() == Material.TRAP_DOOR | e.getClickedBlock().getType() == Material.FENCE_GATE | e.getClickedBlock().getType() == Material.ACACIA_FENCE_GATE | e.getClickedBlock().getType() == Material.BIRCH_FENCE_GATE | e.getClickedBlock().getType() == Material.DARK_OAK_FENCE_GATE | e.getClickedBlock().getType() == Material.JUNGLE_FENCE_GATE | e.getClickedBlock().getType() == Material.SPRUCE_FENCE_GATE | e.getClickedBlock().getType() == Material.FENCE | e.getClickedBlock().getType() == Material.ACACIA_FENCE | e.getClickedBlock().getType() == Material.BIRCH_FENCE | e.getClickedBlock().getType() == Material.DARK_OAK_FENCE | e.getClickedBlock().getType() == Material.JUNGLE_FENCE | e.getClickedBlock().getType() == Material.NETHER_FENCE | e.getClickedBlock().getType() == Material.SPRUCE_FENCE | e.getClickedBlock().getType() == Material.WOOD_BUTTON | e.getClickedBlock().getType() == Material.STONE_BUTTON | e.getClickedBlock().getType() == Material.LEVER | e.getClickedBlock().getType() == Material.CHEST | e.getClickedBlock().getType() == Material.TRAPPED_CHEST | e.getClickedBlock().getType() == Material.ENDER_CHEST | e.getClickedBlock().getType() == Material.NOTE_BLOCK | e.getClickedBlock().getType() == Material.JUKEBOX | e.getClickedBlock().getType() == Material.BED_BLOCK | e.getClickedBlock().getType() == Material.ANVIL | e.getClickedBlock().getType() == Material.FURNACE | e.getClickedBlock().getType() == Material.BURNING_FURNACE | e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE | e.getClickedBlock().getType() == Material.WORKBENCH | e.getClickedBlock().getType() == Material.DROPPER | e.getClickedBlock().getType() == Material.DISPENSER | e.getClickedBlock().getType() == Material.BREWING_STAND | e.getClickedBlock().getType() == Material.BEACON | e.getClickedBlock().getType() == Material.HOPPER | e.getClickedBlock().getType() == Material.REDSTONE_COMPARATOR_ON | e.getClickedBlock().getType() == Material.REDSTONE_COMPARATOR_OFF | e.getClickedBlock().getType() == Material.DIODE_BLOCK_ON | e.getClickedBlock().getType() == Material.DIODE_BLOCK_OFF | e.getClickedBlock().getType() == Material.DAYLIGHT_DETECTOR | e.getClickedBlock().getType() == Material.DAYLIGHT_DETECTOR_INVERTED) {
						if(!(Lobby.getInstance().editMode.contains(player))) {
							e.setCancelled(true);
						}
					}
					
				}
				
			}
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}

}
