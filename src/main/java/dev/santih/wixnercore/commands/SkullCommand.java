package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.WixnerCore;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

@CommandAlias("skull")
public class SkullCommand extends BaseCommand {

    @Dependency
    private WixnerCore plugin;

    @Subcommand("player")
    @Syntax("<player>")
    public void onSkullPlayer(Player player, String playerName) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) skull.getItemMeta();
                meta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
                skull.setItemMeta(meta);

                player.getInventory().addItem(skull);
            }
        }.runTaskAsynchronously(plugin);

        Common.tell(player, Messages.SKULL_RECEIVED.replace("%playerName%", playerName));
    }

    @Default
    public void onSkullSelf(Player player) {


        player.getInventory().addItem(createSkull(player));
        Common.tell(player, Messages.SKULL_RECEIVED_SELF);
    }

    private ItemStack createSkull(final Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);

        return skull;
    }
}
