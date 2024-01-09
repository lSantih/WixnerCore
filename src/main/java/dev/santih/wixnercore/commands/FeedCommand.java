package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("feed")
@Description("Feed a player")
public class FeedCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("[player]")
    public void onFeed(Player player, @Optional String targetName) {

        if (targetName == null) {
            feedPlayer(player);
            Common.tell(player, Messages.FEED_SELF);
        } else {
            if (!player.hasPermission("unitycore.feed.other")) {
                Common.tell(player, Messages.FEED_OTHER_PERMISSION);
                return;
            }

            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                Common.tell(player, Messages.INVALID_PLAYER);
                return;
            }

            feedPlayer(target);
            Common.tell(player, Messages.FEED_SUCCESS.replace("%target_name%", target.getName()));
            Common.tell(target, Messages.FEED_RECEIVED.replace("%player_name%", player.getName()));
        }
    }

    private void feedPlayer(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(10);
        player.setExhaustion(0f);
    }
}
