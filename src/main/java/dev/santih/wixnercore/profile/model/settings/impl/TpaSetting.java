package dev.santih.wixnercore.profile.model.settings.impl;

import dev.morphia.annotations.Entity;
import dev.santih.wixnercore.profile.model.settings.ISetting;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

@Entity
public class TpaSetting implements ISetting {
	private boolean isEnabled = true;

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public void setEnabled(boolean newValue) {
		this.isEnabled = newValue;
	}

	@Override
	public String getDisplayName() {
		return "&bTpa Setting";
	}

	@Override
	public String getIdentifier() {
		return "tpa";
	}

	@Override
	public Material getIcon() {
		return Material.ENDER_PEARL;
	}

	@Override
	public List<String> getDescription() {
		return Arrays.asList(
				"",
				"&7Permite la peticion de teletransporteo",
				"&7de un usuario hacia vos. "
		);
	}

	@Override
	public int getMenuSlot() {
		return 1;
	}
}
