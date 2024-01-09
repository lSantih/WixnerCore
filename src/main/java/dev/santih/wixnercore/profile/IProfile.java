package dev.santih.wixnercore.profile;

import dev.morphia.annotations.Entity;
import dev.santih.wixnercore.profile.model.EconomyAccount;
import dev.santih.wixnercore.profile.model.PlayerHome;
import dev.santih.wixnercore.profile.model.Settings;
import dev.santih.wixnercore.profile.model.TeleportRequest;
import org.bukkit.Location;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity(value = "users", useDiscriminator = false)
public interface IProfile {

	UUID getUuid();

	UUID getLastMessenger();

	void setLastMessenger(UUID sender);

	int getBalance();

	void setBalance(int amount);

	Set<PlayerHome> getHomes();

	Settings getSettings();

	void addTeleportRequest(TeleportRequest request);

	void removeTeleportRequest(TeleportRequest request);

	TeleportRequest getFirstTeleportRequest();

	int getTeleportRequestNumber();

	boolean hasPendingTeleportRequest(UUID sender);

	Optional<TeleportRequest> findTeleportRequest(UUID sender);

	EconomyAccount getBankAccount();

	void setBankAccount(EconomyAccount bankAccount);

	void addHome(String homeName, Location homeLOcation);

	void removeHome(PlayerHome home);

	Optional<PlayerHome> getHomeByName(String name);

	Optional<PlayerHome> getFirstHome();

	int getHomesCount();

	List<String> getHomeNames();
}
