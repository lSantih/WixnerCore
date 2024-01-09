package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("repair")
@Description("Repairs the item in your hand or the item in another player's hand")
public class RepairCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("<target>")
    public void onRepair(Player player, @Optional String targetName) {
        if (targetName == null) {
            if (repairItem(player.getItemInHand())) {
                Common.tell(player, Messages.REPAIR_SUCCESS_SELF);
            } else {
                Common.tell(player, Messages.REPAIR_FAILED);
            }
        } else {
            Player target = Bukkit.getPlayer(targetName);
            if (target != null) {
                if (repairItem(target.getItemInHand())) {
                    Common.tell(player, Messages.REPAIR_SUCCESS_OTHER.replace("%target%", target.getName()));
                    Common.tell(target, Messages.REPAIR_SUCCESS_TARGET.replace("%player%", player.getName()));
                } else {
                    Common.tell(player, Messages.REPAIR_FAILED);
                }
            } else {
                Common.tell(player, Messages.INVALID_PLAYER);
            }
        }
    }

    private boolean repairItem(ItemStack item) {
        if (item != null && item.getType().getMaxDurability() > 0) {
            item.setDurability((short) 0);
            return true;
        } else {
            return false;
        }
    }
}
