package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("tppos")
public class TpposCommand extends BaseCommand {

    @Default
    @CommandCompletion("@nothing @nothing @nothing @worlds")
    @Syntax("<x> <y> <z> <worldName>")
    public void onTppos(Player player, double x, double y, double z, @Optional String worldName) {
        World world = (worldName != null) ? Bukkit.getWorld(worldName) : player.getWorld();
        if (world == null) {
            Common.tell(player, Messages.INVALID_WORLD);
            return;
        }

        Location location = new Location(world, x, y, z);
        player.teleport(location);
        Common.tell(player, Messages.TELEPORT_POS_SUCCESS
                .replace("%x%", String.valueOf(Math.round(x)))
                .replace("%y%", String.valueOf(Math.round(y)))
                .replace("%z%", String.valueOf(Math.round(z)))
                .replace("%world%", world.getName()));
    }
}
