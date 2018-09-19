/**
 * Die Klasse heißt: ScorebaordAPI.java
 * Die Klasse wurde am: 07.05.2017 | 17:58:06 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.APIs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import de.bySwordGames.LobbySystem.MySQL.Spieler;

public class ScoreboardAPI {
	
	@SuppressWarnings("deprecation")
	public static void setScorebaord(Player player) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("bbb", "ccc");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§8➥ §6SwordGames.net §8┃ §eLobby");
		
		String rang = "";
		if(player.hasPermission("Server.Administrator")) {
			rang = "§4Administrator";
			player.setDisplayName("§4" + player.getName());
		} else if(player.hasPermission("Server.Head-Developer")) {
			rang = "§bHead-Developer";
			player.setDisplayName("§b" + player.getName());
		} else if(player.hasPermission("Server.Developer")) {
			rang = "§bDeveloper";
			player.setDisplayName("§b" + player.getName());
		} else if(player.hasPermission("Server.Content")) {
			rang = "§bContent";
			player.setDisplayName("§b" + player.getName());
		} else if(player.hasPermission("Server.Head-Moderator")) {
			rang = "§cHead-Moderator";
			player.setDisplayName("§c" + player.getName());
		} else if(player.hasPermission("Server.Moderator")) {
			rang = "§cModerator";
			player.setDisplayName("§c" + player.getName());
		} else if(player.hasPermission("Server.Supporter")) {
			rang = "§eSupporter";
			player.setDisplayName("§e" + player.getName());
		} else if(player.hasPermission("Server.Builder")) {
			rang = "§2Builder";
			player.setDisplayName("§2" + player.getName());
		} else if(player.hasPermission("Server.Youtuber")) {
			rang = "§5Youtuber";
			player.setDisplayName("§5" + player.getName());
		} else if(player.hasPermission("Server.PremiumPlus")) {
			rang = "§6PremiumPlus";
			player.setDisplayName("§6" + player.getName());
		} else if(player.hasPermission("Server.Premium")) {
			rang = "§6Premium";
			player.setDisplayName("§6" + player.getName());
		} else {
			rang = "§9Spieler";
			player.setDisplayName("§9" + player.getName());
		}
		
		obj.getScore(("§3 ")).setScore(9);
		obj.getScore(("§8• §7Rang")).setScore(8);
		obj.getScore(("§8    ► §r" + rang)).setScore(7);
		obj.getScore(("§2 ")).setScore(6);
		obj.getScore(("§8• §7Coins")).setScore(5);
		obj.getScore(("§8    ► §b" + Spieler.getCoins(player.getUniqueId()))).setScore(4);
		obj.getScore(("§1 ")).setScore(3);
		obj.getScore(("§8• §7Teamspeak")).setScore(2);
		obj.getScore(("§8    ► §eSwordGames.net")).setScore(1);
		obj.getScore(("§0 ")).setScore(0);
		
		
		Team admin = board.registerNewTeam("00-Admin");
		Team hdev = board.registerNewTeam("01-HeadDev");
		Team dev = board.registerNewTeam("02-Dev");
		Team content = board.registerNewTeam("03-Conent");
		Team hmod = board.registerNewTeam("04-HeadMod");
		Team mod = board.registerNewTeam("05-Mod");
		Team sup = board.registerNewTeam("06-Sup");
		Team build = board.registerNewTeam("07-Builder");
		Team yt = board.registerNewTeam("08-Youtuber");
		Team pPlus = board.registerNewTeam("09-PremiumPlus");
		Team premi = board.registerNewTeam("10-Premium");
		Team spieler = board.registerNewTeam("11-Spieler");
		
		admin.setPrefix("§4");
		hdev.setPrefix("§b");
		dev.setPrefix("§b");
		content.setPrefix("§b");
		hmod.setPrefix("§c");
		mod.setPrefix("§c");
		sup.setPrefix("§e");
		build.setPrefix("§2");
		yt.setPrefix("§5");
		pPlus.setPrefix("§6");
		premi.setPrefix("§6");
		spieler.setPrefix("§9");
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.hasPermission("Server.Administrator")) {
				admin.addPlayer(all);
			} else if(all.hasPermission("Server.Head-Developer")) {
				hdev.addPlayer(all);
			} else if(all.hasPermission("Server.Developer")) {
				dev.addPlayer(all);
			} else if(all.hasPermission("Server.Content")) {
				content.addPlayer(all);
			} else if(all.hasPermission("Server.Head-Moderator")) {
				hmod.addPlayer(all);
			} else if(all.hasPermission("Server.Moderator")) {
				mod.addPlayer(all);
			} else if(all.hasPermission("Server.Supporter")) {
				sup.addPlayer(all);
			} else if(all.hasPermission("Server.Builder")) {
				build.addPlayer(all);
			} else if(all.hasPermission("Server.Youtuber")) {
				yt.addPlayer(all);
			} else if(all.hasPermission("Server.PremiumPlus")) {
				pPlus.addPlayer(all);
			} else if(all.hasPermission("Server.Premium")) {
				premi.addPlayer(all);
			} else {
				spieler.addPlayer(all);
			}
		}
		
		player.setScoreboard(board);
	}
	
	public static void setAllPlayers() {
		for(Player all : Bukkit.getOnlinePlayers()) {
			setScorebaord(all);
		}
	}
	
}
