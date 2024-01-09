package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("forcechat")
@Description("Force a player to write something in the public chat")
public class ForceChatCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    @Syntax("<player> <message>")
    public void onForceChat(CommandSender sender, String targetName, String message) {
        if (Bukkit.getPlayer(targetName) == null) {
            Common.tell(sender, Messages.INVALID_PLAYER);
            return;
        }

        final Player targetPlayer = Bukkit.getPlayer(targetName);
        targetPlayer.chat(message);

        Common.tell(sender, Messages.FORCE_CHAT_SUCCESS
                .replace("%target_name%", targetPlayer.getName())
                .replace("%message%", message));
    }
}
