package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("give|i")
@Description("Give items to a player.")
public class GiveCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players @itemstack @nothing")
    @Syntax("<player> <item> [amount]")
    @CommandPermission("unitycore.command.give")
    public void onGive(CommandSender sender, String playerName, Material material, @Optional @Default("1") Integer amount) {
        Player target = Bukkit.getPlayerExact(playerName);
        if (target == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        if (material == null) {
            Common.tell(sender, Messages.GIVE_INVALID_ITEM);
            return;
        }

        final ItemStack giveStack = new ItemStack(material, amount);

        if (target.getInventory().addItem(giveStack).isEmpty()) {
            Common.tell(sender, Messages.GIVE_SUCCESS
                    .replace("%amount%", String.valueOf(amount))
                    .replace("%item%", material.name())
                    .replace("%target_name%", target.getName()));
        } else {
            Common.tell(sender, Messages.GIVE_INVENTORY_FULL
                    .replace("%target_name%", target.getName()));
        }
    }
}
