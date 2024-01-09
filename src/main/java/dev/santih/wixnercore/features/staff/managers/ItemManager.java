package dev.santih.wixnercore.features.staff.managers;

import dev.santih.wixnercore.features.staff.item.IStaffItem;
import dev.santih.wixnercore.features.staff.item.impl.RandomTP;
import dev.santih.wixnercore.features.staff.item.impl.VanishOff;
import dev.santih.wixnercore.features.staff.item.impl.VanishOn;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ItemManager {

	private List<IStaffItem> loadedItems;

	public ItemManager(final VanishManager vanishManager) {

		this.loadedItems = Arrays.asList(
				new RandomTP(),
				new VanishOn(this, vanishManager),
				new VanishOff(this, vanishManager)
		);
	}


	public void giveItems(final Player player) {
		loadedItems.forEach(item -> {
			if(item instanceof VanishOff) return;
			item.addItem(player);
		});
	}

	public IStaffItem getItem(final ItemStack stack) {
		return loadedItems.stream().filter(item -> item.getItem().isSimilar(stack)).findFirst().orElse(null);
	}

	public boolean isItem(final ItemStack stack) {
		return getItemAsOptional(stack).isPresent();
	}

	public Optional<IStaffItem> getItemAsOptional(final ItemStack stack) {
		return loadedItems.stream().filter(item -> item.getItem().isSimilar(stack)).findFirst();
	}

	public Optional<IStaffItem> getItemAsOptional(final String identifier) {
		return loadedItems.stream().filter(item -> item.getidentifier().equals(identifier)).findFirst();
	}
}
