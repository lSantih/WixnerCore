package dev.santih.wixnercore.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerListener implements Listener {

    @EventHandler
    private void handleSpawnerPlace(final BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.SPAWNER) {
            ItemStack item = event.getItemInHand();
            if (item.hasItemMeta()) {
                BlockStateMeta meta = (BlockStateMeta) item.getItemMeta();
                if (meta.getBlockState() instanceof CreatureSpawner) {
                    CreatureSpawner spawner = (CreatureSpawner) meta.getBlockState();
                    EntityType type = spawner.getSpawnedType();

                    CreatureSpawner placedSpawner = (CreatureSpawner) block.getState();
                    placedSpawner.setSpawnedType(type);
                    placedSpawner.update();
                }
            }
        }
    }
}
