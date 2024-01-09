package dev.santih.wixnercore.features.staff.listeners;

import dev.santih.wixnercore.features.staff.managers.ModeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class ProtectionListener implements Listener {

	private final ModeManager modeManager;

	public ProtectionListener(final ModeManager modeManager) {
		this.modeManager = modeManager;
	}

	@EventHandler
	public void handleBreak(final BlockBreakEvent event) {
		final Player player = event.getPlayer();
		if(!modeManager.isInMode(player)) return;

		event.setCancelled(true);
	}

	@EventHandler
	public void handlePlace(final BlockPlaceEvent event) {
		final Player player = event.getPlayer();

		event.setCancelled(modeManager.isInMode(player));
	}

	@EventHandler
	public void handleDamage(final EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) return;

		final Player player = (Player) event.getEntity();
		final Player damager = (Player) event.getDamager();

		event.setCancelled((modeManager.isInMode(damager) || modeManager.isInMode(player)));
	}

	@EventHandler
	public void handlePveDamage(final EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) return;

		final Player player = (Player) event.getEntity();

		event.setCancelled(modeManager.isInMode(player));
	}
}
