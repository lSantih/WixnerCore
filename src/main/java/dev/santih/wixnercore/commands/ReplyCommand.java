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

import java.util.UUID;

@CommandAlias("reply|r")
public class ReplyCommand extends BaseCommand {
    @Dependency
    private ProfileManager profileManager;

    @Default
    @Syntax("<mensaje>")
    public void onReply(Player sender, String message) {
        IProfile senderProfile = profileManager.getProfile(sender.getUniqueId());
        if (senderProfile == null) {
            Common.tell(sender, Messages.PROFILE_NOT_LOADED);
            return;
        }

        UUID lastMessenger = senderProfile.getLastMessenger();
        if (lastMessenger == null) {
            Common.tell(sender, Messages.NO_ONE_TO_REPLY);
            return;
        }

        Player recipient = Bukkit.getPlayer(lastMessenger);
        if (recipient == null) {
            Common.tell(sender, Messages.RECIPIENT_NOT_ONLINE);
            return;
        }

        IProfile recipientProfile = profileManager.getProfile(recipient.getUniqueId());
        if (recipientProfile == null) {
            Common.tell(sender, Messages.TARGET_PROFILE_NOT_LOADED);
            return;
        }

        if (!recipientProfile.getSettings().getMessageSetting().isEnabled()) {
            Common.tell(sender, Messages.RECIPIENT_MESSAGES_DISABLED.replace("%recipient%", recipient.getName()));
            return;
        }

        recipientProfile.setLastMessenger(sender.getUniqueId());
        MessageUtils.sendPrivateMessage(sender, recipient, message);
    }
}