package dev.santih.wixnercore.features.staff.managers;

import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.profile.StaffProfile;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ModeManager {
	private final ItemManager itemManager;
	private final ProfileManager profileManager;

	public ModeManager(final ItemManager itemManager, final ProfileManager profileManager) {
		this.itemManager = itemManager;
		this.profileManager = profileManager;
	}
	public void enableStaffMode(final Player player) {
		player.setGameMode(GameMode.CREATIVE);
		player.setAllowFlight(true);
		player.setFlying(true);

		itemManager.giveItems(player);
	}

	public boolean isInMode(final Player player) {
		if(profileManager.getProfile(player.getUniqueId()) == null) return false;
		if(!(profileManager.getProfile(player.getUniqueId()) instanceof StaffProfile)) return false;

		StaffProfile profile = (StaffProfile) profileManager.getProfile(player.getUniqueId());
		return profile.isStaffModeEnabled();
	}

	public void disableStaffMode(final Player player) {
		player.setGameMode(GameMode.SURVIVAL);

	}
}
