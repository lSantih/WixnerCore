package dev.santih.wixnercore.profile;

import dev.morphia.annotations.Entity;

import java.util.UUID;

@Entity(value = "users", useDiscriminator = false)
public interface IStaffProfile extends IProfile {

	UUID getUuid();
	boolean isStaffModeEnabled();

	void setStaffModeEnabled(boolean staffModeEnabled);

	boolean isVanishEnabled();

	void setVanishEnabled(boolean vanishEnabled);
}
