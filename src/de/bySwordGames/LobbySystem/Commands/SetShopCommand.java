/**
 * Die Klasse heißt: SetShopCommand.java
 * Die Klasse wurde am: 02.05.2017 | 23:11:12 erstellt.
 * Der Author der Klasse ist: bySwordGames
 */

package de.bySwordGames.LobbySystem.Commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.bySwordGames.LobbySystem.Files;
import de.bySwordGames.LobbySystem.Lobby;

public class SetShopCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Lobby.prefix + "§4Dieser Befehl darf nur von §6Spielern §4verwendet werden!");
			return true;
		}
		Player player = (Player) sender;
		
		if(player.hasPermission("Server.Administrator")) {
			if(args.length == 0) {
				
				for(Entity entitys : player.getWorld().getEntities()) {
					if(entitys instanceof Villager) {
						if(entitys.getCustomName().equalsIgnoreCase("§6§lShop")) {
							entitys.remove();
						}
					}
				}
				
				Villager v = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
				v.setCustomName("§6§lShop");
				v.setCustomNameVisible(true);
				v.setRemoveWhenFarAway(false);
				v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255));
				v.setNoDamageTicks(Integer.MAX_VALUE);
				
				try {
					Files.cfg.set("Position.Shop.Welt", player.getWorld().getName());
					Files.cfg.set("Position.Shop.X", player.getLocation().getX());
					Files.cfg.set("Position.Shop.Y", player.getLocation().getY());
					Files.cfg.set("Position.Shop.Z", player.getLocation().getZ());
					Files.cfg.set("Position.Shop.Yaw", player.getLocation().getYaw());
					Files.cfg.set("Position.Shop.Pitch", player.getLocation().getPitch());
					Files.cfg.save(Files.file);
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
				player.sendMessage(Lobby.prefix + "§eDu hast erfolgreich den §6Shop §egespawnt.");
				
			} else {
				player.sendMessage(Lobby.prefix + Lobby.unknownCommand);
			}
		} else {
			player.sendMessage(Lobby.prefix + Lobby.noPermissions);
		}
		
		return true;
	}

}
