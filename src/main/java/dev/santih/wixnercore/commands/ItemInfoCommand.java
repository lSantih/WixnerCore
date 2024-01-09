package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CommandAlias("iteminfo")
public class ItemInfoCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("<player>")
    public void onItemInfo(Player player, @Optional String playerName) {
        Player target = playerName != null ? Bukkit.getPlayer(playerName) : player;
        ItemStack itemInHand = target.getInventory().getItemInMainHand();

        if (itemInHand.getType() == Material.AIR) {
            Common.tell(player, Messages.MUST_HOLD_ITEM);
            return;
        }

        BookMeta bookMeta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
        bookMeta.setTitle(ChatColor.BLACK  + "" + ChatColor.BOLD + "Item Info");
        bookMeta.setAuthor(ChatColor.GRAY + player.getName());

        List<String> pages = new ArrayList<>();

        String displayName = itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasDisplayName() ?
                itemInHand.getItemMeta().getDisplayName() : ChatColor.RED + "No display name";
        pages.add(ChatColor.BLACK + "" + ChatColor.BOLD + "Display Name:\n" + ChatColor.RESET + displayName);

        String lore = itemInHand.hasItemMeta() && itemInHand.getItemMeta().hasLore() ?
                String.join("\n", itemInHand.getItemMeta().getLore()) : ChatColor.RED + "No lore";
        pages.add(ChatColor.BLACK + "" + ChatColor.BOLD + "Lore:\n" + ChatColor.RESET + lore);

        List<String> enchantments = itemInHand.getEnchantments().entrySet().stream()
                .map(entry -> ChatColor.BLACK + "" + ChatColor.BOLD + entry.getKey().getKey().getKey() +
                        ": " + ChatColor.RESET + "" + entry.getValue())
                .collect(Collectors.toList());

        if (enchantments.isEmpty()) {
            enchantments.add(ChatColor.RED + "No enchants");
        }

        pages.add(ChatColor.BLACK + "" + ChatColor.BOLD + "Enchantments:\n" + ChatColor.RESET + String.join("\n", enchantments));

        String materialName = itemInHand.getType().name().toLowerCase().replace("_", " ");
        pages.add(ChatColor.BLACK + "" + ChatColor.BOLD + "Material:\n" + ChatColor.RESET + materialName);

        bookMeta.setPages(pages);

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        book.setItemMeta(bookMeta);

        player.openBook(book);
    }


}
