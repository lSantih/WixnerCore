package dev.santih.wixnercore.features.staff.item.action;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

public interface SimpleAction extends ItemAction {
	Consumer<Player> getAction();
}
