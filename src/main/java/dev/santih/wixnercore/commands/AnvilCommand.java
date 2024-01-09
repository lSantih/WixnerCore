package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

@CommandAlias("anvil")
@Description("Open an Anvil GUI to repair items")
public class AnvilCommand extends BaseCommand {

    @Default
    public void onAvil(Player player) {

        player.openInventory(Bukkit.createInventory(player, InventoryType.ANVIL, "Anvil Portable"));
    }
}
