package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.command.CommandSender;

@CommandAlias("broadcast|bcast")
public class BroadcastCommand extends BaseCommand {

    @Default
    @CommandPermission("unitycore.broadcast")
    @Syntax("<message>")
    @Description("Broadcast a message to all players.")
    public void onBroadcast(CommandSender sender, String[] args) {
        String message = String.join(" ", args);

        Common.broadcast(Messages.BROADCAST_PREFIX + message);
    }
}
