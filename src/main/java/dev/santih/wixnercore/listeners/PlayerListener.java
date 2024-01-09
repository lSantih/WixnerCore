package dev.santih.wixnercore.listeners;

import dev.santih.wixnercore.WixnerCore;
import dev.santih.wixnercore.features.staff.managers.VanishManager;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final WixnerCore plugin;
    private final VanishManager vanishManager;

    public PlayerListener(final WixnerCore plugin) {
        this.plugin = plugin;
        this.vanishManager = plugin.getVanishManager();
    }
    @EventHandler
    public void handleJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if(!player.hasPermission("nordical.staff")) {
            vanishManager.hideVanishedPlayers(player);
        }
        //modeManager.enableStaffMode(player);
    }

}
