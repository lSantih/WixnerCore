package dev.santih.wixnercore.profile.model.settings;

import org.bukkit.Material;

import java.util.List;

public interface ISetting {

	boolean isEnabled();

	void setEnabled(boolean newValue);

	String getDisplayName();

	String getIdentifier();

	Material getIcon();

	List<String> getDescription(); //The lore of the item

	int getMenuSlot();

}
