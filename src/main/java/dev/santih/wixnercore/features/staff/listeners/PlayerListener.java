package dev.santih.wixnercore.features.staff.listeners;

import dev.santih.wixnercore.features.staff.managers.ModeManager;
import dev.santih.wixnercore.features.staff.managers.VanishManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	private final ModeManager modeManager;

	private final VanishManager vanishManager;
	public PlayerListener(final ModeManager modeManager, final VanishManager vanishManager) {
		this.modeManager = modeManager;
		this.vanishManager = vanishManager;
	}

	@EventHandler
	public void handleJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();

		if(!player.hasPermission("nordical.staff")) {
			vanishManager.hideVanishedPlayers(player);
		}
		//modeManager.enableStaffMode(player);
	}

	@EventHandler
	public void handleQuit(final PlayerQuitEvent event) {
		final Player player = event.getPlayer();

		//modeManager.disableStaffMode(player);
	}

}
