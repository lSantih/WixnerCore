package dev.santih.wixnercore.features.staff.item.impl;

import dev.santih.strike.util.Common;
import dev.santih.strike.util.ItemCreator;
import dev.santih.wixnercore.features.staff.item.IStaffItem;
import dev.santih.wixnercore.features.staff.item.action.ItemAction;
import dev.santih.wixnercore.features.staff.item.action.SimpleAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RandomTP implements IStaffItem {
	@Override
	public ItemStack getItem() {
		return new ItemCreator(Material.STONE)
				.displayName("&6Random Teleport")
				.lore(
						"",
						"&fTe teletransporta a un jugador random.")
				.build();
	}

	@Override
	public int getSlot() {
		return 0;
	}

	@Override
	public ItemAction getAction() {
		return (SimpleAction) () -> (player) -> {
			if(Bukkit.getOnlinePlayers().size() == 1) {
				Common.tell(player, "&cDeben haber por lo menos 2 jugadores conectados.");
				return;
			}

			Player targetPlayer = Bukkit.getOnlinePlayers().stream()
					.filter(p -> !p.equals(player))
					.skip(new Random().nextInt(Bukkit.getOnlinePlayers().size() - 1))
					.findFirst()
					.orElse(null);

			if(targetPlayer == null) {
				Common.tell(player, "&cNo fue posible encontrar ningún jugador para teletransporte.");
				return;
			}

			player.teleport(targetPlayer);
			Common.tell(player, "&aHas sido teletransportado a " + targetPlayer.getName());
		};
	}

	@Override
	public String getidentifier() {
		return "randomtp";
	}
}
