package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

    @CommandAlias("broadcastworld|bworld")
    public class BroadcastWorldCommand extends BaseCommand {

        @Default
        @CommandPermission("unitycore.broadcastworld")
        @Syntax("<world> <message>")
        @Description("Broadcast a message to all players in a specific world.")
        public void onBroadcastWorld(CommandSender sender, String worldName, String[] args) {
            final World world = Bukkit.getWorld(worldName);
            if (world == null) {
                Common.tell(sender, Messages.INVALID_WORLD);
                return;
            }

            final String message = Common.colorize(String.join(" ", args));

            for (Player player : world.getPlayers()) {
                Common.tell(player, Messages.BROADCAST_PREFIX + message);
            }
        }
    }

