/**
 * Die Klasse heißt: InventoryClickListener.java
 * Die Klasse wurde am: 02.05.2017 | 13:06:38 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.ItemAPI;
import de.bySwordGames.LobbySystem.APIs.LocationAPI;

public class InventoryClickListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		try {
			if(e.getWhoClicked() instanceof Player) {
				Player player = (Player) e.getWhoClicked();
				
				if(e.getInventory() == null) {
					e.setCancelled(true);
					return;
				}

				if(e.getCurrentItem() == null) {
					e.setCancelled(true);
					return;
				}
				
				if(e.getClick() == ClickType.NUMBER_KEY) {
					if (!(Lobby.getInstance().editMode.contains(player))) {
						e.setCancelled(true);
					}
				}
				
				if(!(Lobby.getInstance().editMode.contains(player))) {
					e.setCancelled(true);
				}
				
				if(e.getInventory().getTitle().equalsIgnoreCase("§8➥ §aWähle einen Spielmodus")) {
					e.setCancelled(true);
					if(e.getCurrentItem().getType() == Material.MAGMA_CREAM) {
						if(LocationAPI.doesLocationExists("Spawn")) {
							player.sendTitle("§7✪ §6Spawn §7✪", "§eDu wurdest erfolgreich teleportiert");
							player.teleport(LocationAPI.getLocation("Spawn"));
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 2F);
						} else {
							player.closeInventory();
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5F, 2F);
							player.sendMessage(Lobby.prefix + "§cEs wurde keine Position für den §eSpawn §cgefunden.");
						}
					}
					if(e.getCurrentItem().getType() == Material.GOLD_INGOT) {
						if(LocationAPI.doesLocationExists("CoinShop")) {
							player.sendTitle("§7✪ §bCoin Shop §7✪", "§eDu wurdest erfolgreich teleportiert");
							player.teleport(LocationAPI.getLocation("CoinShop"));
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 2F);
						} else {
							player.closeInventory();
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5F, 2F);
							player.sendMessage(Lobby.prefix + "§cEs wurde keine Position für den §eCoinShop §cgefunden.");
						}
					}
					if(e.getCurrentItem().getType() == Material.BOOK) {
						if(LocationAPI.doesLocationExists("Belohnung")) {
							player.sendTitle("§7✪ §aTägliche Belohnung §7✪", "§eDu wurdest erfolgreich teleportiert");
							player.teleport(LocationAPI.getLocation("Belohnung"));
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 2F);
						} else {
							player.closeInventory();
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5F, 2F);
							player.sendMessage(Lobby.prefix + "§cEs wurde keine Position für die §eTäglicheBelohnung §cgefunden.");
						}
					}
					if(e.getCurrentItem().getType() == Material.BOW) {
						if(LocationAPI.doesLocationExists("SkyPvP")) {
							player.sendTitle("§7✪ §9SkyPvP §7✪", "§eDu wurdest erfolgreich teleportiert");
							player.teleport(LocationAPI.getLocation("SkyPvP"));
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 2F);
						} else {
							player.closeInventory();
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5F, 2F);
							player.sendMessage(Lobby.prefix + "§cEs wurde keine Position für §eSkyPvP §cgefunden.");
						}
					}
					if(e.getCurrentItem().getType() == Material.IRON_SWORD) {
						if(LocationAPI.doesLocationExists("SurvivalGames")) {
							player.sendTitle("§7✪ §cSurvival Games §7✪", "§eDu wurdest erfolgreich teleportiert");
							player.teleport(LocationAPI.getLocation("SurvivalGames"));
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 2F);
						} else {
							player.closeInventory();
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 0.5F, 2F);
							player.sendMessage(Lobby.prefix + "§cEs wurde keine Position für die §eSurvivalGames §cgefunden.");
						}
					}
					
				}
				
				// TODO: Shop!
				if(e.getClickedInventory().getTitle().equalsIgnoreCase("§8➥ §eExtras")) {
					
					if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8➟ §eBanner") {
						player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
						Inventory bannerselector = Bukkit.createInventory(null, 45, "§8➥ §eExtras §8» §aBanner");
						
						for(int i = 0; i < bannerselector.getSize(); i++) {
							bannerselector.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
							
						}
						
						
						
						player.openInventory(bannerselector);
					}
					
					if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8➟ §eHüte") {
						player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
						Inventory hatselector = Bukkit.createInventory(null, 45, "§8➥ §eExtras §8» §aHüte");
						
						for(int i = 0; i < hatselector.getSize(); i++) {
							hatselector.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
							
						}
						
						
						player.openInventory(hatselector);
					}
					
					if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8➟ §eKöpfe") {
						player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
						Inventory headselector = Bukkit.createInventory(null, 45, "§8➥ §eExtras §8» §aKöpfe");
						
						for(int i = 0; i < headselector.getSize(); i++) {
							headselector.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
							
						}
						
						
						player.openInventory(headselector);
					}
					
					if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8➟ §ePartikel") {
						player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
						Inventory particleselector = Bukkit.createInventory(null, 45, "§8➥ §eExtras §8» §aPartikel");
						
						for(int i = 0; i < particleselector.getSize(); i++) {
							particleselector.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
							
						}
						
						
						player.openInventory(particleselector);
					}

					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8➟ §eSchuss-Effekte")) {
						player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
						Inventory traileffects = Bukkit.createInventory(null, 45, "§8➥ §eExtras §8» §aSchuss-Effekte");
						
						for(int i = 0; i < traileffects.getSize(); i++) {
							traileffects.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
							
						}
						
						
						player.openInventory(traileffects);
					}
					
					if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8➟ §6Premium") {
						player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
						Inventory premium = Bukkit.createInventory(null, 45, "§8➥ §eExtras §8» §6Premium");
						
						for(int i = 0; i < premium.getSize(); i++) {
							premium.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
							
						}
						
						
						player.openInventory(premium);
					}
					
				}
				
				
				
			}
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}

}
