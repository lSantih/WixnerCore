package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("weather")
public class WeatherCommand extends BaseCommand {

    @Subcommand("sun|clear")
    @CommandAlias("sun")
    @CommandCompletion("@worlds")
    @Syntax("<worldName>")
    public void onWeatherSun(CommandSender sender, @Optional String worldName) {
        World world = getWorld(sender, worldName);
        if (world == null) {
            return;
        }

        world.setStorm(false);
        world.setThundering(false);

        Common.tell(sender, Messages.WEATHER_CLEAR.replace("%world%", world.getName()));
    }

    @Subcommand("storm")
    @CommandAlias("storm")
    @CommandCompletion("@worlds")
    @Syntax("<worldName>")
    public void onWeatherStorm(CommandSender sender, @Optional String worldName) {
        World world = getWorld(sender, worldName);
        if (world == null) {
            return;
        }

        world.setStorm(true);
        world.setThundering(false);

        Common.tell(sender, Messages.WEATHER_STORM.replace("%world%", world.getName()));
    }

    @Subcommand("thunder")
    @CommandAlias("thunder")
    @CommandCompletion("@worlds")
    @Syntax("<worldName>")
    public void onWeatherThunder(CommandSender sender, @Optional String worldName) {
        World world = getWorld(sender, worldName);
        if (world == null) {
            return;
        }

        world.setStorm(true);
        world.setThundering(true);

        Common.tell(sender, Messages.WEATHER_THUNDER.replace("%world%", world.getName()));
    }

    private World getWorld(CommandSender sender, String worldName) {
        if (worldName == null) {
            if (sender instanceof Player) {
                return ((Player) sender).getWorld();
            } else {
                Common.tell(sender, Messages.CONSOLE_WORLD_REQUIRED);
                return null;
            }
        } else {
            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                Common.tell(sender, Messages.INVALID_WORLD);
                return null;
            }
            return world;
        }
    }
}
