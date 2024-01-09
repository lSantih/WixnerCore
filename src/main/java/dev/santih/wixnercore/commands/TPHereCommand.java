package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("tphere")
public class TPHereCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @CommandAlias("s")
    @Syntax("<player>")
    public void onTPHere(Player sender, @Single String targetName) {
        Player target = Bukkit.getPlayer(targetName);

        if (target == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        target.teleport(sender.getLocation());
        Common.tell(sender, Messages.TPHERE_TELEPORTED_TARGET.replace("%s", target.getName()));
        Common.tell(target, Messages.TPHERE_TELEPORTED_SELF.replace("%s", sender.getName()));
    }
}
