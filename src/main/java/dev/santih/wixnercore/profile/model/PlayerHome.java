package dev.santih.wixnercore.profile.model;

import dev.morphia.annotations.Entity;
import dev.santih.wixnercore.utils.SimpleLocation;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

@Entity
@NoArgsConstructor
public class PlayerHome {
    private SimpleLocation location;
    private String name;

    public PlayerHome(SimpleLocation location, String name) {
        this.location = location;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void teleport(Player player) {
        location.teleport(player);
    }
}
