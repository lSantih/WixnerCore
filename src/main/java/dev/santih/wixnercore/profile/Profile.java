package dev.santih.wixnercore.profile;

import dev.morphia.annotations.*;
import dev.santih.wixnercore.profile.model.EconomyAccount;
import dev.santih.wixnercore.profile.model.PlayerHome;
import dev.santih.wixnercore.profile.model.Settings;
import dev.santih.wixnercore.profile.model.TeleportRequest;
import dev.santih.wixnercore.utils.SimpleLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Entity(value = "users", useDiscriminator = false)
public class Profile implements IProfile {

	@Id
	@Indexed(options = @IndexOptions(unique = true))
	private UUID uuid;
	@Transient
	private UUID lastMessenger;

	@Property("balance")
	@Getter
	@Setter
	private int balance = 50;

	@Property("homes")
	@Getter
	private Set<PlayerHome> homes = new HashSet<>();

	@Property("settings")
	@Getter
	private Settings settings = new Settings();

	@Transient
	private Set<TeleportRequest> teleportRequests = new HashSet<>();

	public UUID getUuid() {
		return uuid;
	}

	public void addTeleportRequest(TeleportRequest teleportRequest) {
		teleportRequests.add(teleportRequest);
	}

	public void removeTeleportRequest(TeleportRequest teleportRequest) {
		teleportRequests.remove(teleportRequest);
	}

	public TeleportRequest getFirstTeleportRequest() {
		return teleportRequests.stream().iterator().next();
	}

	public int getTeleportRequestNumber() {
		return teleportRequests.size();
	}

	public boolean hasPendingTeleportRequest(UUID sender) {
		return teleportRequests.stream()
				.filter(request -> request.getRequester().equals(sender))
				.findAny()
				.isPresent();
	}

	public Optional<TeleportRequest> findTeleportRequest(UUID sender) {
		if(!hasPendingTeleportRequest(sender)) return Optional.empty();

		return teleportRequests.stream()
				.filter(request -> request.getRequester().equals(sender))
				.findFirst();
	}
	public UUID getLastMessenger() {
		return lastMessenger;
	}

	public void setLastMessenger(UUID lastMessenger) {
		this.lastMessenger = lastMessenger;
	}

	public EconomyAccount getBankAccount() {
		return new EconomyAccount();
		//return bankAccount;
	}

	public void saveData() {

	}

	public void setBankAccount(EconomyAccount bankAccount) {
		//this.bankAccount = bankAccount;
	}

	public void addHome(String homeName, Location homeLocation) {
		if (getHomeByName(homeName).isPresent()) return;

		final PlayerHome home = new PlayerHome(new SimpleLocation(homeLocation), homeName);

		homes.add(home);
	}

	public void removeHome(PlayerHome home) {
		homes.remove(home);
	}

	public Optional<PlayerHome> getHomeByName(String name) {
		return homes.stream()
				.filter(home -> home.getName().equals(name))
				.findFirst();
	}

	public Optional<PlayerHome> getFirstHome() {
		if (homes.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(homes.stream().iterator().next());
	}

	public int getHomesCount() {
		return homes.size();
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public List<String> getHomeNames() {
		return homes.stream().map(home -> home.getName()).collect(Collectors.toList());
		//return homes;
	}
}
