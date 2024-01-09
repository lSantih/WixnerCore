package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Syntax;
import dev.santih.strike.util.Common;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import dev.santih.wixnercore.config.Messages;

@CommandAlias("setarmorhex")
public class SetArmorHexCommand extends BaseCommand {

    @Default
    @Syntax("<hexcolor>")
    public void onSetArmorHex(Player player, String hexColor) {
        // Check if the player is holding a leather armor item
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand == null || !(itemInHand.getItemMeta() instanceof LeatherArmorMeta)) {
            Common.tell(player, Messages.MUST_HOLD_LEATHER_ARMOR);
            return;
        }

        // Parse the hex color
        Color color;
        try {
            color = Color.fromRGB(Integer.parseInt(hexColor, 16));
        } catch (NumberFormatException e) {
            Common.tell(player, Messages.INVALID_HEX_COLOR);
            return;
        }

        // Set the color of the leather armor item
        LeatherArmorMeta meta = (LeatherArmorMeta) itemInHand.getItemMeta();
        meta.setColor(color);
        itemInHand.setItemMeta(meta);
        Common.tell(player, Messages.LEATHER_ARMOR_COLOR_SET.replace("%hexColor%", hexColor));
    }
}
