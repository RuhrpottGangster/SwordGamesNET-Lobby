/**
 * Die Klasse heißt: Files.java
 * Die Klasse wurde am: 02.05.2017 | 01:19:14 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class Files {
	
	public static File file = new File("plugins//" + Lobby.getInstance().getDescription().getName() + "//config.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void createConfigFiles() {
		if(!Lobby.getInstance().getDataFolder().exists()) {
			Lobby.getInstance().getDataFolder().mkdir();
		}
		if(!file.exists()) {
			try {
				file.createNewFile();
				cfg.options().copyDefaults(true);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
