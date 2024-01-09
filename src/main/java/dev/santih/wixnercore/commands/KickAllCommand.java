package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("kickall")
@Description("Kicks all players without admin permission")
public class KickAllCommand extends BaseCommand {

    @Default
    @CommandPermission("unitycore.command.kickall")
    public void onKickAll(Player player) {
        String kickMessage = Messages.KICKALL_REASON.replace("%player_name%", player.getName());
        int kickedPlayers = 0;
        for (final Player target : Bukkit.getOnlinePlayers()) {
            if (!target.hasPermission("shootcore.admin")) {
                target.kickPlayer(kickMessage);
                kickedPlayers++;
            }
        }

        Common.tell(player, Messages.KICKALL_SUCCESS.replace("%kicked_players%", String.valueOf(kickedPlayers)));
    }
}
