package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("heal")
@Description("Heal a player")
public class HealCommand extends BaseCommand {

    @Default
    @Syntax("<player>")
    public void onHeal(Player player, @Optional String targetName) {
        if (targetName == null) {
            healPlayer(player);
            Common.tell(player, Messages.HEAL_SUCCESS_SELF);
        } else {
            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                Common.tell(player, Messages.INVALID_PLAYER);
                return;
            }

            healPlayer(target);
            Common.tell(player, Messages.HEAL_SUCCESS_OTHER.replace("%player_name%", target.getName()));
            Common.tell(target, Messages.HEAL_SUCCESS_TARGET.replace("%healer_name%", player.getName()));
        }
    }

    private void healPlayer(Player player) {
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setSaturation(10);
        player.setExhaustion(0f);
    }
}
