package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Syntax;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("kill")
@Description("Kill a player or yourself")
public class KillCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("<target>")
    public void onKill(Player player, String targetName) {
        if (targetName == null) {
            player.setHealth(0);
            Common.tell(player, Messages.KILL_KILLED_SELF);
            return;
        }

        if(Bukkit.getPlayer(targetName) == null) {
            Common.tell(player, Messages.INVALID_PLAYER);
            return;
        }

        final Player target = Bukkit.getPlayer(targetName);

        target.setHealth(0);
        Common.tell(target, Messages.KILL_KILLED_BY.replace("%killer_name%", player.getName()));
        Common.tell(player, Messages.KILL_KILLED.replace("%target_name%", target.getName()));
    }
}
