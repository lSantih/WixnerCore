package dev.santih.wixnercore.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import dev.santih.strike.util.Common;
import dev.santih.wixnercore.features.staff.managers.ModeManager;
import dev.santih.wixnercore.profile.ProfileManager;
import dev.santih.wixnercore.profile.StaffProfile;
import org.bukkit.entity.Player;

@CommandAlias("staff")
public class StaffModeCommand extends BaseCommand {

	@Dependency
	private ModeManager modeManager;
	@Dependency

	private ProfileManager profileManager;

	@Default
	@CommandPermission("wixner.staff")
	public void handleStaffCommand(final Player player) {
		final StaffProfile playerProfile = (StaffProfile) profileManager.getProfile(player.getUniqueId());

		if(playerProfile.isStaffModeEnabled()) {
			modeManager.disableStaffMode(player);
			playerProfile.setStaffModeEnabled(false);
			Common.tell(player, "&cStaff mode disabled.");
			return;

		}

		playerProfile.setStaffModeEnabled(true);
		modeManager.enableStaffMode(player);
		Common.tell(player, "&aStaff mode enabled");
	}
}
