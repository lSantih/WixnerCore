package dev.santih.wixnercore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import dev.santih.wixnercore.config.Messages;
import dev.santih.wixnercore.managers.TpaManager;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.profile.model.TeleportRequest;
import dev.santih.strike.util.Common;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("tpdeny")
public class TpdenyCommand extends BaseCommand {

    @Dependency
    private ProfileManager profileManager;

    @Dependency
    private TpaManager tpaManager;

    @CommandCompletion("@players @nothing")
    @Default
    @Syntax("<player>")
    public void onTpdeny(Player sender, @Optional String playerName) {
        UUID requester;
        if (playerName != null) {
            Player player = Bukkit.getPlayer(playerName);
            if (player == null) {
                Common.tell(sender, Messages.INVALID_PLAYER.replace("%s", playerName));
                return;
            }
            requester = player.getUniqueId();
        } else {
            IProfile targetProfile = profileManager.getProfile(sender.getUniqueId());
            if (targetProfile == null || targetProfile.getTeleportRequestNumber() == 0) {
                Common.tell(sender, Messages.TPDENY_NO_PENDING_REQUESTS);
                return;
            }
            TeleportRequest firstRequest = targetProfile.getFirstTeleportRequest();
            requester = firstRequest.getRequester();
        }

        tpaManager.denyTeleport(sender.getUniqueId(), requester);
        Common.tell(sender, Messages.TPDENY_REQUEST_DENIED);
    }
}
