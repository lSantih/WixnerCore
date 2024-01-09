package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("time")
public class TimeCommand extends BaseCommand {

    @Default
    @CommandCompletion("@time @worlds")
    @Syntax("<time> <worldName>")
    public void onTime(CommandSender sender, String time, @Optional String worldName) {
        World world = null;

        if (worldName != null) {
            world = Bukkit.getWorld(worldName);
            if (world == null) {
                Common.tell(sender, Messages.TIME_INVALID_WORLD);
                return;
            }
        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            world = player.getWorld();
        } else {
            Common.tell(sender, Messages.TIME_SPECIFY_WORLD_CONSOLE);
            return;
        }

        if (time.equalsIgnoreCase("day") ) {
            setDayTime(world, sender);
        } else if (time.equalsIgnoreCase("night")) {
            setNightTime(world, sender);
        } else if (time.equalsIgnoreCase("noon")) {
            setNoonTime(world, sender);
        } else if (time.equalsIgnoreCase("midnight")) {
            setMidnight(world, sender);
        } else if (NumberUtils.isNumber(time)) {
            setTicks(world, sender, Long.parseLong(time));
        } else {
            Common.tell(sender, Messages.TIME_INVALID_TIME);
        }
    }

    @CommandAlias("day")
    public void onDay(Player player) {
        setDayTime(player.getWorld(), player);
    }

    @CommandAlias("night")
    public void onNight(Player player) {
        setNightTime(player.getWorld(), player);
    }

    @CommandAlias("midnight")
    public void onMidnight(Player player) {
        setMidnight(player.getWorld(), player);
    }

    @CommandAlias("noon")
    public void onNoon(Player player) {
        setNoonTime(player.getWorld(), player);
    }
    private void setDayTime(final World world, final CommandSender sender) {
        world.setTime(1000);
        Common.tell(sender, Messages.TIME_SET_DAY.replace("%world%", world.getName()));
    }

    private void setNightTime(final World world, final CommandSender sender) {
        world.setTime(13000);
        Common.tell(sender, Messages.TIME_SET_NIGHT.replace("%world%", world.getName()));
    }

    private void setNoonTime(final World world, final CommandSender sender) {
        world.setTime(6000);
        Common.tell(sender, Messages.TIME_SET_AFTERNOON.replace("%world%", world.getName()));
    }

    private void setMidnight(final World world, final CommandSender sender) {
        world.setTime(18000);
        Common.tell(sender, Messages.TIME_SET_MIDNIGHT.replace("%world%", world.getName()));
    }

    private void setTicks(final World world, final CommandSender sender, final long ticks) {
        world.setTime(ticks);
        Common.tell(sender, Messages.TIME_SET_TICKS.replace("%ticks%", String.valueOf(ticks)).replace("%world%", world.getName()));
    }
}
