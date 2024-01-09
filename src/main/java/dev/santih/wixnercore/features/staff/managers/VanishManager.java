package dev.santih.wixnercore.features.staff.managers;

import dev.santih.wixnercore.WixnerCore;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.profile.StaffProfile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VanishManager {

	private final ProfileManager profileManager;

	private final WixnerCore plugin;
	public VanishManager(final ProfileManager profileManager, final WixnerCore plugin) {
		this.profileManager = profileManager;
		this.plugin = plugin;
	}
	public void enableVanish(final Player player) {
		if(profileManager.getProfile(player.getUniqueId()) == null || (!(profileManager.getProfile(player.getUniqueId()) instanceof StaffProfile))) return;

		final StaffProfile profile = (StaffProfile) profileManager.getProfile(player.getUniqueId());

		profile.setVanishEnabled(true);

		Bukkit.getOnlinePlayers().stream()
				.filter(target -> target != player)
				.filter(target -> !target.hasPermission("nordical.staff"))
				.forEach(target -> {
					target.hidePlayer(plugin, player);
				});

	}

	public void disableVanish(final Player player) {
		if(profileManager.getProfile(player.getUniqueId()) == null || (!(profileManager.getProfile(player.getUniqueId()) instanceof StaffProfile))) return;

		final StaffProfile profile = (StaffProfile) profileManager.getProfile(player.getUniqueId());

		profile.setVanishEnabled(false);

		Bukkit.getOnlinePlayers().forEach(target -> {
			target.showPlayer(plugin, player);
		});
	}

	public void hideVanishedPlayers(final Player player) {
		Bukkit.getOnlinePlayers().stream()
				.filter(target -> target.hasPermission("nordical.staff"))
				.filter(target -> profileManager.getProfile(target.getUniqueId()) != null)
				.filter(target -> profileManager.getProfile(target.getUniqueId()) instanceof StaffProfile)
				.forEach(target -> {
					StaffProfile staffProfile = (StaffProfile) profileManager.getProfile(target.getUniqueId());
					if(staffProfile.isVanishEnabled()) {
						player.hidePlayer(target);
					}
				});
	}

	public void toggleVanish(final Player player) {
		if(isVanished(player)) {
			disableVanish(player);
			return;
		}

		enableVanish(player);
	}

	public boolean isVanished(final Player player) {
		if(profileManager.getProfile(player.getUniqueId()) == null || (!(profileManager.getProfile(player.getUniqueId()) instanceof StaffProfile))) return false;

		final StaffProfile profile = (StaffProfile) profileManager.getProfile(player.getUniqueId());
		return profile != null && profile.isVanishEnabled();
	}
}
