package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@CommandAlias("disposal")
public class DisposalCommand extends BaseCommand {

    @Default
    @CommandPermission("unitycore.disposal")
    @Description("Open a disposal inventory.")
    public void onDisposal(Player player) {
        Inventory disposalInventory = Bukkit.createInventory(player, 36, "Disposal");
        player.openInventory(disposalInventory);
        Common.tell(player, Messages.DISPOSAL_OPEN);
    }
}
