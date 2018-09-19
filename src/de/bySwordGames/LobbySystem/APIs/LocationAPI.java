/**
 * Die Klasse heißt: LocationAPI.java
 * Die Klasse wurde am: 02.05.2017 | 01:18:59 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.APIs;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.bySwordGames.LobbySystem.Files;

public class LocationAPI {
	
	public static Location getLocation(String Name) {
		Location loc = new Location(Bukkit.getWorld(Files.cfg.getString("Position." + Name + ".Welt")), Files.cfg.getDouble("Position." + Name + ".X"), Files.cfg.getDouble("Position." + Name + ".Y"), Files.cfg.getDouble("Position." + Name + ".Z"));
		loc.setYaw((float) Files.cfg.getDouble("Position." + Name + ".Yaw"));
		loc.setPitch((float) Files.cfg.getDouble("Position." + Name + ".Pitch"));
		
		return loc;
	}
	
	@SuppressWarnings("deprecation")
	public static void setLocation(Location location, String Name) {
		try {
			Files.cfg.set("Position." + Name + ".Welt", location.getWorld().getName());
			Files.cfg.set("Position." + Name + ".X", location.getX());
			Files.cfg.set("Position." + Name + ".Y", location.getY());
			Files.cfg.set("Position." + Name + ".Z", location.getZ());
			Files.cfg.set("Position." + Name + ".Yaw", location.getYaw());
			Files.cfg.set("Position." + Name + ".Pitch", location.getPitch());
			Files.cfg.save(Files.file);
			
			for(Player all : Bukkit.getOnlinePlayers()) {
				all.playEffect(location, Effect.LAVADRIP, 3);
				all.playSound(location, Sound.FIREWORK_LARGE_BLAST2, 2F, 3F);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean doesLocationExists(String Name) {
		if(Files.cfg.getString("Position." + Name + ".Welt") != null) {
			return true;
		}
		return false;
	}

}
