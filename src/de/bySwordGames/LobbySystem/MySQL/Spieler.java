/**
 * Die Klasse heißt: Spieler.java
 * Die Klasse wurde am: 01.05.2017 | 23:36:38 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.bySwordGames.LobbySystem.Lobby;

public class Spieler {
	
	public static boolean isPlayerExisting(UUID UUID) {
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("SELECT * FROM LobbySystem WHERE UUID = ?");
			ps.setString(1, UUID.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return(rs.getString("UUID") != null);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createPlayer(UUID UUID) {
		if(isPlayerExisting(UUID) == false) {
			try {
				PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("INSERT INTO LobbySystem(UUID, Spieler, AutoNick, SilentLobby, Belohnung) VALUES (?, ?, ?, ?, ?)");
				ps.setString(1, UUID.toString());
				ps.setInt(2, 2);
				ps.setInt(3, 0);
				ps.setInt(4, 0);
				ps.setLong(5, System.currentTimeMillis());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--\\
	
	public static Integer getModuleStatus(UUID UUID, String Module) {
		Integer value = -1;
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("SELECT * FROM LobbySystem WHERE UUID = ?");
			ps.setString(1, UUID.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				value = rs.getInt(Module);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static long getModuleLongStatus(UUID UUID, String Module) {
		long value = -1;
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("SELECT * FROM LobbySystem WHERE UUID = ?");
			ps.setString(1, UUID.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				value = rs.getLong(Module);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void setModuleStatus(UUID UUID, String Module, Integer Value) {
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("UPDATE LobbySystem SET " + Module + " = ? WHERE UUID = ?");
			ps.setInt(1, Value);
			ps.setString(2, UUID.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setModuleStatus(UUID UUID, String Module, long Value) {
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("UPDATE LobbySystem SET " + Module + " = ? WHERE UUID = ?");
			ps.setLong(1, Value);
			ps.setString(2, UUID.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getModuleStringStatus(UUID UUID, String Module) {
		String value = "";
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("SELECT * FROM LobbySystem WHERE UUID = ?");
			ps.setString(1, UUID.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				value = rs.getString(Module);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void setModuleStatus(UUID UUID, String Module, String Value) {
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("UPDATE LobbySystem SET " + Module + " = ? WHERE UUID = ?");
			ps.setString(1, Value);
			ps.setString(2, UUID.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void resetPlayer(Player player) {
		if(getModuleStatus(player.getUniqueId(), "SilentLobby") == 1 && !player.hasPermission("Server.Youtuber")) {
			setModuleStatus(player.getUniqueId(), "SilentLobby", 0);
		}
		if(getModuleStatus(player.getUniqueId(), "AutoNick") == 1 && !player.hasPermission("Server.Youtuber") | !player.hasPermission("Server.PremiumPlus")) {
			setModuleStatus(player.getUniqueId(), "AutoNick", 0);
		}
	}
	
	public static void setPlayerVisibility(Player player) {
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(getModuleStatus(all.getUniqueId(), "Spieler") == 2) {
				all.showPlayer(player);
			}
			if(getModuleStatus(all.getUniqueId(), "Spieler") == 1) {
				if(!player.hasPermission("Server.PremiumPlus")) {
					all.hidePlayer(player);
				}
			}
			if(getModuleStatus(all.getUniqueId(), "Spieler") == 0) {
				all.hidePlayer(player);
			}
			if(Spieler.getModuleStatus(all.getUniqueId(), "SilentLobby") == 1) {
				all.hidePlayer(player);
			}
		}
		if(getModuleStatus(player.getUniqueId(), "Spieler") == 2) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!(Spieler.getModuleStatus(all.getUniqueId(), "SilentLobby") == 1)) {
					player.showPlayer(all);
				} else {
					player.hidePlayer(all);
				}
			}
		} else if(getModuleStatus(player.getUniqueId(), "Spieler") == 1) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(!all.hasPermission("Server.PremiumPlus")) {
					player.hidePlayer(all);
				}
			}
		} else if(getModuleStatus(player.getUniqueId(), "Spieler") == 0) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				player.hidePlayer(all);
			}
		}
		if(getModuleStatus(player.getUniqueId(), "SilentLobby") == 1) {
			player.sendMessage(Lobby.prefix + "§7Du wurdest automatisch in der §5SilentLobby §7eingeloggt.");
			Lobby.getInstance().silentLobby.add(player);
			for(Player all : Bukkit.getOnlinePlayers()) {
				all.hidePlayer(player);
				player.hidePlayer(all);
				if(getModuleStatus(all.getUniqueId(), "SilentLobby") == 1) {
					all.hidePlayer(player);
					player.hidePlayer(all);
				}
			}
		}
	}
	
	//--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--\\
	
	public static boolean isSystemPlayerExisting(UUID UUID) {
		try {
			PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("SELECT * FROM System WHERE UUID = ?");
			ps.setString(1, UUID.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return(rs.getString("UUID") != null);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createSystemPlayer(UUID UUID) {
		if(isSystemPlayerExisting(UUID) == false) {
			try {
				PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("INSERT INTO System(UUID, Coins) VALUES (?, ?)");
				ps.setString(1, UUID.toString());
				ps.setInt(2, 50);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Integer getCoins(UUID UUID) {
		Integer value = -1;
		if(isSystemPlayerExisting(UUID)) {
			try {
				PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("SELECT * FROM System WHERE UUID = ?");
				ps.setString(1, UUID.toString());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					value = rs.getInt("Coins");
				}
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public static void setCoins(UUID UUID, Integer Coins) {
		if(isSystemPlayerExisting(UUID)) {
			try {
				PreparedStatement ps = Lobby.MySQL.connection.prepareStatement("UPDATE System SET Coins = ? WHERE UUID = ?");
				ps.setInt(1, Coins);
				ps.setString(2, UUID.toString());
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
