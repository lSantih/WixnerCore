package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.managers.TpaManager;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("tpa")
public class TpaCommand extends BaseCommand {

    @Dependency
    private TpaManager tpaManager;

    @Dependency
    private ProfileManager profileManager;

    @Default
    @CommandCompletion("@players")
    @Syntax("<player>")
    public void onTpa(Player sender, String playerName) {
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            Common.tell(sender, Messages.INVALID_PLAYER.replace("%s", playerName));
            return;
        }

        IProfile targetProfile = profileManager.getProfile(target.getUniqueId());
        if (targetProfile == null) {
            Common.tell(sender, Messages.TARGET_PROFILE_NOT_LOADED);
            return;
        }

        if (!targetProfile.getSettings().getTpaSetting().isEnabled()) {
            Common.tell(sender, Messages.TPA_TELEPORT_REQUEST_DISABLED.replace("%s", playerName));
            return;
        }

        if (targetProfile.hasPendingTeleportRequest(sender.getUniqueId())) {
            Common.tell(sender, Messages.TPA_ALREADY_SENT_REQUEST.replace("%s", playerName));
            return;
        }

        boolean success = tpaManager.requestTeleport(sender.getUniqueId(), target.getUniqueId());
        if (!success) {
            Common.tell(sender, "&cError: Could not send teleport request.");
            return;
        }

        Common.tell(sender, Messages.TPA_REQUEST_SENT.replace("%s", playerName));
        Common.tell(target, Messages.TPA_REQUEST_RECEIVED.replace("%s", sender.getName()));
    }

}
