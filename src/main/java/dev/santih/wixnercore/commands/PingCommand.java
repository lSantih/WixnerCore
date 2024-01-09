package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("ping")
@Description("Check the ping of a player or yourself")
public class PingCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("<jugador>")
    public void onPing(CommandSender sender, @Optional String playerName) {
        if (playerName == null) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                int ping = getPing(player);
                Common.tell(sender, Messages.PING_YOUR_PING.replace("%ping%", String.valueOf(ping)));
            } else {
                Common.tell(sender, Messages.PING_SPECIFY_PLAYER);
            }
        } else {
            Player target = Bukkit.getPlayer(playerName);
            if (target != null) {
                int ping = getPing(target);
                Common.tell(sender, Messages.PING_TARGET_PING
                        .replace("%player_name%", target.getName())
                        .replace("%ping%", String.valueOf(ping)));
            } else {
                Common.tell(sender, Messages.INVALID_PLAYER);
            }
        }
    }

    private int getPing(Player player) {
        return player.getPing();
    }
}
