package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("ptime")
public class PTimeCommand extends BaseCommand {

    @Default
    @CommandCompletion("@time @worlds")
    @Syntax("<time> [worldName]")
    public void onPTime(Player player, String time, @Optional String worldName) {
        World world;

        if (worldName != null) {
            world = Bukkit.getWorld(worldName);
            if (world == null) {
                Common.tell(player, Messages.PTIME_INVALID_WORLD);
                return;
            }
        } else {
            world = player.getWorld();
        }

        if (time.equalsIgnoreCase("day")) {
            player.setPlayerTime(1000, false);
            Common.tell(player, Messages.PTIME_SET_DAY.replace("%world%", world.getName()));
        } else if (time.equalsIgnoreCase("night")) {
            player.setPlayerTime(13000, false);
            Common.tell(player, Messages.PTIME_SET_NIGHT.replace("%world%", world.getName()));
        } else if (time.equalsIgnoreCase("noon")) {
            player.setPlayerTime(6000, false);
            Common.tell(player, Messages.PTIME_SET_AFTERNOON.replace("%world%", world.getName()));
        } else if (time.equalsIgnoreCase("midnight")) {
            player.setPlayerTime(18000, false);
            Common.tell(player, Messages.PTIME_SET_MIDNIGHT.replace("%world%", world.getName()));
        } else if (NumberUtils.isNumber(time)) {
            long ticks = Long.parseLong(time);
            player.setPlayerTime(ticks, false);
            Common.tell(player, Messages.PTIME_SET_CUSTOM.replace("%ticks%", String.valueOf(ticks)).replace("%world%", world.getName()));
        } else if (time.equalsIgnoreCase("reset")) {
            player.resetPlayerTime();
            Common.tell(player, Messages.PTIME_RESET.replace("%world%", world.getName()));
        } else {
            Common.tell(player, Messages.PTIME_INVALID_TIME);
        }
    }
}
