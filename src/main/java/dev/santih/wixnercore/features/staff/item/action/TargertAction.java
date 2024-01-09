package dev.santih.wixnercore.features.staff.item.action;

import org.bukkit.entity.Player;

import java.util.function.BiConsumer;

public interface TargertAction extends ItemAction {

	BiConsumer<Player, Player> getAction();
}
