package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("forcecmd")
@Description("Force a player to run a command with or without permissions")
public class ForceCmdCommand extends BaseCommand {

	@Default
	@CommandCompletion("@players @nothing true|false")
	@Syntax("<player> <command>")
	public void onForceCmd(CommandSender sender, String targetName, String command) {

		if (Bukkit.getPlayer(targetName) == null) {
			Common.tell(sender, Messages.INVALID_PLAYER);
			return;
		}

		final Player target = Bukkit.getPlayer(targetName);
		Bukkit.dispatchCommand(target, command);

		Common.tell(sender, Messages.FORCE_CMD_SUCCESS
				.replace("%target_name%", target.getName())
				.replace("%command%", command));
	}
}
