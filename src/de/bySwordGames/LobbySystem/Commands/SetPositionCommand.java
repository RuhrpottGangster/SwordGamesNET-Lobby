/**
 * Die Klasse heißt: SetPositionCommand.java
 * Die Klasse wurde am: 02.05.2017 | 20:41:01 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.LocationAPI;

public class SetPositionCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Lobby.prefix + "§4Dieser Befehl darf nur von §6Spielern §4verwendet werden!");
			return true;
		}
		Player player = (Player) sender;
		
		if(player.hasPermission("Server.Administrator")) {
			
			if(args.length == 0) {
				player.sendMessage(Lobby.prefix + "§cDu musst einen §eNamen §cdes Minispiels angeben.");
				return true;
			}
			
			if(args.length > 1) {
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
				return true;
			}
			
			LocationAPI.setLocation(player.getLocation(), args[0]);
			player.sendMessage(Lobby.prefix + "§eDu hast die Position für §6" + args[0] + "§e gesetzt.");
			player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1F, 1F);
			
		} else {
			player.sendMessage(Lobby.prefix + Lobby.noPermissions);
		}
		
		return true;
	}

}
