package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.utils.MessageUtils;
import dev.santih.strike.util.Common;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("clear")
public class ClearCommand extends BaseCommand {

    @Default
    @CommandPermission("unitycore.clear")
    @CommandCompletion("@players")
    @Description("Clear a player's inventory.")
    @CommandAlias("ci|clean")
    public void onClear(CommandSender sender, @Optional @Flags("other") Player target) {
        if (target == null) {
            if (!(sender instanceof Player)) {
                MessageUtils.sendCoreMessage(sender, Messages.MUST_SPECIFY_PLAYER);
                return;
            }
            target = (Player) sender;
        }

        target.getInventory().clear();
        Common.tell(target, Messages.INVENTORY_CLEAR_TARGET.replaceAll("%player_name%", sender.getName()));
        if (!sender.equals(target)) {
            Common.tell(sender, Messages.INVENTORY_CLEAR.replaceAll("%player_name%", target.getName()));
        }
    }
}
