package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("tp")
public class TeleportCommand extends BaseCommand {

	@Default
	@CommandCompletion("@players")
	@Syntax("<target>")
	public void onTeleport(Player sender, String targetName) {
		if (Bukkit.getPlayer(targetName) == null) {
			Common.tell(sender, Messages.INVALID_PLAYER);
			return;
		}

		final Player target = Bukkit.getPlayer(targetName);
		if (target.getName().equals(sender.getName())) {
			Common.tell(sender, Messages.TELEPORT_CANNOT_TELEPORT_TO_SELF);
			return;
		}

		sender.teleport(target);
		Common.tell(sender, Messages.TELEPORT_SUCCESS.replace("%target%", target.getName()));
	}
}
