/**
 * Die Klasse heißt: Lobby.java
 * Die Klasse wurde am: 01.05.2017 | 22:52:30 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.bySwordGames.LobbySystem.APIs.EntityAPI;
import de.bySwordGames.LobbySystem.APIs.ItemAPI;
import de.bySwordGames.LobbySystem.APIs.ParticleAPI;
import de.bySwordGames.LobbySystem.Commands.EditCommand;
import de.bySwordGames.LobbySystem.Commands.SetPositionCommand;
import de.bySwordGames.LobbySystem.Commands.SetShopCommand;
import de.bySwordGames.LobbySystem.Listeners.AsyncPlayerChatListener;
import de.bySwordGames.LobbySystem.Listeners.BlockBreakListener;
import de.bySwordGames.LobbySystem.Listeners.BlockBurnListener;
import de.bySwordGames.LobbySystem.Listeners.BlockPlaceListener;
import de.bySwordGames.LobbySystem.Listeners.CraftItemListener;
import de.bySwordGames.LobbySystem.Listeners.EntityChangeBlockListener;
import de.bySwordGames.LobbySystem.Listeners.EntityDamageListener;
import de.bySwordGames.LobbySystem.Listeners.FoodLevelChangeListener;
import de.bySwordGames.LobbySystem.Listeners.HangingBreakListener;
import de.bySwordGames.LobbySystem.Listeners.HangingPlaceListener;
import de.bySwordGames.LobbySystem.Listeners.InventoryClickListener;
import de.bySwordGames.LobbySystem.Listeners.LeavesDecayListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerAchievementAwardedListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerArmorStandManipulateListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerCommandPreprocessListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerDropItemListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerInteractEntityListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerInteractListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerJoinListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerMoveListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerPickupItemListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerQuitListener;
import de.bySwordGames.LobbySystem.Listeners.PlayerUnleashEntityListener;
import de.bySwordGames.LobbySystem.Listeners.ProjectileLaunchListener;
import de.bySwordGames.LobbySystem.Listeners.WeatherChangeListener;
import de.bySwordGames.LobbySystem.MySQL.MySQL;

public class Lobby extends JavaPlugin implements Listener {
	
	private static Lobby plugin;
	public static MySQL MySQL;
	
	public ArrayList<Player> visibilityChangeCooldown = new ArrayList<>();
	public ArrayList<Player> nickChangeCooldown = new ArrayList<>();
	public ArrayList<Player> silentlobbyChangeCooldown = new ArrayList<>();
	
	public ArrayList<Player> silentLobby = new ArrayList<>();
	public ArrayList<Player> editMode = new ArrayList<>();
	
	public Inventory menu = Bukkit.createInventory(null, 9*5, "§8➥ §eExtras");
	public Inventory shop = Bukkit.createInventory(null, 9*5, "§8➥ §6Shop");
	
	public static String prefix = "§8§l┃ §6Lobby §8► ";
	public static String noPermissions = "§cDu hast keinen Zugriff auf diesen Befehl.";
	public static String unknownCommand = "§cDieser Befehl existiert nicht.";
	
	@Override
	public void onEnable() {
		plugin = this;
		MySQL = new MySQL("localhost", 3306, "server", "root", "SportaKuss007");
		Files.createConfigFiles();
		ItemAPI.fillInventorys();
		EntityAPI.spawnShop();
		EntityAPI.spawnReward();
		ParticleAPI.startParticle();
		register();
	}
	
	@Override
	public void onDisable() {
		EntityAPI.removeShop();
		EntityAPI.removeReward();
		MySQL.close();
	}
	
	private void register() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new PlayerJoinListener(), plugin);
		pm.registerEvents(new PlayerQuitListener(), plugin);
		pm.registerEvents(new BlockPlaceListener(), plugin);
		pm.registerEvents(new BlockBreakListener(), plugin);
		pm.registerEvents(new BlockBurnListener(), plugin);
		pm.registerEvents(new LeavesDecayListener(), plugin);
		pm.registerEvents(new PlayerArmorStandManipulateListener(), plugin);
		pm.registerEvents(new PlayerInteractListener(), plugin);
		pm.registerEvents(new PlayerInteractEntityListener(), plugin);
		pm.registerEvents(new AsyncPlayerChatListener(), plugin);
		pm.registerEvents(new InventoryClickListener(), plugin);
		pm.registerEvents(new EntityDamageListener(), plugin);
		pm.registerEvents(new FoodLevelChangeListener(), plugin);
		pm.registerEvents(new WeatherChangeListener(), plugin);
		pm.registerEvents(new PlayerUnleashEntityListener(), plugin);
		pm.registerEvents(new EntityChangeBlockListener(), plugin);
		pm.registerEvents(new PlayerDropItemListener(), plugin);
		pm.registerEvents(new PlayerPickupItemListener(), plugin);
		pm.registerEvents(new PlayerAchievementAwardedListener(), plugin);
		pm.registerEvents(new CraftItemListener(), plugin);
		pm.registerEvents(new PlayerCommandPreprocessListener(), plugin);
		pm.registerEvents(new HangingPlaceListener(), plugin);
		pm.registerEvents(new HangingBreakListener(), plugin);
		pm.registerEvents(new PlayerMoveListener(), plugin);
		pm.registerEvents(new ProjectileLaunchListener(), plugin);
		
		plugin.getCommand("edit").setExecutor(new EditCommand());
		plugin.getCommand("setPos").setExecutor(new SetPositionCommand());
		plugin.getCommand("setShop").setExecutor(new SetShopCommand());
		
	}
	
	public static Lobby getInstance() {
		return plugin;
	}
	
}
