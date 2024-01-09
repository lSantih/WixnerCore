package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

@CommandAlias("smithingtable|stable")
@Description("Open an Smithing Table GUI")
public class SmithingCommand extends BaseCommand {

    @Default
    public void onAvil(Player player) {

        player.openInventory(Bukkit.createInventory(player, InventoryType.SMITHING, "Smithing Portable"));
    }
}
