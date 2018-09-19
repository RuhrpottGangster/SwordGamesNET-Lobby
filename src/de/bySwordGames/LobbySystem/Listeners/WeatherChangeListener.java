/**
 * Die Klasse heißt: WeatherChangeListener.java
 * Die Klasse wurde am: 02.05.2017 | 14:24:13 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
	
	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

}
