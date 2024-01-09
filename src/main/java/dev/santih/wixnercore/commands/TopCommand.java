package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("top")
public class TopCommand extends BaseCommand {

    @Default
    public void onTop(Player player) {
        World world = player.getWorld();
        Location location = player.getLocation();
        int topY = world.getHighestBlockYAt(location);
        if (location.getBlockY() >= topY) {
            Common.tell(player, Messages.TOP_ALREADY_AT_HIGHEST_BLOCK);
        } else {
            location.setY(topY + 1);
            player.teleport(location);
            Common.tell(player, Messages.TOP_TELEPORTED_TO_HIGHEST_BLOCK);
        }
    }
}
