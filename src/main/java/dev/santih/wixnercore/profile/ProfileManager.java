package dev.santih.wixnercore.profile;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    private Map<UUID, IProfile> profiles = new HashMap<>();

    public void loadProfile(IProfile profile) {
        Bukkit.broadcastMessage(String.valueOf(profile == null));

        profiles.put(profile.getUuid(), profile);
        Bukkit.broadcastMessage(String.valueOf(profiles.containsKey(profile.getUuid())));
        Bukkit.broadcastMessage(String.valueOf(profiles.get(profile.getUuid())));
    }

    public void unloadProfile(IProfile profile) {
        // Save profile to storage

        profiles.remove(profile.getUuid());
    }

    public void cacheAllOnline() {
        //Bukkit.getOnlinePlayers().forEach(player -> loadProfile(player.getUniqueId()));
    }

    public IProfile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }
}
