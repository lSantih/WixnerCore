package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Syntax;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand extends BaseCommand {

    @CommandAlias("rename")
    @Description("Rename the item in your hand")
    @Syntax("<display>")
    public static void onRename(Player player, String rename) {
        if(player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            Common.tell(player, Messages.MUST_HOLD_ITEM.replace("%player_name%", player.getName()));
            return;
        }

        final ItemStack playerItem = player.getInventory().getItemInMainHand();
        final ItemMeta playerItemMeta = playerItem.getItemMeta();
        playerItemMeta.setDisplayName((Common.colorize(rename)));
        playerItem.setItemMeta(playerItemMeta);
        Common.tell(player, Messages.RENAME_SUCCESS.replace("%name%", rename));
    }
}
