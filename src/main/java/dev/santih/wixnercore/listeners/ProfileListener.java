package dev.santih.wixnercore.listeners;

import dev.santih.wixnercore.managers.DatabaseManager;
import dev.santih.wixnercore.profile.IProfile;
import dev.santih.wixnercore.profile.Profile;
import dev.santih.wixnercore.profile.ProfileManager;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@AllArgsConstructor
public class ProfileListener implements Listener {

    private final DatabaseManager databaseManager;
    private final ProfileManager profileManager;

    @EventHandler
    private void handleJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();

        Bukkit.getLogger().info("PlayerJoinEvent handled for player: " + player.getName());

        //profileManager.loadProfile(uuid);
        IProfile playerProfile = databaseManager.getProfileOrInput(player);

        if (playerProfile != null) {
            // Log that the profile was retrieved or created
            profileManager.loadProfile(playerProfile);
            Bukkit.getLogger().info("Profile retrieved or created for player: " + player.getName());
        } else {
            // Log an error if the profile is null (you can adjust this)
            Bukkit.getLogger().severe("Failed to retrieve or create profile for player: " + player.getName());
        }
    }

    @EventHandler
    private void handleQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        final IProfile playerProfile = profileManager.getProfile(player.getUniqueId());
        if(playerProfile == null) {
            Bukkit.getLogger().info("Could not save profile for player " + player.getName() + " because its not loaded.");
            return;
        }

        databaseManager.saveProfile(playerProfile);
        profileManager.unloadProfile(playerProfile);
    }
}