package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

@CommandAlias("loom")
@Description("Open an Loom GUI")
public class LoomCommand extends BaseCommand {

    @Default
    public void onAvil(Player player) {
        player.openInventory(Bukkit.createInventory(player, InventoryType.LOOM, "Loom Portable"));
    }
}
