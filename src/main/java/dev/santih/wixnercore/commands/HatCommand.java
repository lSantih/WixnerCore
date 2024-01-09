package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("hat")
@Description("Put the item in your hand on your head as a hat")
@CommandPermission("unitycore.command.hat")
public class HatCommand extends BaseCommand {

    @Default
    public void onHat(Player player) {
        ItemStack hand = player.getInventory().getItemInMainHand();

        if (hand.getType() == Material.AIR) {
            Common.tell(player, Messages.MUST_HOLD_ITEM.replace("%player_name%", player.getName()));
            return;
        }

        ItemStack head = player.getInventory().getHelmet();
        player.getInventory().setHelmet(hand);
        player.getInventory().setItemInMainHand(head);

        Common.tell(player, Messages.HAT_SUCCESS);
    }
}
