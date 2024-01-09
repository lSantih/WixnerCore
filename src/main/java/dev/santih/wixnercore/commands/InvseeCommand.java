package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand extends BaseCommand {

    @CommandAlias("invsee")
    @Description("View a player inventory")
    @CommandCompletion("@players")
    @CommandPermission("unitycore.command.invsee")
    @Syntax("<target>")
    public void onInvsee(Player player, String target) {
        if(Bukkit.getPlayer(target) == null) {
            Common.tell(player, Messages.INVALID_PLAYER);
            return;
        }

        final Player anotherPlayer = Bukkit.getPlayer(target);
        final Inventory inventory = anotherPlayer.getInventory();

        player.closeInventory();
        player.openInventory(inventory);
        Common.tell(player, Messages.INVSEE_OPENED.replace("%player_name%", anotherPlayer.getName()));
    }

}
