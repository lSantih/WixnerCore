package dev.santih.wixnercore.features.staff.item;

import dev.santih.wixnercore.features.staff.item.action.ItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IStaffItem {

	ItemStack getItem();

	int getSlot();

	ItemAction getAction();

	String getidentifier();

	default void addItem(final Player player) {
		player.getInventory().setItem(getSlot(), getItem());
	}
}
