/**
 * Die Klasse heißt: ItemAPI.java
 * Die Klasse wurde am: 01.05.2017 | 23:00:00 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.APIs;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.MySQL.Spieler;

public class ItemAPI {
	
	public static ItemStack createItem(Material material, int subID, int anzahl, String name) {
		ItemStack item = new ItemStack(material, anzahl, (short) subID);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createSkullItem(Material material, String eigentümer, int anzahl, String name) {
		ItemStack item = new ItemStack(material, anzahl, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(eigentümer);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createItemWithLore(Material material, int subID, int anzahl, String name, String... lore) {
		ItemStack item = new ItemStack(material, anzahl, (short) subID);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	//--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--\\
	
	public static void getLobbyItems(Player player) {
		player.getInventory().clear();
		
		player.getInventory().setItem(0, createItem(Material.COMPASS, 0, 1, "§aTeleporter §8┃ §7Unsere Spielmodies"));
		
		if(Spieler.getModuleStatus(player.getUniqueId(), "Spieler") == 2) {
			player.getInventory().setItem(1, createItem(Material.INK_SACK, 10, 1, "§9Sichtbarkeit §8» §aAktiviert"));
		} else if(Spieler.getModuleStatus(player.getUniqueId(), "Spieler") == 1) {
			player.getInventory().setItem(1, createItem(Material.INK_SACK, 5, 1, "§9Sichtbarkeit §8» §5Nur VIPs"));
		} else if(Spieler.getModuleStatus(player.getUniqueId(), "Spieler") == 0) {
			player.getInventory().setItem(1, createItem(Material.INK_SACK, 1, 1, "§9Sichtbarkeit §8» §cDeaktiviert"));
		} else {
			player.getInventory().setItem(1, createItem(Material.BARRIER, 0, 1, "§9Sichtbarkeit §8» §4FEHLER"));
		}
		
		if(player.hasPermission("Server.Youtuber")) {
			if(Spieler.getModuleStatus(player.getUniqueId(), "AutoNick") == 1) {
				player.getInventory().setItem(2, createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §aAktiviert"));
			} else if(Spieler.getModuleStatus(player.getUniqueId(), "AutoNick") == 0) {
				player.getInventory().setItem(2, createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §cDeaktiviert"));
			} else {
				player.getInventory().setItem(2, createItem(Material.BARRIER, 0, 1, "§5Automatischer Nicknamer §8» §4FEHLER"));
			}
			if(Spieler.getModuleStatus(player.getUniqueId(), "SilentLobby") == 1) {
				player.getInventory().setItem(6, createItem(Material.TNT, 0, 1, "§cSilentLobby §8» §aAktiviert"));
			} else if(Spieler.getModuleStatus(player.getUniqueId(), "SilentLobby") == 0) {
				player.getInventory().setItem(6, createItem(Material.TNT, 0, 1, "§cSilentLobby §8» §cDeaktiviert"));
			} else {
				player.getInventory().setItem(6, createItem(Material.BARRIER, 0, 1, "§cSilentLobby §8» §4FEHLER"));
			}
		} else if(player.hasPermission("Server.PremiumPlus")) {
			if(Spieler.getModuleStatus(player.getUniqueId(), "AutoNick") == 1) {
				player.getInventory().setItem(4, createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §aAktiviert"));
			} else if(Spieler.getModuleStatus(player.getUniqueId(), "AutoNick") == 0) {
				player.getInventory().setItem(4, createItem(Material.NAME_TAG, 0, 1, "§5Automatischer Nicknamer §8» §cDeaktiviert"));
			} else {
				player.getInventory().setItem(4, createItem(Material.BARRIER, 0, 1, "§5Automatischer Nicknamer §8» §4FEHLER"));
			}
		}
		
		player.getInventory().setItem(7, createSkullItem(Material.SKULL_ITEM, player.getName(), 1, "§bFreunde §8┃ §7Verwalte deine Freundesliste"));
		player.getInventory().setItem(8, createItem(Material.NETHER_STAR, 0, 1, "§eExtras §8┃ §7Deine gekauften Funktionen"));
		
	}
	
	public static void fillInventorys() {
		
		Inventory extras = Lobby.getInstance().menu;
		for(int i = 0; i < extras.getSize(); i++) {
			extras.setItem(i, createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
		}

		ItemStack B = new ItemStack(Material.BANNER, 1);
		ItemStack CH = new ItemStack(Material.CHAINMAIL_HELMET, 1);
		ItemStack SI = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemStack BP = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemStack A = new ItemStack(Material.ARROW, 1);
		ItemStack GI = new ItemStack(Material.GOLD_INGOT, 1);

		ArrayList<String> BLore = new ArrayList<String>();
		ArrayList<String> CHLore = new ArrayList<String>();
		ArrayList<String> SILore = new ArrayList<String>();
		ArrayList<String> BPLore = new ArrayList<String>();
		ArrayList<String> ALore = new ArrayList<String>();
		ArrayList<String> GILore = new ArrayList<String>();

		BLore.add(0, "");
		BLore.add(1, "§7Ziehe coole §eBanner §7in der Lobby");
		BLore.add(2, "§7an um für Aufmerksamkeit zu sorgen.");
		CHLore.add(0, "");
		CHLore.add(1, "§7Ziehe einzigartige §eHüte §7in der Lobby");
		CHLore.add(2, "§7an und zeige den anderen Spielern, wer der");
		CHLore.add(3, "§7Boss auf dem Server ist.");
		SILore.add(0, "");
		SILore.add(1, "§7Ziehe verschiedene §eKöpfe §7in der Lobby");
		SILore.add(2, "§7an um dich zu verkleiden.");
		BPLore.add(0, "");
		BPLore.add(1, "§7Verursache coole §ePartikel §7in der Lobby");
		BPLore.add(2, "§7und bekomme die Aufmerksamkeit aller Spieler.");
		ALore.add(0, "");
		ALore.add(1, "§7Hinterlasse atemberaubende §eSchuss-Effekte");
		ALore.add(2, "§7beim schießen mit dem Bogen in jedem Spielmodus.");
		GILore.add(0, "");
		GILore.add(1, "§7Kaufe dir §6Premium §7und erhalte Zugriff");
		GILore.add(2, "§7auf alle Features in dieser Kategorie.");

		BannerMeta BMeta = (BannerMeta) B.getItemMeta();
		BMeta.setDisplayName("§8➟ §eBanner");
		BMeta.setBaseColor(DyeColor.WHITE);
		BMeta.setLore(BLore);
		B.setItemMeta(BMeta);
		ItemMeta CHMeta = CH.getItemMeta();
		CHMeta.setDisplayName("§8➟ §eHüte");
		CHMeta.setLore(CHLore);
		CH.setItemMeta(CHMeta);
		ItemMeta SIMeta = SI.getItemMeta();
		SIMeta.setDisplayName("§8➟ §eKöpfe");
		SIMeta.setLore(SILore);
		SI.setItemMeta(SIMeta);
		ItemMeta BPMeta = BP.getItemMeta();
		BPMeta.setDisplayName("§8➟ §ePartikel");
		BPMeta.setLore(BPLore);
		BP.setItemMeta(BPMeta);
		ItemMeta AMeta = A.getItemMeta();
		AMeta.setDisplayName("§8➟ §eSchuss-Effekte");
		AMeta.setLore(ALore);
		A.setItemMeta(AMeta);
		ItemMeta GIMeta = GI.getItemMeta();
		GIMeta.setDisplayName("§8➟ §6Premium");
		GIMeta.setLore(GILore);
		GI.setItemMeta(GIMeta);

		extras.setItem(10, B);
		extras.setItem(12, CH);
		extras.setItem(14, SI);
		extras.setItem(16, BP);
		extras.setItem(29, A);
		extras.setItem(33, GI);
		
		Inventory coinshop = Lobby.getInstance().shop;
		for(int i = 0; i < coinshop.getSize(); i++) {
			coinshop.setItem(i, createItem(Material.STAINED_GLASS_PANE, 15, 1, " "));
		}
		
		ItemStack B1 = new ItemStack(Material.BANNER, 1);
		ItemStack CH1 = new ItemStack(Material.CHAINMAIL_HELMET, 1);
		ItemStack SI1 = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemStack BP1 = new ItemStack(Material.BLAZE_POWDER, 1);
		ItemStack A1 = new ItemStack(Material.ARROW, 1);
		ItemStack GI1 = new ItemStack(Material.GOLD_INGOT, 1);

		ArrayList<String> BLore1 = new ArrayList<String>();
		ArrayList<String> CHLore1 = new ArrayList<String>();
		ArrayList<String> SILore1 = new ArrayList<String>();
		ArrayList<String> BPLore1 = new ArrayList<String>();
		ArrayList<String> ALore1 = new ArrayList<String>();
		ArrayList<String> GILore1 = new ArrayList<String>();

		BLore1.add(0, "");
		BLore1.add(1, "§7Ziehe coole §eBanner §7in der Lobby");
		BLore1.add(2, "§7an um für Aufmerksamkeit zu sorgen.");
		CHLore1.add(0, "");
		CHLore1.add(1, "§7Ziehe einzigartige §eHüte §7in der Lobby");
		CHLore1.add(2, "§7an und zeige den anderen Spielern, wer der");
		CHLore1.add(3, "§7Boss auf dem Server ist.");
		SILore1.add(0, "");
		SILore1.add(1, "§7Ziehe verschiedene §eKöpfe §7in der Lobby");
		SILore1.add(2, "§7an um dich zu verkleiden.");
		BPLore1.add(0, "");
		BPLore1.add(1, "§7Verursache coole §ePartikel §7in der Lobby");
		BPLore1.add(2, "§7und bekomme die Aufmerksamkeit aller Spieler.");
		ALore1.add(0, "");
		ALore1.add(1, "§7Hinterlasse atemberaubende §eSchuss-Effekte");
		ALore1.add(2, "§7beim schießen mit dem Bogen in jedem Spielmodus.");
		GILore1.add(0, "");
		GILore1.add(1, "§7Kaufe dir §6Premium §7und erhalte alle Features");
		GILore1.add(2, "§7des Rangs für die ausgewählte Zeit.");

		BannerMeta BMeta1 = (BannerMeta) B1.getItemMeta();
		BMeta1.setDisplayName("§8➟ §eBanner");
		BMeta1.setBaseColor(DyeColor.WHITE);
		BMeta1.setLore(BLore1);
		B1.setItemMeta(BMeta1);
		ItemMeta CHMeta1 = CH1.getItemMeta();
		CHMeta1.setDisplayName("§8➟ §eHüte");
		CHMeta1.setLore(CHLore1);
		CH1.setItemMeta(CHMeta1);
		ItemMeta SIMeta1 = SI1.getItemMeta();
		SIMeta1.setDisplayName("§8➟ §eKöpfe");
		SIMeta1.setLore(SILore1);
		SI1.setItemMeta(SIMeta1);
		ItemMeta BPMeta1 = BP1.getItemMeta();
		BPMeta1.setDisplayName("§8➟ §ePartikel");
		BPMeta1.setLore(BPLore1);
		BP1.setItemMeta(BPMeta1);
		ItemMeta AMeta1 = A1.getItemMeta();
		AMeta1.setDisplayName("§8➟ §eSchuss-Effekte");
		AMeta1.setLore(ALore1);
		A1.setItemMeta(AMeta1);
		ItemMeta GIMeta1 = GI1.getItemMeta();
		GIMeta1.setDisplayName("§8➟ §6Premium");
		GIMeta1.setLore(GILore1);
		GI1.setItemMeta(GIMeta1);

		coinshop.setItem(10, B1);
		coinshop.setItem(12, CH1);
		coinshop.setItem(14, SI1);
		coinshop.setItem(16, BP1);
		coinshop.setItem(29, A1);
		coinshop.setItem(33, GI1);
		
	}

}
