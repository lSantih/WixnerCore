package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("settings")
public class SettingsCommand extends BaseCommand {

    @Dependency
    private ProfileManager profileManager;

    @CommandCompletion("on|off @nothing")
    @Subcommand("tpa")
    @Syntax("on/off")
    public void onTpa(Player sender, String value) {
        IProfile profile = profileManager.getProfile(sender.getUniqueId());
        if (profile == null) {
            MessageUtils.sendCoreMessage(sender, "&cError: Could not find your profile.");
            return;
        }

        if (value.equalsIgnoreCase("on")) {
            if (profile.getSettings().getMessageSetting().isEnabled()) {
                MessageUtils.sendCoreMessage(sender, "&cError: Teleport requests are already enabled.");
                return;
            }
            profile.getSettings().getTpaSetting().setEnabled(true);
            MessageUtils.sendCoreMessage(sender, "&aTeleport requests enabled.");
        } else if (value.equalsIgnoreCase("off")) {
            if (!profile.getSettings().getTpaSetting().isEnabled()) {
                MessageUtils.sendCoreMessage(sender, "&cError: Teleport requests are already disabled.");
                return;
            }
            profile.getSettings().getTpaSetting().setEnabled(false);
            MessageUtils.sendCoreMessage(sender, "&cTeleport requests disabled.");
        } else {
            MessageUtils.sendCoreMessage(sender, "&cError: Invalid value. Use 'on' or 'off'.");
        }
    }

    @CommandCompletion("on|off @nothing")
    @Subcommand("messages")
    @Syntax("on/off")
    public void onMessages(Player sender, String value) {
        IProfile profile = profileManager.getProfile(sender.getUniqueId());
        if (profile == null) {
            MessageUtils.sendCoreMessage(sender, "&cError: Could not find your profile.");
            return;
        }

        if (value.equalsIgnoreCase("on")) {
            if (profile.getSettings().getMessageSetting().isEnabled()) {
                MessageUtils.sendCoreMessage(sender, "&cError: Private messages are already enabled.");
                return;
            }
            profile.getSettings().getMessageSetting().setEnabled(true);
            MessageUtils.sendCoreMessage(sender, "&aPrivate messages enabled.");
        } else if (value.equalsIgnoreCase("off")) {
            if (!profile.getSettings().getMessageSetting().isEnabled()) {
                MessageUtils.sendCoreMessage(sender, "&cError: Private messages are already disabled.");
                return;
            }
            profile.getSettings().getMessageSetting().setEnabled(false);
            MessageUtils.sendCoreMessage(sender, "&cPrivate messages disabled.");
        } else {
            MessageUtils.sendCoreMessage(sender, "&cError: Invalid value. Use 'on' or 'off'.");
        }
    }

    @Subcommand("view")
    @Syntax("<player>")

    public void onView(Player sender, @Optional String playerName) {
        IProfile profile;
        if (playerName != null) {
            Player player = Bukkit.getPlayer(playerName);
            if (player == null) {
                MessageUtils.sendCoreMessage(sender, "&cError: Player " + playerName + " is not online.");
                return;
            }
            profile = profileManager.getProfile(player.getUniqueId());
        } else {
            profile = profileManager.getProfile(sender.getUniqueId());
        }

        if (profile == null) {
            MessageUtils.sendCoreMessage(sender, "&cError: Could not find the target's profile.");
            return;
        }

        boolean tpaEnabled = profile.getSettings().getMessageSetting().isEnabled();
        MessageUtils.sendCoreMessage(sender, "&aTeleport requests: " + (tpaEnabled ? "enabled" : "disabled"));
    }
}
