package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("lore")
public class LoreCommand extends BaseCommand {

    @Subcommand("add")
    @Description("Add a lore line to the player item")
    public void onRename(Player player, String loreLine) {
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            Common.tell(player, Messages.MUST_HOLD_ITEM);
            return;
        }

        final ItemStack playerItem = player.getInventory().getItemInMainHand();
        final ItemMeta playerItemMeta = playerItem.getItemMeta();

        final List<String> currentItemLore = playerItemMeta.hasLore() ? playerItemMeta.getLore() : new ArrayList<>();
        currentItemLore.add(Common.colorize(loreLine));

        playerItemMeta.setLore(currentItemLore);
        playerItem.setItemMeta(playerItemMeta);
        Common.tell(player, Messages.LORE_LINE_ADDED.replace("%lore%", Common.stripColor(loreLine)).replace("%position%", String.valueOf(currentItemLore.size())));
    }

    @Subcommand("remove")
    @Description("Remove a lore line from the player item")
    public void onLoreRemove(Player player, @Optional @Default("-1") int lineNumber) {
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            Common.tell(player, Messages.MUST_HOLD_ITEM);
            return;
        }

        final ItemStack playerItem = player.getInventory().getItemInMainHand();
        final ItemMeta playerItemMeta = playerItem.getItemMeta();

        if (!playerItemMeta.hasLore()) {
            Common.tell(player, Messages.LORE_NO_LORE);
            return;
        }

        final List<String> currentItemLore = playerItemMeta.getLore();
        if (lineNumber > currentItemLore.size()) {
            Common.tell(player, Messages.LORE_INVALID_LINE_RANGE.replace("%max%", String.valueOf(currentItemLore.size())));
            return;
        }

        if (lineNumber == -1) lineNumber = currentItemLore.size() - 1;
        else lineNumber--;

        currentItemLore.remove(lineNumber);

        playerItemMeta.setLore(currentItemLore);
        playerItem.setItemMeta(playerItemMeta);
        Common.tell(player, Messages.LORE_LINE_REMOVED.replace("%position%", String.valueOf(lineNumber + 1)));
    }

    @Subcommand("set")
    @Description("Set a lore line in the player item")
    public void onLoreSet(Player player, int line, String text) {
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            Common.tell(player, Messages.MUST_HOLD_ITEM);
            return;
        }

        final ItemStack playerItem = player.getInventory().getItemInMainHand();
        final ItemMeta playerItemMeta = playerItem.getItemMeta();

        final List<String> currentItemLore = playerItemMeta.hasLore() ? playerItemMeta.getLore() : new ArrayList<>();

        // If the specified line is out of the current lore range, add empty lines until reaching the specified line
        while (currentItemLore.size() < line) {
            currentItemLore.add("");
        }

        // Set the specified line to the provided text
        currentItemLore.set(line - 1, Common.colorize(text));

        playerItemMeta.setLore(currentItemLore);
        playerItem.setItemMeta(playerItemMeta);
        Common.tell(player, Messages.LORE_LINE_SET
                .replace("%line%", String.valueOf(line))
                .replace("%text%", Common.stripColor(text)));
    }

    @Subcommand("reset")
    @Description("Reset the lore of the player item")
    public void onLoreReset(Player player) {
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            Common.tell(player, Messages.MUST_HOLD_ITEM);
            return;
        }

        final ItemStack playerItem = player.getInventory().getItemInMainHand();
        final ItemMeta playerItemMeta = playerItem.getItemMeta();

        playerItemMeta.setLore(null);

        playerItem.setItemMeta(playerItemMeta);
        Common.tell(player, Messages.LORE_RESET);
    }

}
