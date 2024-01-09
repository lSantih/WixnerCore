package dev.santih.wixnercore.managers;

import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.profile.model.TeleportRequest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpaManager {
    private ProfileManager profileManager;

    public TpaManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public boolean requestTeleport(UUID requester, UUID target) {
        IProfile targetProfile = profileManager.getProfile(target);
        if (targetProfile == null) return false;
        if (!targetProfile.getSettings().getTpaSetting().isEnabled()) return false;
        if(targetProfile.hasPendingTeleportRequest(target)) return false;

        TeleportRequest request = new TeleportRequest(requester, target);
        targetProfile.addTeleportRequest(request);
        return true;
    }

    public void acceptTeleport(UUID target, UUID requester) {
        IProfile targetProfile = profileManager.getProfile(target);
        if (targetProfile == null) {
            return;
        }

        targetProfile.findTeleportRequest(requester).ifPresent(request -> {
            Player requesterPlayer = Bukkit.getPlayer(request.getRequester());
            Player targetPlayer = Bukkit.getPlayer(target);
            if (requesterPlayer != null && targetPlayer != null) {
                requesterPlayer.teleport(targetPlayer);
            }

            targetProfile.removeTeleportRequest(request);
        });
    }

    public void denyTeleport(UUID target, UUID requester) {
        IProfile targetProfile = profileManager.getProfile(target);
        if (targetProfile == null) {
            return;
        }

        targetProfile.findTeleportRequest(requester).ifPresent(request -> {
            targetProfile.removeTeleportRequest(request);
        });
    }
}
