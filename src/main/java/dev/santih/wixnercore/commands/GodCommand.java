package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GodCommand extends BaseCommand {

    @CommandAlias("god")
    @Description("Toggles god mode")
    @CommandCompletion("@players")
    public void onFeed(Player player, @Optional String targetName) {
        if (targetName == null) {
            toggleGodMode(player);
            Common.tell(player, player.isInvulnerable() ? Messages.GOD_MODE_ENABLED : Messages.GOD_MODE_DISABLED);
            return;
        }

        if (Bukkit.getPlayer(targetName) == null) {
            Common.tell(player, Messages.INVALID_PLAYER);
            return;
        }

        final Player anotherPlayer = Bukkit.getPlayer(targetName);
        toggleGodMode(anotherPlayer);
        Common.tell(player, anotherPlayer.isInvulnerable() ? Messages.GOD_MODE_ENABLED_OTHER.replace("%player_name%", anotherPlayer.getName()) : Messages.GOD_MODE_DISABLED_OTHER.replace("%player_name%", anotherPlayer.getName()));
        Common.tell(anotherPlayer, anotherPlayer.isInvulnerable() ? Messages.GOD_MODE_ENABLED_BY.replace("%player_name%", player.getName()) : Messages.GOD_MODE_DISABLED_BY.replace("%player_name%", player.getName()));
    }

    private void toggleGodMode(final Player player) {
        player.setInvulnerable(!player.isInvulnerable());
    }
}
