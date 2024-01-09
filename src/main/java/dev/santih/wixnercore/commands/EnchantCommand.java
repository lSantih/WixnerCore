package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@CommandAlias("enchant")
public class EnchantCommand extends BaseCommand {

    private static final Map<String, Enchantment> ENCHANTMENTS = new HashMap<>();

    static {
        for (Enchantment enchantment : Enchantment.values()) {
            ENCHANTMENTS.put(enchantment.getKey().getKey().replace(' ', '_'), enchantment);
        }
    }

    @Default
    @CommandCompletion("@enchantments @nothing @players")
    @Syntax("<enchantment> [level] [player]")
    @Description("Enchant an item held by a player.")

    public void onEnchant(CommandSender sender, String enchantmentName, @Optional Integer level, @Optional String targetPlayerName) {
        final Player targetPlayer = getTargetPlayer(sender, targetPlayerName);

        if (targetPlayer == null) return;

        final ItemStack item = targetPlayer.getInventory().getItemInMainHand();

        if (item == null || item.getType() == Material.AIR) {
            Common.tell(sender, Messages.MUST_HOLD_ITEM.replaceAll("%player_name%", targetPlayer.getName()));
            return;
        }

        final Enchantment enchantment = ENCHANTMENTS.get(enchantmentName.toLowerCase());
        if (enchantment == null) {
            Common.tell(sender, Messages.INVALID_ENCHANTMENT);
            return;
        }

        level = level == null ? 1 : level;

        if(level == 0) {
            item.removeEnchantment(enchantment);
        }else {
            item.addUnsafeEnchantment(enchantment, level);
        }
        Common.tell(sender, Messages.ENCHANT
                .replaceAll("%material%", item.getType().name().toLowerCase().replace("_", " "))
                .replaceAll("%player_name%", targetPlayer.getName())
                .replaceAll("%enchantment%", enchantmentName.toLowerCase().replace("_", " "))
                .replaceAll("%level%", String.valueOf(level)));

        if (targetPlayer != sender) {
            Common.tell(sender, Messages.ENCHANT_TARGET
                    .replaceAll("%material%", item.getType().name().toLowerCase().replace("_", " "))
                    .replaceAll("%enchantment%", enchantmentName.toLowerCase().replace("_", " "))
                    .replaceAll("%level%", String.valueOf(level)));
        }
    }

    private Player getTargetPlayer(CommandSender sender, String targetPlayerName) {
        if (targetPlayerName == null) {
            if (!(sender instanceof Player)) {
                Common.tell(sender, Messages.MUST_SPECIFY_PLAYER);
                return null;
            } else {
                return (Player) sender;
            }
        } else {
            Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
            if (targetPlayer == null) {
                Common.tell(sender, Messages.INVALID_PLAYER);
                return null;
            }
            return targetPlayer;
        }
    }
}
