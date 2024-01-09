package dev.santih.wixnercore.utils;

import dev.morphia.annotations.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Entity
@NoArgsConstructor
public class SimpleLocation {

	@Getter
	private String worldName;

	@Getter
	private double x;

	@Getter
	private double y;

	@Getter
	private double z;

	@Getter
	private float yaw;

	@Getter
	private float pitch;

	public SimpleLocation(String worldName, double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = 0.0F;
		this.pitch = 0.0F;

		this.worldName = worldName;
	}

	public SimpleLocation(String worldName, double x, double y, double z, float yaw, float pitch) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;

		this.worldName = worldName;
	}

	public SimpleLocation(Location location) {
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();
		this.yaw = location.getYaw();
		this.pitch = location.getPitch();
		this.worldName = location.getWorld().getName();
	}

	public Location toBukkitLocation() {
		return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
	}

	public void teleport(Player player) {
		player.teleport(toBukkitLocation());
	}
}
