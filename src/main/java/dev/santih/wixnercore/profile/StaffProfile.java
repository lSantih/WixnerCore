package dev.santih.wixnercore.profile;

import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import dev.morphia.annotations.Transient;
import org.bukkit.Bukkit;

import java.util.UUID;

public class StaffProfile extends Profile implements IStaffProfile {

	@Id
	@Indexed(options = @IndexOptions(unique = true))
	private UUID uuid;
	
	@Transient
	private boolean staffModeEnabled = false;

	@Transient
	private boolean vanishEnabled = false;

	@Override
	public boolean isStaffModeEnabled() {
		return staffModeEnabled;
	}


	public UUID getUuid() {
		return super.getUuid();
	}

	@Override
	public void setStaffModeEnabled(boolean staffModeEnabled) {
		this.staffModeEnabled = staffModeEnabled;
	}

	@Override
	public boolean isVanishEnabled() {
		return vanishEnabled;
	}

	@Override
	public void setVanishEnabled(boolean vanishEnabled) {
		this.vanishEnabled = vanishEnabled;
	}
}
