package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("tpall")
public class TPAllCommand extends BaseCommand {

	@Default
	public void onTPAll(Player sender) {
		Location loc = sender.getLocation();
		int count = 0;

		for (Player target : Bukkit.getOnlinePlayers()) {
			if (target != sender && !target.hasPermission("shootcore.admin")) {
				target.teleport(loc);
				Common.tell(target, Messages.TPALL_PLAYER_TELEPORTED.replace("%s", sender.getName()));
				count++;
			}
		}

		if (count == 0) {
			Common.tell(sender, Messages.TPALL_NO_PLAYERS);
		} else {
			Common.tell(sender, Messages.TPALL_TELEPORTED_TO_YOU.replace("%d", String.valueOf(count)));
		}
	}
}
