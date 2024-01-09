package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Syntax;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldTpCommand extends BaseCommand {

    @CommandAlias("worldtp")
    @Description("Teleport you to a different world")
    @Syntax("<worldName>")
    public static void onRename(Player player, World world) {
        player.teleport(world.getSpawnLocation());
        Common.tell(player, Messages.WORLD_TELEPORTED.replaceAll("%world_name%", world.getName()));
    }
}
