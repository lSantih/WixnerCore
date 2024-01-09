package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("enderchest|ender|echest|ec")
@Description("Open your own or another player's ender chest.")
public class EnderChestCommand extends BaseCommand {

    @Default
    @CommandPermission("unitycore.enderchest")
    @CommandCompletion("@players")
    @Syntax("<player>")
    public void onEnderChest(CommandSender sender, @Optional String targetName) {

        final Player player = (Player) sender;
        if (targetName == null) {
            player.openInventory(player.getEnderChest());
            Common.tell(player, Messages.ENDERCHEST_OPEN);
        }else {
            if(Bukkit.getPlayer(targetName) == null) {
                Common.tell(player, Messages.INVALID_PLAYER);
                return;
            }
            final Player target = Bukkit.getPlayer(targetName);
            player.openInventory(target.getEnderChest());
            Common.tell(player, Messages.ENDERCHEST_OPEN_OTHER.replaceAll("%player_name%", target.getName()));
        }
    }
}
