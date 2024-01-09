package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("repairall")
@Description("Repairs all items in your inventory or the inventory of another player")
public class RepairAllCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("<target>")
    public void onRepair(final Player player, @Optional final String targetName) {
        if (targetName == null) {
            final int repairedItems = repairInventory(player);
            Common.tell(player, Messages.REPAIR_ALL_SUCCESS_SELF.replace("%count%", String.valueOf(repairedItems)));
        } else {
            final Player target = Bukkit.getPlayer(targetName);
            if (target != null) {
                final int repairedItems = repairInventory(target);
                Common.tell(player, Messages.REPAIR_ALL_SUCCESS_OTHER.replace("%count%", String.valueOf(repairedItems)).replace("%target%", target.getName()));
                Common.tell(target, Messages.REPAIR_ALL_SUCCESS_TARGET.replace("%count%", String.valueOf(repairedItems)).replace("%player%", player.getName()));
            } else {
                Common.tell(player, Messages.INVALID_PLAYER);
            }
        }
    }

    private int repairInventory(final Player player) {
        int repairedItems = 0;
        for (final ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType().getMaxDurability() > 0) {
                item.setDurability((short) 0);
                repairedItems++;
            }
        }
        return repairedItems;
    }
}
