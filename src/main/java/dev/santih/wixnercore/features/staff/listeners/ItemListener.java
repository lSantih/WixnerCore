package dev.santih.wixnercore.features.staff.listeners;

import dev.santih.wixnercore.features.staff.item.action.SimpleAction;
import dev.santih.wixnercore.features.staff.managers.ItemManager;
import dev.santih.wixnercore.features.staff.managers.ModeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

	private final ItemManager itemManager;
	private final ModeManager modeManager;

	public ItemListener(final ItemManager itemManager, final ModeManager modeManager) {
		this.itemManager = itemManager;
		this.modeManager = modeManager;
	}

	@EventHandler
	public void handleSimpleItem(final PlayerInteractEvent event) {
	    final Player player = event.getPlayer();
		final ItemStack item = event.getItem();

		if((event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)) return;
		if(!modeManager.isInMode(player)) return;
		if(event.getHand() != EquipmentSlot.HAND) return;

		itemManager.getItemAsOptional(item).ifPresent(staffItem -> {
			if(!(staffItem.getAction() instanceof SimpleAction)) return;

			SimpleAction itemAction = (SimpleAction) staffItem.getAction();
			itemAction.getAction().accept(player);

			event.setCancelled(true);
		});
	}

	@EventHandler
	public void handleItemDrop(final PlayerDropItemEvent event) {
		final Player player = event.getPlayer();
		final ItemStack stack = event.getItemDrop().getItemStack();

		if(!modeManager.isInMode(player)) return;

		event.setCancelled(itemManager.isItem(stack));
	}
}
