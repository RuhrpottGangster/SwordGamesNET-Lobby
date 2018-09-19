/**
 * Die Klasse heißt: EditCommand.java
 * Die Klasse wurde am: 02.05.2017 | 20:35:38 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Commands;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.bySwordGames.LobbySystem.Lobby;
import de.bySwordGames.LobbySystem.APIs.ItemAPI;

public class EditCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Lobby.prefix + "§4Dieser Befehl darf nur von §6Spielern §4verwendet werden!");
			return true;
		}
		Player player = (Player) sender;
		
		if(player.hasPermission("Server.Administrator") | player.hasPermission("Server.BuildOnMaps")) {
			if(args.length == 0) {
				
				if(Lobby.getInstance().editMode.contains(player)) {
					player.playSound(player.getLocation(), Sound.LAVA_POP, 1F, 1F);
					player.sendMessage(Lobby.prefix + "§eDu darfst die Lobby nun nicht mehr verändern.");
					player.setGameMode(GameMode.ADVENTURE);
					ItemAPI.getLobbyItems(player);
					player.spigot().setCollidesWithEntities(false);
					Lobby.getInstance().editMode.remove(player);
				} else {
					player.playSound(player.getLocation(), Sound.LAVA_POP, 1F, 1F);
					player.sendMessage(Lobby.prefix + "§eDu darfst die Lobby absofort verändern.");
					player.setGameMode(GameMode.CREATIVE);
					player.getInventory().clear();
					player.spigot().setCollidesWithEntities(true);
					Lobby.getInstance().editMode.add(player);
				}
				
			} else {
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
		} else {
			player.sendMessage(Lobby.prefix + Lobby.noPermissions);
		}
		
		return true;
	}

}
