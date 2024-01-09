package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.utils.MessageUtils;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("msg")
public class MsgCommand extends BaseCommand {

    @Dependency
    private ProfileManager profileManager;

    @Default
    @CommandCompletion("@players")
    @Syntax("<player> <message>")
    public void onMsg(Player sender, String playerName, String message) {
        final Player recipient = Bukkit.getPlayer(playerName);
        if (recipient == null) {
            Common.tell(sender, Messages.INVALID_PLAYER.replace("%player_name%", playerName));
            return;
        }

        final IProfile recipientProfile = profileManager.getProfile(recipient.getUniqueId());
        if (recipientProfile == null) {
            Common.tell(sender, Messages.TARGET_PROFILE_NOT_LOADED);
            return;
        }

        if (!recipientProfile.getSettings().getMessageSetting().isEnabled()) {
            Common.tell(sender, Messages.MSG_RECIPIENT_MESSAGES_DISABLED.replace("%player%", playerName));
            return;
        }

        recipientProfile.setLastMessenger(sender.getUniqueId());
        MessageUtils.sendPrivateMessage(sender, recipient, message);
    }
}
