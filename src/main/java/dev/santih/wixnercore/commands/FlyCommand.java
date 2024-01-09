package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("fly")
@Description("Toggles fly mode for a player")
public class FlyCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("[player]")
    public void onFly(Player player, @Optional String targetName) {
        if (targetName == null) {
            toggleFly(player);
            if(player.getAllowFlight()) {
                Common.tell(player, Messages.FLY_SELF_ENABLED);
                return;
            }

            Common.tell(player, Messages.FLY_SELF_DISABLED);
        } else {
            if (!player.hasPermission("unitycore.fly.other")) {
                Common.tell(player, Messages.FLY_OTHER_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                Common.tell(player, Messages.INVALID_PLAYER);
                return;
            }

            toggleFly(target);

            String action = target.getAllowFlight() ? "enabled" : "disabled";
            Common.tell(player, Messages.FLY_SUCCESS
                    .replace("%action%", action)
                    .replace("%target_name%", target.getName()));
            Common.tell(target, Messages.FLY_RECEIVED
                    .replace("%action%", action)
                    .replace("%player_name%", player.getName()));
        }
    }

    private void toggleFly(Player player) {
        boolean currentFlyState = player.getAllowFlight();
        player.setAllowFlight(!currentFlyState);
        player.setFlying(!currentFlyState);

        if (player.getAllowFlight()) {
            player.teleport(player.getLocation().add(0, 0.5, 0));
        }
    }
}
