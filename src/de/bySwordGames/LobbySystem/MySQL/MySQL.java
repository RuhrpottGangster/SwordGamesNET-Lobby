/**
 * Die Klasse heißt: MySQL.java
 * Die Klasse wurde am: 01.05.2017 | 23:18:38 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import de.bySwordGames.LobbySystem.Lobby;

public class MySQL {
	
	private String host;
	private Integer port;
	private String datenbank;
	private String benutzer;
	private String passwort;
	public Connection connection;
	
	public MySQL(String host, Integer port, String datenbank, String benutzer, String passwort) {
		
		this.host = host;
		this.port = port;
		this.datenbank = datenbank;
		this.benutzer = benutzer;
		this.passwort = passwort;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.datenbank + "?autoReconnect=true", this.benutzer, this.passwort);
			Bukkit.getConsoleSender().sendMessage(Lobby.prefix + "§2Die Verbindung zu §aMySQL §2wurde aufgebaut.");
			
			PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS LobbySystem(id INT AUTO_INCREMENT PRIMARY KEY, UUID VARCHAR(100), Spieler INT(100), AutoNick INT(100), SilentLobby INT(100), Belohnung VARCHAR(100))");
			ps.executeUpdate();
			ps.close();
			
			PreparedStatement ps1 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS System(id INT AUTO_INCREMENT PRIMARY KEY, UUID VARCHAR(100), Coins INT(100))");
			ps1.executeUpdate();
			ps1.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void close() {
		try {
			connection.close();
			Bukkit.getConsoleSender().sendMessage(Lobby.prefix + "§2Die Verbindung zu §aMySQL §2wurde geschlossen.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
