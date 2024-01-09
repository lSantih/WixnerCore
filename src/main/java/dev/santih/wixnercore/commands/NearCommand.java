package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@CommandAlias("near")
public class NearCommand extends BaseCommand {

	@Default
	@Syntax("<radius>")
	@CommandPermission("unitycore.command.near")
	public void onNear(Player player, @Optional Integer radius) {
		int searchRadius = (radius == null) ? 100 : radius;

		final List<Player> nearbyPlayers = player.getWorld().getPlayers().stream()
				.filter(p -> p != player && p.getLocation().distance(player.getLocation()) <= searchRadius)
				.collect(Collectors.toList());

		if (nearbyPlayers.isEmpty()) {
			Common.tell(player, Messages.NEAR_NO_PLAYERS_FOUND.replace("%radius%", String.valueOf(searchRadius)));
			return;
		}

		StringBuilder message = new StringBuilder(Messages.NEAR_HEADER.replace("%radius%", String.valueOf(searchRadius)));

		DecimalFormat df = new DecimalFormat("0.##");

		for (Player nearbyPlayer : nearbyPlayers) {
			double distance = player.getLocation().distance(nearbyPlayer.getLocation());
			message.append("\n").append(Messages.NEAR_PLAYER_FORMAT
					.replace("%player%", nearbyPlayer.getName())
					.replace("%distance%", df.format(distance)));
		}

		Common.tell(player, message.toString());
	}
}
