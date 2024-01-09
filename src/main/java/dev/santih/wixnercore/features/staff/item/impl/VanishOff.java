package dev.santih.wixnercore.features.staff.item.impl;

import dev.santih.strike.util.ItemCreator;
import dev.santih.wixnercore.features.staff.item.IStaffItem;
import dev.santih.wixnercore.features.staff.item.action.ItemAction;
import dev.santih.wixnercore.features.staff.item.action.SimpleAction;
import dev.santih.wixnercore.features.staff.managers.ItemManager;
import dev.santih.wixnercore.features.staff.managers.VanishManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VanishOff implements IStaffItem {

	private final ItemManager itemManager;
	private final VanishManager vanishManager;

	public VanishOff(final ItemManager itemManager, final VanishManager vanishManager) {
		this.itemManager = itemManager;
		this.vanishManager = vanishManager;
	}

	@Override
	public ItemStack getItem() {
		return new ItemCreator(Material.GRAY_DYE)
				.displayName("&6Vanish &7(Disabled)")
				.lore(
						"",
						"&fClick derecho para activar.")
				.build();
	}

	@Override
	public int getSlot() {
		return 1;
	}

	@Override
	public ItemAction getAction() {
		return (SimpleAction) () -> (player) -> {
			itemManager.getItemAsOptional("vanishon").ifPresent(item -> {
				item.addItem(player);
				vanishManager.enableVanish(player);
			});
		};
	}

	@Override
	public String getidentifier() {
		return "vanishoff";
	}
}
